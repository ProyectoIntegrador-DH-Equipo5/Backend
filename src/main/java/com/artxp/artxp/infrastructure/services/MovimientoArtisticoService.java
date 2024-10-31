package com.artxp.artxp.infrastructure.services;

import com.artxp.artxp.api.mapper.ObraMapper;
import com.artxp.artxp.api.models.response.ArtistaDTO;
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
    public MovimientoArtisticoEntity buscarOCrearMovimientoArtistico(MovimientoArtisticoEntity movimientoArtisticoEntity) {
        System.out.println("Buscando movimiento con nombre: " + movimientoArtisticoEntity.getNombre());
        Optional<MovimientoArtisticoEntity> movimientoArtisticoEntityOptional =
                movRepository.findByNombre(movimientoArtisticoEntity.getNombre()).stream().findFirst();

        MovimientoArtisticoEntity movimientoArtisticoEntityResult;

        if (movimientoArtisticoEntityOptional.isPresent()) {
            movimientoArtisticoEntityResult = movimientoArtisticoEntityOptional.get();
            System.out.println("Movimiento encontrado: " + movimientoArtisticoEntityResult.getId());
        } else {
            System.out.println("Creando nuevo movimiento: " + movimientoArtisticoEntity.getNombre());

            // Guardar la entidad y hacer flush para asegurar que el ID se genere
            movimientoArtisticoEntityResult = movRepository.saveAndFlush(movimientoArtisticoEntity);

            System.out.println("Nuevo movimiento guardado con ID: " + movimientoArtisticoEntityResult.getId());
        }

        return movimientoArtisticoEntityResult;
    }

    // Buscar movimiento artistico por ID
    public MovimientoArtisticoEntity findById(Integer id) {
        return movRepository.findById(id)
                .orElseThrow(() -> new IdNotFoundException(id, "MovimientoArtistico"));
    }

    // Retorna toda la lista de movimientos artisticos
    public List<MovimientoArtisticoEntity> buscarTodosLosMovArtisticos() {
        List<MovimientoArtisticoEntity> movArtisticos = movRepository.findAll();

        return movArtisticos;
    }

}
