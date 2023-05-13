/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.*;
import View.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.*;
import javax.swing.JOptionPane;

/**
 *
 * @author Miguel Medel Lozada
 */
public class Dao extends Conexion{

    /*
    Crud de la conexion BD de Usuario    
    */        
    public void accerder(Usuario us) throws Exception{
        try{
        Connection con=Conexion.conectar();
        String SQL="SELECT * FROM admin  WHERE usuario = ? AND password = ? ";                    
        PreparedStatement ps=con.prepareStatement(SQL);
        
        ps.setString(1, us.getUsuario());
        ps.setString(2, us.getPassword());
        ResultSet rs =ps.executeQuery();
        if(rs.next()){
            dashboardJFrame ingreso= new dashboardJFrame();
            ingreso.setVisible(true);
        }else{            
             JOptionPane.showMessageDialog(null, "Verefique sus datos","ERROR",JOptionPane.ERROR_MESSAGE);
             //dispose();        
             Login log= new Login();
             log.setVisible(true);
        }
        } catch (SQLException ex) {
          Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }         
    }
    
       public static boolean actUsuario(Usuario cat){
        try {
            String SQL="UPDATE admin set usuario=? , password=? WHERE idUsuario=1";
            Connection con=Conexion.conectar();
            PreparedStatement st=con.prepareStatement(SQL);
            st.setString(1, cat.getUsuario());
            st.setString(2, cat.getPassword());
            if(st.executeUpdate()>0){
            return true;            
        }else{
            return false;    
            }
        } catch (SQLException ex) {
            return false;
        }        
     }
    
    public static ArrayList<Usuario> listarUsuario(){
         try{
             String SQL="select * from admin where idUsuario=1;";             
             Connection con=Conexion.conectar();
             PreparedStatement st=con.prepareStatement(SQL);
             ResultSet resul=st.executeQuery();
             ArrayList<Usuario> lista=new ArrayList<>();
             Usuario lis;
             while(resul.next()){
                 lis=new Usuario();                 
                 lis.setIdUsuario(resul.getInt("idUsuario"));
                 lis.setUsuario(resul.getString("usuario"));
                 lis.setPassword(resul.getString("password"));                 
                 lista.add(lis);                 
             }
             return lista;             
         } catch (SQLException ex) {
             return null;
      }      
     }
     /*
    Crud de la conexion BD de Sucursal    
    */    
     public static ArrayList<Sucursal> listarSucursal(){
         try{
             String SQL="select * from Sucursal where idSucursal=1;";             
             Connection con=Conexion.conectar();
             PreparedStatement st=con.prepareStatement(SQL);
             ResultSet resul=st.executeQuery();
             ArrayList<Sucursal> lista=new ArrayList<>();
             Sucursal lis;
             while(resul.next()){
                 lis=new Sucursal();                 
                 lis.setId_sucursal(resul.getInt("idSucursal"));
                 lis.setNomSucursal(resul.getString("nomSucursal"));
                 lis.setDomicilio(resul.getString("domicilio"));                 
                 lis.setTelefono(resul.getString("telefono"));
                 lista.add(lis);                 
             }
             return lista;             
         } catch (SQLException ex) {
             return null;
      }      
     }
     
     public static boolean actSucursal(Sucursal cat){
        try {
            String SQL="UPDATE Sucursal set nomSucursal=? , domicilio=? , telefono=? WHERE idSucursal=1";
            Connection con=Conexion.conectar();
            PreparedStatement st=con.prepareStatement(SQL);
            st.setString(1, cat.getNomSucursal());
            st.setString(2, cat.getDomicilio());
            st.setString(3, cat.getTelefono());
            if(st.executeUpdate()>0){
            return true;            
        }else{
            return false;    
            }
        } catch (SQLException ex) {
            return false;
        }        
     }
    
