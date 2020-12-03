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
import java.util.Map;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import longtpmse140775.cart.CartDAO;
import longtpmse140775.cart.CartDTO;
import longtpmse140775.cart.CartObject;
import longtpmse140775.user.UserDTO;
import org.apache.log4j.Logger;

/**
 *
 * @author ACER
 */
@WebServlet(name = "DeleteCartServlet", urlPatterns = {"/DeleteCartServlet"})
public class DeleteCartServlet extends HttpServlet {

    private final String SHOW_PAGE = "showCart";
    private final String LOGIN_PAGE = "try";
    private final Logger LOGGER = Logger.getLogger(DeleteCartServlet.class);

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
        /* TODO output your page here. You may use following sample code. */
        String url = SHOW_PAGE;
        String removeCart = request.getParameter("remove");
        String checkin = request.getParameter("checkin");
        String checkout = request.getParameter("checkout");
        float total = 0;
        HttpSession session = request.getSession(false);
        try {
            
            UserDTO user = (UserDTO) session.getAttribute("NAME");
            if (user != null) {
                CartObject cart = (CartObject) session.getAttribute("CART");
                if (cart != null) {
                    CartDAO dao = new CartDAO();
                    cart.removeItemFromCart(Integer.parseInt(removeCart));
                    Map<Integer, Integer> name = cart.getItems();
                    if (name != null) {
                        for (Integer key : name.keySet()) {
                            dao.showDetailCart(key, name.get(key), Date.valueOf(checkin), Date.valueOf(checkout));
                            session.setAttribute("CART", cart);
                            List<CartDTO> result = dao.getListCart();
                            for (CartDTO cakesDTO : result) {
                                total = total + cakesDTO.getPrice() * cakesDTO.getQuantity();
                            }
                            session.setAttribute("ROOMDETAIL", result);
                            session.setAttribute("PRICE", total);
                        }
                    } else {
                        String mess = "EMPTY CART";
                        session.setAttribute("NULLDETAIL", mess);
                    }
                }
            }else{
                url = LOGIN_PAGE;
            }
        } catch (NamingException ex) {
            LOGGER.info("DeleteCartServlet_NamingException: " + ex.getMessage());
        } catch (SQLException ex) {
            LOGGER.info("DeleteCartServlet_SQLException: " + ex.getMessage());
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
