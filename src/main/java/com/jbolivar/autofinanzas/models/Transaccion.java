package com.jbolivar.autofinanzas.models;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class Transaccion {
    @Id
    private Integer id;
    private Integer idCuenta;
    private Integer idCategoria;
    private String fecha;
    private String descripcion;
    private Double monto;
}
