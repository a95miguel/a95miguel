<%-- 
    Document   : actPerfil
    Created on : 14/11/2018, 12:59:40 PM
    Author     : Miguel Medel Lozada
--%>

<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="dao.Conexion"%>
<%@page import="java.sql.Connection"%>
<%@page import="dao.BDControl"%>
<%@page import="model.Perfiles"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="includes/header.jsp"%>
        
<br><br>
<% 
    Connection con=Conexion.conectar();
    PreparedStatement ps;
    ResultSet rs;
    int id_usuario=Integer.parseInt(request.getParameter("id"));
    ps=con.prepareStatement("SELECT * FROM usuarios WHERE id = "+id_usuario);
    rs=ps.executeQuery();
    while(rs.next()){
%>
    
        <div id="page-wrapper">
      <div class="modal-content">          
        <div class="modal-header">   
            <a href="Perfiles.jsp" type="button" class="close" >&times;</a>
          <h4 class="modal-title">Editar Perfil</h4>
        </div>
        <div class="modal-body">
            
            
            
<form class="form-horizontal" role="form" method="post" action="../PerfilControl" >
<input type="hidden"  name="id_usuario" value="<%= rs.getInt("id")%>">
  
  <div class="form-group">
    <label class="col-lg-2 control-label">Nombre:</label>
    <div class="col-lg-10">
      <input type="text" required pattern="[A-Za-z ]{4,30}" title="Ejemplo:Jose,  Minimo 4 letras max 30" class="form-control" value="<%= rs.getString("nombre")%>" name="nombre">
    </div>
  </div>
  
  <div class="form-group">
    <label class="col-lg-2 control-label">Apellido:</label>
    <div class="col-lg-10">      
        <input type="text" required pattern="[A-Za-z ]{4,30}" title="Ejemplo:Sanchez,  Minimo 4 letras max 30" class="form-control" name="apellido" value="<%= rs.getString("apellido")%>">
    </div>
  </div> 
  
<div class="form-group">
    <label class="col-lg-2 control-label">Contraseña</label>
    <div class="col-lg-10">      
        <input type="text" required pattern="[A-Za-z0-9]{5,12}" title="Contraseña con letras y numeros. Minimo 5 caracteres max 12" class="form-control" name="contrasena" value="<%= rs.getString("contrasena")%>">
    </div>
  </div> 

  <div class="form-group">
    <label class="col-lg-2 control-label">Perfil:</label>
    <div class="col-lg-10">      
        <select  class="form-control"  name="perfil_usuario" required>
                <option>portero</option>
                <option>admin</option>
            </select>
    </div>
  </div> 
  
  <div class="form-group">
    <div class="col-lg-offset-2 col-lg-10">        
        <button type="submit" name="accion"  value="actualizar" class="btn btn-block btn-success">Actualizar Perfil</button>
    </div>
  </div>
</form>
<% } %>
        </div>
      </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
    
    
<%@include file="includes/footer.jsp"%>