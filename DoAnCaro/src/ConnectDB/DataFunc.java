package ConnectDB;


import caro.common.Users;
import com.sun.corba.se.spi.activation.Server;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author kietdang
 */
public class DataFunc {
    Connection con = Connections.Newconnect();
    
    
    
    public List<Users> getUserList() {
       
        PreparedStatement stm = null;
        ResultSet rs = null;
        
        try {
           
            stm = con.prepareStatement("select * from Users");
            rs = stm.executeQuery();
            
            List<Users> uslist = new ArrayList<Users>();
            
            while (rs.next()) {
                Users us = new Users();
                us.setId(rs.getInt("Id"));
                us.setUsername(rs.getString("username"));
                us.setPassword(rs.getString("password"));
                us.setWin(rs.getInt("win"));
                us.setLose(rs.getInt("lose"));
                us.setScore(rs.getInt("score"));
                uslist.add(us);
            }
            return uslist;
        } catch (SQLException ex) {
                  
        }    
        return null;
    }
    
    public Users checkLogin(String username, String password) {
        try {
                    
            String sql = "Select * From Users Where username = ? and password = ?";
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setString(1, username);
            stm.setString(2, password);
            ResultSet rs = stm.executeQuery();
            boolean result = rs.next();
            rs.close();
            stm.close();
            if (result) {
                return getUser(username);
            }
        } catch (Exception e) {
        }
        return null;
    }
    
    public boolean register(String username, String password) {
         
                   
            Statement statement;           
            String str = "INSERT INTO Users(username,password)values('"+ username+"','"+password+"')" ;
                        
            if (con != null) {
                try {
                    statement = con.createStatement();
                    statement.executeUpdate(str);
                    return true;
                } catch (SQLException ex) {
                    Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                }
                //String tempuser = rs.getString("account");

            }
                        
       return false; 
}
    
    public boolean checkAva(int id) {
        try {

            String sql = "Select * From Users Where id = ?";
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            boolean result = rs.next();
            rs.close();
            stm.close();
            if (result) {
                return true;
            }
            
        } catch (Exception e) {
        }
        return false;
    }
    
    public boolean updateUser(Users user) throws SQLException 
    {
            String sqlStatement =
            "update Users " +
            "set username = ?,"  +
            "password = ?,"  +
            "win = ?,"  +
            "lose = ?,"  +
            "score = ?"  +
            " where Id = ?;";
            PreparedStatement updateQuery  = con.prepareStatement(sqlStatement);
            updateQuery.setString(1, user.getUsername());
            updateQuery.setString(2, user.getPassword());
            updateQuery.setInt(3, user.getWin());
            updateQuery.setInt(4, user.getLose());
            updateQuery.setInt(5, user.getScore());
            updateQuery.setInt(6, user.getId());
            updateQuery.execute();
                              
            return true;
    }
    
    public boolean updateWin(int  id, int win) throws SQLException 
    {
    
        
            if(checkAva(id) == false)
                return false;
            
            String sqlStatement =
            "update Users " +
            "set win = ?"  +
            " where Id = ?";
            PreparedStatement updateQuery  = con.prepareStatement(sqlStatement);

            updateQuery.setInt(1, win);
            updateQuery.setInt(2, id);

            updateQuery.execute();
                              
            return true;
    }
    public boolean updateLose(int id, int lose) throws SQLException 
    {
    
        
      //      if(checkAva(username) == false)
      //          return false;
            
            String sqlStatement =
            "update Users " +
            "set lose = ?"  +
            " where Id = ?"; 
            PreparedStatement updateQuery  = con.prepareStatement(sqlStatement);

            updateQuery.setInt(1, lose);
            updateQuery.setInt(2, id);

            updateQuery.execute();
                              
            return true;
    }
    public int getId(String username)    {
        PreparedStatement stm = null;
        ResultSet rs = null;
        int result = 0;
        try {
           
            stm = con.prepareStatement("select * from Users Where username = ?");
            stm.setString(1, username);
            rs = stm.executeQuery();
            
            while (rs.next()) {
               result = rs.getInt("Id");
               break;
            }
            rs.close();
            stm.close();
        } catch (SQLException ex) {
                  
        }    
        return result;
    
    }
    public Users getUser(String username)    {
        PreparedStatement stm = null;
        ResultSet rs = null;
                try {
           
            stm = con.prepareStatement("select * from Users Where username = ?");
            stm.setString(1, username);
            
            
            
            rs = stm.executeQuery();
            
         
            
            while (rs.next()) {
       
                    Users us = new Users();
                    us.setId(rs.getInt("Id"));
                    us.setUsername(rs.getString("username"));
                    us.setPassword(rs.getString("password"));
                    us.setWin(rs.getInt("win"));
                    us.setLose(rs.getInt("lose"));
                    us.setScore(rs.getInt("score"));
                   
                    return us;
                
                
        }
           
        } catch (SQLException ex) {
                  
        }    
        return null;
    }
    public boolean DeleteUser(int Id) throws SQLException
    {
            PreparedStatement stm = null;
            
            stm = con.prepareStatement("delete from Users Where Id = ?");
            stm.setInt(1, Id);
                                 
            stm.execute();
        return false;
    
    }
        public boolean updateUserBlockedStatus(int userId, boolean blockedStatus) {
    PreparedStatement ps = null;

    try {
        String sql = "UPDATE Users SET blocked = ? WHERE Id = ?";
        ps = con.prepareStatement(sql);
        ps.setBoolean(1, blockedStatus); // blockedStatus là giá trị boolean mới (true hoặc false)
        ps.setInt(2, userId);

        int rowsAffected = ps.executeUpdate();
        
        return rowsAffected > 0; // Trả về true nếu có ít nhất một hàng bị ảnh hưởng (đã cập nhật)
    } catch (SQLException ex) {
        Logger.getLogger(DataFunc.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
        try {
            if (ps != null) ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(DataFunc.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    return false; // Trả về false nếu có lỗi xảy ra hoặc không có hàng nào bị ảnh hưởng (không cập nhật)
}
    
}
