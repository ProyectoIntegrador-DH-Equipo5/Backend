package com.artxp.artxp.domain.repositories;

import com.artxp.artxp.domain.entities.ImagenEntity;
import com.artxp.artxp.domain.entities.ObraEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImagenRepository extends JpaRepository<ImagenEntity, Integer> {
    List<ImagenEntity> findByObra(ObraEntity obra);
}
