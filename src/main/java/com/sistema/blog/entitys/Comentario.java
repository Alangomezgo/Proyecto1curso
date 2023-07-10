package com.sistema.blog.entitys;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "comentarios")
public class Comentario {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private long id;

    @Getter @Setter
    private String nombre;

    @Getter @Setter
    private String email;

    @Getter @Setter
    private String cuerpo;

    @Getter @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn (name = "publicacion_id", nullable = false)
    private Publicacion publicacion;


    public Comentario() {
    }
}
