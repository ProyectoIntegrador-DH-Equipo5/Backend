package com.artxp.artxp.domain.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity(name = "imagen")
//@NoArgsConstructor
//@AllArgsConstructor
@Builder
//@RequiredArgsConstructor
public class ImagenEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    //@NonNull
    @Column(name = "nombre", nullable = false)
    private String nombre;

    //@NonNull
    @Column(name = "url", nullable = false)
    private String url;

    //@NonNull
    @Column(name = "imagen_id", nullable = false)
    private String imagenId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "obra_id", nullable = true)
    private ObraEntity obra;

    public ImagenEntity(Integer id, String nombre, String url, String imagenId) {
        this.id = id;
        this.nombre = nombre;
        this.url = url;
        this.imagenId = imagenId;
    }

    public ImagenEntity(String nombre, String url, String imagenId) {
        this.nombre = nombre;
        this.url = url;
        this.imagenId = imagenId;
    }

    public ImagenEntity() {
    }

    // Getters & Setters

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getImagenId() {
        return imagenId;
    }

    public void setImagenId(String imagenId) {
        this.imagenId = imagenId;
    }

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
