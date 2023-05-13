/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Controller.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Eventos;
import model.Invitaciones;
import model.Invitados;
import model.Perfiles;

/**
 *
 * @author Miguel Medel Lozada
 */
public class BDControl {
 /////////////**************** Perfil ************************///////////////////////////////////////////////////////////////
  public static boolean registrarPerfil(Perfiles l){
       try{
           Connection con=Conexion.conectar();
           String SQL="INSERT INTO usuarios (nombre,apellido,contrasena,perfil_usuario) VALUES(?,?,?,?)";
           PreparedStatement st=con.prepareStatement(SQL);
           st.setString(1,l.getNombre());
           st.setString(2,l.getApellido());
           st.setString(3,l.getContrasena());
           st.setString(4,l.getPerfil_usuario());
           if(st.executeUpdate()>0){
               return true;
           }else{
               return false;
           }           
       } catch (SQLException ex) {
           return false;
       }
   }        

   public static ArrayList<Perfiles> listarPerfil(){
        try {
            String SQL="SELECT * FROM usuarios;";
            Connection con=Conexion.conectar();
            PreparedStatement st=con.prepareStatement(SQL);
            ResultSet resultado=st.executeQuery();
            ArrayList<Perfiles> lista=new ArrayList<>();
            Perfiles lis;
            while(resultado.next()){
                lis=new Perfiles();
                lis.setId_usuario(resultado.getInt("id"));
                lis.setNombre(resultado.getString("nombre"));                
                lis.setApellido(resultado.getString("apellido"));
                lis.setContrasena(resultado.getString("contrasena"));
                lis.setPerfil_usuario(resultado.getString("perfil_usuario"));
                lista.add(lis);
            }
            return lista;
        } catch (SQLException ex) {
            return null;
        }
     }
  
   public static boolean actualizar(Perfiles l){
        try {
            String SQL="UPDATE usuarios SET nombre=?, apellido=?, contrasena=?, perfil_usuario=? WHERE id=?";
            Connection con=Conexion.conectar();
            PreparedStatement st=con.prepareStatement(SQL);            
            st.setInt(5, l.getId_usuario());
            st.setString(1, l.getNombre());
            st.setString(2, l.getApellido());
            st.setString(3, l.getContrasena());
            st.setString(4, l.getPerfil_usuario());                        
            if(st.executeUpdate()>0){
            return true;
        }else{
            return false;    
            }
        } catch (SQLException ex) {
            return false;
        }
     }
   
     public static boolean eliPerfil(Perfiles el){
        try {
            String SQL="DELETE FROM usuarios WHERE id=?;";
            Connection con=Conexion.conectar();
            PreparedStatement st=con.prepareStatement(SQL);
            st.setInt(1, el.getId_usuario());
            if(st.executeUpdate()>0){
            return true;
        }else{
            return false;    
            }
        } catch (SQLException ex) {
            return false;
        }
     }
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
   
 /////////////**************** Eventos ************************/////////////////////////////////////////////////////////
     public static boolean regEvento(Eventos r){
      try{   
        Connection con=Conexion.conectar();
        String SQL="INSERT INTO evento(nom_eve,fecha,ubicacion) VALUES (?,?,?);";
        PreparedStatement st=con.prepareStatement(SQL);
        st.setString(1, r.getNom_eve());
        st.setString(2, r.getFecha());
        st.setString(3, r.getUbicacion());
        if(st.executeUpdate()>0){
            return true;
        }else{
            return false;
         }    
        
        } catch (SQLException ex) {
         return false;
      }          
     }
     
     public static ArrayList<Eventos> lisEvento(){
         try{
             String SQL="SELECT * FROM evento;";             
             Connection con=Conexion.conectar();
             PreparedStatement st=con.prepareStatement(SQL);
             ResultSet resul=st.executeQuery();
             ArrayList<Eventos> lista=new ArrayList<>();
             Eventos lis;
             while(resul.next()){
                 lis=new Eventos();                 
                 lis.setId_evento(resul.getInt("id_evento"));
                 lis.setNom_eve(resul.getString("nom_eve"));
                 lis.setFecha(resul.getString("fecha"));
                 lis.setUbicacion(resul.getString("ubicacion"));
                 lista.add(lis);                 
             }
             return lista;
         } catch (SQLException ex) {
             return null;
      }      
     }
     
     public static boolean actEventos(Eventos e){
         try{
          String SQL="UPDATE evento SET nom_eve=?, fecha=?, ubicacion=? WHERE id_evento=?;";   
          Connection con=Conexion.conectar();
          PreparedStatement st=con.prepareStatement(SQL);
          st.setInt(4, e.getId_evento());
          st.setString(1, e.getNom_eve());
          st.setString(2, e.getFecha());
          st.setString(3 , e.getUbicacion());
          if(st.executeUpdate()>=0){
              return true;
          }else{
              return false;
            }          
         } catch (SQLException ex) {
             return false;
      }      
     }
     
     public static boolean elimEvento(Eventos e){
         try{
             String SQL="DELETE FROM evento WHERE id_evento=?;";
             Connection con=Conexion.conectar();
             PreparedStatement st=con.prepareStatement(SQL);
             st.setInt(1, e.getId_evento());
             if(st.executeUpdate()>0){
                 return true;
             }else{
                 return false;
             }
         } catch (SQLException ex) {
             return false;
      }      
     }

  ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
   
