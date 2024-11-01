package com.artxp.artxp.api.controllers;


import com.artxp.artxp.api.models.response.ArtistaDTO;
import com.artxp.artxp.api.models.response.ObraDTO;
import com.artxp.artxp.domain.entities.ArtistaEntity;
import com.artxp.artxp.infrastructure.services.ArtistaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/artista")
public class ArtistaController {
    //----------------------- Dependencias -----------------------
    @Autowired
    private ArtistaService artistaService;

    //----------------------- Mapeos -----------------------

    // Buscar o crear un nuevo artista
    @PostMapping
    public ResponseEntity<ArtistaEntity> guardarArtista(@RequestBody ArtistaEntity artistaEntity) {
        return ResponseEntity.ok(artistaService.buscarOCrearArtista(artistaEntity));
    }

    // lista de artistas
    @GetMapping("/listartodos")
    public ResponseEntity<List<ArtistaEntity>> listarTodo() {
        return ResponseEntity.ok(artistaService.buscarTodosLosArtistas());
    }

}
