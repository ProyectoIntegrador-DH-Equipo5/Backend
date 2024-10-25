package com.artxp.artxp.domain.repositories;

import com.artxp.artxp.domain.entities.ArtistaEntity;
import com.artxp.artxp.domain.entities.ObraEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ObraRepository extends JpaRepository<ObraEntity,Integer> {
}
