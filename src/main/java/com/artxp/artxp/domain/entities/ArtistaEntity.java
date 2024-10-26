package com.artxp.artxp.domain.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity(name="artista")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@RequiredArgsConstructor
public class ArtistaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="nombre") @NonNull private String nombre;
}
