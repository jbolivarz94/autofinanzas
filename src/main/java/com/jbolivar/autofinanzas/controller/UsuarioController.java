package com.jbolivar.autofinanzas.controller;

import com.jbolivar.autofinanzas.models.Usuario;
import com.jbolivar.autofinanzas.services.UsuarioService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }
    @GetMapping("/")
    public Flux<Usuario> getUsuarios(){
        return usuarioService.findAll();
    }

    @GetMapping("/nombre/{nombre}")
    public Flux<Usuario> getUsuarioByNombre(@PathVariable String nombre){
        return usuarioService.findByNombre(nombre);
    }

    @GetMapping("/id/{id}")
    public Mono<Usuario> getUsuarioById(@PathVariable Integer id){
        return usuarioService.findById(id);
    }

    @PostMapping("/")
    public Mono<Usuario> createUsuario(@RequestBody Usuario usuario){
        return usuarioService.save(usuario);
    }

    @PutMapping("/")
    public Mono<Usuario> updateUsuario(@RequestBody Usuario usuario){ return usuarioService.save(usuario);}

    @DeleteMapping("/{id}")
    public Mono<Usuario> deleteUsuarioById(@PathVariable Integer id){
        return usuarioService.deleteById(id);
    }

    @DeleteMapping("/all")
    public Mono<Void> deleteAllUsuario(){
        return usuarioService.deleteAll();
    }
}
