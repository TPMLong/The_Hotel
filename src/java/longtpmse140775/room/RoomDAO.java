/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longtpmse140775.room;

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
public class RoomDAO implements Serializable {

    private List<RoomDTO> listRoom;

    public List<RoomDTO> getListRoom() {
        return listRoom;
    }

    private List<RoomDTO> dateRoom;

    public List<RoomDTO> getDateRoom() {
        return dateRoom;
    }

    public void showRoom(int ids)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "SELECT r.roomId, r.quantity, h.location, h.name, c.name, c.price "
                        + "FROM tblRoom as r, tblHotel as h, tblCate as c "
                        + "WHERE r.cateId = c.cateId AND r.hotelId = h.hotelId AND h.hotelId = ?";
                stm = con.prepareStatement(sql);
                stm.setInt(1, ids);
                rs = stm.executeQuery();
                while (rs.next()) {
                    int id = rs.getInt(1);
                    int quantity = rs.getInt(2);
                    String location = rs.getString(3);
                    String name = rs.getString(4);
                    String cate = rs.getString(5);
                    float price = rs.getFloat(6);
                    RoomDTO dto = new RoomDTO(id, quantity, name, cate, price, location);
                    if (this.listRoom == null) {
                        this.listRoom = new ArrayList();
                    }
                    this.listRoom.add(dto);
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

    public void showHotel()
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "SELECT h.hotelId, r.quantity, h.name, h.location "
                        + "FROM tblRoom as r, tblHotel as h "
                        + "WHERE r.hotelId = h.hotelId";
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();

                while (rs.next()) {
                    int id = rs.getInt(1);
                    int quantity = rs.getInt(2);
                    String location = rs.getString(3);
                    String name = rs.getString(4);
                    RoomDTO dto = new RoomDTO(id, quantity, location, name);
                    if (this.listRoom == null) {
                        this.listRoom = new ArrayList();
                    }

                    this.listRoom.add(dto);

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

    public void showHotelName(String searchValue)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "SELECT r.roomId, r.quantity, h.location, h.name, c.name, c.price "
                        + "FROM tblRoom as r, tblHotel as h, tblCate as c "
                        + "WHERE r.cateId = c.cateId AND r.hotelId = h.hotelId AND h.name LIKE ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, "%" + searchValue + "%");
                rs = stm.executeQuery();
                while (rs.next()) {
                    int id = rs.getInt(1);
                    int quantity = rs.getInt(2);
                    String location = rs.getString(3);
                    String name = rs.getString(4);
                    String cate = rs.getString(5);
                    float price = rs.getFloat(6);
                    RoomDTO dto = new RoomDTO(id, quantity, name, cate, price, location);
                    if (this.listRoom == null) {
                        this.listRoom = new ArrayList();
                    }
                    this.listRoom.add(dto);

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

    public void showHotelNameCate(String searchValue, int quantity)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "SELECT r.roomId, r.quantity, h.location, h.name, c.name, c.price "
                        + "FROM tblRoom as r, tblHotel as h, tblCate as c "
                        + "WHERE r.cateId = c.cateId AND r.hotelId = h.hotelId AND h.name LIKE ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, "%" + searchValue + "%");
                rs = stm.executeQuery();
                while (rs.next()) {
                    int id = rs.getInt(1);
                    int quantityResult = rs.getInt(2);
                    String location = rs.getString(3);
                    String name = rs.getString(4);
                    String cate = rs.getString(5);
                    float price = rs.getFloat(6);
                    if (quantity <= quantityResult) {
                        RoomDTO dto = new RoomDTO(id, quantityResult, name, cate, price, location);
                        if (this.listRoom == null) {
                            this.listRoom = new ArrayList();
                        }
                        this.listRoom.add(dto);                     
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

    public void showHotelArea(String searchValue)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "SELECT r.roomId, r.quantity, h.location, h.name, c.name, c.price "
                        + "FROM tblRoom as r, tblHotel as h, tblCate as c "
                        + "WHERE r.cateId = c.cateId AND r.hotelId = h.hotelId AND h.location LIKE ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, "%" + searchValue + "%");
                rs = stm.executeQuery();
                while (rs.next()) {
                    int id = rs.getInt(1);
                    int quantity = rs.getInt(2);
                    String location = rs.getString(3);
                    String name = rs.getString(4);
                    String cate = rs.getString(5);
                    float price = rs.getFloat(6);
                    RoomDTO dto = new RoomDTO(id, quantity, name, cate, price, location);
                    if (this.listRoom == null) {
                        this.listRoom = new ArrayList();
                    }
                    this.listRoom.add(dto);

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

    public void showHotelAreaCate(String searchValue, int quantity)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "SELECT r.roomId, r.quantity, h.location, h.name, c.name, c.price "
                        + "FROM tblRoom as r, tblHotel as h, tblCate as c "
                        + "WHERE r.cateId = c.cateId AND r.hotelId = h.hotelId AND h.location LIKE ? AND r.quantity = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, "%" + searchValue + "%");
                stm.setInt(2, quantity);
                rs = stm.executeQuery();
                while (rs.next()) {
                    int id = rs.getInt(1);
                    int quantityResult = rs.getInt(2);
                    String location = rs.getString(3);
                    String name = rs.getString(4);
                    String cate = rs.getString(5);
                    float price = rs.getFloat(6);
                    RoomDTO dto = new RoomDTO(id, quantityResult, name, cate, price, location);
                    if (this.listRoom == null) {
                        this.listRoom = new ArrayList();
                    }
                    this.listRoom.add(dto);
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

    public boolean isOverlapping(Date start1, Date end1, Date start2, Date end2) {
        return start1.before(end2) && start2.before(end1);
    }

    public void getBookingDate() throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "SELECT b.statusId, d.roomId, d.checkinDate, d.checkoutDate "
                        + "FROM tblBookingDetail as d, tblBooking as b "
                        + "WHERE b.bookId = d.bookId";
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                while (rs.next()) {
                    int statusId = rs.getInt(1);
                    int id = rs.getInt(2);
                    Date in = rs.getDate(3);
                    Date out = rs.getDate(4);
                    if (statusId == 1) {
                        RoomDTO dto = new RoomDTO(statusId, id, in, out);
                        if (this.dateRoom == null) {
                            this.dateRoom = new ArrayList();
                        }
                        this.dateRoom.add(dto);
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

    public boolean checkQuantityCart(int id, int number)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "SELECT quantity "
                        + "FROM tblRoom "
                        + "WHERE roomId = ? ";
                stm = con.prepareStatement(sql);
                stm.setInt(1, id);
                rs = stm.executeQuery();
                if (rs.next()) {
                    int quantity = rs.getInt("quantity");
                    if (number <= 0 || number > quantity) {
                        return false;
                    } else {
                        return true;
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

    public boolean updateHistory(String user, String code)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        Calendar cal = Calendar.getInstance();
        Year year = Year.now();
        int numYear = Integer.parseInt(year.toString());
        int month = cal.getInstance().get(Calendar.MONTH) + 1;
        int day = cal.getInstance().get(Calendar.DAY_OF_MONTH);
        String dates = numYear + "-" + month + "-" + day;
        Date date = Date.valueOf(dates);
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "INSERT INTO tblBooking(date, userId, statusId, code) "
                        + "Values(?,?,?,?)";
                stm = con.prepareStatement(sql);

                stm.setDate(1, date);
                stm.setString(2, user);
                stm.setInt(3, 2);
                stm.setString(4, code);
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

    public int getId()
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "SELECT MAX(bookId) FROM tblBooking";
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                if (rs.next()) {
                    int id = rs.getInt(1);
                    return id;
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

//    public boolean getFeedback(String username)
//            throws SQLException, NamingException {
//        Connection con = null;
//        PreparedStatement stm = null;
//        ResultSet rs = null;
//        try {
//            con = DBHelper.makeConnection();
//            if (con != null) {
//                String sql = "SELECT MAX d.roomId, d.checkoutDate FROM tblBookingDetail as d, tblBooking as b WHERE d.bookId = b.bookId AND b.userId = ?";
//                stm = con.prepareStatement(sql);
//                rs = stm.executeQuery();
//                if (rs.next()) {
//                    int id = rs.getInt(1);
//                    return id;
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
//        return 0;
//    }
}
