package com.artxp.artxp.api.models.response;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MovimientoArtisticoDTO {

    private Integer id;
    private String nombreMovimiento;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombreMovimiento() {
        return nombreMovimiento;
    }

    public void setNombreMovimiento(String nombreMovimiento) {
        this.nombreMovimiento = nombreMovimiento;
    }
}
