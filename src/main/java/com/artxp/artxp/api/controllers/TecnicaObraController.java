package com.artxp.artxp.api.controllers;

import com.artxp.artxp.api.models.response.ObraDTO;
import com.artxp.artxp.api.models.response.TecnicaObraDTO;
import com.artxp.artxp.domain.entities.TecnicaObraEntity;
import com.artxp.artxp.infrastructure.services.TecnicaObraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/tecnicaObra")
public class TecnicaObraController {
    //----------------------- Dependencias -----------------------
    @Autowired
    private TecnicaObraService tecnicaObraService;

    //----------------------- Mapeos -----------------------

    // Buscar o crear una nueva técnica de obra
    @PostMapping
    public ResponseEntity<TecnicaObraEntity> buscarOCrearTecnicaObra(@RequestBody TecnicaObraEntity tecnicaObraEntity) {
        return ResponseEntity.ok(tecnicaObraService.buscarOCrearTecnicaObra(tecnicaObraEntity));
    }

    // lista de tecnicas
    @GetMapping("/listartodos")
    public ResponseEntity<List<TecnicaObraEntity>> listarTodo() {
        return ResponseEntity.ok(tecnicaObraService.buscarTodasLasTecnicas());
    }
}