      /*
    Crud de la conexion BD de Empleado    
    */   
   public static ArrayList<Empleado> listarEmpleado(){
         try{
             String SQL="select * from Empleado;";             
             Connection con=Conexion.conectar();
             PreparedStatement st=con.prepareStatement(SQL);
             ResultSet resul=st.executeQuery();
             ArrayList<Empleado> lista=new ArrayList<>();
             Empleado lis;
             while(resul.next()){
                 lis=new Empleado();                 
                 lis.setIdEmpleado(resul.getString("idEmpleado"));
                 lis.setNomEmpleado(resul.getString("nomEmpleado"));
                 lis.setApeEmpleado(resul.getString("apeEmpleado"));                 
                 lis.setTelefono(resul.getString("telefono"));                 
                 //lis.setTelefono(resul.getInt("telefono"));
                 lista.add(lis);                
             }             
             return lista;             
         } catch (SQLException ex) {
             return null;
      }      
     }
    
   public static boolean registrarEmpleado(Empleado cat){
        try {
            Connection con=Conexion.conectar();
            String SQL="insert into empleado(nomEmpleado,apeEmpleado,telefono) values(?,?,?)";
            PreparedStatement st=con.prepareStatement(SQL);
            st.setString(1, cat.getNomEmpleado());
            st.setString(2, cat.getApeEmpleado());
            st.setString(3, cat.getTelefono());
            //st.setString(3, Integer.toString(cat.getTelefono()));            
            if(st.executeUpdate()>0){
            return true;
        }else{
            return false;    
            }
        } catch (SQLException ex) {
            return false;
        }
     }
   
   public static boolean actEmpleado(Empleado cat){
        try {
            String SQL="UPDATE Empleado set nomEmpleado=? , apeEmpleado=? , telefono=? WHERE idEmpleado=?";
            Connection con=Conexion.conectar();
            PreparedStatement st=con.prepareStatement(SQL);
            st.setString(1, cat.getNomEmpleado());
            st.setString(2, cat.getApeEmpleado());
            st.setString(3, cat.getTelefono());
            st.setString(4, cat.getIdEmpleado());
            //con.close();
            if(st.executeUpdate()>0){
            return true;            
        }else{
            return false;    
            }            
        } catch (SQLException ex) {
            return false;
        }        
     }
     
    public static boolean eliminarEmpleado(Empleado cat){
        try {           
            String SQL="DELETE FROM Empleado WHERE idEmpleado=?";
            Connection con=Conexion.conectar();
            PreparedStatement st=con.prepareStatement(SQL);
            st.setString(1, cat.getIdEmpleado());
            if(st.executeUpdate()>0){
            return true;
        }else{
            return false;    
            }                       
        } catch (SQLException ex) {
            return false;
        }
        
     }
   
     /*
    Crud de la conexion BD de Categoria    
    */   
   public static ArrayList<Categoria> listarCategoria(){
         try{
             String SQL="select * from Categoria order by idCategoria asc;";             
             Connection con=Conexion.conectar();
             PreparedStatement st=con.prepareStatement(SQL);
             ResultSet resul=st.executeQuery();
             ArrayList<Categoria> lista=new ArrayList<>();
             Categoria lis;
             while(resul.next()){
                 lis=new Categoria();                 
                 lis.setIdCategoria(resul.getString("idCategoria"));
                 lis.setNomCategoria(resul.getString("nomCategoria"));
                 lista.add(lis);                
             }             
             return lista;             
         } catch (SQLException ex) {
             return null;
      }      
     }
   
   public static ArrayList<Categoria> lisCategoriaJCombo(){
         try{
             String SQL="select distinct nomCategoria,c.idCategoria from producto e inner join Categoria c ON e.Categoria_id=c.idCategoria;";             
             Connection con=Conexion.conectar();
             PreparedStatement st=con.prepareStatement(SQL);
             ResultSet resul=st.executeQuery();
             ArrayList<Categoria> lista=new ArrayList<>();
             Categoria lis;
             while(resul.next()){
                 lis=new Categoria();                 
                 lis.setIdCategoria(resul.getString("idCategoria"));
                 lis.setNomCategoria(resul.getString("nomCategoria"));
                 lista.add(lis);                
             }             
             return lista;             
         } catch (SQLException ex) {
             return null;
      }      
     }
   
