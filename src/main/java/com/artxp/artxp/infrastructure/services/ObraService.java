package com.artxp.artxp.infrastructure.services;

import com.artxp.artxp.domain.entities.ObraEntity;
import com.artxp.artxp.domain.repositories.ObraRepository;
import com.artxp.artxp.util.exeptions.IdNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ObraService {
    @Autowired
   public ObraRepository obraRepository;

    //guarda Obra
    public ObraEntity guardarObraNueva(ObraEntity obraNueva)
    {
        return obraRepository.save(obraNueva);
    }

    //elimina Obra por ID
    public void eliminaObraPorID(Integer id){
        obraRepository.findById(id).orElseThrow(()-> new IdNotFoundException(id, "Obra"));
    }


    // retorna toda la lista de obras
    public List<ObraEntity> buscarTodasLasObras(){
        return obraRepository.findAll();
    }

}
