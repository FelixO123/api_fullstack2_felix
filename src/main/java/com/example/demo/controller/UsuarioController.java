package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import com.example.demo.modelo.Usuario;
import com.example.demo.service.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    // CREAR USUARIO
    @PostMapping("/crear")
    public Usuario crear(@RequestBody Usuario usuario) {
        return usuarioService.saveOrUpdate(usuario, null);
    }

    // EDITAR USUARIO
    @PutMapping("/editar/{idUsuario}")
    public Usuario editar(@PathVariable Integer idUsuario, @RequestBody Usuario usuario) {
        return usuarioService.saveOrUpdate(usuario, idUsuario);
    }

    // LISTAR TODOS LOS USUARIOS
    @GetMapping("/listar")
    public List<Usuario> listar() {
        return usuarioService.findAll();
    }

    // BUSCAR USUARIO POR ID
    @GetMapping("/buscar/{idUsuario}")
    public Usuario buscar(@PathVariable Integer idUsuario) {
        return usuarioService.findById(idUsuario);
    }

    // ELIMINAR USUARIO
    @DeleteMapping("/eliminar/{idUsuario}")
    public String eliminar(@PathVariable Integer idUsuario) {
        usuarioService.delete(idUsuario);
        return "Usuario eliminado correctamente";
    }
}
