/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longtpmse140775.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
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
@WebServlet(name = "ShowHotelServlet", urlPatterns = {"/ShowHotelServlet"})
public class ShowHotelServlet extends HttpServlet {

    private final String SEARCH_PAGE = "view.jsp";
    //private final String LOGIN_PAGE = "try";
    private final Logger LOGGER = Logger.getLogger(ShowHotelServlet.class);

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
        String url = SEARCH_PAGE;
        try {
            RoomDAO dao = new RoomDAO();
            dao.showHotel();
            List<RoomDTO> result = dao.getListRoom();
            List<Integer> name = new ArrayList<>();
            List<RoomDTO> hotel = new ArrayList<>();
            int size = result.size();
            for (int i = 0; i < size; i++) {
                if(name.contains(result.get(i).getHotelID()) || (result.get(i).getQuantity()) <= 0){
                }else{
                    name.add(result.get(i).getHotelID());
                    hotel.add(result.get(i));
                }
            }
            request.setAttribute("SHOWRESULTS", hotel);
        } catch (NamingException ex) {
            LOGGER.info("ShowRoomServlet_NamingException: " + ex.getMessage());
        } catch (SQLException ex) {
            LOGGER.info("ShowRoomServlet_SQLException: " + ex.getMessage());
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
