package com.artxp.artxp.domain.repositories;

import com.artxp.artxp.domain.entities.ArtistaEntity;
import com.artxp.artxp.domain.entities.ObraEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface ObraRepository extends JpaRepository<ObraEntity,Integer> {
    Optional<ObraEntity> findByNombreAndFechaCreacionAndArtista(String nombre, LocalDate fechaCreacion, ArtistaEntity artista);

}
