package com.jbolivar.autofinanzas.controller;

import com.jbolivar.autofinanzas.models.Transaccion;
import com.jbolivar.autofinanzas.repository.TransaccionRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.sql.Date;

@RestController
@RequestMapping("/transaccion")
public class TransaccionController {
    TransaccionRepository transaccionRepository;
    public TransaccionController(TransaccionRepository transaccionRepository) {
        this.transaccionRepository = transaccionRepository;
    }
    @GetMapping("/{id}")
    public Mono<Transaccion> getTransaccionById(@PathVariable Integer id){
        return  transaccionRepository.findById(id);
    }
    @GetMapping("/{descripcion}/{fecha}")
    public Flux<Transaccion> getTransaccionByDescripcionByFecha(@PathVariable String descripcion, @PathVariable Date fecha){
        return transaccionRepository.findByDescripcionContainingAndFecha(descripcion, fecha);
    }
    @GetMapping("/")
    public Flux<Transaccion> getTransaccion(){
        return transaccionRepository.findAll();
    }
}