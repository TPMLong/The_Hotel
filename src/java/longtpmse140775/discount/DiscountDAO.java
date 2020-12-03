/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longtpmse140775.discount;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.Year;
import java.util.Calendar;
import javax.naming.NamingException;
import longtpmse140775.ultil.DBHelper;

/**
 *
 * @author ACER
 */
public class DiscountDAO implements Serializable{
    public boolean createNewDis(String name, int percent, Date dateExpire) throws NamingException, SQLException {
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
                String sql = "INSERT INTO tblDiscount(codeName, discountPercent, dateCreate, dateExpire) "
                        + "Values(?,?,?,?)";
                //3.Create Connection
                stm = con.prepareStatement(sql);
                stm.setString(1, name);
                stm.setInt(2, percent);
                stm.setDate(3, date);
                stm.setDate(4, dateExpire);
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
}
