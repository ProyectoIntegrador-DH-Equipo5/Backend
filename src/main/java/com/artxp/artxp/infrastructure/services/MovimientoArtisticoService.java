package com.artxp.artxp.infrastructure.services;

import com.artxp.artxp.api.mapper.ObraMapper;
import com.artxp.artxp.api.models.response.ArtistaDTO;
import com.artxp.artxp.api.models.response.MovimientoArtisticoDTO;
import com.artxp.artxp.domain.entities.ArtistaEntity;
import com.artxp.artxp.domain.entities.MovimientoArtisticoEntity;
import com.artxp.artxp.domain.repositories.MovimientoArtisticoRepository;
import com.artxp.artxp.util.exeptions.IdNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MovimientoArtisticoService {
    @Autowired
    private MovimientoArtisticoRepository movRepository;
    private final ObraMapper mapper = ObraMapper.INSTANCE;

    // Se busca un movimiento artistico por el nombre en caso de que exista se retorna el DTO, si no existe se crea
    public MovimientoArtisticoDTO buscarOCrearMovimientoArtistico(MovimientoArtisticoDTO movimientoArtisticoDTO) {
        Optional<MovimientoArtisticoEntity> movimientoArtisticoEntityOptional =
                movRepository.findByNombreMovimiento(movimientoArtisticoDTO.getNombreMovimiento()).stream().findFirst();
        MovimientoArtisticoEntity movimientoArtisticoEntity;

        if (movimientoArtisticoEntityOptional.isPresent()) {
            movimientoArtisticoEntity = movimientoArtisticoEntityOptional.get();
        } else {
            movimientoArtisticoEntity = mapper.movimientoArtisticoDTOToEntity(movimientoArtisticoDTO);
            movimientoArtisticoEntity = movRepository.save(movimientoArtisticoEntity);
        }

        return mapper.movimientoArtisticoEntityToDTO(movimientoArtisticoEntity);
    }

    // Buscar movimiento artistico por ID
    public MovimientoArtisticoEntity findById(Integer id) {
        return movRepository.findById(id)
                .orElseThrow(() -> new IdNotFoundException(id, "MovimientoArtistico"));
    }

    // Retorna toda la lista de movimientos artisticos
    public List<MovimientoArtisticoDTO> buscarTodosLosMovArtisticos() {
        List<MovimientoArtisticoEntity> movArtisticos = movRepository.findAll();
        // Se convierten las entities a DTOs
        return movArtisticos.stream().map(mapper::movimientoArtisticoEntityToDTO).collect(Collectors.toList());
    }

}
