package com.artxp.artxp.api.models.response;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ArtistaDTO {

    private Integer id;
    private String nombre;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}