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
public class Detalle {
    private String num_detalle;
    private String factura_id;
    private String producto_id;
    private String producto_nombre;
    private String cantidad;
    private String precio;
    private String subtotal;
    
    
    public Detalle(){
        
    }

    public Detalle(String num_detalle, String factura_id, String producto_id,String producto_nombre, String cantidad, String precio, String subtotal) {
        this.num_detalle = num_detalle;
        this.factura_id = factura_id;
        this.producto_id = producto_id;
        this.producto_nombre=producto_nombre;
        this.cantidad = cantidad;
        this.precio = precio;
        this.subtotal=subtotal;
    }

    public String getProducto_nombre() {
        return producto_nombre;
    }

    public void setProducto_nombre(String producto_nombre) {
        this.producto_nombre = producto_nombre;
    }

    
    public String getNum_detalle() {
        return num_detalle;
    }

    public void setNum_detalle(String num_detalle) {
        this.num_detalle = num_detalle;
    }

    public String getFactura_id() {
        return factura_id;
    }

    public void setFactura_id(String factura_id) {
        this.factura_id = factura_id;
    }

    public String getProducto_id() {
        return producto_id;
    }

    public void setProducto_id(String producto_id) {
        this.producto_id = producto_id;
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

    public String getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(String subtotal) {
        this.subtotal = subtotal;
    }
   
}
