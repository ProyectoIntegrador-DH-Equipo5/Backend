package com.artxp.artxp.api.controllers;

import com.artxp.artxp.domain.entities.ObraEntity;
import com.artxp.artxp.infrastructure.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

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

    //----------------------- CRUD -----------------------
    // buscar por id
    @GetMapping("/{id}")
    public ResponseEntity<ObraEntity> buscarObraPorId(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(obraService.buscarPorId(id));
    }

    // lista de obras
    @GetMapping("/listartodos")
    public ResponseEntity<List<ObraEntity>> listarTodo() {
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
