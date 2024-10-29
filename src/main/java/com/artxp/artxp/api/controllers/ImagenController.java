package com.artxp.artxp.api.controllers;

import com.artxp.artxp.api.models.response.ImagenDTO;
import com.artxp.artxp.domain.entities.ObraEntity;
import com.artxp.artxp.infrastructure.services.ImagenService;
import com.artxp.artxp.infrastructure.services.ObraService;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/imagenes")
public class ImagenController {
    //----------------------- Dependencias -----------------------
    @Autowired
    private ImagenService imagenService;

    @Autowired
    private ObraService obraService;

    //crear nueva imagen
    @PostMapping("/{obra_id}")
    public ResponseEntity<ImagenDTO> crearImagen(@PathVariable Integer obra_id, @RequestBody ImagenDTO imagenDTO) {
        ObraEntity obra = obraService.buscarPorId(obra_id);
        ImagenDTO nuevaImagen = imagenService.guardarImagen(imagenDTO, obra);
        return ResponseEntity.ok(nuevaImagen);
    }

    // obtener imagen por ID de obra
    @GetMapping("obra/{obra_id}")
    public ResponseEntity<List<ImagenDTO>> buscarImagenesPorObra(@PathVariable Integer obra_id) {
        ObraEntity obra = obraService.buscarPorId(obra_id);
        List<ImagenDTO> imagenes = imagenService.buscarImagenesPorObra(obra);
        return ResponseEntity.ok(imagenes);
    }

    // Eliminar imagen por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarImagen(@PathVariable Integer id) {
        imagenService.eliminaImagenPorID(id);
        return ResponseEntity.ok("Imagen eliminada con éxito");
    }
}
