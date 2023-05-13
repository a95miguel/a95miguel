package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class index_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <title>Control de eventos</title>\n");
      out.write("        <meta charset=\"UTF-8\">\n");
      out.write("\t<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n");
      out.write("<!--===============================================================================================-->\t\n");
      out.write("\t<link rel=\"icon\" type=\"image/png\" href=\"libreria/images/icons/favicon.ico\"/>\n");
      out.write("<!--===============================================================================================-->\n");
      out.write("\t<link rel=\"stylesheet\" type=\"text/css\" href=\"libreria/vendor/bootstrap/css/bootstrap.min.css\">\n");
      out.write("<!--===============================================================================================-->\n");
      out.write("    <a href=\"index.jsp\"></a>\n");
      out.write("\t<link rel=\"stylesheet\" type=\"text/css\" href=\"libreria/fonts/font-awesome-4.7.0/css/font-awesome.min.css\">\n");
      out.write("<!--===============================================================================================-->\n");
      out.write("\t<link rel=\"stylesheet\" type=\"text/css\" href=\"libreria/fonts/iconic/css/material-design-iconic-font.min.css\">\n");
      out.write("<!--===============================================================================================-->\n");
      out.write("\t<link rel=\"stylesheet\" type=\"text/css\" href=\"libreria/vendor/animate/animate.css\">\n");
      out.write("<!--===============================================================================================-->\t\n");
      out.write("\t<link rel=\"stylesheet\" type=\"text/css\" href=\"libreria/vendor/css-hamburgers/hamburgers.min.css\">\n");
      out.write("<!--===============================================================================================-->\n");
      out.write("\t<link rel=\"stylesheet\" type=\"text/css\" href=\"libreria/vendor/animsition/css/animsition.min.css\">\n");
      out.write("<!--===============================================================================================-->\n");
      out.write("\t<link rel=\"stylesheet\" type=\"text/css\" href=\"libreria/vendor/select2/select2.min.css\">\n");
      out.write("<!--===============================================================================================-->\t\n");
      out.write("\t<link rel=\"stylesheet\" type=\"text/css\" href=\"libreria/vendor/daterangepicker/daterangepicker.css\">\n");
      out.write("<!--===============================================================================================-->\n");
      out.write("\t<link rel=\"stylesheet\" type=\"text/css\" href=\"libreria/css/util.css\">\n");
      out.write("\t<link rel=\"stylesheet\" type=\"text/css\" href=\"libreria/css/main.css\">\n");
      out.write("<!--===============================================================================================-->\n");
      out.write("    <style>\n");
      out.write("        #alert{font-size:20px; color :#DC143C}\n");
      out.write("        \n");
      out.write("    </style>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        <div class=\"limiter\">\n");
      out.write("        <div class=\"container-login100\">\n");
      out.write("        <div class=\"wrap-login100\">\n");
      out.write("        <form class=\"login100-form validate-form\" action=\"LoginControl\" method=\"POST\">\n");
      out.write("                <span class=\"login100-form-title p-b-26\">\n");
      out.write("                        Bienvenido\n");
      out.write("                </span>\n");
      out.write("            <div id=\"alert\" >\n");
      out.write("                    ");
      out.print((request.getAttribute("mensaje")!=null?request.getAttribute("mensaje"):""));
      out.write("                                                                                            \n");
      out.write("                </div>\n");
      out.write("            <div class=\"wrap-input100 validate-input\" data-validate = \"Ingrese el nombre\">\n");
      out.write("            <input class=\"input100\" type=\"text\" name=\"nombre\">\n");
      out.write("            <span class=\"focus-input100\" data-placeholder=\"NOMBRE\"></span>\n");
      out.write("            </div>\n");
      out.write("            \n");
      out.write("            <div class=\"wrap-input100 validate-input\" data-validate=\"Ingrese password\">\n");
      out.write("            <span class=\"btn-show-pass\">\n");
      out.write("                    <i class=\"zmdi zmdi-eye\"></i>\n");
      out.write("            </span>\n");
      out.write("            <input class=\"input100\" type=\"password\" name=\"contrasena\">\n");
      out.write("           <span class=\"focus-input100\" data-placeholder=\"CONTRASEÑA\"></span>\n");
      out.write("            </div>\n");
      out.write("            \n");
      out.write("            <div >\n");
      out.write("            <select class=\"input100\" name=\"perfil_usuario\">\n");
      out.write("                    <option class=\"input100\">portero</option>\n");
      out.write("                    <option class=\"input100\">admin</option>\n");
      out.write("            </select>\t\t\t\t\t\t\n");
      out.write("            </div>\n");
      out.write("            <br><br>\n");
      out.write("            \n");
      out.write("            <br><br>\n");
      out.write("            \n");
      out.write("            <div class=\"container-login100-form-btn\">\n");
      out.write("                <div class=\"wrap-login100-form-btn\">\n");
      out.write("                    <div class=\"login100-form-bgbtn\"></div>\n");
      out.write("            \n");
      out.write("            <button class=\"login100-form-btn\" type=\"submit\"  name=\"iniciar\">\n");
      out.write("                Iniciar\n");
      out.write("            </button>\n");
      out.write("                    </div>\n");
      out.write("                </div>\n");
      out.write("           \n");
      out.write("            </div>\n");
      out.write("        </form>\n");
      out.write("                    </div>\n");
      out.write("\t\t</div>\n");
      out.write("\t</div>\n");
      out.write("\t<div id=\"dropDownSelect1\"></div>\n");
      out.write("\t<!--===============================================================================================-->\n");
      out.write("\t<script src=\"libreria/vendor/jquery/jquery-3.2.1.min.js\"></script>\n");
      out.write("<!--===============================================================================================-->\n");
      out.write("\t<script src=\"libreria/vendor/animsition/js/animsition.min.js\"></script>\n");
      out.write("<!--===============================================================================================-->\n");
      out.write("\t<script src=\"libreria/vendor/bootstrap/js/popper.js\"></script>\n");
      out.write("\t<script src=\"libreria/vendor/bootstrap/js/bootstrap.min.js\"></script>\n");
      out.write("<!--===============================================================================================-->\n");
      out.write("\t<script src=\"libreria/vendor/select2/select2.min.js\"></script>\n");
      out.write("<!--===============================================================================================-->\n");
      out.write("\t<script src=\"libreria/vendor/daterangepicker/moment.min.js\"></script>\n");
      out.write("\t<script src=\"libreria/vendor/daterangepicker/daterangepicker.js\"></script>\n");
      out.write("<!--===============================================================================================-->\n");
      out.write("\t<script src=\"libreria/vendor/countdowntime/countdowntime.js\"></script>\n");
      out.write("<!--===============================================================================================-->\n");
      out.write("\t<script src=\"libreria/js/main.js\"></script>\n");
      out.write("\n");
      out.write("        \n");
      out.write("        \n");
      out.write("    </body>\n");
      out.write("</html>\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
