package com.artxp.artxp.infrastructure.services;

import com.artxp.artxp.domain.entities.*;
import com.artxp.artxp.domain.repositories.UsuarioRepository;
import com.artxp.artxp.util.exeptions.BadRequestException;
import com.artxp.artxp.util.exeptions.IdNotFoundException;
import com.artxp.artxp.util.exeptions.UnauthorizedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    //Buscar todos
    public List<UsuarioEntity> buscarTodosUsuarios(){
        return usuarioRepository.findAll();
    }

    //Buscar usuario por ID
    public  UsuarioEntity buscarPorId(Integer id){
        return usuarioRepository.findById(id)
                .orElseThrow(()-> new IdNotFoundException(id, "Usuario"));
    }

    //Eliminar usuario por ID
    public void eliminaUsuarioPorID(Integer idEliminar) {
        Optional<UsuarioEntity> usuarioEliminar = Optional.ofNullable(buscarPorId(idEliminar));
        if (usuarioEliminar.isPresent()  && usuarioEliminar.get().getRol() != Role.ADMIN) {
            usuarioRepository.delete(usuarioEliminar.get());
        } else {
            throw new UnauthorizedException("Eliminar");
        }
    }

    //Actualizar usuario
    public UsuarioEntity actualizarUsuario(UsuarioEntity usuarioActualizar) {

        // Buscamos el email del usuario autenticado (quien va a actualizar) en el context holder
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String emailUsuarioAutenticado = authentication.getName(); // Este es el email del usuario autenticado
        System.out.println(emailUsuarioAutenticado);

        UsuarioEntity usuarioBuscado = usuarioRepository.findById(usuarioActualizar.getId())
                .orElseThrow(() -> new IdNotFoundException(usuarioActualizar.getId(), "Usuario"));

        UsuarioEntity usuarioAutenticado = usuarioRepository.findByEmail(emailUsuarioAutenticado)
                .orElseThrow(() -> new RuntimeException("Usuario autenticado no encontrado"));

        boolean esAdmin = usuarioAutenticado.getRol() == Role.ADMIN;
        boolean esColab = usuarioAutenticado.getRol() == Role.COLAB;

        usuarioBuscado.setName(usuarioActualizar.getName());
        usuarioBuscado.setLastname(usuarioActualizar.getLastname());
        usuarioBuscado.setEmail(usuarioActualizar.getEmail());
        if (!usuarioActualizar.getPassword().equals(usuarioBuscado.getPassword())) {
            usuarioBuscado.setPassword(passwordEncoder.encode(usuarioActualizar.getPassword()));
        }
        // Permitir que solo el admin cambie el rol (para que Colab no vaya cambiarse como Admin y elimine al Admin original)
        if(usuarioActualizar.getRol() != Role.ADMIN) {
            if (esAdmin || esColab) {
                if (usuarioBuscado.getRol() != Role.ADMIN) {
                    usuarioBuscado.setRol(usuarioActualizar.getRol());
                }
            }
        }else{
            throw new UnauthorizedException("Actualizar");
        }
        return usuarioRepository.save(usuarioBuscado);
    }

}
