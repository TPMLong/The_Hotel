/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longtpmse140775.ultil;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author ACER
 */
public class DBHelper implements Serializable{
    public static Connection makeConnection() throws NamingException, SQLException{
        Context currentConnect = new InitialContext();
        Context tomcatConnect = (Context) currentConnect.lookup("java:comp/env");
        DataSource ds =  (DataSource) tomcatConnect.lookup("ML");   
        if(ds != null){
            Connection con = ds.getConnection();
            return con;
        }
        return  null;
    }
}
