package com.artxp.artxp.infrastructure.services;

import com.artxp.artxp.api.mapper.ObraMapper;
import com.artxp.artxp.api.models.response.ImagenDTO;
import com.artxp.artxp.domain.entities.ImagenEntity;
import com.artxp.artxp.domain.entities.ObraEntity;
import com.artxp.artxp.domain.repositories.ImagenRepository;
import com.artxp.artxp.util.exeptions.IdNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class ImagenService {

    @Autowired
    private ImagenRepository imagenRepository;

    private final ObraMapper mapper = ObraMapper.INSTANCE;

    public List<ImagenEntity> list(){
        return imagenRepository.findByOrderById();
    }
    public Optional<ImagenEntity> getOne(Integer id){
        return imagenRepository.findById(id);
    }
    public void save(ImagenEntity imagen){
        imagenRepository.save(imagen);
    }
    public void delete(Integer id){
        imagenRepository.deleteById(id);
    }
    public boolean exists(Integer id){
        return imagenRepository.existsById(id);
    }

}
