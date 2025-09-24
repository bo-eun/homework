package it.korea.app_boot.gallery.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import it.korea.app_boot.gallery.entity.GalleryEntity;

public interface GalleryRepository extends JpaRepository<GalleryEntity, String>{
    // where in절 쓰는 쿼리문
    List<GalleryEntity> findByNumsIn(String[] deleteIds);
}
