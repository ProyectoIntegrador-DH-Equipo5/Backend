package com.artxp.artxp.api.controllers;

import com.artxp.artxp.domain.entities.ObraEntity;
import com.artxp.artxp.infrastructure.services.ObraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/obra")
public class ObraController {
    //----------------------- Dependencias -----------------------
    @Autowired
    private ObraService obraService;

    //----------------------- Mapeos -----------------------

    // lista de obras
    @GetMapping("/listartodos")
    public ResponseEntity<List<ObraEntity>> listarTodo(){
        return ResponseEntity.ok(obraService.buscarTodasLasObras());
    }

    //Agregar nueva Obra
    @PostMapping
    public ResponseEntity<ObraEntity> guardarObra(@RequestBody ObraEntity obraN){
        return ResponseEntity.ok(obraService.guardarObraNueva(obraN));
    }

    //Eliminar
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarObraPorId(@PathVariable("id") Integer idEliminar){
        obraService.eliminaObraPorID(idEliminar);
        return ResponseEntity.ok("Obra de Arte Eliminada");
    }


}
