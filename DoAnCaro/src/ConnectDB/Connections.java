/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ConnectDB;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author kietdang
 */
public class Connections {
     public static Connection Newconnect(){
        Connection con = null;
        try
        {
            String userName = "dtkiet";
            String password = "dtkiet";
            String url = "jdbc:sqlserver://DESKTOP-HIR7EBF;databaseName=caro;";
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(url, userName, password);
            return con;
        }
        catch (Exception ex)
        {
            System.out.println("Connection fail!");
        }
         return con;
    }
}
