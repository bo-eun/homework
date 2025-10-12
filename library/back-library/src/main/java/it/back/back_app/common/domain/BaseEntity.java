package it.back.back_app.common.domain;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PreUpdate;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@MappedSuperclass // 클래스 간 상속관계를 만들지 않지만 상속관계처럼 만들어줄 때 사용, JPA 전용 어노테이션
@EntityListeners(AuditingEntityListener.class)

// Serializable 테이블 직렬화
public class BaseEntity implements Serializable{

    @CreatedDate
    // 업데이트 막음
    @Column(updatable = false)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createDate;

    // 업데이트 될 때만 갱신
    @Column(insertable = false)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateDate;

    // 업데이트가 되기 전 실행
    @PreUpdate
    public void preUpdate() {
        this.updateDate = LocalDateTime.now();
    }
    
}
