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
public class ModelDatos {

    private String idCarrito;
    private String cantidad;
    private String precio;
    private String producto_id;
   
    private String Cliente_id;
    
    private String num_detalle;
    private String factura_id;
    private String Producto_id;
    private String cantidadDetalle;
    private String precioDetalle;

    public ModelDatos(String idCarrito, String cantidad, String precio, String producto_id, String Cliente_id, String num_detalle, String factura_id, String Producto_id, String cantidadDetalle, String precioDetalle) {
        this.idCarrito = idCarrito;
        this.cantidad = cantidad;
        this.precio = precio;
        this.producto_id = producto_id;
        this.Cliente_id = Cliente_id;
        this.num_detalle = num_detalle;
        this.factura_id = factura_id;
        this.Producto_id = Producto_id;
        this.cantidadDetalle = cantidadDetalle;
        this.precioDetalle = precioDetalle;
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

    public String getCantidadDetalle() {
        return cantidadDetalle;
    }

    public void setCantidadDetalle(String cantidadDetalle) {
        this.cantidadDetalle = cantidadDetalle;
    }

    public String getPrecioDetalle() {
        return precioDetalle;
    }

    public void setPrecioDetalle(String precioDetalle) {
        this.precioDetalle = precioDetalle;
    }
    

    public String getCliente_id() {
        return Cliente_id;
    }

    public void setCliente_id(String Cliente_id) {
        this.Cliente_id = Cliente_id;
    }

    public ModelDatos(String Cliente_id) {
        this.Cliente_id = Cliente_id;
    }

    public ModelDatos() {
        
    }
    

    public ModelDatos(String idCarrito,String cantidad, String precio, String producto_id) {
        this.cantidad = cantidad;
        this.precio = precio;
        this.producto_id = producto_id;
        this.idCarrito = idCarrito;
    }

    public String getIdCarrito() {
        return idCarrito;
    }

    public void setIdCarrito(String idCarrito) {
        this.idCarrito = idCarrito;
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

    public String getProducto_id() {
        return producto_id;
    }

    public void setProducto_id(String producto_id) {
        this.producto_id = producto_id;
    }
    
}
