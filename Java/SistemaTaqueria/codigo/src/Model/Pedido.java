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
public class Pedido {
    private String idPedido;
    private String fecha;
    private String hora;
    private String cantidad;
    private String precio;
    private String numero;
    private String Producto_idProducto;
    
    public Pedido(){
        
    }

    public Pedido(String idPedido, String fecha, String hora, String cantidad, String precio, String numero, String Producto_idProducto) {
        this.idPedido = idPedido;
        this.fecha = fecha;
        this.hora = hora;
        this.cantidad = cantidad;
        this.precio = precio;
        this.numero = numero;
        this.Producto_idProducto = Producto_idProducto;
    }

    public String getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(String idPedido) {
        this.idPedido = idPedido;
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

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getProducto_idProducto() {
        return Producto_idProducto;
    }

    public void setProducto_idProducto(String Producto_idProducto) {
        this.Producto_idProducto = Producto_idProducto;
    }
    
}
