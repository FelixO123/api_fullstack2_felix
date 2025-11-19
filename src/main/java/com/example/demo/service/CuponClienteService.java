package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.modelo.CuponCliente;
import com.example.demo.repository.CuponClienteRepository;

import jakarta.transaction.Transactional;

import java.util.List;

import java.util.Optional;

import java.sql.Date;

@Service
@Transactional

public class CuponClienteService {
    @Autowired
    private CuponClienteRepository cuponClienteRepository;

    // CREAR CATEGORIA Y MODIFICAR CATEGORIA
    public CuponCliente saveOrUpdate(CuponCliente cuponCliente, Integer idCuponCliente){
        if (idCuponCliente != null) {
            Optional<CuponCliente> optionalCuponCliente = cuponClienteRepository.findById(idCuponCliente);

            if (optionalCuponCliente.isPresent()) {
                CuponCliente cuponClienteExistente = optionalCuponCliente.get();
                cuponClienteExistente.setCupon(cuponCliente.getCupon());
                cuponClienteExistente.setFechaGeneracion(cuponCliente.getFechaGeneracion());
                cuponClienteExistente.setFechaVencimiento(cuponCliente.getFechaVencimiento());
                cuponClienteExistente.setRunUsuario(cuponCliente.getRunUsuario());
                return cuponClienteRepository.save(cuponClienteExistente);
            }
        }

        // Si no existe o id es null, se crea nuevo
        return cuponClienteRepository.save(cuponCliente);
    }

    //  CONSULTAR TODOS LOS REGISTROS DE LA TABLA "CUPON_CLIENTE"
    public List<CuponCliente> findAll(){
        return cuponClienteRepository.findAll();
    }

    // CONSULTA UN REGISTRO POR SU ID DESDE LA TABLA "CUPON_CLIENTE" 
    public CuponCliente findById(Integer id){
        return cuponClienteRepository.findById(id).get();
    }

    // ELIMINAR UN REGISTRO POR SU ID DESDE LA TABLA "CUPON_CLIENTE"
    public void delete(Integer id){
        cuponClienteRepository.deleteById(id);
    }

    public boolean validarCupon(Integer idCuponCliente, Integer runUsuario) {
    Optional<CuponCliente> cuponClienteOpt = cuponClienteRepository.findById(idCuponCliente);

        if (cuponClienteOpt.isEmpty()) {
            return false; // El cupón no existe
        }

        CuponCliente cuponCliente = cuponClienteOpt.get();

        if (!cuponCliente.getRunUsuario().equals(runUsuario)) {
            return false; // El cupón no pertenece al usuario
        }

        if (cuponCliente.isUsado()) {
            return false; // El cupón ya fue utilizado
        }

        Date fechaActual = new Date(System.currentTimeMillis());
        if (cuponCliente.getFechaVencimiento().before(fechaActual)) {
            return false; // El cupón está vencido
        }

        return true; // El cupón es válido
    }
}
