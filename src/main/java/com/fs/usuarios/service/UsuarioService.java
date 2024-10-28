package com.fs.usuarios.service;


import java.util.List;
import java.util.Optional;

import com.fs.usuarios.model.Usuario;

public interface UsuarioService {

    List<Usuario> getAllUsers();
    
    Optional<Usuario> getUsuarioById(Long id);

    Usuario crearUsuario(Usuario usuario);
    Usuario modificarUsuario(Long id, Usuario usuario);
    void borrarUsuario(Long id);
    Optional<Usuario> autenticarUsuario(String email, String password);
}
