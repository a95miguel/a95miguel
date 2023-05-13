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
public class Caja {
    private String num_ticket;
    private String subtotal;
    private String iva;
    private String ventaTotal;
    private String efectivo;
    private String cambio;
    private String detalle_id;
    private String fecha;
    private String hora;
    private String nomCliente;
    public Caja(){
        
    }

    public Caja(String num_ticket, String subtotal, String iva, String ventaTotal, String efectivo, String cambio, String detalle_id) {
        this.num_ticket = num_ticket;
        this.subtotal = subtotal;
        this.iva = iva;
        this.ventaTotal = ventaTotal;
        this.efectivo = efectivo;
        this.cambio = cambio;
        this.detalle_id = detalle_id;
    }

    public Caja(String fecha, String hora, String nomCliente) {
        this.fecha = fecha;
        this.hora = hora;
        this.nomCliente = nomCliente;
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

    public String getNomCliente() {
        return nomCliente;
    }

    public void setNomCliente(String nomCliente) {
        this.nomCliente = nomCliente;
    }

    
    public String getNum_ticket() {
        return num_ticket;
    }

    public void setNum_ticket(String num_ticket) {
        this.num_ticket = num_ticket;
    }

    public String getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(String subtotal) {
        this.subtotal = subtotal;
    }

    public String getIva() {
        return iva;
    }

    public void setIva(String iva) {
        this.iva = iva;
    }

    public String getVentaTotal() {
        return ventaTotal;
    }

    public void setVentaTotal(String ventaTotal) {
        this.ventaTotal = ventaTotal;
    }

    public String getEfectivo() {
        return efectivo;
    }

    public void setEfectivo(String efectivo) {
        this.efectivo = efectivo;
    }

    public String getCambio() {
        return cambio;
    }

    public void setCambio(String cambio) {
        this.cambio = cambio;
    }

    public String getDetalle_id() {
        return detalle_id;
    }

    public void setDetalle_id(String detalle_id) {
        this.detalle_id = detalle_id;
    }
    
    
}
