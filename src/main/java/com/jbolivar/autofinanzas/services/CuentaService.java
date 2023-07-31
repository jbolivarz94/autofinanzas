package com.jbolivar.autofinanzas.services;

import com.jbolivar.autofinanzas.models.Cuenta;
import com.jbolivar.autofinanzas.repository.CuentaRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CuentaService {
    private final CuentaRepository repository;

    public CuentaService(CuentaRepository repository) {
        this.repository = repository;
    }

    public Flux<Cuenta> findAll(){ return repository.findAll(); }

    public Mono<Cuenta> findByNumero(String numero){
        return repository.findByNumeroContaining(numero);
    }

    public Flux<Cuenta> findByIdUsuario(Integer id){
        return repository.findByIdUsuario(id);
    }

    public Flux<Cuenta> findByTipo(String tipo){
        return repository.findByTipoContaining(tipo);
    }

    public Mono<Cuenta> save(Cuenta cuenta) {
        if (validarCrearActualizarCuenta(cuenta)) {
            return validarCuentaExistente(cuenta)
                    .flatMap(cuentaExistente -> {
                        if (!cuentaExistente) {
                            return repository.save(cuenta);
                        } else {
                            return Mono.empty();
                        }
                    });
        }
        return Mono.empty();
    }


    private boolean validarCrearActualizarCuenta(Cuenta cuenta){
        return !cuenta.getNumero().isEmpty() && (cuenta.getIdUsuario() != 0);
    }

    private Mono<Boolean> validarCuentaExistente(Cuenta cuenta){
        return repository.existsByNumero(cuenta.getNumero());
    }
}
