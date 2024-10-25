package com.artxp.artxp.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name="obra")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class MovimientoArtisticoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @Column(name="nombre_movimiento")
    private String NombreMovimiento;
}
