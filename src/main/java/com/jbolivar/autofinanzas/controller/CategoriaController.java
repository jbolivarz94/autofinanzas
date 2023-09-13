package com.jbolivar.autofinanzas.controller;

import com.jbolivar.autofinanzas.models.Categoria;
import com.jbolivar.autofinanzas.services.CategoriaService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {
    CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @GetMapping("/")
    @PreAuthorize(value = "hasRole('USER,ADMIN')")
    public Flux<Categoria> getCategoria(){
        return categoriaService.findAll();
    }

    @GetMapping("/{nombre}")
    @PreAuthorize(value = "hasRole('USER,ROLE_ADMIN')")
    public Flux<Categoria> getCategoriaByNombre(@PathVariable String nombre){
        return categoriaService.findByNombre(nombre);
    }

    @PostMapping("/")
    @PreAuthorize("hasRole('ADMIN')")
    public Mono<Categoria> createCategoria(@RequestBody Categoria categoria){
        return categoriaService.save(categoria);
    }

    @PutMapping("/")
    @PreAuthorize("hasRole('ADMIN')")
    public Mono<Categoria> updateCategoria(@RequestBody Categoria categoria){
        return categoriaService.save(categoria);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Mono<Categoria> deleteCategoria(@PathVariable Integer id){
        return categoriaService.deleteById(id);
    }
}
