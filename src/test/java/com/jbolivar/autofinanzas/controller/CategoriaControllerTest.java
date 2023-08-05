package com.jbolivar.autofinanzas.controller;

import com.jbolivar.autofinanzas.models.Categoria;
import com.jbolivar.autofinanzas.services.CategoriaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CategoriaControllerTest {

    @Mock
    private CategoriaService categoriaService;

    @InjectMocks
    private CategoriaController categoriaController;

    @BeforeEach
    void setUp() { MockitoAnnotations.openMocks(this); }

    @Test
    void getCategoria() {
        Categoria categoria = new Categoria(1,"Compras","Compras");
        Categoria categoria2 = new Categoria(2,"Comida y Bebida","Gastos generados por comidas y bebidas");
        Flux<Categoria> expected = Flux.just(categoria, categoria2);
        when(categoriaService.findAll()).thenReturn(expected);
        Flux<Categoria> actual = categoriaService.findAll();
        actual.subscribe();
        assertEquals(expected, actual);
    }

    @Test
    void getCategoriaByNombre() {
    }

    @Test
    void createCategoria() {
        Categoria expected = new Categoria(16,"Comida y Bebida","Gastos generados por comidas y bebidas");
        when(categoriaService.save(any(Categoria.class))).thenReturn(Mono.just(expected));
        Mono<Categoria> result = categoriaController.createCategoria(expected);
        StepVerifier.create(result)
                .expectNext(expected)
                .verifyComplete();
    }

    @Test
    void updateCategoria() {
    }

    @Test
    void deleteCategoria() {
        int id = 3;
        Categoria expected = new Categoria(id,"Vivienda","Pagos de vivienda");
        when(categoriaService.deleteById(any())).thenReturn(Mono.just(expected));
        Mono<Categoria> result = categoriaController.deleteCategoria(id);
        assertEquals(expected, result.block());
    }
}