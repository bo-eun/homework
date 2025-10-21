package it.back.back_app.board.dto;

import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonFormat;

import it.back.back_app.board.entity.ReviewEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
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
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createDate;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateDate;

    public static ReviewDTO of(ReviewEntity entity) {

        return ReviewDTO.builder()
                        .reviewId(entity.getReviewId())
                        .bookId(entity.getBookId())
                        .userId(entity.getUserId())
                        .rating(entity.getRating())
                        .content(entity.getContent())
                        
                        .createDate(entity.getCreateDate())
                        .updateDate(entity.getUpdateDate())
                        .build();
    }

    public LocalDateTime getModifiedDate() {
        return this.updateDate == null ? this.createDate : this.updateDate;
    }

}
