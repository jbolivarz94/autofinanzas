package com.jbolivar.autofinanzas.controller;

import com.jbolivar.autofinanzas.models.Transaccion;
import com.jbolivar.autofinanzas.repository.TransaccionRepository;
import org.springframework.web.bind.annotation.*;
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
    @GetMapping("/")
    public Flux<Transaccion> getTransaccion(){
        return transaccionRepository.findAll();
    }
    @GetMapping("/{id}")
    public Mono<Transaccion> getTransaccionById(@PathVariable Integer id){
        return  transaccionRepository.findById(id);
    }
    @GetMapping("/{descripcion}/{fecha}")
    public Flux<Transaccion> getTransaccionByDescripcionByFecha(@PathVariable String descripcion, @PathVariable Date fecha){
        return transaccionRepository.findByDescripcionContainingAndFecha(descripcion, fecha);
    }
    @PostMapping("/")
    public Mono<Transaccion> createTransaccion(@RequestBody Transaccion transaccion){
        return transaccionRepository.save(transaccion);
    }
    @DeleteMapping("/{id}")
    public Mono<Void> deleteTransaccion(@PathVariable Integer id){
        return transaccionRepository.deleteById(id);
    }

}