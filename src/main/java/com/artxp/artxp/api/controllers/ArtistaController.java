package com.artxp.artxp.api.controllers;


import com.artxp.artxp.api.models.response.ArtistaDTO;
import com.artxp.artxp.api.models.response.ObraDTO;
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
    public ResponseEntity<ArtistaDTO> guardarArtista(@RequestBody ArtistaDTO artistaDTO) {
        return ResponseEntity.ok(artistaService.buscarOCrearArtista(artistaDTO));
    }

    // lista de artistas
    @GetMapping("/listartodos")
    public ResponseEntity<List<ArtistaDTO>> listarTodo() {
        return ResponseEntity.ok(artistaService.buscarTodosLosArtistas());
    }

}