     public static boolean registrarCategoria(Categoria cat){
        try {
            Connection con=Conexion.conectar();
            String SQL="insert into Categoria(nomCategoria) values(?)";
            PreparedStatement st=con.prepareStatement(SQL);
            st.setString(1, cat.getNomCategoria());
            if(st.executeUpdate()>0){
            return true;
        }else{
            return false;    
            }
        } catch (SQLException ex) {
            return false;
        }
     }
   
   public static boolean actCategoria(Categoria cat){
        try {
            String SQL="UPDATE Categoria SET nomCategoria=?  WHERE idCategoria=?";
            Connection con=Conexion.conectar();
            PreparedStatement st=con.prepareStatement(SQL);
            st.setString(1, cat.getNomCategoria());
            st.setString(2, cat.getIdCategoria());
            if(st.executeUpdate()>0){
            return true;            
        }else{
            return false;    
            }            
        } catch (SQLException ex) {
            return false;
        }        
     }
     
    public static boolean eliCategoria(Categoria cat){
        try {           
            String SQL="DELETE FROM Categoria WHERE idCategoria=?";
            Connection con=Conexion.conectar();
            PreparedStatement st=con.prepareStatement(SQL);
            st.setString(1, cat.getIdCategoria());
            if(st.executeUpdate()>0){
            return true;
        }else{
            return false;    
            }                       
        } catch (SQLException ex) {
            return false;
        }        
     }
    /*
    Crud de la conexion BD de Producto    
    */   
 public static ArrayList<Producto> listarProducto(){
         try{
             String SQL="SELECT idProducto,nomProducto,precio,descripcion, P.Categoria_id,C.nomCategoria FROM Producto p LEFT OUTER JOIN Categoria C ON p.Categoria_id=C.idCategoria order by idProducto asc;";                    
             //SELECT * FROM producto INNER JOIN Categoria ON producto.Categoria_id=Categoria.idCategoria order by idProducto asc;
             Connection con=Conexion.conectar();
             PreparedStatement st=con.prepareStatement(SQL);
             ResultSet resul=st.executeQuery();
             ArrayList<Producto> lista=new ArrayList<>();
             Producto lis;
             while(resul.next()){
                 lis=new Producto();                 
                 lis.setIdProducto(resul.getString("idProducto"));
                 lis.setNomProducto(resul.getString("nomProducto"));
                 lis.setPrecio(resul.getString("precio"));
                 lis.setDescripcion(resul.getString("descripcion"));                 
                 lis.setCategoria_id(resul.getString("nomCategoria"));
                 lista.add(lis);               
             }             
             return lista;               
         } catch (SQLException ex) {
             return null;
      }  
     }
   
 public static boolean registrarProducto(Producto cat){
        try {
            Connection con=Conexion.conectar();
            String SQL="insert into producto(nomProducto,precio,descripcion,Categoria_id) values(?,?,?,?)";
            PreparedStatement st=con.prepareStatement(SQL);
            st.setString(1, cat.getNomProducto());
            st.setString(2, cat.getPrecio());
            st.setString(3, cat.getDescripcion());
            st.setString(4, cat.getCategoria_id());
            if(st.executeUpdate()>0){
            return true;
        }else{
            return false;    
            }
        } catch (SQLException ex) {
            return false;
        }
     }
     
      public static boolean eliProducto(Producto cat){
        try {           
            String SQL="DELETE FROM Producto WHERE idProducto=?";
            Connection con=Conexion.conectar();
            PreparedStatement st=con.prepareStatement(SQL);
            st.setString(1, cat.getIdProducto());
            if(st.executeUpdate()>0){
            return true;
        }else{
            return false;    
            }                       
        } catch (SQLException ex) {
            return false;
        }        
     }
   
