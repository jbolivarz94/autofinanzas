package com.jbolivar.autofinanzas.models;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class Categoria {
    @Id
    private Integer id;
    private String nombre;
    private String descripcion;
}
