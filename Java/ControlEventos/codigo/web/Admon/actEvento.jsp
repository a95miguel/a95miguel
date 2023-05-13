<%-- 
    Document   : actEvento
    Created on : 21/11/2018, 11:44:36 AM
    Author     : Miguel Medel Lozada
--%>

<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="dao.Conexion"%>
<%@page import="java.sql.Connection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="includes/header.jsp" %>
<br><br>

    <%
        Connection con=Conexion.conectar();
        PreparedStatement ps;
        ResultSet rs;
        int id_evento=Integer.parseInt(request.getParameter("id_evento"));
        ps=con.prepareStatement("SELECT * FROM evento WHERE id_evento="+id_evento);
        rs=ps.executeQuery();
        while(rs.next()){
    %>

        <div id="page-wrapper">
      <div class="modal-content">          
        <div class="modal-header">   
            <a href="Evento.jsp" type="button" class="close" >&times;</a>
          <h4 class="modal-title">Editar Evento</h4>
        </div>
        <div class="modal-body">
            
<form class="form-horizontal" role="form" method="post" action="../PerfilControl" >
    <input type="hidden"  name="id_evento" value="<%= rs.getInt("id_evento") %>">
  
  <div class="form-group">
    <label class="col-lg-2 control-label">Nombre:</label>
    <div class="col-lg-10">
        <input type="text"  class="form-control" value="<%= rs.getString("nom_eve") %>" name="nom_eve">
    </div>
  </div>
  
  <div class="form-group">
    <label class="col-lg-2 control-label">Fecha</label>
    <div class="col-lg-10">      
        <input type="date" required  class="form-control" name="fecha" value="<%= rs.getString("fecha")%>">
    </div>
  </div> 
  
<div class="form-group">
    <label class="col-lg-2 control-label">Lugar</label>
    <div class="col-lg-10">      
        <input type="text" required pattern="[A-Za-z0-9 ]{2,80}" title="Ejemplo:Av 4 sur Puebla ,  Minimo 2 letras max 50" class="form-control" name="ubicacion" value="<%= rs.getString("ubicacion")%>">
    </div>
  </div> 
  
  <div class="form-group">
    <div class="col-lg-offset-2 col-lg-10">        
        <button type="submit" name="accion"  value="actEvento" class="btn btn-block btn-success">Actualizar Evento</button>
    </div>
  </div>
</form>
<%}%>
        </div>
      </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
    
<%@include file="includes/footer.jsp" %>