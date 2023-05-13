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
public class Factura {
    private String idFactura;
    private String Cliente_id;
    private String fecha;
    private String hora;

    public Factura(){
        
    }
    public Factura(String idFactura, String Cliente_id, String fecha, String hora) {
        this.idFactura = idFactura;
        this.Cliente_id = Cliente_id;
        this.fecha = fecha;
        this.hora = hora;
    }

    public String getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(String idFactura) {
        this.idFactura = idFactura;
    }

    public String getCliente_id() {
        return Cliente_id;
    }

    public void setCliente_id(String Cliente_id) {
        this.Cliente_id = Cliente_id;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }
    
}
