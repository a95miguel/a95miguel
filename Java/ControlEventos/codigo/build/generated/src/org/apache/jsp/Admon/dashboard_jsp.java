package org.apache.jsp.Admon;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import dao.Conexion;
import java.sql.Connection;

public final class dashboard_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList<String>(2);
    _jspx_dependants.add("/Admon/includes/header.jsp");
    _jspx_dependants.add("/Admon/includes/footer.jsp");
  }

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
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("    ");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html lang=\"en\">\n");
      out.write("    <head>\n");
      out.write("        <meta charset=\"utf-8\">\n");
      out.write("        <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n");
      out.write("        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n");
      out.write("        <meta name=\"description\" content=\"\">\n");
      out.write("        <meta name=\"author\" content=\"\">\n");
      out.write("        <title>Administrador</title>\n");
      out.write("        \n");
      out.write("        <!-- Bootstrap Core CSS -->\n");
      out.write("        <link href=\"includes/libreria/css/bootstrap.min.css\" rel=\"stylesheet\">\n");
      out.write("\n");
      out.write("        <!-- MetisMenu CSS -->\n");
      out.write("        <link href=\"includes/libreria/css/metisMenu.min.css\" rel=\"stylesheet\">\n");
      out.write("\n");
      out.write("        <!-- Timeline CSS -->\n");
      out.write("        <link href=\"includes/libreria/css/timeline.css\" rel=\"stylesheet\">\n");
      out.write("\n");
      out.write("        <!-- Custom CSS -->\n");
      out.write("        <link href=\"includes/libreria/css/startmin.css\" rel=\"stylesheet\">\n");
      out.write("\n");
      out.write("        <!-- Morris Charts CSS -->\n");
      out.write("        <link href=\"includes/libreria/css/morris.css\" rel=\"stylesheet\">\n");
      out.write("\n");
      out.write("        <!-- Custom Fonts -->\n");
      out.write("        <link href=\"includes/libreria/css/font-awesome.min.css\" rel=\"stylesheet\" type=\"text/css\">\n");
      out.write("        \n");
      out.write("            \n");
      out.write("\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        ");

            String user = (String)session.getAttribute("nombre");
            if(user!=null){
            
        
      out.write("   \n");
      out.write("        <div id=\"wrapper\">                \n");
      out.write("\n");
      out.write("            <!-- Navigation -->\n");
      out.write("            <nav class=\"navbar navbar-inverse navbar-fixed-top\" role=\"navigation\">            \n");
      out.write("                <div class=\"navbar-header\">\n");
      out.write("                    <a class=\"navbar-brand\">Eventos</a>\n");
      out.write("                </div>\n");
      out.write("                   <button type=\"button\" class=\"navbar-toggle\" data-toggle=\"collapse\" data-target=\".navbar-collapse\">\n");
      out.write("                    <span class=\"sr-only\">Toggle navigation</span>\n");
      out.write("                    <span class=\"icon-bar\"></span>\n");
      out.write("                    <span class=\"icon-bar\"></span>\n");
      out.write("                    <span class=\"icon-bar\"></span>\n");
      out.write("                </button>\n");
      out.write("          \n");
      out.write("                    \n");
      out.write("                <form action=\"../Logout\" method=\"post\">\n");
      out.write("                <ul class=\"nav navbar-right navbar-top-links\">                    \n");
      out.write("                    <li class=\"dropdown\">\n");
      out.write("                        <a class=\"dropdown-toggle\" data-toggle=\"dropdown\" href=\"#\">\n");
      out.write("                            <i class=\"fa fa-user fa-fw\"></i> ");
      out.print((String)session.getAttribute("nombre"));
      out.write("   <b class=\"caret\"></b>\n");
      out.write("                        </a>\n");
      out.write("                        <ul class=\"dropdown-menu dropdown-user\">\n");
      out.write("                            <li><a href=\"Perfiles.jsp\"><i class=\"fa fa-user fa-fw\"></i> Perfiles</a>\n");
      out.write("                            </li>\n");
      out.write("                            <li><a href=\"#\"><i class=\"fa fa-gear fa-fw\"></i> Usuarios</a>\n");
      out.write("                            </li>\n");
      out.write("                            \n");
      out.write("                            <li class=\"divider\"></li>\n");
      out.write("                            \n");
      out.write("                            <button type=\"submit\" name=\"accion\" class=\"btn btn-block btn-default\"><i class=\"fa fa-sign-out fa-fw\"></i> Salir </button>                            \n");
      out.write("                            \n");
      out.write("                        </ul>\n");
      out.write("                    </li>\n");
      out.write("                </ul>\n");
      out.write("                </form>                       \n");
      out.write("                        \n");
      out.write("                <!-- /.navbar-top-links -->\n");
      out.write("\n");
      out.write("                <div class=\"navbar-default sidebar\" role=\"navigation\">\n");
      out.write("                    <div class=\"sidebar-nav navbar-collapse\">\n");
      out.write("                        <ul class=\"nav\" id=\"side-menu\">\n");
      out.write("                            <li>\n");
      out.write("                                <a href=\"dashboard.jsp\" class=\"active\"><i class=\"fa fa-dashboard fa-fw\"></i> Dashboard</a>\n");
      out.write("                            </li>\n");
      out.write("                            \n");
      out.write("                            <li>\n");
      out.write("                                <a href=\"Evento.jsp\" class=\"active\"><i class=\"glyphicon glyphicon-menu-hamburger\"></i> Eventos</a>\n");
      out.write("                            </li>\n");
      out.write("                            \n");
      out.write("                            <li>\n");
      out.write("                                <a href=\"Invitados.jsp\" class=\"active\"><i class=\"fa fa-sticky-note-o\"></i> Invitados</a>\n");
      out.write("                            </li>\n");
      out.write("                            \n");
      out.write("                            <li>\n");
      out.write("                                <a href=\"ControlInvitados.jsp\" class=\"active\"><i class=\"glyphicon glyphicon-check\"></i> Invitacciones</a>\n");
      out.write("                            </li>\n");
      out.write("                            \n");
      out.write("                        \n");
      out.write("                        </ul>\n");
      out.write("                        </div>\n");
      out.write("                     \n");
      out.write("                    </div>\n");
      out.write("                \n");
      out.write("\n");
      out.write("            </nav>\n");
      out.write("         \n");
      out.write("     ");
}else{
            response.sendRedirect("../index.jsp");
        
        }
      out.write("\n");
      out.write("    \n");
      out.write("    <br><br>\n");
      out.write("<div id=\"page-wrapper\">\n");
      out.write("    <div class=\"row\">\n");
      out.write("        \n");
      out.write("    <div class=\"col-lg-3 col-md-6\">\n");
      out.write("        <div class=\"panel panel-red\">\n");
      out.write("            <div class=\"panel-heading\">\n");
      out.write("                <div class=\"row\">\n");
      out.write("                    <div class=\"col-xs-3\">\n");
      out.write("                        <i class=\"fa fa-support fa-5x\"></i>\n");
      out.write("                    </div>\n");
      out.write("                    ");
 
                        Connection con=Conexion.conectar();
                        PreparedStatement ps;
                        ResultSet rs;                                        
                        ps=con.prepareStatement("select count(lista) from invitaciones where lista='falta';");
                        rs=ps.executeQuery();
                        while(rs.next()){                  
                                    
      out.write("\n");
      out.write("                    \n");
      out.write("                    \n");
      out.write("                    <div class=\"col-xs-9 text-right\">\n");
      out.write("                        <div class=\"huge\">");
      out.print( rs.getInt(1));
      out.write("</div>\n");
      out.write("                        <div>Faltan Personas!</div>\n");
      out.write("                    </div>\n");
      out.write("                        ");
}
      out.write("\n");
      out.write("                </div>\n");
      out.write("            </div>\n");
      out.write("            <a href=\"#\">\n");
      out.write("                <div class=\"panel-footer\">\n");
      out.write("                    <span class=\"pull-left\">Ver Lista</span>\n");
      out.write("                    <span class=\"pull-right\"><i class=\"fa fa-arrow-circle-right\"></i></span>\n");
      out.write("\n");
      out.write("                    <div class=\"clearfix\"></div>\n");
      out.write("                </div>\n");
      out.write("            </a>\n");
      out.write("        </div>\n");
      out.write("    </div>\n");
      out.write("    \n");
      out.write("    </div>\n");
      out.write("  </div> \n");
      out.write("    \n");
      out.write("    \n");
      out.write("    ");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!-- /.panel-body -->\n");
      out.write("                           \n");
      out.write("                            <!-- /.panel-footer -->\n");
      out.write("                        </div>\n");
      out.write("                        <!-- /.panel .chat-panel -->\n");
      out.write("                    </div>\n");
      out.write("                    <!-- /.col-lg-4 -->\n");
      out.write("                </div>\n");
      out.write("                <!-- /.row -->\n");
      out.write("            </div>\n");
      out.write("            <!-- /#page-wrapper -->\n");
      out.write("\n");
      out.write("        </div>\n");
      out.write("        <!-- /#wrapper -->\n");
      out.write("\n");
      out.write("        <!-- jQuery -->\n");
      out.write("        <script src=\"includes/libreria/js/jquery.min.js\"></script>\n");
      out.write("\n");
      out.write("        <!-- Bootstrap Core JavaScript -->\n");
      out.write("        <script src=\"includes/libreria/js/bootstrap.min.js\"></script>\n");
      out.write("\n");
      out.write("        <!-- Metis Menu Plugin JavaScript -->\n");
      out.write("        <script src=\"includes/libreria/js/metisMenu.min.js\"></script>\n");
      out.write("\n");
      out.write("        <!-- Morris Charts JavaScript -->\n");
      out.write("        <script src=\"includes/libreria/js/raphael.min.js\"></script>\n");
      out.write("        <script src=\"includes/libreria/js/morris.min.js\"></script>\n");
      out.write("        <script src=\"includes/libreria/js/morris-data.js\"></script>\n");
      out.write("\n");
      out.write("        <!-- Custom Theme JavaScript -->\n");
      out.write("        <script src=\"includes/libreria/js/startmin.js\"></script>\n");
      out.write("\n");
      out.write("    </body>\n");
      out.write("       \n");
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
