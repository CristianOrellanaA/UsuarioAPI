package com.edutech.demo.models.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class UsuarioEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idUsuario;

    @Column(name ="nombre")
    private String nombre;

    private String appaterno;
    private String apmaterno;
    private String passwordUsuario;
    private String rut;
    private String fechaNacimiento;
    private String correo;
}
