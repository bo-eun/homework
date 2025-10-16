package it.back.back_app.board.dto;

import java.time.LocalDateTime;

import it.back.back_app.board.entity.ReviewFileEntity;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ReviewFileDTO {

    private int fileId;

    private String fileName;
    private String StoredName;
    private String filePath;
    private LocalDateTime createDate;

    public static ReviewFileDTO of(ReviewFileEntity entity) {
        return ReviewFileDTO.builder()
                .fileId(entity.getFileId())
                .fileName(entity.getFileName())
                .filePath(entity.getFilePath())
                .createDate(entity.getCreateDate())
                .build();
    }
}
