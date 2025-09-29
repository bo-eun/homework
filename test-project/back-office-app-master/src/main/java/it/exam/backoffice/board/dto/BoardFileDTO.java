package it.exam.backoffice.board.dto;

import java.time.LocalDateTime;

import it.exam.backoffice.board.entity.BoardFileEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class BoardFileDTO {
    private int bfId;
    private String fileName;
    private String storedName;
    private String filePath;
    private Long fileSize; 
    private LocalDateTime createDate;

    public static BoardFileDTO of(BoardFileEntity entity) {
        return BoardFileDTO.builder()
                .bfId(entity.getBfId())
                .fileName(entity.getFileName())
                .storedName(entity.getStoredName())
                .fileSize(entity.getFileSize())
                .filePath(entity.getFilePath())
                .createDate(entity.getCreateDate())
                .build();
    }
}
