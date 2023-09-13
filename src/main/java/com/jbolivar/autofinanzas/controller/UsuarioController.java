package com.jbolivar.autofinanzas.controller;

import com.jbolivar.autofinanzas.models.Usuario;
import com.jbolivar.autofinanzas.services.UsuarioConsumerService;
import com.jbolivar.autofinanzas.services.UsuarioSQSService;
import com.jbolivar.autofinanzas.services.UsuarioService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    UsuarioService usuarioService;
    UsuarioConsumerService usuarioConsumerService;
    UsuarioSQSService usuarioSQSService;

    public UsuarioController(UsuarioService usuarioService, UsuarioConsumerService usuarioConsumerService, UsuarioSQSService usuarioSQSService) {
        this.usuarioService = usuarioService;
        this.usuarioConsumerService = usuarioConsumerService;
        this.usuarioSQSService = usuarioSQSService;
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

    @GetMapping("/topico-kafka/{topico}")
    public Mono<String> getUsuarioFromTopicoKafka(@PathVariable String topico){
        return  Mono.just(usuarioConsumerService.obtenerNombreUltimoUsuario(topico));
    }

    @PostMapping("/aws/createQueue")
    public Mono<String> postCreateQueue(@RequestBody Map<String, Object> requestBody){
        return Mono.just(usuarioSQSService.createQueue((String) requestBody.get("queueName")));
    }
}