      public static boolean actProducto(Producto cat){
        try {
            String SQL="UPDATE Producto SET nomProducto=?,precio=?,descripcion=?,Categoria_id=?  WHERE idProducto=?";
            Connection con=Conexion.conectar();
            PreparedStatement st=con.prepareStatement(SQL);
            st.setString(1, cat.getNomProducto());
            st.setString(2, cat.getPrecio());
            st.setString(3, cat.getDescripcion());
            st.setString(4, cat.getCategoria_id());
            st.setString(5, cat.getIdProducto());
            if(st.executeUpdate()>0){
            return true;            
        }else{
            return false;    
            }            
        } catch (SQLException ex) {
            return false;
        }        
     }
      /*
    Crud de la conexion BD de Cliente    
    */
      public static boolean registrarClienet(Cliente cat){
        try {
            Connection con=Conexion.conectar();
            String SQL="insert into Cliente(nombreCliente,telefono) values(?,?)";
            PreparedStatement st=con.prepareStatement(SQL);
            st.setString(1, cat.getNombreCliente());
            st.setString(2, cat.getTelefono());            
            if(st.executeUpdate()>0){
            return true;
        }else{
            return false;    
            }
        } catch (SQLException ex) {
            return false;
        }
     }
      
      public static ArrayList<Cliente> MostrarCliente(){
         try{
             String SQL="SELECT * FROM cliente ORDER BY idCliente DESC LIMIT 1;";                          
             Connection con=Conexion.conectar();
             PreparedStatement st=con.prepareStatement(SQL);
             ResultSet resul=st.executeQuery();
             ArrayList<Cliente> lista=new ArrayList<>();
             Cliente lis;
             while(resul.next()){
                 lis=new Cliente();                 
                 lis.setIdCliente(resul.getString("idCliente"));
                 lis.setNombreCliente(resul.getString("nombreCliente"));
                 lis.setTelefono(resul.getString("telefono"));                                  
                 lista.add(lis);               
             }             
             return lista;               
         } catch (SQLException ex) {
             return null;
      }  
     }
      
        /*
    Crud de la conexion BD de Factura    
    */
     public static boolean registrarFactura(ModelDatos a){
        try {
            Connection con=Conexion.conectar();
            String SQL="INSERT INTO factura(Cliente_id,fecha,hora)VALUES(?,CURDATE(),CURTIME());";
            PreparedStatement st=con.prepareStatement(SQL);
            st.setString(1,a.getCliente_id());            
            if(st.executeUpdate()>0){
            return true;
        }else{
            return false;    
            }
        } catch (SQLException ex) {
            return false;
        }
     }
      
     public static ArrayList<Factura> MostrarUltimaFactura(){
         try{
             String SQL="SELECT * FROM factura ORDER BY idFactura DESC LIMIT 1;";                          
             Connection con=Conexion.conectar();
             PreparedStatement st=con.prepareStatement(SQL);
             ResultSet resul=st.executeQuery();
             ArrayList<Factura> lista=new ArrayList<>();
             Factura lis;
             while(resul.next()){
                 lis=new Factura();                 
                 lis.setIdFactura(resul.getString("idFactura"));
                 lis.setCliente_id(resul.getString("Cliente_id"));
                 lis.setFecha(resul.getString("fecha"));                                  
                 lis.setHora(resul.getString("hora"));                                  
                 lista.add(lis);               
             }             
             return lista;               
         } catch (SQLException ex) {
             return null;
      }  
     }
      
