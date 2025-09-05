package it.korea.jpa.service.gym;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.korea.jpa.dto.gym.MembersDTO;
import it.korea.jpa.entity.gym.LockEntity;
import it.korea.jpa.entity.gym.MembersEntity;
import it.korea.jpa.repository.gym.LockRepository;
import it.korea.jpa.repository.gym.MembersRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MembersRepository memRepository;
    private final LockRepository lockRepository;

    @Transactional
    public void newMembers(MembersDTO dto) throws Exception {
        // 아이디, 이름, 라커번호
        /*
         * Optional는 특정 객체를 담는 컨테이너
         * 데이터 베이스 또는 로직으로부터 얻은 객체가 비어 있거나 정상적이지 않을 때,
         * 개발자가 처리할 수 있도록 만든 상자
         * null로 인한 오류를 방지하기 위한 장치
         */

         // JPA로 호출된 객체라서 영속성 안에 존재
        Optional<LockEntity> lock = lockRepository.findById(dto.getLockNum());


        // LockEntity lockEntity = lock.orElse(null); // 없으면 null 전달 
        // LockEntity lockEntity = lock.orElseThrow(() -> new RuntimeException("사물함이 없음"));
        // if(lock.get().getUseYn().equals("Y")) {
        //     throw new ClassNotFoundException("사물함 점유 중");
        // }

        // lock이 있는지
        if(!lock.isPresent() || lock.get().getUseYn().equals("Y")) {
            throw new ClassNotFoundException("사물함이 없거나 점유 중");
        }

        // 사물함 Y로 변경
        LockEntity lockEntity = lock.get(); // 엔티티만 요청
        lockEntity.setUseYn("Y"); // 트랜잭션 안에서 entity 변경 시 자동으로 update
        
        MembersEntity MembersEntity = MembersDTO.to(dto);
        memRepository.save(MembersEntity);
    }
}
