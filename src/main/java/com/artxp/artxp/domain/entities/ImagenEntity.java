package com.artxp.artxp.domain.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity(name = "imagen")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@RequiredArgsConstructor
public class ImagenEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NonNull
    @Column(name = "url", nullable = false)
    private String url;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "obra_id", nullable = false)
    private ObraEntity obra;

    // Getters & Setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public ObraEntity getObra() {
        return obra;
    }

    public void setObra(ObraEntity obra) {
        this.obra = obra;
    }
}
