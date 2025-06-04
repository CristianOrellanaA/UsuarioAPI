package com.edutech.demo.models;

public class Usuario {
    private Integer idUsuario;
    private String passwordUsuario;
    private String rut;
    private String nombre;
    private String appaterno;
    private String apmaterno;
    private String fechaNacimiento;
    private String correo;
    
    public Usuario(Integer idUsuario, String passwordUsuario, String rut, String nombre, String appaterno,
            String apmaterno, String fechaNacimiento, String correo) {
        this.idUsuario = idUsuario;
        this.passwordUsuario = passwordUsuario;
        this.rut = rut;
        this.nombre = nombre;
        this.appaterno = appaterno;
        this.apmaterno = apmaterno;
        this.fechaNacimiento = fechaNacimiento;
        this.correo = correo;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getPasswordUsuario() {
        return passwordUsuario;
    }

    public void setPasswordUsuario(String passwordUsuario) {
        this.passwordUsuario = passwordUsuario;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAppaterno() {
        return appaterno;
    }

    public void setAppaterno(String appaterno) {
        this.appaterno = appaterno;
    }

    public String getApmaterno() {
        return apmaterno;
    }

    public void setApmaterno(String apmaterno) {
        this.apmaterno = apmaterno;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    @Override
    public String toString() {
        return "Usuario [idUsuario=" + idUsuario + ", passwordUsuario=" + passwordUsuario + ", rut=" + rut + ", nombre="
                + nombre + ", appaterno=" + appaterno + ", apmaterno=" + apmaterno + ", fechaNacimiento="
                + fechaNacimiento + ", correo=" + correo + "]";
    }

    

}
