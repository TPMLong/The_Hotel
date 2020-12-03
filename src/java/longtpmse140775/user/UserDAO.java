/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longtpmse140775.user;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Year;
import java.util.Calendar;
import javax.naming.NamingException;
import longtpmse140775.ultil.DBHelper;
import org.apache.commons.codec.digest.DigestUtils;

/**
 *
 * @author ACER
 */
public class UserDAO implements Serializable {

    private UserDTO user;

    public UserDTO getUser() {
        return user;
    }

    private String changeCode;

    public String getChangeCode() {
        return changeCode;
    }

    public boolean checkLogin(String id, String password)
            throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "Select r.name, u.name, u.status From tblUser as u, tblRole as r Where u.userId = ? and u.password = ? and u.roleId = r.roleId";
                stm = con.prepareStatement(sql);
                stm.setString(1, id);
                String ePassword = DigestUtils.sha256Hex(password);
                stm.setString(2, ePassword);
                rs = stm.executeQuery();
                if (rs.next()) {
//                    String role = rs.getString(1);
//                    String name = rs.getString(2);
//                    String gmail = id;
                    return true;

                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }

    public boolean checkRole(String id, String password)
            throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "Select r.name, u.name, u.status From tblUser as u, tblRole as r Where u.userId = ? and u.password = ? and u.roleId = r.roleId";
                stm = con.prepareStatement(sql);
                stm.setString(1, id);
                String ePassword = DigestUtils.sha256Hex(password);
                stm.setString(2, ePassword);
                rs = stm.executeQuery();
                if (rs.next()) {
                    String role = rs.getString(1);
                    String name = rs.getString(2);
                    String gmail = id;
                    if (role.equals("admin") || role.equals("member")) {
                        user = new UserDTO(name, role, gmail);
                        return true;
                    } else {
                        return false;
                    }
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }

    public boolean updateChangeCode(String id, String change)
            throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "UPDATE tblUser SET change = ? WHERE userId = ?";
                stm = con.prepareStatement(sql);
                String eChange = DigestUtils.sha256Hex(change);
                stm.setString(1, eChange);
                stm.setString(2, id);
                int row = stm.executeUpdate();
                if (row > 0) {
                    return true;
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }

    public boolean getChangeCode(String id)
            throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "Select change From tblUser Where userId = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, id);
                rs = stm.executeQuery();
                if (rs.next()) {
                    changeCode = rs.getString("change");
                    return true;
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }

    public boolean getBookCode(int id)
            throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {

                String sql = "Select code From tblBooking Where bookId = ?";
                stm = con.prepareStatement(sql);
                stm.setInt(1, id);
                rs = stm.executeQuery();
                if (rs.next()) {
                    changeCode = rs.getString("code");
                    return true;
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }

    public boolean createNewAccount(String id, String password, String name, String phone, String address) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        Calendar cal = Calendar.getInstance();
        Year year = Year.now();
        int numYear = Integer.parseInt(year.toString());
        //int year = cal.getInstance().get(Calendar.YEAR);
        int month = cal.getInstance().get(Calendar.MONTH) + 1;
        int day = cal.getInstance().get(Calendar.DAY_OF_MONTH);
        String dates = numYear + "-" + month + "-" + day;
        Date date = Date.valueOf(dates);
        try {
            //1.make  connection

            con = DBHelper.makeConnection();
            if (con != null) {
                //2. Create SQL String
                String sql = "INSERT INTO tblUser(userId, password, roleId, name, phone, address, createDate, status) "
                        + "Values(?,?,?,?,?,?,?,?)";
                //3.Create Connection
                stm = con.prepareStatement(sql);
                stm.setString(1, id);
                String ePassword = DigestUtils.sha256Hex(password);
                stm.setString(2, ePassword);
                stm.setString(3, "1");
                stm.setString(4, name);
                stm.setString(5, phone);
                stm.setString(6, address);
                stm.setDate(7, date);
                stm.setString(8, "active");
                int row = stm.executeUpdate();
                if (row > 0) {
                    return true;
                }
            }
        } finally {

            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }

    public boolean changePassword(String id, String password)
            throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "UPDATE tblUser SET password = ? WHERE userId = ?";
                stm = con.prepareStatement(sql);
                String ePassword = DigestUtils.sha256Hex(password);
                stm.setString(1, ePassword);
                stm.setString(2, id);
                int row = stm.executeUpdate();
                if (row > 0) {
                    return true;
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }
}
