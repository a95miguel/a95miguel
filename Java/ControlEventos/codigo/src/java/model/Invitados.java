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
public class Invitados implements Serializable{
    private int id_invitado;
    private String nom_invi;
    private String apellido;
    private String telefono;
    private String direccion ;
    private String sexo;
    
    public Invitados(){
        this.id_invitado=0;
        this.nom_invi="";
        this.apellido="";
        this.telefono="";
        this.direccion="";
        this.sexo="";
    }

    public Invitados(int id_invitado, String nom_invi, String apellido, String telefono, String direccion, String sexo) {
        this.id_invitado = id_invitado;
        this.nom_invi = nom_invi;
        this.apellido = apellido;
        this.telefono = telefono;
        this.direccion = direccion;
        this.sexo = sexo;
    }

    public int getId_invitado() {
        return id_invitado;
    }

    public void setId_invitado(int id_invitado) {
        this.id_invitado = id_invitado;
    }

    public String getNom_invi() {
        return nom_invi;
    }

    public void setNom_invi(String nom_invi) {
        this.nom_invi = nom_invi;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }
    
    
}
