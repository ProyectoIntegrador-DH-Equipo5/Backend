package com.artxp.artxp.api.models.response;

import com.artxp.artxp.util.Sizes;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ObraDTO {

    private Integer id;
    private String nombre;
    private LocalDate fechaCreacion;
    private String descripcion;
    private Double precioRenta;
    //private String img;
    private Boolean disponibilidad;

    //Enum de tamaños
    private Sizes tamano;

    //Referencia a otro DTOs
    private TecnicaObraDTO tecnicaObra;
    private MovimientoArtisticoDTO movimientoArtistico;
    private ArtistaDTO artista;
    private List<ImagenDTO> imagenes;


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

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Double getPrecioRenta() {
        return precioRenta;
    }

    public void setPrecioRenta(Double precioRenta) {
        this.precioRenta = precioRenta;
    }

    /*
    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
    */

    public Boolean getDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(Boolean disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    public Sizes getTamano() {
        return tamano;
    }

    public void setTamano(Sizes tamano) {
        this.tamano = tamano;
    }

    public TecnicaObraDTO getTecnicaObra() {
        return tecnicaObra;
    }

    public void setTecnicaObra(TecnicaObraDTO tecnicaObra) {
        this.tecnicaObra = tecnicaObra;
    }

    public MovimientoArtisticoDTO getMovimientoArtistico() {
        return movimientoArtistico;
    }

    public void setMovimientoArtistico(MovimientoArtisticoDTO movimientoArtistico) {
        this.movimientoArtistico = movimientoArtistico;
    }

    public ArtistaDTO getArtista() {
        return artista;
    }

    public void setArtista(ArtistaDTO artista) {
        this.artista = artista;
    }

    public List<ImagenDTO> getImagenes() {
        return imagenes;
    }

    public void setImagenes(List<ImagenDTO> imagenes) {
        this.imagenes = imagenes;
    }
}
