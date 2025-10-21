// package it.back.back_app.board.service;

// import java.util.HashMap;
// import java.util.List;
// import java.util.Map;


// import org.springframework.beans.factory.annotation.Value;
// import org.springframework.data.domain.Page;
// import org.springframework.data.domain.Pageable;
// import org.springframework.stereotype.Service;
// import org.springframework.transaction.annotation.Transactional;


// import it.back.back_app.board.dto.ReviewDTO;
// import it.back.back_app.board.dto.ReviewFileDTO;
// import it.back.back_app.board.entity.ReviewEntity;
// import it.back.back_app.board.entity.ReviewFileEntity;
// import it.back.back_app.board.repository.ReviewFileRepository;
// import it.back.back_app.board.repository.ReviewRepository;
// import it.back.back_app.common.utils.FileUtils;
// import lombok.RequiredArgsConstructor;

// @Service
// @RequiredArgsConstructor
// public class ReviewService {

//     private final ReviewRepository reviewRepository;
//     private final FileUtils fileUtils;
//     private final ReviewFileRepository boardFileRepository;

//     @Value("${server.file.review.path}")
//     String filePath;

//     @Transactional(readOnly = true)
//     public Map<String, Object> getReviewList(Pageable pageable) throws Exception {
//         Map<String, Object> resultMap = new HashMap<>();

//         Page<ReviewEntity> reviewList = reviewRepository.findAll(pageable);

//         // toList()로 불변객체를 만들어 데이터 변경을 막음
//         List<ReviewDTO> list = reviewList.getContent().stream().map(ReviewDTO::of).toList();

//         resultMap.put("total", reviewList.getTotalElements());
//         resultMap.put("page", reviewList.getNumber());
//         resultMap.put("content", list);

//         return resultMap;
//     }

//     @Transactional(readOnly = true)
//     public ReviewDTO getReview(int reviewId) {
//         ReviewEntity entity = reviewRepository.getReview(reviewId).orElseThrow(() -> new RuntimeException("찾는 게시글이 없음"));
        
//         return ReviewDTO.of(entity);
//     }

//     @Transactional
//     public Map<String, Object> writeReview(ReviewDTO request) throws Exception {
//         Map<String, Object> resultMap = new HashMap<>();
//         ReviewEntity entity = new ReviewEntity();

//         entity.setContent(request.getContent());

//         // 기존 파일 삭제
//         if(request.getFileList() != null && !request.getFileList().isEmpty()) {
//             Map<String, Object> fileMap = fileUtils.uploadFiles(request.getFileList(), filePath);

//             if(fileMap != null) {
//                 ReviewFileEntity fileEntity = new ReviewFileEntity();
//                 fileEntity.setFileName(fileMap.get("fileName").toString());
//                 fileEntity.setStoredName(fileMap.get("storedFileName").toString());
//                 fileEntity.setFilePath(fileMap.get("filePath").toString());
//                 fileEntity.setFileSize(request.getFileList().getSize());

//                 entity.addFiles(fileEntity);
//             } else {
//                 throw new RuntimeException("파일 업로드 오류");
//             }
//         }

//         reviewRepository.save(entity);

//         resultMap.put("resultCode", 200);
//         resultMap.put("resultMsg", "OK");

//         return resultMap;
//     }



//     @Transactional
//     public Map<String, Object> updateReview(ReviewDTO request) throws Exception {
//         Map<String, Object> resultMap = new HashMap<>();
//         ReviewEntity entity = reviewRepository.getReview(request.getReviewId()).orElseThrow(() -> new RuntimeException("찾는 게시글이 없음"));

//         ReviewDTO detail = ReviewDTO.of(entity);

//         // 첨부 파일이 있다면
//         if(request.getFile() != null && !request.getFile().isEmpty()) {
//             Map<String, Object> fileMap = fileUtils.uploadFiles(request.getFile(), filePath);
//             if(fileMap != null) {
//                 ReviewFileEntity fileEntity = new ReviewFileEntity();
//                 fileEntity.setFileName(fileMap.get("fileName").toString());
//                 fileEntity.setStoredName(fileMap.get("storedFileName").toString());
//                 fileEntity.setFilePath(fileMap.get("filePath").toString());
//                 fileEntity.setFileSize(request.getFile().getSize());

//                 // 수정한 첨부 파일 추가
//                 entity.getFileList().clear();
//                 entity.addFiles(fileEntity);
//             } else {
//                 throw new RuntimeException("파일 업로드 오류");
//             }

//         }

//         entity.setTitle(request.getTitle());
//         entity.setContents(request.getContents());

//         // 게시글 업데이트
//         reviewRepository.save(entity);

//         // 기존 파일 삭제
//         if(request.getFile() != null && !request.getFile().isEmpty()) {
//             if(detail.getFileList() != null && !detail.getFileList().isEmpty()) {
//                 for(ReviewFileDTO file : detail.getFileList()) {
//                     String oldFilePath = filePath + file.getStoredName();
//                     fileUtils.deleteFile(oldFilePath);
//                 }
//             }
//         }

//         resultMap.put("resultCode", 200);
//         resultMap.put("resultMsg", "OK");

//         return resultMap;
//     }

//     @Transactional
//     public Map<String, Object> deleteFile(int bfId) throws Exception {
//         Map<String, Object> resultMap = new HashMap<>();

//         ReviewFileEntity fileEntity = boardFileRepository.findById(bfId).orElseThrow(() -> new RuntimeException("파일이 존재하지 않습니다."));

//         String oldFilePath = fileEntity.getFilePath() + fileEntity.getStoredName();

//         // 실제 저장된 파일 삭제
//         fileUtils.deleteFile(oldFilePath);
//         // DB에 저장된 파일 정보 삭제
//         boardFileRepository.delete(fileEntity);

//         resultMap.put("resultCode", 200);
//         resultMap.put("resultMsg", "OK");

//         return resultMap;
//     }

//     @Transactional
//     public Map<String, Object> deleteReview(int reviewId) throws Exception {
//         Map<String, Object> resultMap = new HashMap<>();
//         ReviewEntity entity = reviewRepository.getReview(reviewId).orElseThrow(() -> new RuntimeException("찾는 게시글이 없음"));

//         // dto로 변경
//         ReviewDTO detail = ReviewDTO.of(entity);
//         // 게시글 삭제 > 고아파일 정책 실행해서 file 디비도 같이 지워짐
//         reviewRepository.delete(entity);

//         // 기존 파일 삭제
//         if(detail.getFileList() != null && !detail.getFileList().isEmpty()) {
//             for(ReviewFileDTO file : detail.getFileList()) {
//                 String oldFilePath = filePath + file.getStoredName();
//                 fileUtils.deleteFile(oldFilePath);
//             }
//         }

//         resultMap.put("resultCode", 200);
//         resultMap.put("resultMsg", "OK");

//         return resultMap;
//     }

// }
