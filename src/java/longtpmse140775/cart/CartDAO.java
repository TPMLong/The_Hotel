/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longtpmse140775.cart;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Year;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.naming.NamingException;
import longtpmse140775.ultil.DBHelper;

/**
 *
 * @author ACER
 */
public class CartDAO implements Serializable {

    private List<CartDTO> listCart;

    public List<CartDTO> getListCart() {
        return listCart;
    }

    public void showDetailCart(int id, int quantity, Date in, Date out)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "SELECT r.roomId, h.name, c.name, c.price "
                        + "FROM tblHotel as h, tblCate as c, tblRoom as r "
                        + "WHERE r.hotelId = h.hotelId AND r.cateId = c.cateId AND r.roomId = ? ";
                stm = con.prepareStatement(sql);
                stm.setInt(1, id);
                rs = stm.executeQuery();
                if (rs.next()) {
                    int ids = rs.getInt(1);
                    String hotelName = rs.getString(2);
                    String cateName = rs.getString(3);
                    float price = rs.getFloat(4);
                    CartDTO dto = new CartDTO(ids, hotelName, cateName, quantity, price, in, out);
                    if (this.listCart == null) {
                        this.listCart = new ArrayList();
                    }
                    this.listCart.add(dto);
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
    }

    public int updateHistoryDetail(int roomId, int bookId, int quantity, float price, Date in, Date out)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {

            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "INSERT INTO tblBookingDetail(roomId, bookId, quantity, price, checkinDate, checkoutDate) "
                        + "Values(?,?,?,?,?,?)";
                stm = con.prepareStatement(sql);
                stm.setInt(1, roomId);
                stm.setInt(2, bookId);
                stm.setInt(3, quantity);
                stm.setFloat(4, price);
                stm.setDate(5, in);
                stm.setDate(6, out);
                int row = stm.executeUpdate();
                if (row > 0) {
                    return quantity;
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
        return 0;
    }

    public int updateQuantityRoom(int roomId, int quantity)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "UPDATE tblRoom set quantity = quantity - ? WHERE roomId = ?";
                stm = con.prepareStatement(sql);
                stm.setInt(1, quantity);
                stm.setInt(2, roomId);
                int row = stm.executeUpdate();
                if (row > 0) {
                    return quantity;
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
        return 0;
    }

    public boolean getHistory(String id) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "SELECT bookId, date, statusId "
                        + "FROM tblBooking "
                        + "WHERE userId = ? ";
                stm = con.prepareStatement(sql);
                stm.setString(1, id);
                rs = stm.executeQuery();
                while (rs.next()) {
                    int bookId = rs.getInt(1);
                    Date date = rs.getDate(2);
                    int status = rs.getInt(3);
                    CartDTO dto = new CartDTO(date, bookId, id);
                    if (this.listCart == null) {
                        this.listCart = new ArrayList();
                    }
                    if (status == 1) {
                        this.listCart.add(dto);
                    }
                }
                return true;
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

    public boolean getDetailHistory(int id, String user) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "SELECT d.detailId, d.roomId, d.quantity, d.price, d.checkinDate, d.checkoutDate, c.name, h.name, b.statusId  FROM tblBooking as b, tblBookingDetail as d, tblRoom as r, tblCate as c, tblHotel as h WHERE d.bookId = ? AND b.userId = ? AND d.bookId = b.bookId AND d.roomId = r.roomId AND r.cateId = c.cateId AND r.hotelId = h.hotelId";
                stm = con.prepareStatement(sql);
                stm.setInt(1, id);
                stm.setString(2, user);
                rs = stm.executeQuery();
                while (rs.next()) {
                    int detailId = rs.getInt(1);
                    int roomId = rs.getInt(2);
                    int quantity = rs.getInt(3);
                    float price = rs.getFloat(4);
                    Date in = rs.getDate(5);
                    Date out = rs.getDate(6);
                    String cate = rs.getString(7);
                    String hotel = rs.getString(8);
                    int statusId = rs.getInt(9);
                    if (statusId == 1) {
                        CartDTO dto = new CartDTO(detailId, roomId, hotel, cate, quantity, price, in, out, id);
                        if (this.listCart == null) {
                            this.listCart = new ArrayList();
                        }
                        this.listCart.add(dto);
                    }

                }
                return true;
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

    public boolean getDetailHistory(int id) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "SELECT d.detailId, d.roomId, d.quantity, d.price, d.checkinDate, d.checkoutDate, c.name, h.name, b.statusId  FROM tblBooking as b, tblBookingDetail as d, tblRoom as r, tblCate as c, tblHotel as h WHERE d.bookId = ? AND d.bookId = b.bookId AND d.roomId = r.roomId AND r.cateId = c.cateId AND r.hotelId = h.hotelId";
                stm = con.prepareStatement(sql);
                stm.setInt(1, id);
                rs = stm.executeQuery();
                while (rs.next()) {
                    int detailId = rs.getInt(1);
                    int roomId = rs.getInt(2);
                    int quantity = rs.getInt(3);
                    float price = rs.getFloat(4);
                    Date in = rs.getDate(5);
                    Date out = rs.getDate(6);
                    String cate = rs.getString(7);
                    String hotel = rs.getString(8);
                    int statusId = rs.getInt(9);
                    if (statusId == 1) {
                        CartDTO dto = new CartDTO(detailId, roomId, hotel, cate, quantity, price, in, out, id);
                        if (this.listCart == null) {
                            this.listCart = new ArrayList();
                        }
                        this.listCart.add(dto);
                    }

                }
                return true;
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

    public boolean deleteHistory(int id) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "UPDATE tblBooking SET statusId = ? WHERE bookId = ?";
                stm = con.prepareStatement(sql);
                stm.setInt(1, 2);
                stm.setInt(2, id);
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

    public boolean confirmHistory(int id) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "UPDATE tblBooking SET statusId = ? WHERE bookId = ?";
                stm = con.prepareStatement(sql);
                stm.setInt(1, 1);
                stm.setInt(2, id);
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

    public void getHistoryDate(Date date, String id) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
//                String sql = "SELECT d.roomId, d.quantity, d.price, d.checkinDate, d.checkoutDate "
//                        + "FROM tblBooking as b, tblBookingDetail as d "
//                        + "WHERE b.bookId = d.bookId AND b.date = ? AND b.userId = ? ";
                String sql = "SELECT b.bookId, statusId "
                        + "FROM tblBooking as b "
                        + "WHERE b.date = ? AND b.userId = ? ";
                stm = con.prepareStatement(sql);
                stm.setDate(1, date);
                stm.setString(2, id);
                rs = stm.executeQuery();
                while (rs.next()) {
                    int bookId = rs.getInt(1);
                    int statusId = rs.getInt(2);
                    if (statusId == 1) {
                        CartDTO dto = new CartDTO(statusId, date, bookId);
                        if (this.listCart == null) {
                            this.listCart = new ArrayList();
                        }
                        this.listCart.add(dto);
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
    }

    public void getHistoryName(int id, String user) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
//                String sql = "SELECT d.roomId, d.quantity, d.price, d.checkinDate, d.checkoutDate "
//                        + "FROM tblBooking as b, tblBookingDetail as d "
//                        + "WHERE b.bookId = d.bookId AND b.bookId = ? AND b.userId = ? ";
                String sql = "SELECT date, statusId "
                        + "FROM tblBooking as b "
                        + "WHERE b.bookId = ? AND b.userId = ? ";
                stm = con.prepareStatement(sql);
                stm.setInt(1, id);
                stm.setString(2, user);
                rs = stm.executeQuery();
                if (rs.next()) {
                    Date in = rs.getDate(1);
                    int statusId = rs.getInt(2);
                    if (statusId == 1) {
                        CartDTO dto = new CartDTO(statusId, in, id);
                        if (this.listCart == null) {
                            this.listCart = new ArrayList();
                        }
                        this.listCart.add(dto);
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
    }

    public boolean ratingHistoryDetail(String userId, int feedback, int detailId, int roomId)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {

            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "INSERT INTO tblFeedback(userId, feedback, detailId, roomId, statusId) "
                        + "Values(?,?,?,?,?)";
                stm = con.prepareStatement(sql);
                stm.setString(1, userId);
                stm.setInt(2, feedback);
                stm.setFloat(4, roomId);
                stm.setInt(3, detailId);
                stm.setInt(5, 1);
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

    public boolean updateRatingDetail(int feedback, int detailId)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "UPDATE tblFeedback SET feedback = ? WHERE detailId = ?";
                stm = con.prepareStatement(sql);
                stm.setInt(1, feedback);
                stm.setInt(2, detailId);
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

    public boolean checkStatusRating(int detailId, String name)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "SELECT statusId FROM tblFeedback WHERE detailId = ? AND userId = ?";
                stm = con.prepareStatement(sql);
                stm.setInt(1, detailId);
                stm.setString(2, name);
                rs = stm.executeQuery();
                if (rs.next()) {
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

    public int getRatingPoint(int detailId, String name)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {

            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "SELECT feedback FROM tblFeedback WHERE detailId = ? AND userId = ?";
                stm = con.prepareStatement(sql);
                stm.setInt(1, detailId);
                stm.setString(2, name);
                rs = stm.executeQuery();
                if (rs.next()) {
                    int status = rs.getInt("feedback");
                    return status;
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
        return -1;
    }

    public int getCodeName(String name)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {

            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "SELECT discountPercent, dateExpire FROM tblDiscount WHERE codeName = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, name);
                rs = stm.executeQuery();
                if (rs.next()) {
                    Calendar cal = Calendar.getInstance();
                    Year year = Year.now();
                    int numYear = Integer.parseInt(year.toString());
                    int month = cal.getInstance().get(Calendar.MONTH) + 1;
                    int day = cal.getInstance().get(Calendar.DAY_OF_MONTH);
                    String dates = numYear + "-" + month + "-" + day;
                    Date date = Date.valueOf(dates);
                    int discount = rs.getInt("discountPercent");
                    Date exDate = rs.getDate("dateExpire");
                    if (date.before(exDate)) {
                        return discount;
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
        return 1;
    }
//
//    public void showOrderID(int searchValue)
//            throws SQLException, NamingException {
//        Connection con = null;
//        PreparedStatement stm = null;
//        ResultSet rs = null;
//
//        try {
//            con = DBHelper.makeConnection();
//            if (con != null) {
//                String sql = "SELECT p.orderID, p.userID , p.date, p.address FROM tblOrder as p WHERE p.orderID = ? ";
//                stm = con.prepareStatement(sql);
//                stm.setInt(1, searchValue);
//
//                rs = stm.executeQuery();
//                while (rs.next()) {
//                    int id = rs.getInt(1);
//                    String pname = rs.getString(2);
//                    Date cDate = rs.getDate(3);
//                    String address = rs.getString(4);
//                    CartDTO dto = new CartDTO(id, pname, cDate, address);
//                    if (this.listCart == null) {
//                        this.listCart = new ArrayList();
//                    }
//                    this.listCart.add(dto);
//                }
//            }
//        } finally {
//            if (rs != null) {
//                rs.close();
//            }
//            if (stm != null) {
//                stm.close();
//            }
//            if (con != null) {
//                con.close();
//            }
//        }
//
//    }
//
//    public void showProductID(int searchValue)
//            throws SQLException, NamingException {
//        Connection con = null;
//        PreparedStatement stm = null;
//        ResultSet rs = null;
//
//        try {
//            con = DBHelper.makeConnection();
//            if (con != null) {
//                String sql = "SELECT c.productID FROM tblOrder as p, tblOrderdetail as c WHERE p.orderID = c.orderID AND p.orderID = ? ";
//                stm = con.prepareStatement(sql);
//                stm.setInt(1, searchValue);
//                rs = stm.executeQuery();
//                while (rs.next()) {
//                    int num = rs.getInt(1);
//                    String id = String.valueOf(num);
//                    if (this.listProduct == null) {
//                        this.listProduct = new ArrayList();
//                    }
//                    this.listProduct.add(id);
//                }
//            }
//
//        } finally {
//            if (rs != null) {
//                rs.close();
//            }
//            if (stm != null) {
//                stm.close();
//            }
//            if (con != null) {
//                con.close();
//            }
//        }
//    }
}
