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
import longtpmse140775.ultil.VerifyRecaptcha;
import longtpmse140775.user.UserDAO;
import longtpmse140775.user.UserDTO;

import org.apache.log4j.Logger;

/**
 *
 * @author ACER
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {//v thi chua co code ma + jsp vao a

    private static final long serialVersionUID = -6506682026701304964L;
    private final String MAIN_PAGE = "show";
    private final String REGISTER_PAGE = "register";
    private final String LOGIN_PAGE = "try";
    private final Logger LOGGER = Logger.getLogger(LoginServlet.class);

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
        String url = LOGIN_PAGE;
        String buttonRes = request.getParameter("txtRes");
        String buttonLogin = request.getParameter("txtLogin");
        String username = request.getParameter("txtEmail");
        String password = request.getParameter("txtPassword");
        String gRecaptchaResponse = request.getParameter("g-recaptcha-response");
        HttpSession session = request.getSession();
        try {
            boolean verify = VerifyRecaptcha.verify(gRecaptchaResponse);
            if (buttonRes != null) {
                url = REGISTER_PAGE;
            }
            if (buttonLogin != null) {
                UserDAO dao = new UserDAO();
                boolean result = dao.checkLogin(username, password);

                if (result && verify) {
                    if (dao.checkRole(username, password)) {
                        UserDTO dto = dao.getUser();
                        session.removeAttribute("CAPTCHA");
                        session.removeAttribute("WRONGPASS");
                        session.setAttribute("NAME", dto);
                        if (dto.getRole().equals("admin")) {
                            session.setAttribute("ADMIN", dto.getRole());
                        }
                        url = MAIN_PAGE;
                    } else {
                        session.removeAttribute("CAPTCHA");
                        session.removeAttribute("WRONGPASS");
                        url = REGISTER_PAGE;
                    }
                } else {
                    if (!verify) {
                        session.setAttribute("CAPTCHA", "You have not check captcha");
                    } else {
                        session.setAttribute("WRONGPASS", "Your password is incorrect");
                    }
                }
            }
        } catch (NamingException ex) {
            LOGGER.info("LoginServlet_NamingException: " + ex.getMessage());
        } catch (SQLException ex) {
            LOGGER.info("LoginServlet_SQLException: " + ex.getMessage());
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
