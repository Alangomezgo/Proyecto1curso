package com.sistema.blog.repository;

import com.sistema.blog.entitys.Rol;
import com.sistema.blog.entitys.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RolRepositorio extends JpaRepository<Rol, Long> {
    public Optional<Rol> findByNombre(String nombre);
}
