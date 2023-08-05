package com.jbolivar.autofinanzas.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
public class Categoria {
    @Id
    private Integer id;
    private String nombre;
    private String descripcion;
}
