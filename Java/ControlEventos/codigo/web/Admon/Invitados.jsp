<%-- 
    Document   : Invitados
    Created on : 21/11/2018, 01:45:20 PM
    Author     : Miguel Medel Lozada
--%>

<%@page import="model.Invitados"%>
<%@page import="dao.BDControl"%>
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
          <h4 class="modal-title">Agregar Nuevo Invitado</h4>
        </div>
        <div class="modal-body">
 
   <form action="../PerfilControl" method="POST"  class="form-horizontal" >
  <div class="form-group ">
    <label class="col-lg-2 control-label">Nombre:</label>
    <div class="col-lg-10">      
        <input type="text" required pattern="[A-Za-z ]{2,90}" title="Ejemplo:Jose,  Minimo 2 letras max 30"  class="form-control" name="nom_invi"  placeholder="Ingrese nombre del invitado" />
    </div>
  </div> 
       
   <div class="form-group ">
    <label class="col-lg-2 control-label">Apellido:</label>
    <div class="col-lg-10">      
        <input type="text" required pattern="[A-Za-z ]{2,90}" title="Ejemplo:Sanchez,  Minimo 2 letras max 30"  class="form-control" name="apellido"  placeholder="Ingrese apellido del invitado" />
    </div>
  </div>    
  
  <div class="form-group ">
    <label class="col-lg-2 control-label">Telefono:</label>
    <div class="col-lg-10">      
        <input type="text" required pattern="[0-9]{8,10}" title="Ejemplo:2423469042,  Minimo 8 Max 10 numeros"  maxlength="10"  class="form-control" name="telefono"  placeholder="Ingrese el telefono" />
    </div>
  </div>     
       
   <div class="form-group ">
    <label class="col-lg-2 control-label">Direccion:</label>
    <div class="col-lg-10">      
        <input type="text" required pattern="[A-Za-z0-9 ]{2,80}" title="Ejemplo:Av 4 sur Puebla ,  Minimo 2 letras max 50"  class="form-control" name="direccion"  placeholder="Ingrese la direccion" />
    </div>
  </div>    
             
       <div class="form-group">
        <label class="col-lg-2 control-label">Sexo:</label>
        <div  class="col-lg-10">
            <label >
                <input required type="radio" name="sexo" id="" value="h" checked>Hombre
            </label>            
             <label>
                <input type="radio" name="sexo" id="" value="m">Mujer
            </label>
        </div>        
        
        </div>
       
             
    <div class="form-group">
    <div class="col-lg-offset-2 col-lg-10">
        <button type="submit" name="accion" value="regInvitado" class="btn btn-block btn-primary"> Agregar nuevo Evento</button>
    </div>
  </div>    
             
</form>
        </div>
      </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
  </div><!-- /.modal --> 
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
                                                <th>Nombre</th>
                                                <th>Apellido</th>
                                                <th>Telefono</th>
                                                <th>Direccion</th>
                                                <th>Sexo</th>
                                                <th>Acciones</th>                                                                                                
                                            </tr>
                                        </thead>
                                        <tbody>
                                            
                                            <% for(Invitados lis:BDControl.lisInvitados()){ %>
                                            <tr class="gradeA">
                                                <form action="../PerfilControl" method="get">
                                                <input type="hidden" name="id_invitado" value="<%= lis.getId_invitado()%>"/> 
                                        
                                                <td><%= lis.getNom_invi() %></td>                                                    
                                                <td ><%= lis.getApellido() %></td>
                                                <td><%= lis.getTelefono()%></td>                                                
                                                <td><%= lis.getDireccion() %></td>
                                                <td><%= lis.getSexo()%></td>
                                                
                                               <td style='width:80px;'>
                                                   <a href="actInvitado.jsp?id_invitado=<%= lis.getId_invitado() %>" class='btn btn-warning btn-xs' data-toggle="modal" >
                                                    <i  class='fa fa-edit' class='boton'></i>
                                                   </a>
                                                    
                                                   <button type="submit" name="accion" value="elimInvitado" class="btn btn-danger btn-xs"><i class="fa fa-trash-o"></i>
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
        


<%@include file="includes/footer.jsp" %>