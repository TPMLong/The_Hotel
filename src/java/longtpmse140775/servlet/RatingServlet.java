/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longtpmse140775.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import longtpmse140775.cart.CartDAO;
import longtpmse140775.user.UserDTO;
import org.apache.log4j.Logger;

/**
 *
 * @author ACER
 */
@WebServlet(name = "RatingServlet", urlPatterns = {"/RatingServlet"})
public class RatingServlet extends HttpServlet {

    private final Logger LOGGER = Logger.getLogger(RatingServlet.class);
    private final String HISTORY_PAGE = "viewDetail";
    private final String LOGIN_PAGE = "try";

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
        PrintWriter out = response.getWriter();
        String room = request.getParameter("room");
        String detail = request.getParameter("detail");
        String cbx = request.getParameter("cboBook");
        String url = HISTORY_PAGE;
        HttpSession session = request.getSession(false);
        CartDAO dao = new CartDAO();
        try {
            UserDTO user = (UserDTO) session.getAttribute("NAME");
            if (user != null) {
                if (dao.checkStatusRating(Integer.parseInt(detail), user.getUsername())) {
                    
                    dao.updateRatingDetail(Integer.parseInt(cbx), Integer.parseInt(detail));
                } else {
                    dao.ratingHistoryDetail(user.getUsername(), Integer.parseInt(cbx), Integer.parseInt(detail), Integer.parseInt(room));
                }
            } else {
                url = LOGIN_PAGE;
            }
        } catch (NamingException ex) {
            LOGGER.info("RatingServlet_NamingException: " + ex.getMessage());
        } catch (SQLException ex) {
            LOGGER.info("RatingServlet_SQLException: " + ex.getMessage());
        } finally {
            response.sendRedirect(url);
            out.close();
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
