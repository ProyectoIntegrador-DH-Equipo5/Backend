package com.artxp.artxp.util.initvalues;

import com.artxp.artxp.api.models.response.ArtistaDTO;
import com.artxp.artxp.domain.entities.ArtistaEntity;
import com.artxp.artxp.domain.entities.MovimientoArtisticoEntity;
import com.artxp.artxp.domain.entities.ObraEntity;
import com.artxp.artxp.domain.entities.TecnicaObraEntity;
import com.artxp.artxp.infrastructure.services.ObraService;
import com.artxp.artxp.util.Sizes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class RegistrosDefault implements ApplicationRunner {
    //  -----------  Inyeccion de servicios -----------
    @Autowired
    ObraService obraService;

    //  -----------  Insercion de datos de Ejemplo -----------
    @Override
    public void run(ApplicationArguments args) throws Exception {

        //***** Artista *****
        ArtistaEntity artista1 = ArtistaEntity.builder()
                .nombre(" artistaEjemplo1")
                .build();

        ArtistaEntity artista2 = ArtistaEntity.builder()
                .nombre(" artistaEjemplo2")
                .build();

        ArtistaEntity artista3 = ArtistaEntity.builder()
                .nombre(" artistaEjemplo3")
                .build();

        //***** Movimiento *****
        MovimientoArtisticoEntity mov1 = MovimientoArtisticoEntity.builder()
                .NombreMovimiento("Renacentista")
                .build();

        MovimientoArtisticoEntity mov2 = MovimientoArtisticoEntity.builder()
                .NombreMovimiento("Barroco")
                .build();

        MovimientoArtisticoEntity mov3 = MovimientoArtisticoEntity.builder()
                .NombreMovimiento("Gotico")
                .build();

        //***** Tecnica *****
        TecnicaObraEntity tecnica1 = TecnicaObraEntity.builder()
                .nombre("Dibujo en carboncillo")
                .build();

        TecnicaObraEntity tecnica2 = TecnicaObraEntity.builder()
                .nombre("Pintura al oleo")
                .build();

        TecnicaObraEntity tecnica3 = TecnicaObraEntity.builder()
                .nombre("Aquarela")
                .build();

        //***** Obra *****
        ObraEntity obra1 = ObraEntity.builder()
                .nombre("Cielo Estrellado")
                .fechaCreacion(LocalDate.of(1872, 7, 13))
                .descripcion(" es una obra muy bonita :D")
                .precioRenta(123456d)
                .img("/images/img1.jpg")
                .disponibilidad(true)
                .tecnicaObra(tecnica1)
                .artista(artista1)
                .movimientoArtistico(mov1)
                .tamano(Sizes.PEQUEÑO)
                .build();

        obraService.guardarObraNueva(obra1);


        ObraEntity obra2 = ObraEntity.builder()
                .nombre("Mona Lissa")
                .fechaCreacion(LocalDate.of(1857, 8, 30))
                .descripcion(" esto es arte, aprecienloo!!!")
                .precioRenta(999999999d)
                .img("/images/img2.jpg")
                .disponibilidad(false)
                .tecnicaObra(tecnica2)
                .artista(artista2)
                .movimientoArtistico(mov2)
                .tamano(Sizes.MEDIANO)
                .build();

        obraService.guardarObraNueva(obra2);



        ObraEntity obra3 = ObraEntity.builder()
                .nombre("el Grito D=")
                .fechaCreacion(LocalDate.of(2000, 7, 13))
                .descripcion(" aaaaaaaaaaaaa")
                .precioRenta(3000d)
                .img("/images/img3.jpg")
                .disponibilidad(true)
                .tecnicaObra(tecnica3)
                .artista(artista3)
                .movimientoArtistico(mov3)
                .tamano(Sizes.GRANDE)
                .build();

        obraService.guardarObraNueva(obra3);



    }



}
