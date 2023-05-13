/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;

/**
 *
 * @author Miguel Medel Lozada
 */
public class Perfiles implements Serializable {

 private int id_usuario;
 private String nombre;
 private String apellido;
 private String contrasena;
 private String perfil_usuario;
 
 public Perfiles(){
     this.id_usuario = 0;
     this.nombre = "";
     this.apellido = "";
     this.contrasena = "";
     this.perfil_usuario = "";
 }
 
 public Perfiles(int id_usuario, String nombre, String apellido, String contrasena, String perfil_usuario) {
        this.id_usuario = id_usuario;
        this.nombre = nombre;
        this.apellido = apellido;
        this.contrasena = contrasena;
        this.perfil_usuario = perfil_usuario;
 }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getPerfil_usuario() {
        return perfil_usuario;
    }

    public void setPerfil_usuario(String perfil_usuario) {
        this.perfil_usuario = perfil_usuario;
    }
 
}
