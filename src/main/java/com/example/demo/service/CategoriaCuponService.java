package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.modelo.CategoriaCupon;
import com.example.demo.repository.CategoriaCuponRepository;

import jakarta.transaction.Transactional;

import java.util.List;

import java.util.Optional;

@Service
@Transactional

public class CategoriaCuponService {
    @Autowired
    private CategoriaCuponRepository categoriaCuponRepository;


    // CREAR CATEGORIA Y MODIFICAR CATEGORIA
    public CategoriaCupon saveOrUpdate(CategoriaCupon categoriaCupon, Integer idCategoriaCupon){
        if (idCategoriaCupon != null) {
            Optional<CategoriaCupon> optionalCategoriaCupon = categoriaCuponRepository.findById(idCategoriaCupon);

            if (optionalCategoriaCupon.isPresent()) {
                CategoriaCupon categoriaExistente = optionalCategoriaCupon.get();
                categoriaExistente.setCategoria(categoriaCupon.getCategoria());
                return categoriaCuponRepository.save(categoriaExistente);
            }
        }

        // Si no existe o id es null, se crea nuevo
        return categoriaCuponRepository.save(categoriaCupon);
    }

    //  CONSULTAR TODAS LAS CATEGORIAS
    public List<CategoriaCupon> findAll(){
        return categoriaCuponRepository.findAll();
    }

    // CONSULTA UNA CATEGORIA POR SU ID
    public CategoriaCupon findById(Integer id){
        return categoriaCuponRepository.findById(id).get();
    }

    // ELIMINAR UNA CATEGORIA POR SU ID
    public void delete(Integer id){
        categoriaCuponRepository.deleteById(id);
    }
    
    
}
