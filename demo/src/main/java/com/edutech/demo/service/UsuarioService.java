package com.edutech.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.edutech.demo.models.Usuario;
import com.edutech.demo.models.dto.UsuarioDto;
import com.edutech.demo.models.entity.UsuarioEntity;
import com.edutech.demo.repository.UsuarioRepository;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    private final List<Usuario> usuarios = new ArrayList<>();

    public UsuarioService(){
        usuarios.add(new Usuario(1,"password","16.954.815-5", "pedro","gonzalez","pereira","18 noviembre 1968", "pedro@gmail.com"));
    }


    public List<Usuario> obtenerUsuarios(){
        return usuarios;
    }
    
    public String crearUsuario(Usuario user){
        try {
            Boolean estado = usuarioRepository.existsByCorreo(user.getCorreo());
            if(!estado){
                UsuarioEntity usuarioNuevo = new UsuarioEntity();
                usuarioNuevo.setIdUsuario(user.getIdUsuario());
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

        }catch (Exception e) {
            e.printStackTrace();
            return "Error al crear usuario" + e.getMessage();
    }
    }

public Usuario obtenerUsuario(String correo){
    try{
        UsuarioEntity usuario = usuarioRepository.findByCorreo(correo);
        if(usuario != null){
            Usuario user = new Usuario(
            usuario.getIdUsuario(),
            usuario.getNombre(),
            usuario.getAppaterno(),
            usuario.getApmaterno(),
            usuario.getFechaNacimiento(),
            usuario.getPasswordUsuario(),
            usuario.getRut(),
            usuario.getCorreo()
            );
            return user;
        }
        return null;
    }catch (Exception e){
        return null;
    }
}
    public boolean borrarUsuario(Integer id) {
    if (usuarioRepository.existsById(id)) {
        usuarioRepository.deleteById(id);
        return true;
    }
    return false;
}

public boolean actualizarUsuario(Integer id, Usuario usuarioActualizado) {
    Optional<UsuarioEntity> usuarioExistente = usuarioRepository.findById(id);
    if (usuarioExistente.isPresent()) {
        UsuarioEntity usuario = usuarioExistente.get();
        usuario.setPasswordUsuario(usuarioActualizado.getPasswordUsuario());
        usuario.setRut(usuarioActualizado.getRut());
        usuario.setNombre(usuarioActualizado.getNombre());
        usuario.setAppaterno(usuarioActualizado.getAppaterno());
        usuario.setApmaterno(usuarioActualizado.getApmaterno());
        usuario.setFechaNacimiento(usuarioActualizado.getFechaNacimiento());
        usuario.setCorreo(usuarioActualizado.getCorreo());
        usuarioRepository.save(usuario);
        return true;
    }
    return false;
}


public UsuarioDto obtenerUsuarioDto(Integer idUsuario){
    try{
        UsuarioEntity usuario = usuarioRepository.findByIdUsuario(idUsuario);
        UsuarioDto nuevoUsuario = new UsuarioDto(
            usuario.getNombre(),
            usuario.getAppaterno(),
            usuario.getApmaterno(),
            usuario.getCorreo()
        );
        return nuevoUsuario;
    }catch(Exception e){
        return null;
    }
}

public ResponseEntity<UsuarioDto> obtenerUserDto(@PathVariable String correo){
    Boolean estado = usuarioRepository.existsByCorreo(correo);
    if(estado){
        UsuarioEntity nuevoUsuario = usuarioRepository.findByCorreo(correo);
        UsuarioDto usuarioResponse = new UsuarioDto(
            nuevoUsuario.getNombre(),
            nuevoUsuario.getAppaterno(),
            nuevoUsuario.getApmaterno(),
            nuevoUsuario.getCorreo()
        );
        return ResponseEntity.ok(usuarioResponse);
    }
    return ResponseEntity.notFound().build();
}

}
