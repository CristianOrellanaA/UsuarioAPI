package com.edutech.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.edutech.demo.models.Usuario;
import com.edutech.demo.models.dto.UsuarioDto;
import com.edutech.demo.repository.UsuarioRepository;
import com.edutech.demo.service.UsuarioService;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
public class UsuarioController {

    private final UsuarioRepository usuarioRepository;
    @Autowired
    private UsuarioService usuarioService;
    UsuarioService accionUsuarioService = new UsuarioService();

    UsuarioController(UsuarioRepository usuarioRepository){
        this.usuarioRepository = usuarioRepository;
    }
    @GetMapping("/usuarios")
    public List<Usuario> traerUsuarios() {
        return accionUsuarioService.obtenerUsuarios();
    }
        @GetMapping("/usuarios/{correo}")
    public ResponseEntity<Usuario> traerUsuario(@PathVariable String correo){
        return ResponseEntity.ok(usuarioService.obtenerUsuario(correo));
    }
    @PostMapping("/crearUsuario")
    public ResponseEntity<String> obtenerUsuario(@RequestBody Usuario usuario){
       return ResponseEntity.ok(usuarioService.crearUsuario(usuario)); 
    }

    @Operation(summary = "Este endpoint permite agregar usuario")

    @GetMapping("/obtenerUsuario/{correo}")
    public ResponseEntity<Usuario> obtenerUsuario(@PathVariable String correo){
        Usuario usuario = usuarioService.obtenerUsuario(correo);
        if(usuario != null){
            return ResponseEntity.ok(usuario);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/borrarUsuariosDto/{id}")
    public ResponseEntity<String> borrarUsuario(@PathVariable Integer id){
        boolean borrado = usuarioService.borrarUsuario(id);
        if (borrado) {
            return ResponseEntity.ok("Usuario borrado correctamente.");
        } else {
        return ResponseEntity.notFound().build();
        }
    }

        @PutMapping("/actualizar/{idCurso}")
    public ResponseEntity<String> actualizarUsuario(@PathVariable Integer id, @RequestBody Usuario usuarioActualizado) {
        boolean actualizado = usuarioService.actualizarUsuario(id, usuarioActualizado);
        if (actualizado) {
            return ResponseEntity.ok("Usuario actualizado correctamente");
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/usuarioDto/{correo}")
    public ResponseEntity<UsuarioDto> obtenerUserDto(@PathVariable String correo) {
        return usuarioService.obtenerUserDto(correo);
    }
    
    @GetMapping("/obtenerUsuarioDto/{id}")
    public ResponseEntity<UsuarioDto> obtenerUsuarioDto(@PathVariable("id") Integer idUsuario) {
        UsuarioDto dto = usuarioService.obtenerUsuarioDto(idUsuario);
        if (dto != null) {
            return ResponseEntity.ok(dto);
        }
        return ResponseEntity.notFound().build();
    }
    

}
