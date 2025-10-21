package it.back.back_app.admin.dto;

import it.back.back_app.admin.entity.PolicyEntity;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PolicyDTO {
    private String exchange;
    private String refund;
    private String returnPolicy;

    public static PolicyEntity to(PolicyDTO dto) {
        return PolicyEntity.builder()
        .exchange(dto.getExchange())
        .refund(dto.getRefund())
        .returnPolicy(dto.getReturnPolicy())
        .build();
    }
}
