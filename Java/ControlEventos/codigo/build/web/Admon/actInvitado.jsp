<%-- 
    Document   : actInvitado
    Created on : 21/11/2018, 03:28:19 PM
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
        int id_invitado=Integer.parseInt(request.getParameter("id_invitado"));
        ps=con.prepareStatement("SELECT * FROM invitados WHERE id_invitado="+id_invitado);
        rs=ps.executeQuery();
        while(rs.next()){
    %>

        <div id="page-wrapper">
      <div class="modal-content">          
        <div class="modal-header">   
            <a href="Invitados.jsp" type="button" class="close" >&times;</a>
          <h4 class="modal-title">Editar Invitado</h4>
        </div>
        <div class="modal-body">
            
<form class="form-horizontal" role="form" method="post" action="../PerfilControl" >
    <input type="hidden"  name="id_invitado" value="<%= rs.getInt("id_invitado") %>">
  
  <div class="form-group">
    <label class="col-lg-2 control-label">Nombre:</label>
    <div class="col-lg-10">
        <input type="text"  class="form-control" value="<%= rs.getString("nom_invi") %>" name="nom_invi">
    </div>
  </div>
  
  <div class="form-group">
    <label class="col-lg-2 control-label">Apellido</label>
    <div class="col-lg-10">
        <input type="text"  class="form-control" value="<%= rs.getString("apellido") %>" name="apellido">
    </div>
  </div>
    
  <div class="form-group">
    <label class="col-lg-2 control-label">Telefono:</label>
    <div class="col-lg-10">
        <input type="text"  class="form-control" value="<%= rs.getString("telefono") %>" name="telefono">
    </div>
  </div>
    
<div class="form-group">
    <label class="col-lg-2 control-label">Direccion</label>
    <div class="col-lg-10">      
        <input type="text" required pattern="[A-Za-z0-9 ]{2,80}" title="Ejemplo:Av 4 sur Puebla ,  Minimo 2 letras max 50" class="form-control" name="direccion" value="<%= rs.getString("direccion")%>">
    </div>
  </div> 
  
<div class="form-group">
<label class="col-lg-2 control-label">Sexo:</label>
<div  class="col-lg-10">
    <label >
        <input required type="radio" name="sexo" id="" value="h" >Hombre
    </label>            
     <label>
        <input required type="radio" name="sexo" id="" value="m">Mujer
    </label>
</div>        

</div>
    
  <div class="form-group">
    <div class="col-lg-offset-2 col-lg-10">        
        <button type="submit" name="accion"  value="actInvitado" class="btn btn-block btn-success">Actualizar Evento</button>
    </div>
  </div>
</form>
<%}%>
        </div>
      </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
    
<%@include file="includes/footer.jsp" %>