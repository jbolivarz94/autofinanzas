package com.jbolivar.autofinanzas.models;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class Transaccion {
    @Id
    private Integer id;
    private Integer idPresupuesto;
    private Integer idCuenta;
    private String fecha;
    private String descripcion;
    private Double monto;
}