      /*
    Crud de la conexion BD de Detalle de la compra    
    */
      public static ArrayList<Producto> listarJCombo(){
         try{
             String SQL="SELECT distinct nomCategoria FROM producto INNER JOIN Categoria ON producto.Categoria_id=Categoria.idCategoria;";                          
             Connection con=Conexion.conectar();
             PreparedStatement st=con.prepareStatement(SQL);
             ResultSet resul=st.executeQuery();
             ArrayList<Producto> lista=new ArrayList<>();
             Producto lis;
             while(resul.next()){
                 lis=new Producto();                 
                 lis.setIdProducto(resul.getString("idProducto"));
                 lis.setNomProducto(resul.getString("nomProducto"));
                 lis.setPrecio(resul.getString("precio"));
                 lis.setDescripcion(resul.getString("descripcion"));                 
                 lis.setCategoria_id(resul.getString("nomCategoria"));
                 lista.add(lis);               
             }             
             return lista;               
         } catch (SQLException ex) {
             return null;
      }  
     }
  
      public static ArrayList<Producto> getData(String Categoria_id){
         try{
             String SQL="SELECT * FROM producto where Categoria_id="+Categoria_id;                    
             //SELECT * FROM producto INNER JOIN Categoria ON producto.Categoria_id=Categoria.idCategoria order by idProducto asc;
             Connection con=Conexion.conectar();
             PreparedStatement st=con.prepareStatement(SQL);
             ResultSet resul=st.executeQuery();
             ArrayList<Producto> lista=new ArrayList<Producto>();             
             Producto lis;
             while(resul.next()){
                 lis=new Producto();                 
                 lis.setIdProducto(resul.getString("idProducto"));
                 lis.setNomProducto(resul.getString("nomProducto"));
                 lis.setPrecio(resul.getString("precio"));
                 lis.setDescripcion(resul.getString("descripcion"));                 
                 lis.setCategoria_id(resul.getString("Categoria_id"));
                 lista.add(lis);               
             }             
             return lista;               
         } catch (SQLException ex) {
             return null;
      }  
     }
      
      public static boolean registrarDatalle(Detalle a){
        try {
            Connection con=Conexion.conectar();
            String SQL="INSERT INTO detalle(num_detalle,fatura_id,Producto_id,cantidad,precio) VALUES(?,?,?,?,?);";
            PreparedStatement st=con.prepareStatement(SQL);
            st.setString(1,a.getNum_detalle());            
            st.setString(2,a.getFactura_id());  
            st.setString(3,a.getProducto_id());  
            st.setString(4,a.getCantidad());  
            st.setString(5,a.getPrecio());  
            if(st.executeUpdate()>0){
            return true;
        }else{
            return false;    
            }
        } catch (SQLException ex) {
            return false;
        }
     }

     public static ArrayList<Detalle> getDetalle(String IdCliente){
         try{
             String SQL="SELECT * FROM detalle INNER JOIN Producto ON detalle.Producto_id=Producto.idProducto where num_detalle="+IdCliente;                    
             //SELECT * FROM producto INNER JOIN Categoria ON producto.Categoria_id=Categoria.idCategoria order by idProducto asc;
             Connection con=Conexion.conectar();
             PreparedStatement st=con.prepareStatement(SQL);
             ResultSet resul=st.executeQuery();
             ArrayList<Detalle> lista=new ArrayList<Detalle>();             
             Detalle lis;
             while(resul.next()){
                 lis=new Detalle();                 
                 lis.setProducto_id(resul.getString("idProducto"));
                 lis.setProducto_nombre(resul.getString("nomProducto"));
                 lis.setCantidad(resul.getString("cantidad"));
                 lis.setPrecio(resul.getString("precio"));                 
                 lista.add(lis);               
             }             
             return lista;               
         } catch (SQLException ex) {
             return null;
      }  
     }
     
     public static boolean eliDetalle(Detalle cat){
        try {           
            String SQL="DELETE FROM detalle WHERE num_detalle=? and Producto_id=?;";
            Connection con=Conexion.conectar();
            PreparedStatement st=con.prepareStatement(SQL);
            st.setString(1, cat.getNum_detalle());
            st.setString(2, cat.getProducto_id());
            if(st.executeUpdate()>0){
            return true;
        }else{
            return false;    
            }                       
        } catch (SQLException ex) {
            return false;
        }        
     }
 
