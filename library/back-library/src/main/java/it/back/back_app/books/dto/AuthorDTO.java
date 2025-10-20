package it.back.back_app.books.dto;

import java.time.LocalDate;

import it.back.back_app.books.entity.AuthorEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthorDTO {
    private int authorId;
    private String authorName;
    private String intro;
    private String nationality;
    private LocalDate birthDate;

    public static AuthorDTO of(AuthorEntity entity) {
        return AuthorDTO.builder()
                .authorId(entity.getAuthorId())
                .authorName(entity.getAuthorName())
                .intro(entity.getIntro())
                .nationality(entity.getNationality().name())
                .birthDate(entity.getBirthDate())
                .build();
    }
}