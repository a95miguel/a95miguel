/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import javax.swing.JComboBox;

/**
 *
 * @author Miguel Medel Lozada
 */
public class Producto {
    private String idProducto;
    private String nomProducto;
    private String precio;
    private String descripcion;
    private String Categoria_id;
    private JComboBox cmb;
    
    public Producto(){
        cmb= new JComboBox();
        String obj=Categoria_id;
        cmb.addItem(obj);
    }

    public Producto(String idProducto, String nomProducto, String precio, String descripcion, String Categoria_id) {
        this.idProducto = idProducto;
        this.nomProducto = nomProducto;
        this.precio = precio;
        this.descripcion = descripcion;
        this.Categoria_id = Categoria_id;
    }

   
    public String getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(String idProducto) {
        this.idProducto = idProducto;
    }

    public String getNomProducto() {
        return nomProducto;
    }

    public void setNomProducto(String nomProducto) {
        this.nomProducto = nomProducto;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCategoria_id() {
        return Categoria_id;
    }

    public void setCategoria_id(String Categoria_id) {
        this.Categoria_id = Categoria_id;
    }

 @Override
    public String toString(){
        return Categoria_id;
        //return idCategoria+" "+nomCategoria;
    }
    
}
