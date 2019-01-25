/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import simplejdbc.CustomerEntity;
import simplejdbc.DAO;
import simplejdbc.DataSourceFactory;

/**
 *
 * @author Netto Léa
 */
@WebServlet(name = "EtatsClients", urlPatterns = {"/EtatsClients"})
public class EtatsClients extends HttpServlet {

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
            out.println("<title>Servlet EtatsClients</title>"); 
            out.println("<style type=\"text/css\">td,th{border:1px solid black} </style>");
            out.println("</head>");
            out.println("<body>");
            try {   // Trouver la valeur du paramètre HTTP state
                String state = request.getParameter("codeEtat");
                if (state == null) {
                    throw new Exception("La paramètre codeEtat n'a pas été transmis");
                }
                DAO dao = new DAO(DataSourceFactory.getDataSource());
                List<CustomerEntity> listCustomer = dao.customersInState(state);
                if (listCustomer.isEmpty()) {
                    throw new Exception("pas de client dans cet état");
                }
                // Afficher les propriétés des clients de l'état
                else{
                    out.println("<table>");
                    out.println("<thead>");
                    out.println("<tr>");
                    out.println("<th>Id</th>");
                    out.println("<th>Name</th>");
                    out.println("<th>Adresse</th>");
                    out.println("</tr>");
                    out.println("</thead>");
                    out.println("<tbody>");
                    for (CustomerEntity customer :listCustomer){
                        out.println("<tr>");
                        out.println("<td>");
                        out.printf(" n°%s",customer.getCustomerId());
                        out.println("</td>");
                        out.println("<td>");
                        out.printf("%s",customer.getName());
                        out.println("</td>");
                        out.println("<td>");
                        out.printf(" %s",customer.getAddressLine1());
                        out.println("</td>");
                        out.println("</tr>");
                    }
                    out.println("</tbody>");
                    out.println("</table>");
                }
            out.printf("<hr><a href='%s'>Retour au menu</a>", request.getContextPath());
            out.println("</body>");
            out.println("</html>");
        } catch (Exception ex) {
            Logger.getLogger("servlet").log(Level.SEVERE, "Erreur de traitement", ex);
        }
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
        processRequest(request, response);
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
