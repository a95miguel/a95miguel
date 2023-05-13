<%-- 
    Document   : header
    Created on : 9/11/2018, 02:33:16 PM
    Author     : Miguel Medel Lozada
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="">
        <meta name="author" content="">
        <title>Administrador</title>
        
        <!-- Bootstrap Core CSS -->
        <link href="includes/libreria/css/bootstrap.min.css" rel="stylesheet">

        <!-- MetisMenu CSS -->
        <link href="includes/libreria/css/metisMenu.min.css" rel="stylesheet">

        <!-- Timeline CSS -->
        <link href="includes/libreria/css/timeline.css" rel="stylesheet">

        <!-- Custom CSS -->
        <link href="includes/libreria/css/startmin.css" rel="stylesheet">

        <!-- Morris Charts CSS -->
        <link href="includes/libreria/css/morris.css" rel="stylesheet">

        <!-- Custom Fonts -->
        <link href="includes/libreria/css/font-awesome.min.css" rel="stylesheet" type="text/css">
        
            

    </head>
    <body>
        <%
            String user = (String)session.getAttribute("nombre");
            if(user!=null){
            
        %>   
        <div id="wrapper">                

            <!-- Navigation -->
            <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">            
                <div class="navbar-header">
                    <a class="navbar-brand">Eventos</a>
                </div>
                   <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
          
                    
                <form action="../Logout" method="post">
                <ul class="nav navbar-right navbar-top-links">                    
                    <li class="dropdown">
                        <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                            <i class="fa fa-user fa-fw"></i> <%=(String)session.getAttribute("nombre")%>   <b class="caret"></b>
                        </a>
                        <ul class="dropdown-menu dropdown-user">
                            <li><a href="Perfiles.jsp"><i class="fa fa-user fa-fw"></i> Perfiles</a>
                            </li>
                            <li><a href="#"><i class="fa fa-gear fa-fw"></i> Usuarios</a>
                            </li>
                            
                            <li class="divider"></li>
                            
                            <button type="submit" name="accion" class="btn btn-block btn-default"><i class="fa fa-sign-out fa-fw"></i> Salir </button>                            
                            
                        </ul>
                    </li>
                </ul>
                </form>                       
                        
                <!-- /.navbar-top-links -->

                <div class="navbar-default sidebar" role="navigation">
                    <div class="sidebar-nav navbar-collapse">
                        <ul class="nav" id="side-menu">
                            <li>
                                <a href="dashboard.jsp" class="active"><i class="fa fa-dashboard fa-fw"></i> Dashboard</a>
                            </li>
                            
                            <li>
                                <a href="Evento.jsp" class="active"><i class="glyphicon glyphicon-menu-hamburger"></i> Eventos</a>
                            </li>
                            
                            <li>
                                <a href="Invitados.jsp" class="active"><i class="fa fa-sticky-note-o"></i> Invitados</a>
                            </li>
                            
                            <li>
                                <a href="ControlInvitados.jsp" class="active"><i class="glyphicon glyphicon-check"></i> Invitacciones</a>
                            </li>
                            
                        
                        </ul>
                        </div>
                     
                    </div>
                

            </nav>
         
     <%}else{
            response.sendRedirect("../index.jsp");
        
        }%>