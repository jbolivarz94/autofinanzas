package com.jbolivar.autofinanzas.controller;

import com.jbolivar.autofinanzas.models.Cuenta;
import com.jbolivar.autofinanzas.repository.CuentaRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/cuenta")
public class CuentaController {
    CuentaRepository cuentaRepository;

    public CuentaController(CuentaRepository cuentaRepository) {
        this.cuentaRepository = cuentaRepository;
    }

    @GetMapping("/")
    public Flux<Cuenta> getCuentas(){
        return cuentaRepository.findAll();
    }
}
