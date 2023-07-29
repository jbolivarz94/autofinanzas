package com.jbolivar.autofinanzas.repository;

import com.jbolivar.autofinanzas.models.Cuenta;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CuentaRepository extends R2dbcRepository<Cuenta,Integer> {
    Mono<Cuenta> findByNumero(Integer numero);
    Flux<Cuenta> findByTipoContaining(String tipo);
}
