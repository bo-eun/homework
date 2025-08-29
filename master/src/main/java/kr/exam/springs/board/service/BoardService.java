package kr.exam.springs.board.service;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import kr.exam.springs.board.mapper.BoardMapper;
import kr.exam.springs.board.vo.Board;
import kr.exam.springs.common.page.PageVO;
import lombok.RequiredArgsConstructor;

// BoardMapper에서 만든 BoardMapper interface를 구현한 클래스를 가져와야 함.. 구현 클래스는 spring내부에서 만들어서 클래스 이름을 모름
// @Service 어노테이션으로 spring 내부에서 만든 클래스를 사용할 수 있음
@Service


//@NoArgsConstructor // 매개변수가 없는 기본 생성자
//@AllArgsConstructor // 모든 멤버변수를 매개변수로 갖는 생성자

@RequiredArgsConstructor // 멤버변수 중에 final 처리가 된 것들만 매개변수 가지는 생성자
public class BoardService {
	
	private final BoardMapper mapper;
	
	public Map<String, Object> getBoardList(Map<String, Object> param) throws Exception {
		Map<String, Object> map = new HashMap<>();
		
		int currentPage = (int) param.get("currentPage");
		
		// 전체 리스트 개수
		int total = mapper.getBoardTotal();
		
		// 페이징 객체를 호출
		PageVO pageVO = new PageVO();
		pageVO.setData(currentPage, total);
		
		List<Board.Response> boardList = new ArrayList<>();
		
		if(total > 0) {
			param.put("offSet", pageVO.getOffSet());
			param.put("count", pageVO.getCount());
			
			boardList = mapper.getBoardList(param);
		}
		
		map.put("currentPage", currentPage);
		map.put("dataList", boardList);
		map.put("total", total);
		map.put("pageHTML", pageVO.pageHTML());
		
		return map;
	}
	
	/* 내부적으로 AOP를 만들어서 Transaction 기능을 함
	* insert, update, delete가 발생하면 transaction 시작함
	* commit - 내가 한 동작 확정
	* rollback - 내가 한 동작 취소
	* 
	* 아래 1,2,3 중 하나라도 실패할 경우 transaction 동작함
	* transaction 설정하지 않을 경우 2가 오류났을 때 1이 완료처리됨...
	*/ 
	@Transactional(
			rollbackFor = Exception.class, // 오류가 났을 때 어떤 Exception을 실행할 것인지 >> 모든 Exception 실행
			propagation = Propagation.REQUIRED, // 기존 트랜잭션이 있으면 사용, 없으면 생성
			isolation = Isolation.READ_COMMITTED // 적용된것만 읽기 >> 데이터를 가져올 때 같은 시간에 수정 중이거나 처리 중인건 제외
			)
	
	public int writerBoard(Board.Request request) throws Exception {
		int result = 0;
		
		try {
			// 1. 글 등록
			result = mapper.insertBoard(request);
		
			/* 방어코드... result에 오류가 나면(result = -1) 바로 exception으로 넘어가지만 
			 * 라이브러리 충돌이 발생할 경우 exception으로 넘어가지 않을 수 있음 그럴 경우를 대비
			 */
			if(result < 0) {
				throw new Exception();
			}
			// 2. 파일 업로드
			Board.BoardFiles files = this.uploadFiles(request.getBrdId(), request.getFile());
			if(files != null) {
				// 3. 파일 등록
				result = mapper.insertBoardFiles(files);
				if(result < 0) {
					throw new Exception();
				}
			}

		} catch(Exception e) {
			throw new RuntimeException("글 등록 중 오류");
		}
	
		return result;
	}
	
	/**
	 * 상세화면 데이터 가져오기
	 * @param brdId
	 * @return
	 * @throws Exception
	 */
	public Board.Detail getBoard(int brdId) throws Exception {
		Board.Detail detail = mapper.getBoard(brdId);
		
		return detail;
	}
	
	
	
	// brdId를 받아와 board의 상세 내용을 담은 Board.Detail객체를 리턴함 
	// Board.Detail 객체는 Board.java VO 파일에서 선언한 클래스. 
	/**
	 * 상세화면 데이터 가져오기 with 조회수 처리
	 * @param brdId
	 * @param cookieValue
	 * @param resp
	 * @return
	 * @throws Exception
	 */
	public Board.Detail getBoard(int brdId, String cookieValue, HttpServletResponse resp) throws Exception {
		Board.Detail detail = mapper.getBoard(brdId);
		String readId = "read_" + brdId;
		
		// 쿠키에 brdId 값이 없으면 처음 보는 게시물
		if(!cookieValue.contains(readId)) {
			int count = detail.getReadCount();
			detail.setReadCount(++count);
			// 데이터베이스 처리
			mapper.updateReadCount(brdId, count);
			
			// 쿠키 저장
			cookieValue += "_" + readId;
			Cookie cookie = new Cookie("board", cookieValue);
			// 쿠키 저장 시간
			cookie.setMaxAge(60 * 60 * 3);
			// map 처럼 key가 중복되면 value가 최신화
			resp.addCookie(cookie);
		}
		
		return mapper.getBoard(brdId);
	}
	
