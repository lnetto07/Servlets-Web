/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import simplejdbc.DAO;
import simplejdbc.DataSourceFactory;

/**
 *
 * @author Netto Léa
 */
@WebServlet(name = "ChoixEtats", urlPatterns = {"/ChoixEtats"})
public class ChoixEtats extends HttpServlet {

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
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ChoixEtats</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<form action='EtatClient'>");
            out.println("<select name='codeEtat'>");
            DAO dao = new DAO(DataSourceFactory.getDataSource());
            Set<String> listState = dao.States();
            for (String state : listState) {
                out.println("<option value='val'>");
                out.printf("%s", state);
                out.println("</option>");
            }
            out.println("</select>");
            out.println("<input type='submit' name='valider' value='valider'>");
            out.println("</form>");
            out.println("</body>");
            out.println("</html>");
        } catch (Exception ex) {
            Logger.getLogger("servlet").log(Level.SEVERE, "Erreur de traitement", ex);
        }
    }
}
