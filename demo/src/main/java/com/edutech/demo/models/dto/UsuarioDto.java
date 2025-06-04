package com.edutech.demo.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDto {
    private String nombre;
    private String appaterno;
    private String apmaterno; 
    private String correo;

}
