/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myproject;

import java.sql.*;

/**
 *
 * @author Sagar
 */
public class SingleConnection {
    Connection conn = null;
    public void runconn()
    {
        try
                {
                    Class.forName("com.mysql.jdbc.Driver");
                    conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sagar","root","root");
                }
        catch(Exception ex)
        {
            System.out.println(ex);
        }
    }
    
}
