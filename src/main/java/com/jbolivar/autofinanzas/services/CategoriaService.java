package com.jbolivar.autofinanzas.services;

import com.jbolivar.autofinanzas.models.Categoria;
import com.jbolivar.autofinanzas.repository.CategoriaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CategoriaService {
    private final CategoriaRepository repository;
    private final Logger LOGGER = LoggerFactory.getLogger(CategoriaService.class);

    public CategoriaService(CategoriaRepository repository) {
        this.repository = repository;
    }

    public Flux<Categoria> findAll() {
        return repository.findAll();
    }

    public Flux<Categoria> findByNombre(String nombre) {
        return repository.findByNombreContaining(nombre).onErrorResume(throwable -> {
            LOGGER.error("Error al consultar la categoria con nombre:" + nombre, throwable);
            return Mono.empty();
        }).switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND,
                "Categoria con nombre = " + nombre + " no encontrado")));
    }

    public Mono<Categoria> save(Categoria categoria) {
        return repository.save(categoria).onErrorResume(throwable -> {
                    LOGGER.error("Error al crear la categoria: " + categoria.getNombre(), throwable);
                    return Mono.empty();
                })
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Categoria no creada").getMostSpecificCause()));
    }

    public Mono<Categoria> deleteById(Integer id) {
        return repository.findById(id).flatMap(categoria -> repository.deleteById(categoria.getId()).thenReturn(categoria))
                .onErrorResume(throwable -> {
                    LOGGER.error("Error al borrar la Categoria con Id: " + id, throwable);
                    return Mono.empty();
                })
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Categoria con Id=" + id + " no borrada").getMostSpecificCause()));
    }
}
