package com.edutech.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.edutech.demo.models.Usuario;
import com.edutech.demo.models.dto.UsuarioDto;
import com.edutech.demo.service.UsuarioService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<List<Usuario>> listarUsuarios() {
        return ResponseEntity.ok(usuarioService.obtenerUsuarios());
    }

    @GetMapping("/correo/{correo}")
    public ResponseEntity<Usuario> obtenerUsuarioPorCorreo(@PathVariable String correo) {
        Usuario usuario = usuarioService.obtenerUsuario(correo);
        if (usuario != null) {
            return ResponseEntity.ok(usuario);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/dto/{id}")
    public ResponseEntity<UsuarioDto> obtenerUsuarioDtoPorId(@PathVariable Integer id) {
        UsuarioDto dto = usuarioService.obtenerUsuarioDto(id);
        if (dto != null) {
            return ResponseEntity.ok(dto);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/dto/correo/{correo}")
    public ResponseEntity<UsuarioDto> obtenerUsuarioDtoPorCorreo(@PathVariable String correo) {
        return usuarioService.obtenerUserDto(correo);
    }

    @PostMapping
    @Operation(summary = "Crear un nuevo usuario")
    public ResponseEntity<String> crearUsuario(@RequestBody Usuario usuario) {
        String resultado = usuarioService.crearUsuario(usuario);
        if ("Usuario creado correctamente".equals(resultado)) {
            return ResponseEntity.ok(resultado);
        } else if ("El correo ya existe".equals(resultado)) {
            return ResponseEntity.badRequest().body(resultado);
        } else {
            return ResponseEntity.status(500).body(resultado);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> actualizarUsuario(@PathVariable Integer id, @RequestBody Usuario usuarioActualizado) {
        String resultado = usuarioService.actualizarUsuario(id, usuarioActualizado);
        if ("Usuario actualizado correctamente".equals(resultado)) {
            return ResponseEntity.ok(resultado);
        } else if ("Usuario no encontrado".equals(resultado)) {
            return ResponseEntity.status(404).body(resultado);
        } else {
            return ResponseEntity.status(500).body(resultado);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> borrarUsuario(@PathVariable Integer id) {
        String resultado = usuarioService.borrarUsuario(id);
        if ("Usuario Borrado correctamente".equals(resultado)) {
            return ResponseEntity.ok(resultado);
        } else if ("Usuario No existe".equals(resultado)) {
            return ResponseEntity.status(404).body(resultado);
        } else {
            return ResponseEntity.status(500).body(resultado);
        }
    }
}