 /*
    Crud de la conexion BD de Caja    
    */
      public static ArrayList<Caja> idUltimoCaja(){
         try{
             String SQL="SELECT num_ticket FROM caja ORDER BY num_ticket DESC LIMIT 1;";             
             Connection con=Conexion.conectar();
             PreparedStatement st=con.prepareStatement(SQL);
             ResultSet resul=st.executeQuery();
             ArrayList<Caja> lista=new ArrayList<>();
             Caja lis;
             while(resul.next()){
                 lis=new Caja();                 
                 lis.setNum_ticket(resul.getString("num_ticket"));
                 lista.add(lis);                 
             }
             return lista;             
         } catch (SQLException ex) {
             return null;
      }      
     }
      
      public static ArrayList<Detalle> getSubTotal(String idCliente){
         try{
             String SQL="SELECT SUM(precio) FROM detalle where num_detalle="+idCliente;                    
             Connection con=Conexion.conectar();
             PreparedStatement st=con.prepareStatement(SQL);
             ResultSet resul=st.executeQuery();
             ArrayList<Detalle> lista=new ArrayList<Detalle>();             
             Detalle lis;
             while(resul.next()){
                 lis=new Detalle();                 
                 lis.setPrecio(resul.getString("SUM(precio)"));                 
                 lista.add(lis);               
             }             
             return lista;               
         } catch (SQLException ex) {
             return null;
      }  
     }
 
      public static boolean registrarCaja(Caja a){
        try {
            Connection con=Conexion.conectar();
            String SQL="INSERT INTO Caja(num_ticket,subtotal,iva,ventaTotal,efectivo,cambio,detalle_id) VALUES(?,?,?,?,?,?,?);";
            PreparedStatement st=con.prepareStatement(SQL);
            st.setString(1,a.getNum_ticket());            
            st.setString(2,a.getSubtotal());  
            st.setString(3,a.getIva());  
            st.setString(4,a.getVentaTotal());  
            st.setString(5,a.getEfectivo());  
            st.setString(6,a.getCambio());  
            st.setString(7,a.getDetalle_id());  
            if(st.executeUpdate()>0){
            return true;
        }else{
            return false;    
            }
        } catch (SQLException ex) {
            return false;
        }
     }
      
         public static ArrayList<Caja> mostrarCaja(){
         try{
             String SQL="SELECT  distinct num_ticket,c.ventaTotal,f.fecha,f.hora,n.nombreCliente  \n" +
                "FROM caja c\n" +
"	INNER JOIN detalle d ON c.detalle_id=d.num_detalle\n" +
"	INNER JOIN factura f ON d.fatura_id=f.idFactura\n" +
"	INNER JOIN cliente n ON f.Cliente_id=n.idCliente;";             
             Connection con=Conexion.conectar();
             PreparedStatement st=con.prepareStatement(SQL);
             ResultSet resul=st.executeQuery();
             ArrayList<Caja> lista=new ArrayList<>();
             Caja lis;
             while(resul.next()){
                 lis=new Caja();                 
                 lis.setNum_ticket(resul.getString("num_ticket"));
                 lis.setNomCliente(resul.getString("nombreCliente"));
                 lis.setFecha(resul.getString("fecha"));
                 lis.setHora(resul.getString("hora"));                                  
                 lis.setVentaTotal(resul.getString("ventaTotal"));
                 lista.add(lis);                 
                 
             }
             return lista;             
         } catch (SQLException ex) {
             return null;
      }      
     }
         public static ArrayList<Caja> sumaTotalCaja(){
         try{
             String SQL="SELECT SUM(ventaTotal) FROM caja;";             
             Connection con=Conexion.conectar();
             PreparedStatement st=con.prepareStatement(SQL);
             ResultSet resul=st.executeQuery();
             ArrayList<Caja> lista=new ArrayList<>();
             Caja lis;
             while(resul.next()){
                 lis=new Caja();                                      
                 lis.setVentaTotal(resul.getString("SUM(ventaTotal)"));
                 lista.add(lis);                 
                 
             }
             return lista;             
         } catch (SQLException ex) {
             return null;
      }      
     }
     
