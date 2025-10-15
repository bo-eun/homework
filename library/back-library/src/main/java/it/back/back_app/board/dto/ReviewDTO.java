package it.back.back_app.board.dto;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonFormat;

import it.back.back_app.board.entity.ReviewEntity;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ReviewDTO {
    private int reviewId;
    private int bookId;
    private String userId;
    private int rating;
    private String content;
    private List<ReviewFileDTO> fileList;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createDate;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateDate;

    public static ReviewDTO of(ReviewEntity entity) {
        List<ReviewFileDTO> fileList = entity.getFileList().stream().map(ReviewFileDTO::of).toList();
        return ReviewDTO.builder()
                        .reviewId(entity.getReviewId())
                        .bookId(entity.getBookId())
                        .userId(entity.getUserId())
                        .rating(entity.getRating())
                        .content(entity.getContent())
                        .fileList(fileList)
                        
                        .createDate(entity.getCreateDate())
                        .updateDate(entity.getUpdateDate())
                        .build();
    }

    public LocalDateTime getModifiedDate() {
        return this.updateDate == null ? this.createDate : this.updateDate;
    }

}
