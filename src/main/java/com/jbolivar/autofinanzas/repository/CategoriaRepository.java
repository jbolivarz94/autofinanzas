package com.jbolivar.autofinanzas.repository;

import com.jbolivar.autofinanzas.models.Categoria;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface CategoriaRepository extends R2dbcRepository<Categoria,Integer> {
    Flux<Categoria> findByNombreContaining(String nombre);
}
