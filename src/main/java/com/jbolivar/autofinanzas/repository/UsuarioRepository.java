package com.jbolivar.autofinanzas.repository;

import com.jbolivar.autofinanzas.models.Usuario;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UsuarioRepository extends R2dbcRepository<Usuario, Integer> {
    Mono<Usuario> findByIdentificacion(String identificacion);

    Flux<Usuario> findByNombreContaining(String nombre);
}
