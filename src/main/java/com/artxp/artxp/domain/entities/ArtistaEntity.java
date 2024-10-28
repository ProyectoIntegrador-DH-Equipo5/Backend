package com.artxp.artxp.domain.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity(name="artista")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@RequiredArgsConstructor
public class ArtistaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="nombre")
    @NonNull
    private String nombre;


    // Getters & Setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public @NonNull String getNombre() {
        return nombre;
    }

    public void setNombre(@NonNull String nombre) {
        this.nombre = nombre;
    }
}
