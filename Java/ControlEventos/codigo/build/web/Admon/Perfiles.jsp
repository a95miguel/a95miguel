<%-- 
    Document   : Perfiles
    Created on : 9/11/2018, 03:44:50 PM
    Author     : Miguel Medel Lozada
--%>
<%@page import="dao.BDControl"%>
<%@page import="model.Perfiles"%>
<%@page import="dao.Conexion"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>

<%@include file="includes/header.jsp"%>
   
   
   <br><br>
    <div id="page-wrapper">
        <div class="row">
                    
         <a  data-toggle="modal" href="#myModal" class="pull-right btn btn-success">Agregar Nuevo Perfil</a>            
     <!-- Modulo de agregar -->
  <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
          <h4 class="modal-title">Agregar Perfil</h4>
        </div>
        <div class="modal-body">
 
         <form action="../PerfilControl" method="POST"  class="form-horizontal" >
              
        
  <div class="form-group ">
    <label class="col-lg-2 control-label">Nombre:</label>
    <div class="col-lg-10">      
        <input type="text" required pattern="[A-Za-z ]{4,30}" title="Ejemplo:Jose,  Minimo 4 letras max 30"  class="form-control" name="nombre"  placeholder="Ingrese el nombre" />
    </div>
  </div> 
  
   <div class="form-group">
    <label class="col-lg-2 control-label">Apellido:</label>
    <div class="col-lg-10">      
        <input type="text" required pattern="[A-Za-z ]{4,30}" title="Ejemplo:Sanchez,  Minimo 4 letras max 30" class="form-control" name="apellido"  placeholder="Ingrese el apellido" />
    </div>
  </div> 
    
    <div class="form-group">
    <label class="col-lg-2 control-label">Contraseña:</label>
        <div class="col-lg-10">      
            <input type="text" required pattern="[A-Za-z0-9]{5,12}" title="Contraseña con letras y numeros. Minimo 5 caracteres max 12" class="form-control"  name="contrasena" placeholder="Ingrese la contraseña" />
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
        <button type="submit" name="accion" value="registrar" class="btn btn-block btn-primary"> Agregar nuevo perfil</button>
    </div>
  </div>
            
</form>
        </div>
      </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
  </div><!-- /.modal --> 
           <br><br> 
            
           
            <div  class="col-lg-12">
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                Perfiles
                            </div>
                            <!-- /.panel-heading -->
          
                              <div class="panel-body">
                                <div class="table-responsive">
                                    <table class="table">
                                        <thead>
                                            <tr>
                                                <th>Nombre</th>
                                                <th>Apellido</th>
                                                <th>Contraseña</th>
                                                <th>Perfil</th>
                                                <th>Acciones</th>
                                            </tr>
                                        </thead>
                                        
                                        <tbody>
                                            <% for(Perfiles lis:BDControl.listarPerfil()) { %>
                                            
                                            <tr>
                                        <form action="../PerfilControl" method="get">
                                            <input type="hidden" name="id" value="<%= lis.getId_usuario()%>"/> 
                                            
                                                <td class="success"><%= lis.getNombre()%></td>
                                                <td class="info"><%= lis.getApellido()%></td>
                                                <td class="warning"><%= lis.getContrasena()%></td>
                                                <td class="danger"><%= lis.getPerfil_usuario()%></td>
                                                
                                                <td style='width:80px;'> 
                                                    <a href="actPerfil.jsp?id=<%= lis.getId_usuario()%>" class='btn btn-warning btn-xs' data-toggle="modal" >
                                                    <i  class='fa fa-edit' class='boton'></i>
                                                    </a> 
                                                    <button type="submit" name="accion" value="eliminar" class="btn btn-danger btn-xs"><i class="fa fa-trash-o"></i> </button>                                                                 
                                                </td>
                                        </form>
                                        <%}%>   
                                            </tr>
                                            
                                            
                                        </tbody>
                                    </table>
                                    
                                </div>
                                <!-- /.table-responsive -->
                            </div>
                            <!-- /.panel-body -->
                        </div>
                        <!-- /.panel -->
                    </div>
                                 
        </div>
     </div>              
                                              
    
<%@include file="includes/footer.jsp"%>
