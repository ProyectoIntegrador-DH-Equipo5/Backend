package com.artxp.artxp.domain.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity(name="tecnica_obra")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@RequiredArgsConstructor
public class TecnicaObraEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="nombre")
    @NonNull
    private String nombre;

    @Override
    public String toString() {
        return "TecnicaObraEntity{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                '}';
    }

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
