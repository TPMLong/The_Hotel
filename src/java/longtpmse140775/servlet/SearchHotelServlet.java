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
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import longtpmse140775.room.RoomDAO;
import longtpmse140775.room.RoomDTO;
import org.apache.log4j.Logger;

/**
 *
 * @author ACER
 */
@WebServlet(name = "SearchHotelServlet", urlPatterns = {"/SearchHotelServlet"})
public class SearchHotelServlet extends HttpServlet {

    private final Logger LOGGER = Logger.getLogger(SearchHotelServlet.class);
    private final String SEARCH_PAGE = "view.jsp";

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
        String checkout = request.getParameter("checkout");
        String option = request.getParameter("cboBook");
        String quantity = request.getParameter("txtQuantity");
        String searchValue = request.getParameter("txtSearch");
        Calendar cal = Calendar.getInstance();
        Year year = Year.now();
        int numYear = Integer.parseInt(year.toString());
        //int year = cal.getInstance().get(Calendar.YEAR);
        int month = cal.getInstance().get(Calendar.MONTH) + 1;
        int day = cal.getInstance().get(Calendar.DAY_OF_MONTH);
        String dates = numYear + "-" + month + "-" + day;
        Date currentDay = Date.valueOf(dates);
        String url = SEARCH_PAGE;
        List<RoomDTO> list = new ArrayList<>();
        boolean flag = false;
        Date inDay = null;
        Date outDay = null;
        RoomDAO dao = new RoomDAO();
        int num = 0;
        try {
            try {
                inDay = Date.valueOf(checkin);
                outDay = Date.valueOf(checkout);
            } catch (IllegalArgumentException ex) {
                LOGGER.info("SearchHotelServlet_IllegalArgumentException: " + ex.getMessage());
                flag = true;
            }
            if (flag == false) {
                if (inDay.before(currentDay) || outDay.before(inDay)) {
                    request.setAttribute("ERROR", "Invalid checkin or checkout day");
                } else {
//                System.out.println(dao.isOverlapping(Date.valueOf(checkin), Date.valueOf(checkin), Date.valueOf(checkout)));
//            if (quantity.equals("") && checkin.equals("") && checkout.equals("")) {
//                if (option.equals("name")) {
//                    dao.showHotelName(searchValue);
//                    List<RoomDTO> result = dao.getListRoom();
//                    request.setAttribute("NAMESEARCH", result);
//                } else {
//                    dao.showHotelArea(searchValue);
//                    List<RoomDTO> result = dao.getListRoom();
//                    request.setAttribute("NAMESEARCH", result);
//                }
//            } else {
//                if (!quantity.equals("") && checkin.equals("") && checkout.equals("")) {
//                    if (option.equals("name")) {
//                        dao.showHotelNameCate(searchValue, Integer.parseInt(quantity));
//                        List<RoomDTO> result = dao.getListRoom();
//                        request.setAttribute("NAMESEARCH", result);
//                    } else {
//                        dao.showHotelAreaCate(searchValue, Integer.parseInt(quantity));
//                        List<RoomDTO> result = dao.getListRoom();
//                        request.setAttribute("NAMESEARCH", result);
//                    }
//                } else {
                    dao.getBookingDate();
                    List<RoomDTO> date = dao.getDateRoom();
                    if (quantity.equals("") && !(checkin.equals("") && checkout.equals(""))) {
                        if (option.equals("name")) {
                            for (int i = 0; i < date.size(); i++) {
//                            if (dao.isOverlapping(date.get(i).getInDate(), date.get(i).getOutDate(), Date.valueOf(checkin), Date.valueOf(checkout))) {
                                if (dao.isOverlapping(Date.valueOf(checkin), Date.valueOf(checkout), date.get(i).getInDate(), date.get(i).getOutDate())) {
                                    list.add(date.get(i));
                                }
                            }
                            dao.showHotelName(searchValue);
                            List<RoomDTO> result = dao.getListRoom();
                            for (int i = 0; i < result.size(); i++) {
                                for (int j = 0; j < list.size(); j++) {
                                    if (!result.isEmpty()) {
                                        if (result.get(i).getRoomId() == date.get(j).getRoomId()) {
                                            result.remove(i);
                                        }
                                    } else {
                                        flag = true;
                                        break;
                                    }
                                }
                            }
                            request.setAttribute("IN", Date.valueOf(checkin));
                            request.setAttribute("OUT", Date.valueOf(checkout));
                            request.setAttribute("NAMESEARCH", result);
                        } else {
                            for (int i = 0; i < date.size(); i++) {
                                if (dao.isOverlapping(date.get(i).getInDate(), date.get(i).getOutDate(), Date.valueOf(checkin), Date.valueOf(checkout)) == true) {
                                    list.add(date.get(i));
                                }
                            }
                            dao.showHotelArea(searchValue);
                            List<RoomDTO> result = dao.getListRoom();
                            for (int i = 0; i < result.size(); i++) {
                                for (int j = 0; j < list.size(); j++) {
                                    if (!result.isEmpty()) {
                                        if (result.get(i).getRoomId() == date.get(j).getRoomId()) {
                                            result.remove(i);
                                        }
                                    } else {
                                        flag = true;
                                        break;
                                    }
                                }
                            }
                            request.setAttribute("IN", Date.valueOf(checkin));
                            request.setAttribute("OUT", Date.valueOf(checkout));
                            request.setAttribute("NAMESEARCH", result);
                        }
                    } else {
                        try {
                            num = Integer.parseInt(quantity);
                        } catch (NumberFormatException ex) {
                            LOGGER.info("SearchHotelServlet_NumberFormatException: " + ex.getMessage());
                            flag = true;
                        }
                        if (flag == false) {
                            if (!quantity.equals("") && !(checkin.equals("") && checkout.equals(""))) {
                                if (option.equals("name")) {
                                    for (int i = 0; i < date.size(); i++) {
                                        if (dao.isOverlapping(date.get(i).getInDate(), date.get(i).getOutDate(), Date.valueOf(checkin), Date.valueOf(checkout)) == true) {
                                            list.add(date.get(i));
                                        }
                                    }
                                    dao.showHotelNameCate(searchValue, num);

                                    List<RoomDTO> result = dao.getListRoom();
                                    if (result != null) {
                                        for (int i = 0; i < result.size(); i++) {
                                            for (int j = 0; j < list.size(); j++) {
                                                if (!result.isEmpty()) {
                                                    if (result.get(i).getRoomId() == date.get(j).getRoomId()) {
                                                        result.remove(i);
                                                    }
                                                } else {
                                                    flag = true;
                                                    break;
                                                }
                                            }
                                        }
                                        request.setAttribute("IN", Date.valueOf(checkin));
                                        request.setAttribute("OUT", Date.valueOf(checkout));
                                        request.setAttribute("NAMESEARCH", result);
                                    } else {
                                        flag = true;
                                    }
                                } else {
                                    for (int i = 0; i < date.size(); i++) {
                                        if (dao.isOverlapping(date.get(i).getInDate(), date.get(i).getOutDate(), Date.valueOf(checkin), Date.valueOf(checkout)) == true) {
                                            list.add(date.get(i));
                                        }
                                    }
                                    dao.showHotelAreaCate(searchValue, Integer.parseInt(quantity));
                                    List<RoomDTO> result = dao.getListRoom();
                                    for (int i = 0; i < result.size(); i++) {
                                        for (int j = 0; j < list.size(); j++) {
                                            if (!result.isEmpty()) {
                                                if (result.get(i).getRoomId() == date.get(j).getRoomId()) {
                                                    result.remove(i);
                                                }
                                            } else {
                                                flag = true;
                                                break;
                                            }
                                        }
                                    }
                                    request.setAttribute("IN", Date.valueOf(checkin));
                                    request.setAttribute("OUT", Date.valueOf(checkout));
                                    request.setAttribute("NAMESEARCH", result);
                                }
                            }
                        }
                    }
                }
            }
            if (flag == true) {
                request.setAttribute("ERROR", "CAN'T FIND ROOM");
            }
        } catch (NamingException ex) {
            LOGGER.info("SearchHotelServlet_NamingException: " + ex.getMessage());
        } catch (SQLException ex) {
            LOGGER.info("SearchHotelServlet_SQLException: " + ex.getMessage());
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
