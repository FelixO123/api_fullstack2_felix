package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.modelo.Cupon;
import com.example.demo.repository.CuponRepository;

import jakarta.transaction.Transactional;

import java.util.List;

import java.util.Optional;

@Service
@Transactional

public class CuponService {
    @Autowired
    private CuponRepository cuponRepository;

        // CREAR CATEGORIA Y MODIFICAR CUPON
    public Cupon saveOrUpdate(Cupon cupon, Integer idCupon){
        if (idCupon != null) {
            Optional<Cupon> optionalCupon = cuponRepository.findById(idCupon);

            if (optionalCupon.isPresent()) {
                Cupon cuponExistente = optionalCupon.get();
                cuponExistente.setCategoriaCupon(cupon.getCategoriaCupon());
                cuponExistente.setPorcentajeDescuento(cupon.getPorcentajeDescuento());
                return cuponRepository.save(cuponExistente);
            }
        }

        // Si no existe o id es null, se crea nuevo
        return cuponRepository.save(cupon);
    }

    //  CONSULTAR TODAS LAS CATEGORIAS
    public List<Cupon> findAll(){
        return cuponRepository.findAll();
    }

    // CONSULTA UNA CATEGORIA POR SU ID
    public Cupon findById(Integer id){
        return cuponRepository.findById(id).get();
    }

    // ELIMINAR UNA CATEGORIA POR SU ID
    public void delete(Integer id){
        cuponRepository.deleteById(id);
    }

    
}
