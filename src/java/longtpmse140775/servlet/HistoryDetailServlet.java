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
import java.time.Year;
import java.util.ArrayList;
import java.util.Calendar;
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
@WebServlet(name = "HistoryDetailServlet", urlPatterns = {"/HistoryDetailServlet"})
public class HistoryDetailServlet extends HttpServlet {

    private final Logger LOGGER = Logger.getLogger(HistoryDetailServlet.class);
    private final String HISTORY_PAGE = "detail";

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
        String url = HISTORY_PAGE;
        String list = request.getParameter("room");
        String rate = request.getParameter("btnRate");
        Calendar cal = Calendar.getInstance();
        Year year = Year.now();
        int numYear = Integer.parseInt(year.toString());
        //int year = cal.getInstance().get(Calendar.YEAR);
        int month = cal.getInstance().get(Calendar.MONTH) + 1;
        int day = cal.getInstance().get(Calendar.DAY_OF_MONTH);
        String dates = numYear + "-" + month + "-" + day;
        Date currentDay = Date.valueOf(dates);
        boolean flag = false;
        CartDTO dto;
        CartDAO dao = new CartDAO();
        int ratePoint = 0;
        try {
            HttpSession session = request.getSession(false);
            UserDTO user = (UserDTO) session.getAttribute("NAME");

            if (user != null) {
                if (list != null) {
                    flag = dao.getDetailHistory(Integer.parseInt(list), user.getUsername());
//                    System.out.println("1");
                } else {
                    String room = (String) session.getAttribute("RATINGID");
                    flag = dao.getDetailHistory(Integer.parseInt(room), user.getUsername());
                    rate = (String) session.getAttribute("RATE");
                }
                List<CartDTO> result = dao.getListCart();
                List<CartDTO> rateRoom = new ArrayList<>();
                if (flag) {
                    if (rate == null) {
                        if (result != null) {
                            session.setAttribute("DETAILHISTORY", result);
                            session.removeAttribute("ERRORS");
                            session.removeAttribute("RATINGHISTORY");
                            session.removeAttribute("DELETERATING");
                        } else {
                            session.setAttribute("DELETERATING", "HISTORY HAVE BEEN DELETED");
                        }
                    } else {
                        if (result != null) {
                            session.removeAttribute("DELETERATING");
                            for (int i = 0; i < result.size(); i++) {
                                if (currentDay.after(result.get(i).getCheckout())) {
                                    if (dao.getRatingPoint(result.get(i).getDetaiId(), user.getUsername()) != (-1)) {
                                        ratePoint = dao.getRatingPoint(result.get(i).getDetaiId(), user.getUsername());
                                    } else {
                                        ratePoint = 0;
                                    }
                                    dto = new CartDTO(result.get(i).getDetaiId(), result.get(i).getRoomId(), result.get(i).getHotelName(), result.get(i).getCateName(), result.get(i).getQuantity(), result.get(i).getPrice(), result.get(i).getCheckin(), result.get(i).getCheckout(), ratePoint, result.get(i).getBookId());
                                    rateRoom.add(dto);
                                }
                            }
                            if (list != null) {
                                session.setAttribute("RATE", rate);
                                session.setAttribute("RATINGID", list);
                            }
                        } else {
                            session.setAttribute("DELETERATING", "HISTORY HAVE BEEN DELETED");
                        }
                        session.setAttribute("RATINGHISTORY", rateRoom);
                    }
                    session.removeAttribute("ERRORS");
                } else {
                    session.setAttribute("ERRORS", "EMPTY HISTORY");
                }
            }
        } catch (NamingException ex) {
            LOGGER.info("HistoryDetailServlet_NamingException: " + ex.getMessage());
        } catch (SQLException ex) {
            LOGGER.info("HistoryDetailServlet_SQLException: " + ex.getMessage());
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
