package com.example.demo.modelo;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

@Entity(name = "cupon_cliente")
public class CuponCliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCuponCliente;

    /*@ManyToOne
    @JoinColumn(name = "run_usuario", nullable = false)
    private Usuario usuario;
    */
    
    //ESTA COLUMNA ES PROVISORIA
    @Column(name = "run_usuario", nullable = false)
    private Integer runUsuario;

    @ManyToOne
    @JoinColumn(name = "id_cupon", nullable = false)
    @JsonBackReference
    private Cupon cupon;

    @Column(name = "fecha_generacion")
    private Date fechaGeneracion;

    @Column(name = "fecha_vencimiento")
    private Date fechaVencimiento;

    @Column(name = "usado")
    private boolean usado;
}
