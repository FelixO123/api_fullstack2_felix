package com.example.demo.controller;

import com.example.demo.modelo.Cupon;
import com.example.demo.service.CuponService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;


@RestController
@RequestMapping("/api/cupones")
public class CuponController {
    @Autowired
    private CuponService cuponService;

    // CONSULTAR TODAS LAS CATEGORIAS
    @GetMapping
    public ResponseEntity<List<Cupon>> findByAll(){
        List<Cupon> cupones = cuponService.findAll();
        if(cupones.isEmpty()){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(cupones);
    }

        // REGISTRAR NUEVO CUPON
    @PostMapping
    public ResponseEntity<Cupon> createCupon(@RequestBody Cupon cupon){
        Cupon createdCupon = cuponService.saveOrUpdate(cupon, null);
        return new ResponseEntity<>(createdCupon, HttpStatus.CREATED);
    }

    // Actualizar cupón existente
    @PutMapping("/{id}")
    public ResponseEntity<Cupon> updateCupon(@PathVariable("id") Integer idCupon, 
        @RequestBody Cupon cupon){
        Cupon updatedCupon = cuponService.saveOrUpdate(cupon, idCupon);
        return new ResponseEntity<>(updatedCupon, HttpStatus.OK);
    }

    
    @GetMapping("/{id}")
    public ResponseEntity<Cupon> findById(@PathVariable Integer id){
        try {
            Cupon cupon = cuponService.findById(id);
            return ResponseEntity.ok(cupon);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id){

        try{
            cuponService.delete(id);
            return ResponseEntity.noContent().build();
            
        }catch(Exception e){
            return ResponseEntity.notFound().build();
            
        }
    }

}

