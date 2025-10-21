package it.back.back_app.books.dto;
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

    public static BookImageDTO of(BookImageEntity entity) {
        return BookImageDTO.builder()
                .imageId(entity.getImageId())
                .fileName(entity.getFileName())
                .storedName(entity.getStoredName())
                .filePath(entity.getFilePath())
                .build();
    }
}