	public int updateLikeCount(Map<String, Object> param) throws Exception{
		return mapper.updateLikeCount(param);
	}
	
	public Board.BoardFiles getBoardFiles(int bfId) throws Exception {
		return mapper.getBoardFiles(bfId);
	}
	
	
	// 파일 업로드는 파일 업로드 -> 업로드한 파일 복사하여 서버에 저장 하는 방식
	public Board.BoardFiles uploadFiles(int brdId, MultipartFile multiFile) throws Exception {

		// 내용이 없음 > 선택 안하면 비어 있음
		if(multiFile == null || multiFile.isEmpty()) {
			return null;
		}
		
		String fileName = multiFile.getOriginalFilename(); // 원본이름 저장
		String extention = fileName.substring(fileName.lastIndexOf(".") + 1); // 확장자 구하기
		//중복 없는 이름 만들기
		// UUID ? 네트워크 전송 시 생성하는 식별자 중복방지를 위함
		String uuId = UUID.randomUUID().toString().replaceAll("-", "");
		// 길이를 줄이기
		uuId = uuId.substring(0, 16);
		String storedName = uuId + "." + extention; // 저장할 파일 이름 만듦
		
		// 파일 경로
		String filePath = "C:\\files\\down\\board\\";
		
		//저장할 전체 경로
		String fullPath = filePath + storedName;
		
		// 반 파일(아직 없음) 객체 만들기
		File newFile = new File(fullPath);
		
		// 파일 경로 없을 시 만들기
		if(!newFile.getParentFile().exists()) {
			// 상위...상위 경로들이 없을 때 다 만들어주기
			newFile.getParentFile().mkdirs();
		}
		
		// 백지 파일 만들기
		newFile.createNewFile();
		// 복사
		multiFile.transferTo(newFile);
		
		Board.BoardFiles files = new Board.BoardFiles();
		files.setBrdId(brdId);
		files.setFileName(fileName);
		files.setStoredName(storedName);
		files.setFilePath(filePath);
		files.setFileSize(multiFile.getSize());
		
		return files;
	}
	
	
	
	// 파일 하나 지우기
	public int deleteFile(int bfId) throws Exception {
		int result = 0;
		Board.BoardFiles fileInfo = mapper.getBoardFiles(bfId);
		
		if(fileInfo != null) {
			result = mapper.deleteBoardFile(bfId);
			if(result < 0) throw new Exception("파일 삭제 실패");
			
			String fullPath = fileInfo.getFilePath() + fileInfo.getStoredName();
			// 물리적 파일 지우기
			this.deleteFile(fullPath);
		}
		return result;
	}
	
	@Transactional(
		rollbackFor = Exception.class, // 오류가 났을 때 어떤 Exception을 실행할 것인지 >> 모든 Exception 실행
		propagation = Propagation.REQUIRED, // 기존 트랜잭션이 있으면 사용, 없으면 생성
		isolation = Isolation.READ_COMMITTED // 적용된것만 읽기 >> 데이터를 가져올 때 같은 시간에 수정 중이거나 처리 중인건 제외
	)
	public int deleteBoard(int brdId) throws Exception {
		
		Board.Detail detail = mapper.getBoard(brdId);
		
		int result = mapper.deleteBoard(brdId);
		
		// 오류나면 예외처리
		if(result < 0) throw new Exception();
		
		// 기존 파일 삭제
		for(Board.BoardFiles file : detail.getFiles()) {
			String fullPath = file.getFilePath() + file.getFileName();
			
			this.deleteFile(fullPath);
		}
		
		return result;
	}
	
	
	// 물리적인 파일 지우기
	public void deleteFile(String filePath) {
		
		File file = new File(filePath);
		
		if(file.exists()) {
			file.delete();
		}
	}
	
	public int updateBoard(Board.Request request) throws Exception {
		int result = 0;
		
		try {
			// 1. 수정하기 위해 기존 정보를 불러온다.
			Board.Detail detail = mapper.getBoard(request.getBrdId());
			detail.setTitle(request.getTitle());
			detail.setContents(request.getContents());
			
			result = mapper.updateBoard(detail);
		
			if(result < 0) {
				throw new Exception();
			}
			
			// 파일 업로드할 게 있으면 기존 파일 삭제 후 등록
			if(!request.getFile().isEmpty()) {
				for(Board.BoardFiles file : detail.getFiles()) {
					String fullPath = file.getFilePath() + file.getStoredName();
					// 디비 지우기
					mapper.deleteBoardFile(file.getBfId());
					// 물리적 파일 지우기
					this.deleteFile(fullPath);
				}
				
				// 2. 파일 업로드
				Board.BoardFiles files = this.uploadFiles(request.getBrdId(), request.getFile());
				if(files != null) {
					// 3. 파일 등록
					result = mapper.insertBoardFiles(files);
					if(result < 0) {
						throw new Exception();
					}
				}
			}

		} catch(Exception e) {
			throw new RuntimeException("글 등록 중 오류");
		}
	
		return result;
	}
	
	
}
