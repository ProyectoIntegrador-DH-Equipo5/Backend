package com.artxp.artxp.infrastructure.services;

import com.artxp.artxp.api.mapper.ObraMapper;
import com.artxp.artxp.api.models.response.ArtistaDTO;
import com.artxp.artxp.domain.entities.ArtistaEntity;
import com.artxp.artxp.domain.entities.TecnicaObraEntity;
import com.artxp.artxp.domain.repositories.TecnicaObraRepository;
import com.artxp.artxp.util.exeptions.IdNotFoundException;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TecnicaObraService {
    @Autowired
    private TecnicaObraRepository tecnicaObraRepository;

    private final ObraMapper mapper = ObraMapper.INSTANCE;

    // Se busca una técnica de obra por el nombre en caso de que exista se retorna el DTO, si no existe se crea
    public TecnicaObraEntity buscarOCrearTecnicaObra(TecnicaObraEntity tecnicaObraEntity) {
        System.out.println("Buscando tecnica con nombre: " + tecnicaObraEntity.getNombre());
        Optional<TecnicaObraEntity> tecnicaObraEntityOptional =
                tecnicaObraRepository.findByNombre(tecnicaObraEntity.getNombre()).stream().findFirst();

        TecnicaObraEntity tecnicaObraEntityResult;

        if (tecnicaObraEntityOptional.isPresent()) {
            tecnicaObraEntityResult = tecnicaObraEntityOptional.get();
            System.out.println("Tecnica encontrado: " + tecnicaObraEntityResult.getId());
        } else {
            System.out.println("Creando nuevo tecnica: " + tecnicaObraEntity.getNombre());

            // Guardar la entidad y hacer flush para asegurar que el ID se genere
            tecnicaObraEntityResult = tecnicaObraRepository.saveAndFlush(tecnicaObraEntity);

            System.out.println("Nuevo tecnica guardado con ID: " + tecnicaObraEntityResult.getId());
        }

        return tecnicaObraEntityResult;
    }


    // Buscar Tecnica obra por ID
    public TecnicaObraEntity findById(Integer id) {
        return tecnicaObraRepository.findById(id)
                .orElseThrow(() -> new IdNotFoundException(id, "TecnicaObra"));
    }

    // Retorna toda la lista de tecnicas
    public List<TecnicaObraEntity> buscarTodasLasTecnicas() {
        List<TecnicaObraEntity> tecnicas = tecnicaObraRepository.findAll();

        return tecnicas;
    }
}
