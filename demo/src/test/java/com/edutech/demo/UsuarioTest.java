package com.edutech.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.edutech.demo.models.Usuario;
import com.edutech.demo.models.entity.UsuarioEntity;
import com.edutech.demo.repository.UsuarioRepository;
import com.edutech.demo.service.UsuarioService;

public class UsuarioTest {
    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private UsuarioService usuarioService;
    
    private Usuario usuario;
    private UsuarioEntity usuarioEntity;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.openMocks(this);
        usuario = new Usuario(1, "pass", "1234", "juan", "perez", "pereira", "19/10/1988", "juan@gmail.com");
        usuarioEntity = new UsuarioEntity();
        usuarioEntity.setIdUsuario(1);
        usuarioEntity.setPasswordUsuario("pass");
        usuarioEntity.setRut("1234");
        usuarioEntity.setNombre("juan");
        usuarioEntity.setAppaterno("perez");
        usuarioEntity.setApmaterno("pereira");
        usuarioEntity.setFechaNacimiento("19/10/1988");
        usuarioEntity.setCorreo("juan@gmail.com");
    }

    @Test
    public void testAgregarUsuario_nuevo(){
        when(usuarioRepository.existsByCorreo(usuario.getCorreo())).thenReturn(false);
        when(usuarioRepository.save(any(UsuarioEntity.class))).thenReturn(usuarioEntity);

        String resultado = usuarioService.crearUsuario(usuario);
        assertEquals("Usuario creado correctamente", resultado);
    }

    @Test
    public void testAgregarUsuario_existe(){
        when(usuarioRepository.existsByCorreo(usuario.getCorreo())).thenReturn(true);

        String resultado = usuarioService.crearUsuario(usuario);
        assertEquals("El correo ya existe", resultado);
    }

    @Test
    public void testTraerUsuario(){
        when(usuarioRepository.findByCorreo("juan@gmail.com")).thenReturn(usuarioEntity);
        Usuario resultado = usuarioService.obtenerUsuario("juan@gmail.com");
        assertNotNull(resultado);
        assertEquals("juan", resultado.getNombre());
    }

    @Test
    public void testTraerUsuarioNoexiste(){
        when(usuarioRepository.findByCorreo("noexiste@gmail.com")).thenReturn(null);
        Usuario resultado = usuarioService.obtenerUsuario("noexiste@gmail.com");
        assertNull(resultado);
    }

    @Test
    public void actualizarUsuario_existe(){
        when(usuarioRepository.findById(1)).thenReturn(Optional.of(usuarioEntity));
        when(usuarioRepository.save(any(UsuarioEntity.class))).thenReturn(usuarioEntity);

        Usuario nuevo = new Usuario(1, "pass", "1234", "juan", "perez", "pereira", "19/10/1988", "juan@gmail.com");
        String resultado = usuarioService.actualizarUsuario(1,nuevo);

        assertEquals("Usuario actualizado correctamente", resultado);
    }

    @Test
    public void actualizarUsuario_noExiste() {
        when(usuarioRepository.findById(2)).thenReturn(Optional.empty());

        Usuario nuevo = new Usuario(2, "pass", "1234", "maria", "lopez", "santiago", "01/01/1990", "maria@gmail.com");
        String resultado = usuarioService.actualizarUsuario(2, nuevo);

        assertEquals("Usuario no encontrado", resultado);
    }

    @Test
    public void testBorrarUsuario(){
        when(usuarioRepository.existsById(1)).thenReturn(true);
        doNothing().when(usuarioRepository).deleteById(1);
        String resultado = usuarioService.borrarUsuario(1);

        assertEquals("Usuario Borrado correctamente", resultado);
    }

    @Test
    public void testBorrarUsuario_Noexiste(){
        when(usuarioRepository.existsById(78979)).thenReturn(false);
        String resultado = usuarioService.borrarUsuario(78979);
        assertEquals("Usuario No existe", resultado);

    }


}
             