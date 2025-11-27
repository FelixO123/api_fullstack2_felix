package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.modelo.Usuario;
import com.example.demo.repository.UsuarioRepository;

import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    // CREAR O MODIFICAR USUARIO
    public Usuario saveOrUpdate(Usuario usuario, Integer idUsuario) {

        if (idUsuario != null) {
            Optional<Usuario> optionalUsuario = usuarioRepository.findById(idUsuario);

            if (optionalUsuario.isPresent()) {
                Usuario usuarioExistente = optionalUsuario.get();

                usuarioExistente.setNombre(usuario.getNombre());
                usuarioExistente.setEmail(usuario.getEmail());
                usuarioExistente.setPassword(usuario.getPassword());
                usuarioExistente.setComuna(usuario.getComuna());
                usuarioExistente.setRegion(usuario.getRegion());
                usuarioExistente.setTelefono(usuario.getTelefono());
                usuarioExistente.setFechaCreacion(usuario.getFechaCreacion());

                return usuarioRepository.save(usuarioExistente);
            }
        }

        // Crear nuevo si id es null o no existe
        return usuarioRepository.save(usuario);
    }

    // CONSULTAR TODOS LOS USUARIOS
    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    // CONSULTAR USUARIO POR ID
    public Usuario findById(Integer idUsuario) {
        Optional<Usuario> optionalUsuario = usuarioRepository.findById(idUsuario);
        if (optionalUsuario.isPresent()) {
            return optionalUsuario.get();
        }
        return null;
    }

    // ELIMINAR USUARIO POR ID
    public void delete(Integer idUsuario) {
        usuarioRepository.deleteById(idUsuario);
    }
}
