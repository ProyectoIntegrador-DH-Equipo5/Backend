package com.artxp.artxp.infrastructure.services;

import com.artxp.artxp.domain.entities.MovimientoArtisticoEntity;
import com.artxp.artxp.domain.entities.ObraEntity;
import com.artxp.artxp.domain.repositories.MovimientoArtisticoRepository;
import com.artxp.artxp.domain.repositories.ObraRepository;
import com.artxp.artxp.util.exeptions.ConflictException;
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
    @Autowired
    private ObraRepository obraRepository;

    // Se busca un movimiento artistico por el nombre en caso de que exista se retorna, si no existe se crea
    public MovimientoArtisticoEntity buscarOCrearMovimientoArtistico(MovimientoArtisticoEntity movimientoArtisticoEntity) {
        System.out.println("Buscando Movimiento Artístico con nombre: " + movimientoArtisticoEntity.getNombre());
        Optional<MovimientoArtisticoEntity> movimientoArtisticoEntityOptional =
                movRepository.findByNombre(movimientoArtisticoEntity.getNombre()).stream().findFirst();

        MovimientoArtisticoEntity movimientoArtisticoEntityResult;

        if (movimientoArtisticoEntityOptional.isPresent()) {
            System.out.println("Movimiento Artístico encontrado: " + movimientoArtisticoEntityOptional.get().getId());
//            throw new ConflictException("El Movimiento Artístico ya existe.");
            movimientoArtisticoEntityResult = movimientoArtisticoEntityOptional.get();

        } else {
            System.out.println("Creando nuevo Movimiento Artístico: " + movimientoArtisticoEntity.getNombre());

            // Guardar la entidad y hacer flush para asegurar que el ID se genere
            movimientoArtisticoEntityResult = movRepository.saveAndFlush(movimientoArtisticoEntity);

            System.out.println("Nuevo Movimiento Artístico guardado con ID: " + movimientoArtisticoEntityResult.getId());
        }

        return movimientoArtisticoEntityResult;
    }

    // Buscar Movimiento Artistico por ID
    public MovimientoArtisticoEntity findById(Integer id) {
        System.out.println("El id del Movimiento Artístico es: " + id);
        return movRepository.findById(id)
                .orElseThrow(() -> new IdNotFoundException(id, "MovimientoArtistico"));
    }

    // Retorna toda la lista de movimientos artisticos
    public List<MovimientoArtisticoEntity> buscarTodosLosMovArtisticos() {
        List<MovimientoArtisticoEntity> movArtisticos = movRepository.findAll();
        return movArtisticos;
    }

    // Buscar Obra con el Movimiento Arístico buscado
    public List<ObraEntity> obraConMovimiento(Integer movimientoId) throws RuntimeException {
        Optional<MovimientoArtisticoEntity> movimientoArtisticoBuscado = Optional.ofNullable(findById(movimientoId));
        if (movimientoArtisticoBuscado.isPresent()) {
            return obraRepository.findByMovimientoArtistico(movimientoArtisticoBuscado.get()).get();
        } else {
            throw new IdNotFoundException(movimientoId, "Movimiento Artistico");
        }
    }

    //Eliminar Movimiento Artístico
    public void eliminarMovimientoArtisticoPorId(Integer id){
        // Buscar el Movimiento Artistico, lanzar excepción si no existe
        MovimientoArtisticoEntity movimientoArtisticoBuscada = movRepository.findById(id)
                .orElseThrow(() -> new IdNotFoundException(id, "Movimiento Artistico"));

        // Verificar si el Movimiento Artistico está asociada a alguna obra
        List<ObraEntity> obrasAsociadas = obraConMovimiento(id);
        if (!obrasAsociadas.isEmpty()) {
            // Crear un mensaje que incluya los nombres de las obras asociadas
            String mensajeError = "No se puede eliminar el Movimiento Artistico, ya que está asociado a las siguientes obras: " +
                    obrasAsociadas.stream()
                            .map(ObraEntity::getNombre)
                            .collect(Collectors.joining(", "));
            throw new ConflictException(mensajeError);
        }

        // Si no hay obras asociadas, eliminar la técnica
        movRepository.deleteById(id);
    }

    //Actualizar Movimiento Artístico
    public MovimientoArtisticoEntity actualizarMovimientoArtistico(MovimientoArtisticoEntity movimientoArtisticoActualizado) {
        // Buscar el Movimiento Artisitico, lanzar excepción si no existe
        MovimientoArtisticoEntity movimientoArtisticoBuscado = movRepository.findById(movimientoArtisticoActualizado.getId())
                .orElseThrow(() -> new IdNotFoundException(movimientoArtisticoActualizado.getId(), "Técnica"));

        MovimientoArtisticoEntity movimientoArtisticoActualizacion = MovimientoArtisticoEntity.builder()
                .id(movimientoArtisticoBuscado.getId())
                .nombre(movimientoArtisticoActualizado.getNombre())
                .build();
//        System.out.println("___________________>>>>>>>>>>>>>>>>"+tecnicaActualizacion);
        return movRepository.save(movimientoArtisticoActualizacion);
    }
}
