package com.example.board_practice.baseEntity;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

/* 생성 시간, 수정 시간 자동화 */

@Getter
@MappedSuperclass // 공통된 매핑 정보가 필요한 경우 사용
@EntityListeners(AuditingEntityListener.class)
public class TimeEntity {

    @CreatedDate // 생성되어 저장될 때 시간이 자동 저장
    private LocalDateTime registeredAt;
    @LastModifiedDate // 값을 변경할 때 시간이 자동 저장
    private LocalDateTime updatedAt;

}
