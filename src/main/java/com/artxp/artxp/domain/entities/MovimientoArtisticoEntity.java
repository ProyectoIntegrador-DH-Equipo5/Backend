package com.artxp.artxp.domain.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity(name="movimiento_artistico")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@RequiredArgsConstructor
public class MovimientoArtisticoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @Column(name="nombre_movimiento")
    @NonNull
    private String nombreMovimiento;

    // Getters & Setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public @NonNull String getNombreMovimiento() {
        return nombreMovimiento;
    }

    public void setNombreMovimiento(@NonNull String nombreMovimiento) {
        this.nombreMovimiento = nombreMovimiento;
    }
}
