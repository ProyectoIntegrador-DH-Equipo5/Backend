package com.artxp.artxp.infrastructure.services;

import com.artxp.artxp.api.mapper.ObraMapper;
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
    public ArtistaEntity buscarOCrearArtista(ArtistaEntity artistaEntity) {
        System.out.println("Buscando artista con nombre: " + artistaEntity.getNombre());
        Optional<ArtistaEntity> artistaEntityOptional =
                artistaRepository.findByNombre(artistaEntity.getNombre()).stream().findFirst();

        ArtistaEntity artistaEntityResult;

        if (artistaEntityOptional.isPresent()) {
            artistaEntityResult = artistaEntityOptional.get();
            System.out.println("Artista encontrado: " + artistaEntityResult.getId());
        } else {
            System.out.println("Creando nuevo artista: " + artistaEntity.getNombre());

            // Guardar la entidad y hacer flush para asegurar que el ID se genere
            artistaEntityResult = artistaRepository.saveAndFlush(artistaEntity);

            System.out.println("Nuevo artista guardado con ID: " + artistaEntityResult.getId());
        }

        return artistaEntityResult;
    }

    // Buscar artista por ID
    public ArtistaEntity findById(Integer id) {
        return artistaRepository.findById(id)
                .orElseThrow(() -> new IdNotFoundException(id, "Artista"));
    }

    // Retorna toda la lista de artistas
    public List<ArtistaEntity> buscarTodosLosArtistas() {
        List<ArtistaEntity> artistas = artistaRepository.findAll();

        return artistas;
    }
}
