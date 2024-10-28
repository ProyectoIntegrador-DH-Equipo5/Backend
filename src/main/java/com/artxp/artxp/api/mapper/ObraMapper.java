package com.artxp.artxp.api.mapper;

import com.artxp.artxp.api.models.response.ArtistaDTO;
import com.artxp.artxp.api.models.response.MovimientoArtisticoDTO;
import com.artxp.artxp.api.models.response.ObraDTO;
import com.artxp.artxp.api.models.response.TecnicaObraDTO;
import com.artxp.artxp.domain.entities.ArtistaEntity;
import com.artxp.artxp.domain.entities.MovimientoArtisticoEntity;
import com.artxp.artxp.domain.entities.ObraEntity;
import com.artxp.artxp.domain.entities.TecnicaObraEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ObraMapper {

    ObraMapper INSTANCE = Mappers.getMapper(ObraMapper.class);

    // Mapeo de Obra
    @Mapping(source = "id", target = "id")
    @Mapping(source = "nombre", target = "nombre")
    @Mapping(source = "fechaCreacion", target = "fechaCreacion")
    @Mapping(source = "descripcion", target = "descripcion")
    @Mapping(source = "precioRenta", target = "precioRenta")
    @Mapping(source = "img", target = "img")
    @Mapping(source = "disponibilidad", target = "disponibilidad")
    @Mapping(source = "tamano", target = "tamano")
    @Mapping(source = "tecnicaObra", target = "tecnicaObra")
    @Mapping(source = "movimientoArtistico", target = "movimientoArtistico")
    @Mapping(source = "artista", target = "artista")
    ObraDTO obraEntityToDTO(ObraEntity obraEntity);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "nombre", target = "nombre")
    @Mapping(source = "fechaCreacion", target = "fechaCreacion")
    @Mapping(source = "descripcion", target = "descripcion")
    @Mapping(source = "precioRenta", target = "precioRenta")
    @Mapping(source = "img", target = "img")
    @Mapping(source = "disponibilidad", target = "disponibilidad")
    @Mapping(source = "tamano", target = "tamano")
    @Mapping(source = "tecnicaObra", target = "tecnicaObra")
    @Mapping(source = "movimientoArtistico", target = "movimientoArtistico")
    @Mapping(source = "artista", target = "artista")
    ObraEntity obraDTOToEntity(ObraDTO obraDTO);

    // Mapeo de Artista
    @Mapping(source = "id", target = "id")
    @Mapping(source = "nombre", target = "nombre")
    ArtistaDTO artistaEntityToDTO(ArtistaEntity artistaEntity);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "nombre", target = "nombre")
    ArtistaEntity artistaDTOToEntity(ArtistaDTO artistaDTO);

    // Mapeo de MovimientoArtistico
    @Mapping(source = "id", target = "id")
    @Mapping(source = "nombreMovimiento", target = "nombreMovimiento")
    MovimientoArtisticoDTO movimientoArtisticoEntityToDTO(MovimientoArtisticoEntity movimientoArtisticoEntity);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "nombreMovimiento", target = "nombreMovimiento")
    MovimientoArtisticoEntity movimientoArtisticoDTOToEntity(MovimientoArtisticoDTO movimientoArtisticoDTO);

    // Mapeo de TecnicaObra
    @Mapping(source = "id", target = "id")
    @Mapping(source = "nombre", target = "nombre")
    TecnicaObraDTO tecnicaObraEntityToDTO(TecnicaObraEntity tecnicaObraEntity);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "nombre", target = "nombre")
    TecnicaObraEntity tecnicaObraDTOToEntity(TecnicaObraDTO tecnicaObraDTO);
}
