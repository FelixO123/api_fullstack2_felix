package com.example.demo.modelo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@NoArgsConstructor
@AllArgsConstructor
@Data

@Entity
@Table(name = "cupon")

public class Cupon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCupon;

    @Column(unique = false, precision = 3, scale = 2, nullable = false)
    private BigDecimal porcentajeDescuento;

    @ManyToOne
    @JoinColumn(name = "id_categoria", nullable = false)
    @JsonBackReference
    private CategoriaCupon categoriaCupon;
    
    @OneToMany(mappedBy = "cupon", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<CuponCliente> cuponesAplicados = new ArrayList<>();

}
