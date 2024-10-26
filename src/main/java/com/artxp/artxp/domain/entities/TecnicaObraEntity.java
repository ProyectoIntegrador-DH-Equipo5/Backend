package com.artxp.artxp.domain.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity(name="tecnica_obra")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@RequiredArgsConstructor
public class TecnicaObraEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @Column(name="nombre") @NonNull private String nombre;
}
