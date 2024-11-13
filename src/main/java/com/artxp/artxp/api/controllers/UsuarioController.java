package com.artxp.artxp.api.controllers;

import com.artxp.artxp.domain.entities.ArtistaEntity;
import com.artxp.artxp.domain.entities.UsuarioEntity;
import com.artxp.artxp.infrastructure.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;
    // Crear usuarios está en el registro de usuario (AuthenticationController)

    // Listar usuarios
    @GetMapping("/listartodos")
    public ResponseEntity<List<UsuarioEntity>> listarTodo() {
        return ResponseEntity.ok(usuarioService.buscarTodosUsuarios());
    }

    // Buscar usuario por id
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioEntity> buscarPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(usuarioService.buscarPorId(id));
    }

    // Actualizar usuario
    @PutMapping
    public ResponseEntity<UsuarioEntity> actualizarUsuario(@RequestBody UsuarioEntity usuarioEntity){
        return ResponseEntity.ok(usuarioService.actualizarUsuario(usuarioEntity));
    }

    // Eliminar usuario
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarUsuario(@PathVariable("id") Integer id) {
        usuarioService.eliminaUsuarioPorID(id);
        return ResponseEntity.ok("Éxito al eliminar el Usuario");
    }

}
