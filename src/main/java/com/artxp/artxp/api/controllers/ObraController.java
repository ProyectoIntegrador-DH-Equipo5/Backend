package com.artxp.artxp.api.controllers;

import com.artxp.artxp.api.mapper.ObraMapper;
import com.artxp.artxp.api.models.response.ObraDTO;
import com.artxp.artxp.api.models.response.TecnicaObraDTO;
import com.artxp.artxp.domain.entities.ImagenEntity;
import com.artxp.artxp.domain.entities.ObraEntity;
import com.artxp.artxp.domain.entities.TecnicaObraEntity;
import com.artxp.artxp.infrastructure.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path="/obra")
public class ObraController {
    //----------------------- Dependencias -----------------------
    @Autowired
    private ObraService obraService;

    @Autowired
    private TecnicaObraService tecnicaObraService;

    @Autowired
    private ArtistaService artistaService;

    @Autowired
    private MovimientoArtisticoService movimientoArtisticoService;

    @Autowired
    private CloudinaryService cloudinaryService;

    private final ObraMapper mapper = ObraMapper.INSTANCE;

    //----------------------- Mapeos -----------------------

    // buasr por id
    // este es para ahora

    // lista de obras
    @GetMapping("/listartodos")
    public ResponseEntity<List<ObraDTO>> listarTodo() {
        return ResponseEntity.ok(obraService.buscarTodasLasObras());
    }

    //Agregar nueva Obra
    @PostMapping
    public ResponseEntity<?> guardarObra(@ModelAttribute ObraEntity obra,
                                               @RequestPart("files") MultipartFile[] files) {
        ObraEntity obraEntitySaved = obraService.guardarObraNueva(obra, files);

        return ResponseEntity.ok(obraEntitySaved);
    }

    //Eliminar
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarObraPorId(@PathVariable("id") Integer idEliminar) {
        obraService.eliminaObraPorID(idEliminar);
        return ResponseEntity.ok("Obra de Arte Eliminada");
    }

    //editar por id

    //paginar
}
