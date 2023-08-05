package com.jbolivar.autofinanzas.models;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;

@Data
public class Transaccion {
    @Id
    private Integer id;
    private Integer idCuenta;
    private Integer idCategoria;
    private LocalDate fecha;
    private String descripcion;
    private Double monto;
}
