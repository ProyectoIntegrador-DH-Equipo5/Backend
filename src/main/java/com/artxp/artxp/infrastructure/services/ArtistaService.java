package com.artxp.artxp.infrastructure.services;

import com.artxp.artxp.api.mapper.ObraMapper;
import com.artxp.artxp.api.models.response.ArtistaDTO;
import com.artxp.artxp.api.models.response.ObraDTO;
import com.artxp.artxp.domain.entities.ArtistaEntity;
import com.artxp.artxp.domain.entities.ObraEntity;
import com.artxp.artxp.domain.repositories.ArtistaRepository;
import com.artxp.artxp.util.exeptions.IdNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ArtistaService {
    @Autowired
    private ArtistaRepository artistaRepository;

    private final ObraMapper mapper = ObraMapper.INSTANCE;

    // Se busca un artista por el nombre en caso de que exista se retorna el DTO, si no existe se crea
    public ArtistaDTO buscarOCrearArtista(ArtistaDTO artistaDTO) {
        System.out.println("Buscando artista con nombre: " + artistaDTO.getNombre());
        Optional<ArtistaEntity> artistaEntityOptional =
                artistaRepository.findByNombre(artistaDTO.getNombre()).stream().findFirst();
        ArtistaEntity artistaEntity;

        if (artistaEntityOptional.isPresent()) {
            artistaEntity = artistaEntityOptional.get();
            System.out.println("Artista encontrado: " + artistaEntity.getId());
        } else {
            artistaEntity = mapper.artistaDTOToEntity(artistaDTO);
            System.out.println("Creando nuevo artista: " + artistaEntity.getNombre());
            System.out.println("ID antes de guardar: " + artistaEntity.getId());

            // Guardar la entidad y hacer flush para asegurar que el ID se genere
            artistaEntity = artistaRepository.saveAndFlush(artistaEntity);

            System.out.println("Nuevo artista guardado con ID: " + artistaEntity.getId());
        }

        ArtistaDTO resultDTO = mapper.artistaEntityToDTO(artistaEntity);
        System.out.println("Retornando ArtistaDTO con ID: " + resultDTO.getId());
        return resultDTO;
    }

    // Buscar artista por ID
    public ArtistaEntity findById(Integer id) {
        return artistaRepository.findById(id)
                .orElseThrow(() -> new IdNotFoundException(id, "Artista"));
    }

    // Retorna toda la lista de artistas
    public List<ArtistaDTO> buscarTodosLosArtistas() {
        List<ArtistaEntity> artistas = artistaRepository.findAll();
        // Se convierten las entities a DTOs
        return artistas.stream().map(mapper::artistaEntityToDTO).collect(Collectors.toList());
    }
}
