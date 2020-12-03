/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longtpmse140775.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import longtpmse140775.cart.CartDAO;
import longtpmse140775.cart.CartDTO;
import org.apache.log4j.Logger;

/**
 *
 * @author ACER
 */
@WebServlet(name = "UseCodeServlet", urlPatterns = {"/UseCodeServlet"})
public class UseCodeServlet extends HttpServlet {

    private final String UPDATE_PAGE = "showCart";
    private final String LOGIN_PAGE = "try";
    private final Logger LOGGER = Logger.getLogger(UseCodeServlet.class);

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
        String code = request.getParameter("code");
        String url = LOGIN_PAGE;
        HttpSession session = request.getSession();
        try {
            if (code != null) {
                CartDAO dao = new CartDAO();
                if (dao.getCodeName(code) == 1) {
                    session.setAttribute("ERRORCODE", "Wrong code");
                    url = UPDATE_PAGE;
                } else {
                    // price
                    session.removeAttribute("ERRORCODE");
                    List<CartDTO> dto = (List<CartDTO>) session.getAttribute("ROOMDETAIL");
                    for (int i = 0; i < dto.size(); i++) {
                        dto.get(i).setPrice(dto.get(i).getPrice() * dao.getCodeName(code) / 100);
                    }
                    float price = (float) session.getAttribute("PRICE");
                    price = (price * dao.getCodeName(code)) / 100;
                    session.setAttribute("PRICE", price);
                    session.setAttribute("ROOMDETAIL", dto);
                    url = UPDATE_PAGE;
                }
            }
            /* TODO output your page here. You may use following sample code. */

        } catch (NamingException ex) {
            LOGGER.info("UseCodeServlet_NamingException: " + ex.getMessage());
        } catch (SQLException ex) {
            LOGGER.info("UseCodeServlet_SQLException: " + ex.getMessage());
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
