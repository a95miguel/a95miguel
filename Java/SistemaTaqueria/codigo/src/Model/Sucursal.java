/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author Miguel Medel Lozada
 */
public class Sucursal {
    private int id_sucursal;
    private String nomSucursal;
    private String domicilio;
    private String telefono;
    public Sucursal(){
        
    }
    
    public Sucursal(int id_sucursal, String nomSucursal, String domicilio, String telefono) {
        this.id_sucursal = id_sucursal;
        this.nomSucursal = nomSucursal;
        this.domicilio = domicilio;
        this.telefono = telefono;        
    }

    public int getId_sucursal() {
        return id_sucursal;
    }

    public void setId_sucursal(int id_sucursal) {
        this.id_sucursal = id_sucursal;
    }

    public String getNomSucursal() {
        return nomSucursal;
    }

    public void setNomSucursal(String nomSucursal) {
        this.nomSucursal = nomSucursal;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
   
    
}
