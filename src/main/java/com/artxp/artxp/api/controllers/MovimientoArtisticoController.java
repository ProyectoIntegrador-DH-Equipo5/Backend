package com.artxp.artxp.api.controllers;

import com.artxp.artxp.api.models.response.MovimientoArtisticoDTO;
import com.artxp.artxp.api.models.response.ObraDTO;
import com.artxp.artxp.domain.repositories.MovimientoArtisticoRepository;
import com.artxp.artxp.infrastructure.services.MovimientoArtisticoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/movimientoArtistico")
public class MovimientoArtisticoController {
    //----------------------- Dependencias -----------------------
    @Autowired
    private MovimientoArtisticoService movimientoArtisticoService;

    //----------------------- Mapeos -----------------------

    // Buscar o crear un nuevo movimiento artístico
    @PostMapping
    public ResponseEntity<MovimientoArtisticoDTO> buscarOCrearMovimientoArtistico(@RequestBody MovimientoArtisticoDTO movimientoArtisticoDTO) {
        return ResponseEntity.ok(movimientoArtisticoService.buscarOCrearMovimientoArtistico(movimientoArtisticoDTO));
    }

    // lista de movimientos artisticos
    @GetMapping("/listartodos")
    public ResponseEntity<List<MovimientoArtisticoDTO>> listarTodo() {
        return ResponseEntity.ok(movimientoArtisticoService.buscarTodosLosMovArtisticos());
    }
}
