package com.jbolivar.autofinanzas.models;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class Usuario {
    @Id
    private Integer id;
    private String identificacion;
    private String nombre;
    private String apellido;
    private String email;
    private String telefono;
}
