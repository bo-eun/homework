package kr.exam.springs.board.vo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

public class Board {

	// 이너 클래스..지만 밖에서 쓰기 편하게 static으로 생성
	@Data
	public static class Request{
		private int brdId;
		private String title;
		private String contents;
		private String writer;
		// 첨부파일
		private MultipartFile file;
	}
	
	// 리스트 용
	@Data
	public static class Response{
		private int brdId;
		private String title;
		private String contents;
		private String writer;
		private int readCount;
		private int likeCount;
		private LocalDateTime createDate;
		private LocalDateTime updateDate;
		
		// updateDate 가공한 데이터...
		/*
		 * 클라이언트에서 VO 데이터를 가져갈 때는
		 * getter 함수를 호출하여 가져간다.
		 * get + 이름 () >> 모두 getter 메서드이다.
		 * */
		public String getModifiedDate() {
			LocalDateTime modified = updateDate == null ? createDate : updateDate;
			// 시간 -> String
			return modified.format(DateTimeFormatter.ofPattern("yyyy-mm-dd HH:mm:ss"));
		}
	}
	
	// 상세 페이지 용
	@Data
	public static class Detail{
		private int brdId;
		private String title;
		private String contents;
		private String writer;
		private int readCount;
		private int likeCount;
		private LocalDateTime createDate;
		private LocalDateTime updateDate;
		//List<BoardFiles> files = new ArrayList<>();
		List<BoardFiles> files;
		
		// list에 값이 없을 경우 null대신 빈 ArrayList객체 생성
		public List<BoardFiles> getFiles() {
			if(this.files == null) {
				this.files = new ArrayList<>();
			}
			
			return this.files;
		}
	}	
	
	// 파일 등록용
	@Data
	public static class BoardFiles {
		private int bfId;
		
		private int brdId;
		
		private String fileName;
		
		private String storedName;
		
		private String filePath;
		
		private long fileSize;
	}
}
