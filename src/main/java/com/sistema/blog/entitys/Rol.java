package com.sistema.blog.entitys;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
    
@Entity
@Table(name = "roles")
public class Rol {
    
    @Id
    @Getter @Setter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    
    @Getter @Setter
    @Column(length = 60)
    private String nombre;


    public Rol() {
    }
}
