<%-- 
    Document   : Evento
    Created on : 20/11/2018, 04:14:56 PM
    Author     : Miguel Medel Lozada
--%>

<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@page import="dao.BDControl"%>
<%@page import="model.Eventos"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="includes/header.jsp" %>     
     <br><br>
    <div id="page-wrapper">
        <div class="row">
                    
<a  data-toggle="modal" href="#myModal" class="pull-right btn btn-info">Agregar Nuevo Evento</a>            
     <!-- Modulo de agregar -->
  <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
          <h4 class="modal-title">Agregar Evento</h4>
        </div>
        <div class="modal-body">
 
   <form action="../PerfilControl" method="POST"  class="form-horizontal" >
  <div class="form-group ">
    <label class="col-lg-2 control-label">Nombre:</label>
    <div class="col-lg-10">      
        <input type="text" required pattern="[A-Za-z ]{2,90}" title="Ejemplo:Boda,  Minimo 2 letras max 30"  class="form-control" name="nom_eve"  placeholder="Ingrese nombre evento" />
    </div>
  </div> 
  
   <div class="form-group">
    <label class="col-lg-2 control-label">Fecha:</label>
    <div class="col-lg-10">      
        <input type="date" required class="form-control" name="fecha" data-date-format="DD MMMM YYYY"/>
    </div>
  </div> 
 
  <div class="form-group ">
    <label class="col-lg-2 control-label">Lugar:</label>
    <div class="col-lg-10">      
        <input type="text" required pattern="[A-Za-z0-9 ]{2,80}" title="Ejemplo:Av 4 sur Puebla ,  Minimo 2 letras max 50"  class="form-control" name="ubicacion"  placeholder="Ingrese la ubicacion" />
    </div>
  </div> 
             
             
    <div class="form-group">
    <div class="col-lg-offset-2 col-lg-10">
        <button type="submit" name="accion" value="regEvento" class="btn btn-block btn-primary"> Agregar nuevo Evento</button>
    </div>
  </div>
             
</form>
        </div>
      </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
  </div><!-- /.modal --> 
           
  <br><br><br>
  
  
     <div class="row">
                    <div class="col-lg-12">                      
                    
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                Registro de los eventos
                            </div>
                            <!-- /.panel-heading -->
                            <div class="panel-body">
                                <div class="dataTable_wrapper">
                                    <table class="table table-striped table-bordered table-hover" id="dataTables-example">
                                        <thead>
                                            
                                            <tr>                                                
                                                <th>Nombre del evento </th>
                                                <th>Fecha(año-mes-dia)</th>
                                                <th>Ubicacion</th>
                                                <th>Acciones</th>                                                                                                
                                            </tr>
                                        </thead>
                                        <tbody>
                                            
                                            <% for(Eventos lis:BDControl.lisEvento()){ %>
                                            <tr class="gradeA">
                                        <form action="../PerfilControl" method="get">
                                        <input type="hidden" name="id_evento" value="<%= lis.getId_evento() %>"/> 
                                        
                                                <td><%= lis.getNom_eve()%></td>                                                    
                                                <td ><%= lis.getFecha() %></td>
                                                <td><%= lis.getUbicacion() %></td>                                                
                                                
                                               <td style='width:80px;'>
                                                   <a href="actEvento.jsp?id_evento=<%= lis.getId_evento() %>" class='btn btn-warning btn-xs' data-toggle="modal" >
                                                    <i  class='fa fa-edit' class='boton'></i>
                                                   </a>   
                                                    <button type="submit" name="accion" value="elimEvento" class="btn btn-danger btn-xs"><i class="fa fa-trash-o"></i></button>
                                               </td>           
                                        </form>
                                            </tr>
                                            <%}%>
                                        </tbody>
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