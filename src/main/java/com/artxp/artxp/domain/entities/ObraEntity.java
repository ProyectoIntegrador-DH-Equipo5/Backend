package com.artxp.artxp.domain.entities;

import com.artxp.artxp.util.Sizes;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity(name="obra")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@RequiredArgsConstructor

public class ObraEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NonNull @Column(name="nombre")
    private String nombre;

    @NonNull @Column(name="fecha_creacion")
    private LocalDate fechaCreacion;

    @NonNull @Column(name="descripcion")
    private String descripcion;

    @NonNull @Column(name="precio_renta")
    private Double precioRenta;

    @NonNull @Column(name="disponibilidad")
    private Boolean disponibilidad;

    //Enum de tamaños
    @NonNull @Column(name="tamanio")
    private Sizes tamano;

    // ----------- KEYS -----------
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "tecnica_id", nullable = false)
    @NonNull private TecnicaObraEntity tecnicaObra;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "artista_id", nullable = false)
    @NonNull private MovimientoArtisticoEntity movimientoArtistico;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "movimiento_artistico_id", nullable = false)
    @NonNull private ArtistaEntity artista;

    @OneToMany(mappedBy = "obra", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ImagenEntity> imagenes = new ArrayList<>(); //si permitimos guardar obras sin imagenes para no obtener un null

    // Getters & Setters
    public Integer getId() { return id; }

    public void setId(Integer id) {
        this.id = id;
    }

    public @NonNull String getNombre() {
        return nombre;
    }

    public void setNombre(@NonNull String nombre) {
        this.nombre = nombre;
    }

    public @NonNull LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(@NonNull LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public @NonNull String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(@NonNull String descripcion) {
        this.descripcion = descripcion;
    }

    public @NonNull Double getPrecioRenta() {
        return precioRenta;
    }

    public void setPrecioRenta(@NonNull Double precioRenta) {
        this.precioRenta = precioRenta;
    }

    public @NonNull Boolean getDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(@NonNull Boolean disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    public @NonNull Sizes getTamano() {
        return tamano;
    }

    public void setTamano(@NonNull Sizes tamano) {
        this.tamano = tamano;
    }

    public @NonNull TecnicaObraEntity getTecnicaObra() {
        return tecnicaObra;
    }

    public void setTecnicaObra(@NonNull TecnicaObraEntity tecnicaObra) {
        this.tecnicaObra = tecnicaObra;
    }

    public @NonNull MovimientoArtisticoEntity getMovimientoArtistico() {
        return movimientoArtistico;
    }

    public void setMovimientoArtistico(@NonNull MovimientoArtisticoEntity movimientoArtistico) {
        this.movimientoArtistico = movimientoArtistico;
    }

    public @NonNull ArtistaEntity getArtista() {
        return artista;
    }

    public void setArtista(@NonNull ArtistaEntity artista) {
        this.artista = artista;
    }

    public @NonNull List<ImagenEntity> getImagenes() {
        return imagenes;
    }

    public void setImagenes(@NonNull List<ImagenEntity> imagenes) {
        this.imagenes = imagenes;
    }
}
