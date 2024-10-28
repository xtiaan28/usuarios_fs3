package com.fs.usuarios.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fs.usuarios.model.Usuario;
import com.fs.usuarios.service.UsuarioService;

import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    private static final Logger log = LoggerFactory.getLogger(UsuarioController.class);

    @Autowired
    private UsuarioService usuarioService;


    @GetMapping
    public List<Usuario> getUsuarios(){
        log.info("GET/usuarios");
        log.info("Retornando todos los usuarios");
        return usuarioService.getAllUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity <Object> getUserById(@PathVariable Long id) {
        Optional<Usuario> user = usuarioService.getUsuarioById(id);
        if(user.isEmpty()){
            log.error("No se encontro usuario con ID {}", id);
            
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse("No se encontro usuario con ID " + id));
            //return ResponseEntity.notFound().build();
        }
        log.info("Usuario encontrado con exito");
        return ResponseEntity.ok(usuarioService.getUsuarioById(id));
    }

    @PostMapping
    public ResponseEntity<Object> createUser(@RequestBody Usuario usuario){
        Usuario createdUser = usuarioService.crearUsuario(usuario);
        if(createdUser == null){
            log.error("Error al crear usuario {}",usuario);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse("Error al crear usuario")); 
        }
        return ResponseEntity.ok(createdUser);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateUser(@PathVariable Long id, @RequestBody Usuario usuario) {
        Usuario updatedUser = usuarioService.modificarUsuario(id, usuario);
        if(updatedUser == null){
            log.error("Error al actualizar {}",usuario);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse("Error al actualizar el usuario id "+id)); 
        }
        return ResponseEntity.ok(updatedUser);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteVenta(@PathVariable Long id){
        Optional<Usuario> usuario = usuarioService.getUsuarioById(id);
        if (usuario.isEmpty()) {
            log.error("El usuario con ID {} no existe", id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se encontró usuario con ID " + id);
        }
    
        usuarioService.borrarUsuario(id);
        log.info("Usuario con ID {} eliminado correctamente", id);
        return ResponseEntity.ok("Usuario con ID " + id + " eliminado correctamente");
    }

    @PostMapping("/login")
    public ResponseEntity<Object> autenticarUsuario(@RequestBody Usuario usuario) {
        Optional<Usuario> usuarioAutenticado = usuarioService.autenticarUsuario(usuario.getEmail(), usuario.getPassword());
        
        if (usuarioAutenticado.isPresent()) {
            log.info("Usuario autenticado correctamente");
            return ResponseEntity.ok(usuarioAutenticado.get());
        } else {
            log.error("Error de autenticación para el usuario con email {}", usuario.getEmail());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ErrorResponse("Credenciales incorrectas"));
        }
    }

    static class ErrorResponse {
        private final String message;

        public ErrorResponse(String message){
            this.message = message;
        }

        public String getMessage(){
            return message;
        }
    }
    
}
