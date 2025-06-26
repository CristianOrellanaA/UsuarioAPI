package com.edutech.demo.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Service;

import com.edutech.demo.models.Usuario;
import com.edutech.demo.models.dto.UsuarioDto;
import com.edutech.demo.models.entity.UsuarioEntity;
import com.edutech.demo.repository.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> obtenerUsuarios() {
        return usuarioRepository.findAll().stream()
                .map(this::convertirAUsuario)
                .collect(Collectors.toList());
    }

    public Usuario obtenerUsuario(String correo) {
        try {
            UsuarioEntity usuario = usuarioRepository.findByCorreo(correo);
            return usuario != null ? convertirAUsuario(usuario) : null;
        } catch (Exception e) {
            return null;
        }
    }

    public String crearUsuario(Usuario user) {
        try {
            boolean existe = usuarioRepository.existsByCorreo(user.getCorreo());
            if (!existe) {
                UsuarioEntity usuarioNuevo = new UsuarioEntity();
                usuarioNuevo.setNombre(user.getNombre());
                usuarioNuevo.setAppaterno(user.getAppaterno());
                usuarioNuevo.setApmaterno(user.getApmaterno());
                usuarioNuevo.setFechaNacimiento(user.getFechaNacimiento());
                usuarioNuevo.setPasswordUsuario(user.getPasswordUsuario());
                usuarioNuevo.setRut(user.getRut());
                usuarioNuevo.setCorreo(user.getCorreo());
                usuarioRepository.save(usuarioNuevo);
                return "Usuario creado correctamente";
            }
            return "El correo ya existe";
        } catch (ObjectOptimisticLockingFailureException e) {
            return "Error de concurrencia: " + e.getMessage();
        } catch (Exception e) {
            return "Error al crear usuario: " + e.getMessage();
        }
    }

    public String borrarUsuario(Integer id) {
        try {
            if (usuarioRepository.existsById(id)) {
                usuarioRepository.deleteById(id);
                return "Usuario Borrado correctamente";
            }
            return "Usuario No existe";
        } catch (Exception e) {
            return "Error al borrar usuario: " + e.getMessage();
        }
    }

    public String actualizarUsuario(Integer id, Usuario usuarioActualizado) {
        try {
            Optional<UsuarioEntity> opt = usuarioRepository.findById(id);
            if (opt.isPresent()) {
                UsuarioEntity usuario = opt.get();
                usuario.setPasswordUsuario(usuarioActualizado.getPasswordUsuario());
                usuario.setRut(usuarioActualizado.getRut());
                usuario.setNombre(usuarioActualizado.getNombre());
                usuario.setAppaterno(usuarioActualizado.getAppaterno());
                usuario.setApmaterno(usuarioActualizado.getApmaterno());
                usuario.setFechaNacimiento(usuarioActualizado.getFechaNacimiento());
                usuario.setCorreo(usuarioActualizado.getCorreo());
                usuarioRepository.save(usuario);
                return "Usuario actualizado correctamente";
            }
            return "Usuario no encontrado";
        } catch (Exception e) {
            return "Error al actualizar usuario: " + e.getMessage();
        }
    }

    public UsuarioDto obtenerUsuarioDto(Integer idUsuario) {
        try {
            UsuarioEntity usuario = usuarioRepository.findByIdUsuario(idUsuario);
            if (usuario != null) {
                return new UsuarioDto(
                        usuario.getNombre(),
                        usuario.getAppaterno(),
                        usuario.getApmaterno(),
                        usuario.getCorreo()
                );
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    public ResponseEntity<UsuarioDto> obtenerUserDto(String correo) {
        UsuarioEntity usuario = usuarioRepository.findByCorreo(correo);
        if (usuario != null) {
            UsuarioDto usuarioResponse = new UsuarioDto(
                    usuario.getNombre(),
                    usuario.getAppaterno(),
                    usuario.getApmaterno(),
                    usuario.getCorreo()
            );
            return ResponseEntity.ok(usuarioResponse);
        }
        return ResponseEntity.notFound().build();
    }

    private Usuario convertirAUsuario(UsuarioEntity entidad) {
        return new Usuario(
                entidad.getIdUsuario(),
                entidad.getPasswordUsuario(),
                entidad.getRut(),
                entidad.getNombre(),
                entidad.getAppaterno(),
                entidad.getApmaterno(),
                entidad.getFechaNacimiento(),
                entidad.getCorreo()
        );
    }
}
