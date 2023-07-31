package com.jbolivar.autofinanzas.controller;

import com.jbolivar.autofinanzas.models.Categoria;
import com.jbolivar.autofinanzas.services.CategoriaService;
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
    public Flux<Categoria> getCategoria(){
        return categoriaService.findAll();
    }

    @GetMapping("/{nombre}")
    public Flux<Categoria> getCategoriaByNombre(@PathVariable String nombre){
        return categoriaService.findByNombre(nombre);
    }

    @PostMapping("/")
    public Mono<Categoria> createCategoria(@RequestBody Categoria categoria){
        return categoriaService.save(categoria);
    }

    @PutMapping("/")
    public Mono<Categoria> updateCategoria(@RequestBody Categoria categoria){
        return categoriaService.save(categoria);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deleteCategoria(@PathVariable Integer id){
        return categoriaService.deleteById(id);
    }
}
