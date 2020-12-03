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
import longtpmse140775.cart.CartDAO;
import longtpmse140775.cart.CartDTO;
import longtpmse140775.user.UserDAO;
import org.apache.log4j.Logger;

/**
 *
 * @author ACER
 */
@WebServlet(name = "CheckCodeServlet", urlPatterns = {"/CheckCodeServlet"})
public class CheckCodeServlet extends HttpServlet {

    private final String CHANGE_PAGE = "change.jsp";
    private final String LOGIN_PAGE = "login.jsp";
    private final Logger LOGGER = Logger.getLogger(CheckCodeServlet.class);

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
        String change = request.getParameter("key1");
        String user = request.getParameter("key2");
        String booking = request.getParameter("key3");
//        String gmail = request.getParameter("gcode");
//        String code = request.getParameter("tcode");
//        String ucode = request.getParameter("txtCode");
        String url = LOGIN_PAGE;
        try {
            if (booking == null) {
                UserDAO dao = new UserDAO();
                dao.getChangeCode(user);
                String code = dao.getChangeCode();
                if (change.equals(code)) {
                    request.setAttribute("CODE", code);
                    request.setAttribute("USER", user);
                    url = CHANGE_PAGE;
                }
            } else {
                UserDAO dao = new UserDAO();
                dao.getBookCode(Integer.parseInt(user));
                String code = dao.getChangeCode();
                if (change.equals(code)) {               
                    CartDAO cdao = new CartDAO();
                    cdao.confirmHistory(Integer.parseInt(user));
                    cdao.getDetailHistory(Integer.parseInt(user));
                    List<CartDTO> result = cdao.getListCart();
                    for (int i = 0; i < result.size(); i++) {  
                        cdao.updateQuantityRoom(result.get(i).getRoomId(), result.get(i).getQuantity());
                    }
                }
            }
        } catch (NamingException ex) {
            LOGGER.info("CheckCodeServlet_NamingException: " + ex.getMessage());
        } catch (SQLException ex) {
            LOGGER.info("CheckCodeServlet_SQLException: " + ex.getMessage());
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
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
