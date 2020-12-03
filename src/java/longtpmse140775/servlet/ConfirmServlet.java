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
import java.util.Map;
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
import longtpmse140775.cart.CartObject;
import longtpmse140775.room.RoomDAO;
import longtpmse140775.ultil.SendMail;
import longtpmse140775.user.UserDTO;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;

/**
 *
 * @author ACER
 */
@WebServlet(name = "ConfirmServlet", urlPatterns = {"/ConfirmServlet"})
public class ConfirmServlet extends HttpServlet {

    private final String UPDATE_PAGE = "cart.jsp";
    private final String RESET_PAGE = "reset.jsp";
    private final Logger LOGGER = Logger.getLogger(ConfirmServlet.class);

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
        String url = UPDATE_PAGE;
        /* TODO output your page here. You may use following sample code. */
        HttpSession session = request.getSession(false);
        CartDAO cdao = new CartDAO();
        RoomDAO dao = new RoomDAO();
        String mess = "";
        boolean flag = false;

        int codes = (int) Math.floor(((Math.random() * 899999) + 100000));
        try {
            UserDTO user = (UserDTO) session.getAttribute("NAME");
            if (user != null) {
                CartObject cart = (CartObject) session.getAttribute("CART");
                List<CartDTO> dto = (List<CartDTO>) session.getAttribute("ROOMDETAIL");
                Map<Integer, Integer> id = cart.getItems();
                for (Integer key : id.keySet()) {
                    if (dao.checkQuantityCart(key, id.get(key)) == false) {

                        String error = "Out of order";
                        session.setAttribute("ERRORCONFIRM", error);
                        flag = true;
                        break;
                    }
                }
                if (flag == false) {
                    try {
                        String eChange = DigestUtils.sha256Hex(String.valueOf(codes));

                        dao.updateHistory(user.getUsername(), eChange);
                        int num = dao.getId();
                        for (int i = 0; i < dto.size(); i++) {
                            cdao.updateHistoryDetail(dto.get(i).getRoomId(), num, dto.get(i).getQuantity(), dto.get(i).getPrice(), dto.get(i).getCheckin(), dto.get(i).getCheckout());
                            mess = mess + "Room id: " + dto.get(i).getRoomId() + "\n" + "Booking id: " + num + "\n" + "Number of room: " + dto.get(i).getQuantity() + "\n" + "price: " + dto.get(i).getPrice() + "\n" + "Checkin day: " + dto.get(i).getCheckin() + "\n" + "Checkout day: " + dto.get(i).getCheckout() + "\n\n";
                        }
                        mess = mess + "YOUR CONFIRM LINK IS: " + "http://localhost:8080/The_Hotel/CheckCodeServlet?key1=" + eChange + "&key2=" + dao.getId() + "&key3=" + dao.getId();
                        SendMail sm = new SendMail(user.getUsername(), mess);
                        sm.sendMailBooking();
                        session.removeAttribute("ERRORCONFIRM");
                        session.removeAttribute("CART");
                        request.setAttribute("MESS", "An email to confirm booking have been sent to your email!!!");

                        url = RESET_PAGE;

                    } catch (NullPointerException ex) {
                        LOGGER.info("ConfirmServlet_NullPointerException: " + ex.getMessage());
                    }
                }
            }
        } catch (NamingException ex) {
            LOGGER.info("ConfirmServlet_NamingException: " + ex.getMessage());
        } catch (SQLException ex) {
            LOGGER.info("ConfirmServlet_SQLException: " + ex.getMessage());
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
