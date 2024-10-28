package com.fs.usuarios.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fs.usuarios.model.Usuario;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository <Usuario, Long> {
  Optional<Usuario> findByEmail(String email);
  Optional<Usuario> findByEmailAndPassword(String email, String password);
}