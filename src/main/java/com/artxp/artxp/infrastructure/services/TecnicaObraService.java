package com.artxp.artxp.infrastructure.services;

import com.artxp.artxp.api.mapper.ObraMapper;
import com.artxp.artxp.api.models.response.ArtistaDTO;
import com.artxp.artxp.api.models.response.TecnicaObraDTO;
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
    public TecnicaObraDTO buscarOCrearTecnicaObra(TecnicaObraDTO tecnicaObraDTO) {
        Optional<TecnicaObraEntity> tecnicaObraEntityOptional =
                tecnicaObraRepository.findByNombre(tecnicaObraDTO.getNombre()).stream().findFirst();
        TecnicaObraEntity tecnicaObraEntity;

        if (tecnicaObraEntityOptional.isPresent()) {
            tecnicaObraEntity = tecnicaObraEntityOptional.get();
        } else {
            tecnicaObraEntity = mapper.tecnicaObraDTOToEntity(tecnicaObraDTO);
            tecnicaObraEntity = tecnicaObraRepository.save(tecnicaObraEntity);
        }

        return mapper.tecnicaObraEntityToDTO(tecnicaObraEntity);
    }


    // Buscar Tecnica obra por ID
    public TecnicaObraEntity findById(Integer id) {
        return tecnicaObraRepository.findById(id)
                .orElseThrow(() -> new IdNotFoundException(id, "TecnicaObra"));
    }

    // Retorna toda la lista de tecnicas
    public List<TecnicaObraDTO> buscarTodasLasTecnicas() {
        List<TecnicaObraEntity> tecnicas = tecnicaObraRepository.findAll();
        // Se convierten las entities a DTOs
        return tecnicas.stream().map(mapper::tecnicaObraEntityToDTO).collect(Collectors.toList());
    }
}
