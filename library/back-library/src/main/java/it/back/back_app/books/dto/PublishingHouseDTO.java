package it.back.back_app.books.dto;

import it.back.back_app.books.entity.PublishingHouseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PublishingHouseDTO {
    private int publishingId;
    private String publishingName;

    public static PublishingHouseDTO of(PublishingHouseEntity entity) {
        return PublishingHouseDTO.builder()
                .publishingId(entity.getPublishingId())
                .publishingName(entity.getPublishingName())
                .build();
    }
}
