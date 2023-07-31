package com.jbolivar.autofinanzas.services;

import com.jbolivar.autofinanzas.models.Categoria;
import com.jbolivar.autofinanzas.repository.CategoriaRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CategoriaService {
    private final CategoriaRepository repository;

    public CategoriaService(CategoriaRepository repository) {
        this.repository = repository;
    }

    public Flux<Categoria> findAll(){ return repository.findAll(); }

    public Flux<Categoria> findByNombre(String nombre){
        return repository.findByNombreContaining(nombre);
    }

    public Mono<Categoria> save(Categoria categoria){
        return repository.save(categoria);
    }

    public Mono<Void> deleteById(Integer id){
        return repository.findById(id)
                .flatMap(categoria -> repository.deleteById(categoria.getId()).then());
    }
}
