package com.artxp.artxp.infrastructure.services;

import com.artxp.artxp.domain.entities.*;
import com.artxp.artxp.domain.repositories.ObraRepository;
import com.artxp.artxp.util.exeptions.BadRequestException;
import com.artxp.artxp.util.exeptions.ConflictException;
import com.artxp.artxp.util.exeptions.IdNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ObraService {
    @Autowired
    public ObraRepository obraRepository;
    @Autowired
    private ArtistaService artistaService;
    @Autowired
    private MovimientoArtisticoService movimientoArtisticoService;
    @Autowired
    private TecnicaObraService tecnicaObraService;
    @Autowired
    private ImagenService imagenService;
    @Autowired
    private CloudinaryService cloudinaryService;

    //Buscar Obra por ID
    public ObraEntity buscarPorId(Integer id){
        return obraRepository.findById(id)
                .orElseThrow(()-> new IdNotFoundException(id, "Obra"));
    }

    // Retorna toda la lista de obras
    public List<ObraEntity> buscarTodasLasObras() {
        return obraRepository.findAll();
        // Se convierten las entities a DTOs
        // return obras.stream().map(mapper::obraEntityToDTO).collect(Collectors.toList());
    }

    // Guardar Obra
    public ObraEntity guardarObraNueva(ObraEntity obra, MultipartFile[] files) {

        // Verificar si la obra ya existe por nombre
        Optional<ObraEntity> obraEntityOptional = Optional.empty();
        if (obra.getNombre() != null) {
            obraEntityOptional = obraRepository.findByNombre(obra.getNombre());
        }

        if (obraEntityOptional.isPresent()) {
            throw new ConflictException("La obra ya existe.");
        } else {
            // Manejo de Artista
            ArtistaEntity artistaEntity = artistaService.buscarOCrearArtista(obra.getArtista());

            // Manejo de MovimientoArtistico
            MovimientoArtisticoEntity movimientoArtisticoEntity =
                    movimientoArtisticoService.buscarOCrearMovimientoArtistico(obra.getMovimientoArtistico());

            // Manejo de TecnicaObra
            TecnicaObraEntity tecnicaObraEntity = tecnicaObraService.buscarOCrearTecnicaObra(obra.getTecnicaObra());

            // Crear ObraEntityToSave
            ObraEntity obraEntityToSave = new ObraEntity();
            obraEntityToSave.setNombre(obra.getNombre());
            obraEntityToSave.setFechaCreacion(obra.getFechaCreacion());
            obraEntityToSave.setDescripcion(obra.getDescripcion());
            obraEntityToSave.setPrecioRenta(obra.getPrecioRenta());
            obraEntityToSave.setDisponibilidad(obra.getDisponibilidad());
            obraEntityToSave.setTamano(obra.getTamano());
            obraEntityToSave.setTecnicaObra(tecnicaObraEntity);
            obraEntityToSave.setMovimientoArtistico(movimientoArtisticoEntity);
            obraEntityToSave.setArtista(artistaEntity);
            obraEntityToSave.setImagenes(new ArrayList<>());

            // Guardar la entidad Obra antes de asociar las imágenes
            ObraEntity savedObraEntity = obraRepository.save(obraEntityToSave);

            List<ImagenEntity> imagenEntities = new ArrayList<>();
            for (MultipartFile file : files) {
                try {
                    Map result = cloudinaryService.upload(file);
                    ImagenEntity imagen = new ImagenEntity(
                            (String) result.get("original_filename"),
                            (String) result.get("url"),
                            (String) result.get("public_id")
                    );
                    imagen.setObra(savedObraEntity); // Asociar a la entidad Obra guardada
                    imagenEntities.add(imagen);
                } catch (IOException e) {
                    throw new ConflictException("Error al subir las imágenes." + e.getMessage());
                }
            }

            // Actualizar la referencia de la colección de imágenes
            savedObraEntity.getImagenes().addAll(imagenEntities);
            return obraRepository.save(savedObraEntity);

        }
    }

    // Eliminar Obra por id
    public void eliminaObraPorID(Integer idEliminar) {
        Optional<ObraEntity> obraBuscada = Optional.ofNullable(buscarPorId(idEliminar));
        if (obraBuscada.isPresent()) {
            obraRepository.delete(obraBuscada.get());
        } else {
            throw new BadRequestException();
        }
    }

    // Actualizar Obra
    public ObraEntity actualizarObraNueva(ObraEntity obraActualizada, MultipartFile[] files){
        return null;
    }
}
