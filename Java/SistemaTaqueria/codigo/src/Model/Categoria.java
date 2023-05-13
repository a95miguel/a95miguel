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
public class Categoria {
    private String idCategoria;
    private String nomCategoria;
    private JComboBox cmb;
    
    
    public Categoria(){
        cmb= new JComboBox();
        String obj=nomCategoria;
        cmb.addItem(obj);
    }
    
    public Categoria(String idCategoria, String nomCategoria) {
        this.idCategoria = idCategoria;
        this.nomCategoria = nomCategoria;
    }


    public String getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(String idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getNomCategoria() {
        return nomCategoria;
    }

    public void setNomCategoria(String nomCategoria) {
        this.nomCategoria = nomCategoria;
    }
    
    @Override
    public String toString(){
        return nomCategoria;
        //return idCategoria+" "+nomCategoria;
    }
    
    public String id(){
        return  idCategoria;
    }
    
    public boolean equals(Object obj){
        if(obj ==null){
            return false;
        }
        if (this == obj) {
            return true;
        }
        if(!(obj instanceof Categoria )){
            return false;
        }
        Categoria nomCategoria=(Categoria)obj;
        if(idCategoria!=nomCategoria.idCategoria){
            return false;
        }
        if(this.nomCategoria!=null ? ! this.nomCategoria.equals(nomCategoria.nomCategoria):nomCategoria.nomCategoria!=null){
            return false;                    
        }
        return true;        
    }
   
}
