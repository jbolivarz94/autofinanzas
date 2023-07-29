package com.jbolivar.autofinanzas.models;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class Cuenta {
    @Id
    private Integer id;
    private Integer idUsuario;
    private String numero;
    private String tipo;
    private Double montoInicial;
}