 /////////////**************** Invitados ************************///////////////////////////////////////////////////////
    
     public static boolean registrarInvitado(Invitados i){
         try{
             Connection con=Conexion.conectar();
             String SQL="INSERT INTO invitados(nom_invi,apellido,telefono,direccion,sexo) VALUES(?,?,?,?,?);";
             PreparedStatement st=con.prepareStatement(SQL);
             st.setString(1, i.getNom_invi());
             st.setString(2, i.getApellido());
             st.setString(3, i.getTelefono());
             st.setString(4, i.getDireccion());
             st.setString(5, i.getSexo());
             if(st.executeUpdate()>0){
                 return true;
             }else{
                 return false;
             }
         } catch (SQLException ex) {
             return false;
      }
     }
     
    public static ArrayList<Invitados> lisInvitados(){
        try{
            Connection con=Conexion.conectar();
            String SQL="SELECT * FROM invitados;";
            PreparedStatement st=con.prepareStatement(SQL);
            ResultSet res=st.executeQuery();
            ArrayList<Invitados> lista= new ArrayList<>();            
            Invitados lis;
            while(res.next()){
              lis=new Invitados();
              lis.setId_invitado(res.getInt("id_invitado"));
              lis.setNom_invi(res.getString("nom_invi"));
              lis.setApellido(res.getString("apellido"));
              lis.setTelefono(res.getString("telefono"));
              lis.setDireccion(res.getString("direccion"));
              lis.setSexo(res.getString("sexo"));
              lista.add(lis);             
            }
            return lista;
        } catch (SQLException ex) {
            return null;
      }      
    }
    
     public static boolean actInvitados(Invitados i){
         try{
             Connection con=Conexion.conectar();
             String SQL="UPDATE invitados SET nom_invi=?, apellido=?,telefono=?,direccion=?,sexo=? WHERE id_invitado=?;";
             PreparedStatement st=con.prepareStatement(SQL);
             st.setInt(6, i.getId_invitado());
             st.setString(1, i.getNom_invi());
             st.setString(2, i.getApellido());
             st.setString(3, i.getTelefono());
             st.setString(4, i.getDireccion());
             st.setString(5, i.getSexo());
             if(st.executeUpdate()>0){
                 return true;
             }else{
                 return false;
             }
         } catch (SQLException ex) {
      return false;
      }      
     }
     
     public static boolean elimInvitados(Invitados i){
         try{
             Connection con=Conexion.conectar();
             String SQL="DELETE FROM invitados WHERE id_invitado=?;";
             PreparedStatement st=con.prepareStatement(SQL);
             st.setInt(1, i.getId_invitado());
             if(st.executeUpdate()>0){
                 return true;
             }else{
                 return false;
             }             
         } catch (SQLException ex) {
          return false;
      }      
     }
 ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
   
 /////////////**************** Invitaciones ************************////////////////////////////////////////////////////
     public static boolean regInvitaciones(Invitaciones w){
         try{
         Connection con=Conexion.conectar();         
         String SQL="INSERT INTO invitaciones(invitado,evento,lista) VALUES(?,?,'falta');";
         PreparedStatement st=con.prepareStatement(SQL);
         st.setInt(1,w.getInvitado());
         st.setInt(2,w.getEvento());         
         if(st.executeUpdate()>0){
             return true;
         }else{
             return false;
            }
         } catch (SQLException ex) {
            return false;
      }      
     }

public static boolean elimInvitaciones(Invitaciones w){
         try{
             Connection con=Conexion.conectar();
             String SQL="DELETE FROM invitaciones WHERE id_invitaciones=?;";
             PreparedStatement st=con.prepareStatement(SQL);
             st.setInt(1, w.getId_invitaciones());
             if(st.executeUpdate()>0){
                 return true;
             }else{
                 return false;
             }             
         } catch (SQLException ex) {
          return false;
      }      
     }

//*************************Consulta de datos 

///////////////////Mostrar Hombres 
public static ArrayList<Invitados> MostrarH(){
        try{
            Connection con=Conexion.conectar();
            String SQL="select count(sexo) from invitados where sexo ='h'";
            PreparedStatement st=con.prepareStatement(SQL);
            ResultSet res=st.executeQuery();       
            ArrayList<Invitados> lista= new ArrayList<>();            
            Invitados lis;
            while(res.next()){
              lis=new Invitados();
              lis.setId_invitado(res.getInt(1));
              lista.add(lis);     
            }
            return lista;
        } catch (SQLException ex) {
            return null;
      }      
    }

///////////////////Mostrar mujeres 
public static ArrayList<Invitados> MostrarM(){
        try{
            Connection con=Conexion.conectar();
            String SQL="select count(sexo) from invitados where sexo ='m'";
            PreparedStatement st=con.prepareStatement(SQL);
            ResultSet res=st.executeQuery();       
            ArrayList<Invitados> lista= new ArrayList<>();            
            Invitados lis;
            while(res.next()){
              lis=new Invitados();
              lis.setId_invitado(res.getInt(1));
              lista.add(lis);     
            }
            return lista;
        } catch (SQLException ex) {
            return null;
      }      
    }


}
