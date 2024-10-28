package com.artxp.artxp.infrastructure.services;

import com.artxp.artxp.api.mapper.ObraMapper;
import com.artxp.artxp.api.models.response.ArtistaDTO;
import com.artxp.artxp.api.models.response.MovimientoArtisticoDTO;
import com.artxp.artxp.api.models.response.ObraDTO;
import com.artxp.artxp.api.models.response.TecnicaObraDTO;
import com.artxp.artxp.domain.entities.ArtistaEntity;
import com.artxp.artxp.domain.entities.MovimientoArtisticoEntity;
import com.artxp.artxp.domain.entities.ObraEntity;
import com.artxp.artxp.domain.entities.TecnicaObraEntity;
import com.artxp.artxp.domain.repositories.ObraRepository;
import com.artxp.artxp.util.exeptions.IdNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ObraService {
    @Autowired
    public ObraRepository obraRepository;
    @Autowired
    private ArtistaService artistaService;
    @Autowired
    private MovimientoArtisticoService movimientoArtisticoService;
    @Autowired
    private TecnicaObraService tecnicaObraService;

    private final ObraMapper mapper = ObraMapper.INSTANCE;

    // Guardar Obra
    public ObraDTO guardarObraNueva(ObraDTO obraDTO) {
        // Manejo de Artista
        ArtistaDTO artistaDTO = artistaService.buscarOCrearArtista(obraDTO.getArtista());
        ArtistaEntity artistaEntity = artistaService.findById(artistaDTO.getId());

        // Manejo de MovimientoArtistico
        MovimientoArtisticoDTO movimientoArtisticoDTO =
                movimientoArtisticoService.buscarOCrearMovimientoArtistico(obraDTO.getMovimientoArtistico());
        MovimientoArtisticoEntity movimientoArtisticoEntity = movimientoArtisticoService.findById(movimientoArtisticoDTO.getId());

        // Manejo de TecnicaObra
        TecnicaObraDTO tecnicaObraDTO = tecnicaObraService.buscarOCrearTecnicaObra(obraDTO.getTecnicaObra());
        TecnicaObraEntity tecnicaObraEntity = tecnicaObraService.findById(tecnicaObraDTO.getId());

        // Verificar si la obra ya existe por ID
        Optional<ObraEntity> obraEntityOptional = Optional.empty();
        if (obraDTO.getId() != null) {
            obraEntityOptional = obraRepository.findById(obraDTO.getId());
        }

        // Si no se encuentra por ID, verificar por atributos clave
        if (!obraEntityOptional.isPresent()) {
            obraEntityOptional = obraRepository.findByNombreAndFechaCreacionAndArtista(
                    obraDTO.getNombre(), obraDTO.getFechaCreacion(), artistaEntity);
        }

        ObraEntity obraEntity;

        if (obraEntityOptional.isPresent()) {
            obraEntity = obraEntityOptional.get();
        } else {
            // Crear ObraEntity
            obraEntity = mapper.obraDTOToEntity(obraDTO);
            obraEntity.setArtista(artistaEntity);
            obraEntity.setMovimientoArtistico(movimientoArtisticoEntity);
            obraEntity.setTecnicaObra(tecnicaObraEntity);
            obraEntity = obraRepository.save(obraEntity);
        }

        return mapper.obraEntityToDTO(obraEntity);
    }

    // Elimina Obra por ID
    public void eliminaObraPorID(Integer id) {
        ObraEntity obra = obraRepository.findById(id)
                .orElseThrow(() -> new IdNotFoundException(id, "Obra"));
        obraRepository.delete(obra);
    }

    // Retorna toda la lista de obras
    public List<ObraDTO> buscarTodasLasObras() {
        List<ObraEntity> obras = obraRepository.findAll();
        // Se convierten las entities a DTOs
        return obras.stream().map(mapper::obraEntityToDTO).collect(Collectors.toList());
    }
}