         public static ArrayList<Caja> sumaVentasRealizadasCaja(){
         try{
             String SQL="SELECT COUNT(num_ticket) FROM caja;";             
             Connection con=Conexion.conectar();
             PreparedStatement st=con.prepareStatement(SQL);
             ResultSet resul=st.executeQuery();
             ArrayList<Caja> lista=new ArrayList<>();
             Caja lis;
             while(resul.next()){
                 lis=new Caja();                                      
                 lis.setNum_ticket(resul.getString("COUNT(num_ticket)"));
                 lista.add(lis);                 
                 
             }
             return lista;             
         } catch (SQLException ex) {
             return null;
      }      
     }
         public static boolean eliCaja(Caja cat){
        try {           
            String SQL="DELETE FROM caja WHERE num_ticket=?";
            Connection con=Conexion.conectar();
            PreparedStatement st=con.prepareStatement(SQL);
            st.setString(1, cat.getNum_ticket());
            if(st.executeUpdate()>0){
            return true;
        }else{
            return false;    
            }                       
        } catch (SQLException ex) {
            return false;
        }        
     }
         
         public static ArrayList<Caja> buscarCaja(String valor){
         try{
             String SQL="SELECT  distinct num_ticket,c.ventaTotal,f.fecha,f.hora,n.nombreCliente  \n" +
                "FROM caja c\n" +
"	INNER JOIN detalle d ON c.detalle_id=d.num_detalle\n" +
"	INNER JOIN factura f ON d.fatura_id=f.idFactura\n" +
"	INNER JOIN cliente n ON f.Cliente_id=n.idCliente WHERE nombreCliente LIKE '%"+valor+"%'";
                                 
             Connection con=Conexion.conectar();
             PreparedStatement st=con.prepareStatement(SQL);
             //st.setString(1, cat.getNomCliente());
             ResultSet resul=st.executeQuery();
             ArrayList<Caja> lista=new ArrayList<>();
             Caja lis;
             while(resul.next()){
                 lis=new Caja();                 
                 lis.setNum_ticket(resul.getString("num_ticket"));
                 lis.setNomCliente(resul.getString("nombreCliente"));
                 lis.setFecha(resul.getString("fecha"));
                 lis.setHora(resul.getString("hora"));                                  
                 lis.setVentaTotal(resul.getString("ventaTotal"));
                 lista.add(lis);                 
                 
             }
             return lista;             
         } catch (SQLException ex) {
             return null;
      }      
     }
          public static ArrayList<Producto> TotalProducto(){
         try{
             String SQL="SELECT COUNT(idProducto) FROM Producto;";             
             Connection con=Conexion.conectar();
             PreparedStatement st=con.prepareStatement(SQL);
             ResultSet resul=st.executeQuery();
             ArrayList<Producto> lista=new ArrayList<>();
             Producto lis;
             while(resul.next()){
                 lis=new Producto();                                      
                 lis.setIdProducto(resul.getString("COUNT(idProducto)"));
                 lista.add(lis);                 
                 
             }
             return lista;             
         } catch (SQLException ex) {
             return null;
      }      
     }
          public static ArrayList<Empleado> TotalEmpleado(){
         try{
             String SQL="SELECT COUNT(idEmpleado) FROM Empleado;";             
             Connection con=Conexion.conectar();
             PreparedStatement st=con.prepareStatement(SQL);
             ResultSet resul=st.executeQuery();
             ArrayList<Empleado> lista=new ArrayList<>();
             Empleado lis;
             while(resul.next()){
                 lis=new Empleado();                                      
                 lis.setIdEmpleado(resul.getString("COUNT(idEmpleado)"));
                 lista.add(lis);                 
                 
             }
             return lista;             
         } catch (SQLException ex) {
             return null;
      }      
     }
}
