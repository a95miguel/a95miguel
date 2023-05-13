<%-- 
    Document   : dashboard
    Created on : 8/11/2018, 04:47:43 PM
    Author     : Miguel Medel Lozada
--%>

<%@page import="dao.BDControl"%>
<%@page import="model.Invitados"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="dao.Conexion"%>
<%@page import="java.sql.Connection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
    <%@include file="includes/header.jsp"%>
    
    <br><br>
<div id="page-wrapper">
    <div class="row">
                
       <div class="col-lg-3 col-md-6">
        <div class="panel panel-red">
            <div class="panel-heading">
                <div class="row">
                    <div class="col-xs-3">
                        <i class="fa fa-support fa-5x"></i>
                    </div>
                    <% 
                        for(Invitados lis:BDControl.MostrarH()){
                                       
                                    %>
                    <div class="col-xs-9 text-right">
                        <div class="huge"><%= lis.getId_invitado() %></div>
                        <div>Hombres </div>
                    </div>
                        <%}%>
                </div>
            </div>
            <a data-toggle="modal" href="#myModal" class="pull-right btn btn-info">
                <div class="panel-footer">
                    <span class="pull-left">Ver Lista</span>
                    <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>

                    <div class="clearfix"></div>
                </div>
            </a>
        </div>
    </div>
    
                
                
 <div class="col-lg-3 col-md-6">
        <div class="panel panel-red">
            <div class="panel-heading">
                <div class="row">
                    <div class="col-xs-3">
                        <i class="fa fa-support fa-5x"></i>
                    </div>
                    <% 
                        for(Invitados lis:BDControl.MostrarM()){
                                       
                                    %>
                    
                    
                    <div class="col-xs-9 text-right">
                        <div class="huge"><%= lis.getId_invitado() %></div>
                        <div>Mujeres </div>
                    </div>
                        <%}%>
                </div>
            </div>
            <a href="#">
                <div class="panel-footer">
                    <span class="pull-left">Ver Lista</span>
                    <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>

                    <div class="clearfix"></div>
                </div>
            </a>
        </div>
    </div>
                
                
                
    </div>
  </div> 
    
    
    
    <%@include file="includes/footer.jsp"%>