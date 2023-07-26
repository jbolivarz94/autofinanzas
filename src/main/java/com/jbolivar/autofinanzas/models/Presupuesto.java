package com.jbolivar.autofinanzas.models;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class Presupuesto {
    @Id
    private Integer id;
    private String descripcion;
    private Boolean activo;
}
