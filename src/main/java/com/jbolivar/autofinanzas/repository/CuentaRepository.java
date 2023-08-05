package com.jbolivar.autofinanzas.repository;

import com.jbolivar.autofinanzas.models.Cuenta;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface CuentaRepository extends R2dbcRepository<Cuenta,Integer> {
    Mono<Cuenta> findByNumeroContaining(String numero);
    Flux<Cuenta> findByTipoContaining(String tipo);
    Flux<Cuenta> findByIdUsuario(Integer id);
    Mono<Boolean> existsByNumero(String numero);
    Mono<Void> deleteByNumero(String numero);
}
