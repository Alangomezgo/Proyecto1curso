package com.sistema.blog.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class ComentarioDTO {
    
    @Getter @Setter
    private long id;

    @NotEmpty (message = "El nombre no debe estar vacio o nulo")
    @Getter @Setter
    private String nombre;

    @NotEmpty (message = "El email no debe estar vacio o nulo")
    @Email
    @Getter @Setter
    private String email;

    @NotEmpty 
    @Size(min = 10, message = "El cuerpo del comentario debe tener minimo 10 caracteres")
    @Getter @Setter
    private String cuerpo;


    public ComentarioDTO() {
    }
}
