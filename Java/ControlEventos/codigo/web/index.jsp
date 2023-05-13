<%-- 
    Document   : index
    Created on : 8/11/2018, 03:40:57 PM
    Author     : Miguel Medel Lozada
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Control de eventos</title>
        <meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
<!--===============================================================================================-->	
	<link rel="icon" type="image/png" href="libreria/images/icons/favicon.ico"/>
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="libreria/vendor/bootstrap/css/bootstrap.min.css">
<!--===============================================================================================-->
    <a href="index.jsp"></a>
	<link rel="stylesheet" type="text/css" href="libreria/fonts/font-awesome-4.7.0/css/font-awesome.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="libreria/fonts/iconic/css/material-design-iconic-font.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="libreria/vendor/animate/animate.css">
<!--===============================================================================================-->	
	<link rel="stylesheet" type="text/css" href="libreria/vendor/css-hamburgers/hamburgers.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="libreria/vendor/animsition/css/animsition.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="libreria/vendor/select2/select2.min.css">
<!--===============================================================================================-->	
	<link rel="stylesheet" type="text/css" href="libreria/vendor/daterangepicker/daterangepicker.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="libreria/css/util.css">
	<link rel="stylesheet" type="text/css" href="libreria/css/main.css">
<!--===============================================================================================-->
    <style>
        #alert{font-size:20px; color :#DC143C}
        
    </style>
    </head>
    <body>
        <div class="limiter">
        <div class="container-login100">
        <div class="wrap-login100">
        <form class="login100-form validate-form" action="LoginControl" method="POST">
                <span class="login100-form-title p-b-26">
                        Bienvenido
                </span>
            <div id="alert" >
                    <%=(request.getAttribute("mensaje")!=null?request.getAttribute("mensaje"):"")%>                                                                                            
                </div>
            <div class="wrap-input100 validate-input" data-validate = "Ingrese el nombre">
            <input class="input100" type="text" name="nombre">
            <span class="focus-input100" data-placeholder="NOMBRE"></span>
            </div>
            
            <div class="wrap-input100 validate-input" data-validate="Ingrese password">
            <span class="btn-show-pass">
                    <i class="zmdi zmdi-eye"></i>
            </span>
            <input class="input100" type="password" name="contrasena">
           <span class="focus-input100" data-placeholder="CONTRASEÑA"></span>
            </div>
            
            <div >
            <select class="input100" name="perfil_usuario">
                    <option class="input100">portero</option>
                    <option class="input100">admin</option>
            </select>						
            </div>
            <br><br>
            
            <br><br>
            
            <div class="container-login100-form-btn">
                <div class="wrap-login100-form-btn">
                    <div class="login100-form-bgbtn"></div>
            
            <button class="login100-form-btn" type="submit"  name="iniciar">
                Iniciar
            </button>
                    </div>
                </div>
           
            </div>
        </form>
                    </div>
		</div>
	</div>
	<div id="dropDownSelect1"></div>
	<!--===============================================================================================-->
	<script src="libreria/vendor/jquery/jquery-3.2.1.min.js"></script>
<!--===============================================================================================-->
	<script src="libreria/vendor/animsition/js/animsition.min.js"></script>
<!--===============================================================================================-->
	<script src="libreria/vendor/bootstrap/js/popper.js"></script>
	<script src="libreria/vendor/bootstrap/js/bootstrap.min.js"></script>
<!--===============================================================================================-->
	<script src="libreria/vendor/select2/select2.min.js"></script>
<!--===============================================================================================-->
	<script src="libreria/vendor/daterangepicker/moment.min.js"></script>
	<script src="libreria/vendor/daterangepicker/daterangepicker.js"></script>
<!--===============================================================================================-->
	<script src="libreria/vendor/countdowntime/countdowntime.js"></script>
<!--===============================================================================================-->
	<script src="libreria/js/main.js"></script>

        
        
    </body>
</html>
