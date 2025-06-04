package com.edutech.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.edutech.demo.models.entity.UsuarioEntity;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Integer> {

    UsuarioEntity findByCorreo(String correo);
    Boolean existsByCorreo(String correo);
    void deleteByCorreo(String correo);
    UsuarioEntity findByIdUsuario(Integer idUsuario);
}
