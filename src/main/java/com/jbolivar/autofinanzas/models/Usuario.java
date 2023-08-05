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

    public Usuario() {
    }

    public Usuario(Integer id, String identificacion, String nombre, String apellido, String email, String telefono) {
        this.id = id;
        this.identificacion = identificacion;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.telefono = telefono;
    }
}
