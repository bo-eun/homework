package it.exam.backoffice.board.entity;

import java.util.HashSet;
import java.util.Set;

import it.exam.backoffice.common.domain.BaseEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="tb_notice")
@Getter
@Setter
public class BoardEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int brdId;

    private String title;

    private String writer;

    private String contents;

    private int viewCount;

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<BoardFileEntity> fileList = new HashSet<>();

    public void addFiles(BoardFileEntity entity) {
        if(fileList == null) this.fileList = new HashSet<>();
        entity.setBoard(this);
        fileList.add(entity);
    }
}
