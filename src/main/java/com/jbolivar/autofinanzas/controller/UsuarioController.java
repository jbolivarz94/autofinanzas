package com.jbolivar.autofinanzas.controller;

import com.jbolivar.autofinanzas.models.Usuario;
import com.jbolivar.autofinanzas.repository.UsuarioRepository;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    UsuarioRepository usuarioRepository;

    public UsuarioController(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @GetMapping("/")
    public Flux<Usuario> getUsuarios(){
        return usuarioRepository.findAll();
    }

    @GetMapping("/{nombre}")
    public Flux<Usuario> getUsuarioByNombre(@PathVariable String nombre){
        return usuarioRepository.findByNombreContaining(nombre);
    }

    @PostMapping("/")
    public Mono<Usuario> createUsuario(@RequestBody Usuario usuario){
        return usuarioRepository.save(usuario);
    }
}
