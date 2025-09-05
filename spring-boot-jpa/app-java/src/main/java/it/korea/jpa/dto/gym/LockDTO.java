package it.korea.jpa.dto.gym;

import it.korea.jpa.entity.gym.LockEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class LockDTO {

    private int lockNum;
    private int numbers;
    private String useYn;
    
    public static LockDTO of(LockEntity entity) {
        return LockDTO.builder()
                        .lockNum(entity.getLockNum())
                        .numbers(entity.getNumbers())
                        .useYn(entity.getUseYn())
                        .build();
    };

    public static LockEntity to (LockDTO dto) {
        LockEntity entity = new LockEntity();
        entity.setLockNum(dto.getLockNum());
        entity.setNumbers(dto.getNumbers());
        entity.setUseYn(dto.getUseYn());
        return entity;
        
    }
}
