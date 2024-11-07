package com.artxp.artxp.domain.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity(name="movimiento_artistico")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class MovimientoArtisticoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @Column(name="nombre_movimiento")
    @NonNull
    private String nombre;

}
