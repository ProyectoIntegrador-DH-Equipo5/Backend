package com.artxp.artxp.api.models.response;

import com.artxp.artxp.util.Sizes;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ObraDTO {


    private String nombre;
    private LocalDate fechaCreacion;
    private String descripcion;
    private Double precioRenta;
    private String img;
    private Boolean disponibilidad;

    //Enum de tamaños
    private Sizes tamano;


}
