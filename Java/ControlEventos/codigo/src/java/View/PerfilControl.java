/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.BDControl;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;
import model.Eventos;
import model.Invitaciones;
import model.Invitados;
import model.Perfiles;

/**
 *
 * @author Miguel Medel Lozada
 */
public class PerfilControl extends HttpServlet {

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
        /*
        try (PrintWriter out = response.getWriter()) {
            
         if(request.getParameter("accion").equals("eliminar")){
            Perfiles el=new Perfiles();            
            int id_usuario=Integer.parseInt(request.getParameter("id_usuario"));            
            el.setId_usuario(id_usuario);
            BDControl.eliPerfil(el);
            response.sendRedirect("Admon/Perfiles.jsp");       
            
         }
        }*/
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
        String accion = request.getParameter("accion");
        System.out.println("Do get");
        System.out.println(accion);
        switch (accion) {
            case "eliminar":
                eliPerfil(request, response);
                break;
            case "elimEvento":
                eliminarEvento(request, response);
                break;            
            case "elimInvitado":
                eliminarInvitado(request, response);
                break;
            case "elimInvitaciones":    
                eliminarInvitaciones(request, response);
                break;
        }
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
        String accion = request.getParameter("accion");
        System.out.println("Do Post");
        System.out.println(accion);
        switch (accion) {
            case "registrar":
                regPerfil(request, response);
                break;
            case "actualizar":
                actPerfil(request, response);
                break;            
            case "regEvento":
                regEvento(request, response);
                break;     
            case "actEvento":
                actEvento(request, response);
                break;
            case "regInvitado":
                regInvitado(request, response);
                break;
            case "actInvitado":
                actInvitados(request,response);
                break;                
            case "contInvitaciones":
                regInvitaciones(request, response);
                break;
                
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

    private void regPerfil(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {        
            String nombre=request.getParameter("nombre");
            String apellido=request.getParameter("apellido");
            String contrasena=request.getParameter("contrasena");
            String perfil_usuario=request.getParameter("perfil_usuario");

            if(nombre.equals("") || apellido.equals("") || contrasena.equals("") || perfil_usuario.equals("")){                              
                response.sendRedirect("Admon/Perfiles.jsp");        
            }else{
             Perfiles c=new Perfiles();
            c.setNombre(nombre);
            c.setApellido(apellido);
            c.setContrasena(contrasena);
            c.setPerfil_usuario(perfil_usuario);

            BDControl.registrarPerfil(c);
            response.sendRedirect("Admon/Perfiles.jsp");        
            }
    }

    private void actPerfil(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
            int id_usuario=Integer.parseInt(request.getParameter("id_usuario"));
            String nombre=request.getParameter("nombre");
            String apellido=request.getParameter("apellido");
            String contrasena=request.getParameter("contrasena");
            String perfil_usuario=request.getParameter("perfil_usuario");
            
            if(nombre.equals("") || apellido.equals("") || contrasena.equals("") || perfil_usuario.equals("")){                              
                JOptionPane.showMessageDialog(null, "Error Verefique los datos");                
            }else{            
            Perfiles a=new Perfiles();
            a.setId_usuario(id_usuario);
            a.setNombre(nombre);
            a.setApellido(apellido);
            a.setContrasena(contrasena);
            a.setPerfil_usuario(perfil_usuario);

            BDControl.actualizar(a);
            response.sendRedirect("Admon/Perfiles.jsp");        
            }
    }

    private void eliPerfil(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            int id_usuario=Integer.parseInt(request.getParameter("id_usuario"));
            Perfiles el=new Perfiles();
            el.setId_usuario(id_usuario);
            BDControl.eliPerfil(el);
            response.sendRedirect("Admon/Perfiles.jsp");                                    
    }

    private void regEvento(HttpServletRequest request, HttpServletResponse response) 
             throws ServletException, IOException { 
        String nom_eve=request.getParameter("nom_eve");
        String fecha=request.getParameter("fecha");
        String ubicacion=request.getParameter("ubicacion");
        if(nom_eve.equals("") || fecha.equals("") || ubicacion.equals("")){
            response.sendRedirect("Admon/Eveto.jsp");
        }else{
            Eventos r=new Eventos();
            r.setNom_eve(nom_eve);
            r.setFecha(fecha);
            r.setUbicacion(ubicacion);
            BDControl.regEvento(r);
            response.sendRedirect("Admon/Evento.jsp");
        }
        
    }

    private void actEvento(HttpServletRequest request, HttpServletResponse response) 
             throws ServletException, IOException { 
        int id_evento=Integer.parseInt(request.getParameter("id_evento"));
        String nom_eve=request.getParameter("nom_eve");
        String fecha=request.getParameter("fecha");
        String ubicacion=request.getParameter("ubicacion");
        if(nom_eve.equals("") || fecha.equals("") || ubicacion.equals("") ){
            response.sendRedirect("Admon/Evento.jsp");
        }else{
            Eventos p=new Eventos();
            p.setId_evento(id_evento);
            p.setNom_eve(nom_eve);
            p.setFecha(fecha);
            p.setUbicacion(ubicacion);
            BDControl.actEventos(p);
            response.sendRedirect("Admon/Evento.jsp");
        }        
    }

    private void eliminarEvento(HttpServletRequest request, HttpServletResponse response) 
             throws ServletException, IOException { 
        int id_evento=Integer.parseInt(request.getParameter("id_evento"));
        Eventos p=new Eventos();
        p.setId_evento(id_evento);
        BDControl.elimEvento(p);
        response.sendRedirect("Admon/Evento.jsp");
    }

    private void regInvitado(HttpServletRequest request, HttpServletResponse response) 
             throws ServletException, IOException { 
        String nom_invi=request.getParameter("nom_invi");
        String apellido=request.getParameter("apellido");
        String telefono=request.getParameter("telefono");
        String direccion=request.getParameter("direccion");
        String sexo=request.getParameter("sexo");
        if(nom_invi.equals("") || apellido.equals("") || telefono.equals("") || direccion.equals("") || sexo.equals("")){
            response.sendRedirect("Admon/Invitados.jsp");
        }else{
            Invitados i=new Invitados();
            i.setNom_invi(nom_invi);
            i.setApellido(apellido);
            i.setTelefono(telefono);
            i.setDireccion(direccion);
            i.setSexo(sexo);
            BDControl.registrarInvitado(i);
            response.sendRedirect("Admon/Invitados.jsp");
        }
        
    }

    private void eliminarInvitado(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException { 
        int id_invitado=Integer.parseInt(request.getParameter("id_invitado"));
        Invitados i= new Invitados();
        i.setId_invitado(id_invitado);
        BDControl.elimInvitados(i);
        response.sendRedirect("Admon/Invitados.jsp");
    }

    private void actInvitados(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException { 
        int id_invitado=Integer.parseInt(request.getParameter("id_invitado"));
        String nom_invi=request.getParameter("nom_invi");
        String apellido=request.getParameter("apellido");
        String telefono=request.getParameter("telefono");
        String direccion=request.getParameter("direccion");
        String sexo=request.getParameter("sexo");
        if(nom_invi.equals("") || apellido.equals("") || telefono.equals("") || direccion.equals("") || sexo.equals("")){
            response.sendRedirect("Admon/Invitados.jsp");
        }else{
            Invitados i=new Invitados();
            i.setId_invitado(id_invitado);
            i.setNom_invi(nom_invi);
            i.setApellido(apellido);
            i.setTelefono(telefono);
            i.setDireccion(direccion);
            i.setSexo(sexo);
            BDControl.actInvitados(i);
            response.sendRedirect("Admon/Invitados.jsp");
        }
        
    }

    private void regInvitaciones(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException { 
        int invitado=Integer.parseInt(request.getParameter("invitado"));
        int evento=Integer.parseInt(request.getParameter("evento"));
        
        Invitaciones w=new Invitaciones();
        w.setInvitado(invitado);
        w.setEvento(evento);
        BDControl.regInvitaciones(w);
        response.sendRedirect("Admon/ControlInvitados.jsp");
    }

    private void eliminarInvitaciones(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException { 
        int id_invitaciones=Integer.parseInt(request.getParameter("id_invitaciones"));
        Invitaciones w= new Invitaciones();
        w.setId_invitaciones(id_invitaciones);
        BDControl.elimInvitaciones(w);
        response.sendRedirect("Admon/ControlInvitados.jsp");
    }

}

   