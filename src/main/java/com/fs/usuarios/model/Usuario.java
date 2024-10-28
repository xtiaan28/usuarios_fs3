package com.fs.usuarios.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "usuarios")


public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_usuario")
    private Long idUsuario;

    @Column(name = "nombre")
    private String nombre;
    
    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    public Usuario(){}

    public Usuario(Long idUsuario, String nombre, String email, String password){
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.email = email;
        this.password = password;
    }

    public Long getIdUsuario(){
        return idUsuario;
    }

    public String getNombre(){
        return nombre;
    }

    public String getEmail(){
        return email;
    }

    public String getPassword(){
        return password;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setPassword(String password) {
        this.password = password;
    }

}
