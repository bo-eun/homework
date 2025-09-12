package it.korea.app_boot.board.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import it.korea.app_boot.board.dto.BoardSearchDTO;
import it.korea.app_boot.board.entity.BoardEntity;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;


/*
 * dinamic where 절을 사용하기 위해 만듦
 */
public class BoardSearchSpecification implements Specification<BoardEntity> {
    
    private BoardSearchDTO searchDTO;

    public BoardSearchSpecification(BoardSearchDTO searchDTO) {
        this.searchDTO  = searchDTO;
    }

    /**
     * root : 비교대상 > entity >> jpa가 만들어서 넣어줌
     * query : sql 조작 할 수 있는 역할
     * cb : where 조건문 만드는 역할
     */
    @Override
    public Predicate toPredicate(Root<BoardEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        List<Predicate> predicates = new ArrayList<>();

        /**
         * query 사용 예시
         * 
         * query.distinct(true);
         * query.groupBy(root.get("title"));
         * query.orderBy(cb.desc(root.get("createDate")));
         * 
         * query 파라메터는 sql을 조작할 수 있지만 잘 안쓰는 이유
         * service단 또는 pageable 에서 이미 정렬 또는 페이징처리 하기 때문
         * 여기서 추가로 조작하면 복잡도 상승, 오류 시 유지보수가 어려워짐
         * 
         */

        String likeText = "%" + searchDTO.getSchText() + "%";

        // 하드코딩 된 값을 비교할 때에는 하드코딩된 값을 기준으로 비교해야 null오류를 막을 수 있다.
        if("title".equals(searchDTO.getSchType())) {
            predicates.add(cb.like(root.get("title"), likeText));
        } else if ("writer".equals(searchDTO.getSchType())) {
            predicates.add(cb.like(root.get("writer"), likeText));
        }

        // 아래 방법을 사용하면 searchDTO.getSchType()이 null일 경우 .equals() 실행 시 null 오류가 난다.
        // if(searchDTO.getSchType().equals("title")) {
        //     predicates.add(cb.like(root.get("title"), likeText));
        // } else if (searchDTO.getSchType().equals("writer")) {
        //     predicates.add(cb.like(root.get("writer"), likeText));
        // }
        return andTogether(predicates, cb);
    }

    private Predicate andTogether(List<Predicate> predicates, CriteriaBuilder cb) {
        // toArray(new Predicate[0]) > new Predicate[0] 타입으로 Array를 만들어라
        // toArray를 하면서 where and 조건을 만들어준다.
        return cb.and(predicates.toArray(new Predicate[0]));
    }

}
