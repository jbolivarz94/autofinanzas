package com.jbolivar.autofinanzas.controller;

import com.jbolivar.autofinanzas.models.Cuenta;
import com.jbolivar.autofinanzas.repository.CuentaRepository;
import com.jbolivar.autofinanzas.services.CuentaService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/cuenta")
public class CuentaController {
    CuentaRepository cuentaRepository;
    CuentaService cuentaService;

    public CuentaController(CuentaService cuentaService) {
        this.cuentaService = cuentaService;
    }

    @GetMapping("/")
    public Flux<Cuenta> getCuentas(){
        return cuentaService.findAll();
    }

    @GetMapping("/numero/{numero}")
    public Mono<Cuenta> getCuentaById(@PathVariable String numero){
        return cuentaService.findByNumero(numero);
    }

    @GetMapping("/id/{idUsuario}")
    public Flux<Cuenta> getCuentaByIdUsuario(@PathVariable Integer idUsuario){
        return cuentaService.findByIdUsuario(idUsuario);
    }

    @GetMapping("/tipo/{tipo}")
    public Flux<Cuenta> getCuentaByTipo(@PathVariable String tipo){
        return cuentaService.findByTipo(tipo);
    }

    @PostMapping("/")
    public Mono<Cuenta> createCuenta(@RequestBody Cuenta cuenta){
        return cuentaService.save(cuenta);
    }

    @PutMapping("/{id}")
    public Mono<Cuenta> updateCuenta(@PathVariable Integer id, @RequestBody Cuenta cuenta){
        return cuentaService.update( id, cuenta);
    }
}
