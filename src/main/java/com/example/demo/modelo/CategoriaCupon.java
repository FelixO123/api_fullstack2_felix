package com.example.demo.modelo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@NoArgsConstructor
@AllArgsConstructor
@Data


@Entity
@Table(name = "categoria_cupon")




public class CategoriaCupon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCategoria;

    @Column(unique = true, length = 25, nullable = false)
    private String categoria;

    @OneToMany(mappedBy = "categoriaCupon", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Cupon> cupones;

}

