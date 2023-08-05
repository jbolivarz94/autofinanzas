package com.jbolivar.autofinanzas.controller;

import com.jbolivar.autofinanzas.models.Usuario;
import com.jbolivar.autofinanzas.services.UsuarioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UsuarioControllerTest {

    @Mock
    private UsuarioService usuarioService;
    @InjectMocks
    private UsuarioController usuarioController;
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getUsuarios() {
        Usuario usuario = new Usuario(1, "1123456709", "juan", "perez", "juan.perez@correo.com", "3043456789");
        Usuario usuario2 = new Usuario(2, "1123459087", "andrea", "gomez", "andrea.gomez@correo.com", "3043453465");
        Flux<Usuario> expectedUsuarios = Flux.just(usuario, usuario2);
        when(usuarioService.findAll()).thenReturn(expectedUsuarios);
        Flux<Usuario> actual = usuarioService.findAll();
        actual.subscribe();
        assertEquals(expectedUsuarios, actual);
    }

    @Test
    void getUsuarioByNombre() {
        String nombre = "juan";
        Usuario usuarioExpected = new Usuario(1, "1123456709", "juan", "perez", "juan.perez@correo.com", "3043456789");

    }

    @Test
    void getUsuarioById() {
        int id = 4;
        Usuario usuarioExpected = new Usuario(id, "1123456709", "juan", "perez", "juan.perez@correo.com", "3043456789");
        when(usuarioService.findById(any())).thenReturn(Mono.just(usuarioExpected));
        Mono<Usuario> actual = usuarioController.getUsuarioById(id);
        assertEquals(usuarioExpected, actual.block());
    }

    @Test
    void createUsuario() {
        Usuario usuarioExpected = new Usuario(5, "31264855", "Pascale", "Little", "Pascale.Little@correo.com", "4389606");
        when(usuarioService.save(any(Usuario.class))).thenReturn(Mono.just(usuarioExpected));
        Mono<Usuario> result = usuarioController.createUsuario(usuarioExpected);
        StepVerifier.create(result)
                .expectNext(usuarioExpected)
                .verifyComplete();
    }

    @Test
    void updateUsuario() {
    }

    @Test
    void deleteUsuarioById() throws Exception{
        int id = 3;
        Usuario usuarioToDelete = new Usuario(id, "31264855", "Pascale", "Little", "Pascale.Little@correo.com", "4389606");
        when(usuarioService.deleteById(any())).thenReturn(Mono.just(usuarioToDelete));
        Mono<Usuario> result = usuarioController.deleteUsuarioById(id);
        assertEquals(usuarioToDelete, result.block());
    }

    @Test
    void deleteAllUsuario() {
        when(usuarioService.deleteAll()).thenReturn(Mono.empty());
        Mono<Void> result = usuarioController.deleteAllUsuario();
        result.subscribe();
        assertEquals(Mono.empty(), result);
    }
}