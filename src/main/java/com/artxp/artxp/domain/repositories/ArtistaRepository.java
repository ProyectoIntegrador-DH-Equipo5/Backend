package com.artxp.artxp.domain.repositories;

import com.artxp.artxp.domain.entities.ArtistaEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ArtistaRepository extends JpaRepository<ArtistaEntity,Integer> {
}
