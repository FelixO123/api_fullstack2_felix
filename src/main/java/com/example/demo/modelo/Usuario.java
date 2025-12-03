package com.example.demo.modelo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data


@Entity
@Table(name = "usuario")


public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idUsuario;

    @Column(unique = true, length = 25, nullable = false)
    private String nombre;

    @Column(unique = true, length = 25, nullable = false)
    private String email;

    @Column(unique = false, length = 25, nullable = false)
    private String password;

    @Column(unique = false, length = 9, nullable = false)
    private Integer telefono;

    @Column(unique = false, length = 25, nullable = false)
    private String region;

    @Column(unique = false, length = 25, nullable = false)
    private String comuna;

    @Column(unique = false, nullable = false)
    private LocalDate fechaCreacion = LocalDate.now();


}

