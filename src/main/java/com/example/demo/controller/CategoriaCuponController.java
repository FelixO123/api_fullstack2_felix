package com.example.demo.controller;

import com.example.demo.modelo.CategoriaCupon;

import com.example.demo.service.CategoriaCuponService;

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
@RequestMapping("/api/categorias-cupones")
public class CategoriaCuponController {
    @Autowired
    private CategoriaCuponService categoriaCuponService;

    // CONSULTAR TODAS LAS CATEGORIAS
    @GetMapping
    public ResponseEntity<List<CategoriaCupon>> findAll(){
        List<CategoriaCupon> categoriasCupones = categoriaCuponService.findAll();
        if(categoriasCupones.isEmpty()){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(categoriasCupones);
    }

    // REGISTRAR NUEVA CATEGORIA
    @PostMapping
    public ResponseEntity<CategoriaCupon> createCategoriaCupon(@RequestBody CategoriaCupon categoriaCupon){
        CategoriaCupon createdCategoria = categoriaCuponService.saveOrUpdate(categoriaCupon, null);
        return new ResponseEntity<>(createdCategoria, HttpStatus.CREATED);
    }

    // Actualizar categoría cupón existente
    @PutMapping("/{id}")
    public ResponseEntity<CategoriaCupon> updateCategoriaCupon(@PathVariable("id") Integer idCategoriaCupon, 
        @RequestBody CategoriaCupon categoriaCupon){
        CategoriaCupon updatedCategoria = categoriaCuponService.saveOrUpdate(categoriaCupon, idCategoriaCupon);
        return new ResponseEntity<>(updatedCategoria, HttpStatus.OK);
    }
    
    // Consultar por id de categoria
    @GetMapping("/{id}")
    public ResponseEntity<CategoriaCupon> findById(@PathVariable Integer id){
        try {
            CategoriaCupon categoriaCupon = categoriaCuponService.findById(id);
            return ResponseEntity.ok(categoriaCupon);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    // ELIMINAR CATEGORIA POR SU ID
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id){

        try{
            categoriaCuponService.delete(id);
            return ResponseEntity.noContent().build();
            
        }catch(Exception e){
            return ResponseEntity.notFound().build();
            
        }
    }

}
