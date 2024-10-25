package com.artxp.artxp.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name="tecnica_obra")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class TecnicaObraEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @Column(name="nombre")
    private String nombre;
}
