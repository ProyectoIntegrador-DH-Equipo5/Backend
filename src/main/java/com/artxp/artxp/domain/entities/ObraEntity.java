package com.artxp.artxp.domain.entities;

import com.artxp.artxp.domain.Sizes;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity(name="obra")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ObraEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="nombre")
    private String nombre;

    @Column(name="fecha_creacion")
    private LocalDate fechaCreacion;

    @Column(name="descripcion")
    private String descripcion;

    @Column(name="precio_renta")
    private Double precioRenta;

    @Column(name="img")
    private String img;

    @Column(name="disponibilidad")
    private Boolean disponibilidad;

    @Column(name="artista")
    ArtistaEntity artista;
    //Enum de tamaños
    @Column(name="tamanio")
    private Sizes tamano;


}
