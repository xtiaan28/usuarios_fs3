package com.fs.usuarios.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fs.usuarios.model.Usuario;
import com.fs.usuarios.repository.UsuarioRepository;


@Service
public class UsuarioServiceImpl implements UsuarioService{

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public List<Usuario> getAllUsers() {
        return usuarioRepository.findAll();
    }

    @Override
    public Optional<Usuario> getUsuarioById(Long id) {
        return usuarioRepository.findById(id);
    }
    @Override
    public Usuario crearUsuario(Usuario usuario) {

        return usuarioRepository.save(usuario);
    }

    @Override
    public Usuario modificarUsuario(Long id, Usuario usuario) {
        return usuarioRepository
        .findById(id)
        .map(user -> {
          user.setNombre(usuario.getNombre());
          user.setPassword(usuario.getPassword());
          return usuarioRepository.save(user);
        })
        .orElseThrow(() -> new RuntimeException("No se encontró el usuario " + id));
    }
    public void borrarUsuario(Long id){
        if (usuarioRepository.existsById(id)) {
            usuarioRepository.deleteById(id);
          } else {
            throw new RuntimeException("No se encontró el usuario: " + id);
          }
    }
    @Override
    public Optional<Usuario> autenticarUsuario(String email, String password) {
        return usuarioRepository.findByEmailAndPassword(email, password);
    }
    
}
