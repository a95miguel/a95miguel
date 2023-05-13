/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.LoginDao;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Miguel Medel Lozada
 */
public class LoginControl extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet LoginControl</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet LoginControl at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String nombre=request.getParameter("nombre");
        String contrasena=request.getParameter("contrasena");        
        String perfil_usuario=request.getParameter("perfil_usuario");
        LoginDao l=new LoginDao();     
        
        try {
            
           if("admin".equals(perfil_usuario)){
               if(l.authenticate(nombre,contrasena,perfil_usuario)){
                   
                  HttpSession objsesion=request.getSession(true);
                  
                  objsesion.setAttribute("nombre", nombre);
                  objsesion.setAttribute("contrasena", contrasena);
                  objsesion.setAttribute("perfil_usuario", perfil_usuario);                    
                  response.sendRedirect("Admon/dashboard.jsp");
                  
               }
               else{
                //JOptionPane.showMessageDialog(null,"Login incorrecto");
                request.setAttribute("mensaje", "Login incorrecto");
                request.getRequestDispatcher("index.jsp").forward(request, response);
                }
               
               
           }
           else if(l.authenticate(nombre,contrasena ,perfil_usuario)){
               HttpSession objsesion=request.getSession(true);
               objsesion.setAttribute("nombre", nombre);
               objsesion.setAttribute("contrasena", contrasena);
               objsesion.setAttribute("perfil_usuario", perfil_usuario);
               JOptionPane.showMessageDialog(null,"empleado");
               request.getRequestDispatcher("index.jsp").forward(request, response);
           }else{
                //JOptionPane.showMessageDialog(null,"Login incorrecto");
                request.setAttribute("mensaje", "Login incorrecto");
                request.getRequestDispatcher("index.jsp").forward(request, response);
           }
           
           
           
        } catch (Exception ex) {
            Logger.getLogger(LoginControl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
