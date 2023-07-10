package com.sistema.blog.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class PublicacionRespuesta {

    @Getter @Setter
    private List<PublicacionDTO> contenido;

    @Getter @Setter
    private int numeroDePagina;

    @Getter @Setter
    private int medidaDePagina;

    @Getter @Setter
    private long totalElementos;

    @Getter @Setter
    private int totalPaginas;

    @Getter @Setter
    private boolean ultima;

    public PublicacionRespuesta() {
    }
}
