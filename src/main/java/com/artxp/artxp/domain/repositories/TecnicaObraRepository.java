package com.artxp.artxp.domain.repositories;

import com.artxp.artxp.domain.entities.ArtistaEntity;
import com.artxp.artxp.domain.entities.TecnicaObraEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TecnicaObraRepository extends JpaRepository<TecnicaObraEntity,Integer> {
}
