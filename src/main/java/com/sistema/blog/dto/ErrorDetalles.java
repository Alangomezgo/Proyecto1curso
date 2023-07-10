package com.sistema.blog.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

public class ErrorDetalles {
    
    @Getter @Setter
    private Date marcaDeTiempo;

    @Getter @Setter
    private String mensaje;

    @Getter @Setter
    private String detalles;

    public ErrorDetalles(Date marcaDeTiempo, String mensaje, String detalles) {
        this.marcaDeTiempo = marcaDeTiempo;
        this.mensaje = mensaje;
        this.detalles = detalles;
    }
}
