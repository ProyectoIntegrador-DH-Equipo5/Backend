package com.artxp.artxp.infrastructure.services;

import com.artxp.artxp.api.mapper.ObraMapper;
import com.artxp.artxp.api.models.response.ImagenDTO;
import com.artxp.artxp.domain.entities.ImagenEntity;
import com.artxp.artxp.domain.entities.ObraEntity;
import com.artxp.artxp.domain.repositories.ImagenRepository;
import com.artxp.artxp.util.exeptions.IdNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ImagenService {

    @Autowired
    private ImagenRepository imagenRepository;

    private final ObraMapper mapper = ObraMapper.INSTANCE;

    // Guardar nueva imagen
    public ImagenDTO guardarImagen(ImagenDTO imagenDTO, ObraEntity obra) {
        ImagenEntity imagenEntity = mapper.imagenDTOToEntity(imagenDTO);
        imagenEntity.setObra(obra);
        imagenEntity = imagenRepository.save(imagenEntity);
        return mapper.imagenEntityToDTO(imagenEntity);
    }

    // Buscar imagen por ID
    public ImagenEntity findById(Integer id) {
        return imagenRepository.findById(id)
                .orElseThrow(() -> new IdNotFoundException(id, "Imagen"));
    }

    // Eliminar imagen por ID
    public void eliminaImagenPorID(Integer id) {
        ImagenEntity imagen = findById(id);
        imagenRepository.delete(imagen);
    }

    // Retorna toda la lista de imágenes asociadas a una obra
    public List<ImagenDTO> buscarImagenesPorObra(ObraEntity obra) {
        List<ImagenEntity> imagenes = imagenRepository.findByObra(obra);
        return imagenes.stream().map(mapper::imagenEntityToDTO).collect(Collectors.toList());
    }
}
