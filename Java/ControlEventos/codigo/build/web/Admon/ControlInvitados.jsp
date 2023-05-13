<%-- 
    Document   : ControlInvitados
    Created on : 22/11/2018, 11:55:41 AM
    Author     : Miguel Medel Lozada
--%>

<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="dao.Conexion"%>
<%@page import="java.sql.Connection"%>
<%@page import="model.Invitados"%>
<%@page import="dao.BDControl"%>
<%@page import="model.Eventos"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="includes/header.jsp" %>
    <br><br>
      <div id="page-wrapper">
          <div class="row">
              <form action="../PerfilControl" method="POST">
                
              <div class="form-group">
                    <label>Invitados</label>
                    <select name="invitado" class="form-control">
                        <%for(Invitados e:BDControl.lisInvitados()){%>
                        <option value="<%= e.getId_invitado()%>"><%= e.getNom_invi()%> <%= e.getApellido()%></option>                        
                        <%}%>
                    </select>
              </div>
                    
             <div class="form-group">
                    <label>Evento</label>
                    <select name="evento" class="form-control">
                        <%for(Eventos e:BDControl.lisEvento()){%>
                        <option value="<%= e.getId_evento()%>"><%= e.getNom_eve()%> Fecha: <%= e.getFecha()%>  Ubicacion: <%= e.getUbicacion() %></option>                        
                        <%}%>
                    </select>
                </div>
                     
            
                    <button type="submit" name="accion" value="contInvitaciones" class="btn btn-outline btn-success">Registrar</button>
        
             </form>
                    
                    
        <br><br>

        
  <div class="row">
                    <div class="col-lg-12">                                          
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                INVITADOS
                            </div>
                            <!-- /.panel-heading -->
                            <div class="panel-body">
                                <div class="dataTable_wrapper">
                                    <table class="table table-striped table-bordered table-hover" id="dataTables-example">
                                        <thead>
                                            <tr>                                                
                                                <th>Invitado</th>
                                                <th>Telefono</th>
                                                <th>Lista</th>
                                                <th>Evento</th>                                                
                                            </tr>
                                        </thead>
                                        <tbody>
                                    <% 
                                        Connection con=Conexion.conectar();
                                        PreparedStatement ps;
                                        ResultSet rs;                                        
                                        ps=con.prepareStatement("SELECT * FROM invitaciones u LEFT JOIN evento ur ON u.evento= ur.id_evento LEFT JOIN invitados ue ON u.invitado= ue.id_invitado order by id_invitaciones asc ;");
                                        rs=ps.executeQuery();
                                        while(rs.next()){
                                    %>

                                           
                                            <tr class="gradeA">
                                                <form action="../PerfilControl" method="get">
                                                    <input type="hidden" name="id_invitaciones" value="<%= rs.getInt("id_invitaciones")%>"/> 
                                        
                                                <td><%= rs.getString("nom_invi") %> <%= rs.getString("apellido") %></td>                                                    
                                                <td ><%= rs.getString("telefono") %></td>
                                                <td ><%= rs.getString("lista") %></td>
                                                <td ><%= rs.getString("nom_eve") %></td>
                                                
                                               <td style='width:80px;'>
                                                   
                                                   <button type="submit" name="accion" value="elimInvitaciones" class="btn btn-danger btn-xs"><i class="fa fa-trash-o"></i></button>
                                               </td>           
                                                </form>
                                            </tr>
                                            
                                        </tbody>
                                        <%}%>
                                    </table>
                                    
                                </div>
                               
                            </div>
                            <!-- /.panel-body -->
                        </div>
                        <!-- /.panel -->
                    </div>
                    <!-- /.col-lg-12 -->
                </div>
              
  
                
                
                
                
                
                
                
                
                
                
          </div>
      </div>
<%@include file="includes/footer.jsp" %>