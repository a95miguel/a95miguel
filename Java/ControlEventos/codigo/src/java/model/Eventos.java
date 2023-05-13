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
public class Eventos implements Serializable {  
    private int id_evento;
    private String nom_eve;
    private String fecha;
    private String ubicacion;
    
    public Eventos(){
        this.id_evento=0;
        this.nom_eve="";
        this.fecha="";
        this.ubicacion="";
    }
    
     public Eventos(int id_evento, String nom_eve, String fecha, String ubicacion) {
        this.id_evento = id_evento;
        this.nom_eve = nom_eve;
        this.fecha = fecha;
        this.ubicacion = ubicacion;
    }
     
      public int getId_evento() {
        return id_evento;
    }

    public void setId_evento(int id_evento) {
        this.id_evento = id_evento;
    }

    public String getNom_eve() {
        return nom_eve;
    }

    public void setNom_eve(String nom_eve) {
        this.nom_eve = nom_eve;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }    
}
