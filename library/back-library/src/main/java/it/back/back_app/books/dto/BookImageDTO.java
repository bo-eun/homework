package it.back.back_app.books.dto;

import java.time.LocalDateTime;

import it.back.back_app.books.entity.BookImageEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class BookImageDTO {
    private int imageId;
    private String fileName;
    private String storedName;
    private String filePath;
    private String fileThumbName;
    private LocalDateTime createDate;

    public static BookImageDTO of(BookImageEntity entity) {
        return BookImageDTO.builder()
                .imageId(entity.getImageId())
                .fileName(entity.getFileName())
                .storedName(entity.getStoredName())
                .filePath(entity.getFilePath())
                .fileThumbName(entity.getFileThumbName())
                .createDate(entity.getCreateDate())
                .build();
    }
}
