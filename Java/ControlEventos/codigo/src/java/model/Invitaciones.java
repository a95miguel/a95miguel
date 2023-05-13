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
public class Invitaciones implements Serializable{
    private int id_invitaciones;
    private int invitado;
    private int evento;
    private String lista;
    
    public Invitaciones(){
        this.id_invitaciones=0;
        this.invitado=0;
        this.evento=0;
        this.lista="";
    }

    public Invitaciones(int id_invitaciones, int invitado, int evento, String lista) {
        this.id_invitaciones = id_invitaciones;
        this.invitado = invitado;
        this.evento = evento;
        this.lista = lista;
    }

    public int getId_invitaciones() {
        return id_invitaciones;
    }

    public void setId_invitaciones(int id_invitaciones) {
        this.id_invitaciones = id_invitaciones;
    }

    public int getInvitado() {
        return invitado;
    }

    public void setInvitado(int invitado) {
        this.invitado = invitado;
    }

    public int getEvento() {
        return evento;
    }

    public void setEvento(int evento) {
        this.evento = evento;
    }

    public String getLista() {
        return lista;
    }

    public void setLista(String lista) {
        this.lista = lista;
    }
    
}
