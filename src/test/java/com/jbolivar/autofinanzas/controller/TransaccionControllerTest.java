package com.jbolivar.autofinanzas.controller;

import com.jbolivar.autofinanzas.services.TransaccionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class TransaccionControllerTest {

    @Mock
    private TransaccionService transaccionService;

    @InjectMocks
    private TransaccionController transaccionController;

    @BeforeEach
    void setUp() { MockitoAnnotations.openMocks(this); }

    @Test
    void getTransaccion() {
    }

    @Test
    void getTransaccionById() {
    }

    @Test
    void getTransaccionByDescripcionByFecha() {
    }

    @Test
    void createTransaccion() {
    }

    @Test
    void deleteTransaccion() {
    }
}