package com.example.demo.controller;

import com.example.demo.modelo.CuponCliente;

import com.example.demo.service.CuponClienteService;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;


@RestController
@RequestMapping("/api/cupones-clientes")
public class CuponClienteController {
    @Autowired
    private CuponClienteService cuponClienteService;

    // CONSULTAR TODAS LAS CATEGORIAS
    @GetMapping
    public ResponseEntity<List<CuponCliente>> findAll(){
        List<CuponCliente> cuponClientes = cuponClienteService.findAll();
        if(cuponClientes.isEmpty()){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(cuponClientes);
    }

    // REGISTRAR CATEGORIA O MODIFICAR CATEGORIA EXISTENTE
    @PostMapping
    public ResponseEntity<CuponCliente> createCuponCliente(@RequestBody CuponCliente cuponCliente){
        CuponCliente createdCuponCliente = cuponClienteService.saveOrUpdate(cuponCliente, null);
        return new ResponseEntity<>(createdCuponCliente, HttpStatus.CREATED);
    }

    // Actualizar categoría cupón existente
    @PutMapping("/{id}")
    public ResponseEntity<CuponCliente> updateCuponCliente(@PathVariable("id") Integer idCuponCliente, 
        @RequestBody CuponCliente cuponCliente){
        CuponCliente updatedCuponCliente = cuponClienteService.saveOrUpdate(cuponCliente, idCuponCliente);
        return new ResponseEntity<>(updatedCuponCliente, HttpStatus.OK);
    }

    
    @GetMapping("/{id}")
    public ResponseEntity<CuponCliente> findById(@PathVariable Integer id){
        try {
            CuponCliente cuponCliente = cuponClienteService.findById(id);
            return ResponseEntity.ok(cuponCliente);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id){

        try{
            cuponClienteService.delete(id);
            return ResponseEntity.noContent().build();
            
        }catch(Exception e){
            return ResponseEntity.notFound().build();
            
        }
    }

    @GetMapping("/validar")
    public ResponseEntity<String> validarCupon(@RequestParam Integer idCuponCliente, @RequestParam Integer runCliente) {

        boolean esValido = cuponClienteService.validarCupon(idCuponCliente, runCliente);

        if (esValido) {
            return ResponseEntity.ok("El cupón es válido.");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El cupón no es válido.");
        }
    }

}
