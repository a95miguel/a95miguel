/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.sql.PreparedStatement;
//import controller.inteLogin;
import java.sql.Connection;
import java.sql.ResultSet;


/**
 *
 * @author Miguel Medel Lozada
 */
public class LoginDao {
   public boolean authenticate(String nombre, String contrasena ,String perfil_usuario ) throws Exception 
    {
        Connection con=Conexion.conectar();
        String SQL="SELECT * FROM usuarios WHERE nombre = ? AND contrasena = ? AND perfil_usuario = ? ";                    
        PreparedStatement ps=con.prepareStatement(SQL);
        
        ps.setString(1, nombre);
        ps.setString(2, contrasena);
        ps.setString(3, perfil_usuario);
        ResultSet rs =ps.executeQuery();
        return rs.next();
    }   
    
    
}
