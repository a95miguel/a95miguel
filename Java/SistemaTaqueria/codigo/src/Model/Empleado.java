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
public class Empleado {
    private String idEmpleado;
    private String  nomEmpleado;
    private String apeEmpleado;
    private String telefono;
    
    public Empleado(){
        
    }

    public Empleado(String idEmpleado, String nomEmpleado, String apeEmpleado, String telefono) {
        this.idEmpleado = idEmpleado;
        this.nomEmpleado = nomEmpleado;
        this.apeEmpleado = apeEmpleado;
        this.telefono = telefono;
    }

    public String getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(String idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public String getNomEmpleado() {
        return nomEmpleado;
    }

    public void setNomEmpleado(String nomEmpleado) {
        this.nomEmpleado = nomEmpleado;
    }

    public String getApeEmpleado() {
        return apeEmpleado;
    }

    public void setApeEmpleado(String apeEmpleado) {
        this.apeEmpleado = apeEmpleado;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    
}
