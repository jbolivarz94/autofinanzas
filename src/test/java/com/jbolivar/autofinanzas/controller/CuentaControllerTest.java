package com.jbolivar.autofinanzas.controller;

import com.jbolivar.autofinanzas.models.Cuenta;
import com.jbolivar.autofinanzas.services.CuentaService;
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
class CuentaControllerTest {

    @Mock
    private CuentaService cuentaService;

    @InjectMocks
    private CuentaController cuentaController;

    @BeforeEach
    void setUp() { MockitoAnnotations.openMocks(this); }

    @Test
    void getCuentas() {
        Cuenta cuenta = new Cuenta(1, 1, "DO86573826631444853777675716","Inversion", 500000D);
        Cuenta cuenta2 = new Cuenta(2, 1, "FO8654721633621776","Cuenta de ahorros", 6500000D);
        Flux<Cuenta> expected = Flux.just(cuenta, cuenta2);
        when(cuentaService.findAll()).thenReturn(expected);
        Flux<Cuenta> actual = cuentaService.findAll();
        actual.subscribe();
        assertEquals(expected, actual);
    }

    @Test
    void getCuentaById() {
        String numero = "FO8654721633621776";
        Cuenta expected = new Cuenta(2, 1, "FO8654721633621776","Cuenta de ahorros", 6500000D);
        when(cuentaService.findByNumero(any())).thenReturn(Mono.just(expected));
        Mono<Cuenta> actual = cuentaController.getCuentaById(numero);
        assertEquals(expected, actual.block());
    }

    @Test
    void getCuentaByIdUsuario() {
        int id = 1;
        Cuenta cuenta = new Cuenta(1, 1, "DO86573826631444853777675716","Inversion", 500000D);
        Cuenta cuenta2 = new Cuenta(2, 1, "FO8654721633621776","Cuenta de ahorros", 6500000D);
        Flux<Cuenta> expected = Flux.just(cuenta, cuenta2);
        when(cuentaService.findByIdUsuario(id)).thenReturn(expected);
        Flux<Cuenta> actual = cuentaService.findByIdUsuario(id);
        actual.subscribe();
        assertEquals(expected, actual);
    }

    @Test
    void getCuentaByTipo() {
    }

    @Test
    void createCuenta() {
        Cuenta expected = new Cuenta(2, 1, "ME59874027878281835592","Tarjeta de credito", 8500000D);
        when(cuentaService.save(any(Cuenta.class))).thenReturn(Mono.just(expected));
        Mono<Cuenta> result = cuentaController.createCuenta(expected);
        StepVerifier.create(result)
                .expectNext(expected)
                .verifyComplete();
    }

    @Test
    void updateCuenta() {
        int id = 2;
        Cuenta expected = new Cuenta(2, 1, "ME59874027878281835592","Tarjeta de credito", 8000000D);
        when(cuentaService.update(any(Integer.class),any(Cuenta.class))).thenReturn(Mono.just(expected));
        Mono<Cuenta> result = cuentaController.updateCuenta(expected.getId(), expected);
        StepVerifier.create(result)
                .expectNext(expected)
                .verifyComplete();
    }
}