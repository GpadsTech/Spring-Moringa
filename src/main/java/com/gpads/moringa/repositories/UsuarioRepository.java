package com.gpads.moringa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gpads.moringa.entities.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    
}
