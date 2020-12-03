/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longtpmse140775.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import longtpmse140775.cart.CartDAO;
import longtpmse140775.cart.CartDTO;
import longtpmse140775.user.UserDTO;
import org.apache.log4j.Logger;

/**
 *
 * @author ACER
 */
@WebServlet(name = "SearchDateServlet", urlPatterns = {"/SearchDateServlet"})
public class SearchDateServlet extends HttpServlet {

    private final Logger LOGGER = Logger.getLogger(SearchDateServlet.class);
    private final String SEARCH_PAGE = "showHistory";
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
        String checkin = request.getParameter("checkin");
        String cbx = request.getParameter("cboBook");
        String url = SEARCH_PAGE;
        HttpSession session = request.getSession(false);
        CartDAO dao = new CartDAO();
        Date inDay = null;
        boolean flag = false;
        int id = 0;
        try {
            UserDTO user = (UserDTO) session.getAttribute("NAME");
            if (user != null) {
                if (cbx.equals("checkin")) {
                    try {
                        inDay = Date.valueOf(checkin);
                    } catch (IllegalArgumentException ex) {
                        LOGGER.info("SearchDateServlet_IllegalArgumentException: " + ex.getMessage());
                        flag = true;
                    }
                    if (flag == false) {
                        dao.getHistoryDate(inDay, user.getUsername());
                        List<CartDTO> result = dao.getListCart();
                        session.setAttribute("HISTORY", result);
                        session.removeAttribute("INPUTDATE");
                    } else {
                        session.setAttribute("INPUTDATE", "Input right date format");
                    }
                } else {
                    try {
                        id = Integer.parseInt(checkin);
                    } catch (NumberFormatException ex) {
                        LOGGER.info("SearchDateServlet_NumberFormatException: " + ex.getMessage());
                        flag = true;
                    }
                    if (flag == false) {
                        dao.getHistoryName(id, user.getUsername());
                        List<CartDTO> result = dao.getListCart();
                        session.setAttribute("HISTORY", result);
                        session.removeAttribute("INPUTDATE");
                    } else {
                        session.setAttribute("INPUTDATE", "Input right name format");
                    }
                }

            } else {
                url = LOGIN_PAGE;
            }
        } catch (NamingException ex) {
            LOGGER.info("SearchDateServlet_NamingException: " + ex.getMessage());
        } catch (SQLException ex) {
            LOGGER.info("SearchDateServlet_SQLException: " + ex.getMessage());
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
