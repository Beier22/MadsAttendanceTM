package madsattendancetm.dal;


import madsattendancetm.be.User;
import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class UserDAO {
    
        
    SQLServerDataSource ds;
    
    public UserDAO() {
        this.ds = new SQLServerDataSource();
        ds.setDatabaseName(("alexAttendance"));
        ds.setUser(("CS2018B_2"));
        ds.setPassword(("CS2018B_2"));
        ds.setServerName(("10.176.111.31"));
        ds.setPortNumber(((1433)));
    }
    
        public List<User> getAllUsers() {
        List<User> allUsers = new ArrayList<>();
        try (Connection con = ds.getConnection()) {
            String sqlStatement = "SELECT * FROM [alexAttendance].[dbo].[User]";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sqlStatement);
            while (rs.next()) { 
                User u = new User(rs.getInt("id"),  rs.getString("name"), rs.getString("email"), rs.getInt("isTeacher"), rs.getString("password"));
                allUsers.add(u);
            }
        }   catch (SQLException ex) 
            {
                Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        return allUsers; 
    }


    public void login(String email, String date) {
    String sql = "UPDATE [alexAttendance].[dbo].[DateAttendance] SET attendance=1 WHERE email=? AND date=?";
    try (Connection con = ds.getConnection()) {
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, email);
        ps.setString(2, date);
        ps.addBatch();
        ps.executeBatch();
    } catch (SQLException ex) 
        {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void unattendance(String email1, String date1, String email, String date) {
    String sql = "IF EXISTS (SELECT * FROM [alexAttendance].[dbo].[DateAttendance] WHERE (email=? AND date=?)) BEGIN WAITFOR delay '00:00:00' END ELSE INSERT INTO [alexAttendance].[dbo].[DateAttendance] (email, date, attendance) VALUES (?, ?, 0)";;
    try (Connection con = ds.getConnection()) {
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, email1);
        ps.setString(2, date1);
        ps.setString(3, email);
        ps.setString(4, date);
        ps.addBatch();
        ps.executeBatch();
    } catch (SQLException ex) 
        {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public int attendanceData(String email)
    {
        System.out.println(email);
        int n = 0;
        try (Connection con = ds.getConnection()) {
            String sqlStatement = "SELECT * FROM [alexAttendance].[dbo].[DateAttendance] WHERE (email = 'alex@uldahl.dk' AND attendance = 1)";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sqlStatement);
            while (rs.next()) { 
                n++;
            }
        }   catch (SQLException ex) 
            {
                Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        System.out.println(n);
        return n;
    }
    
    public int absenseData(String email)
    {
        System.out.println(email);
        int n = 0;
        try (Connection con = ds.getConnection()) {
            String sqlStatement = "SELECT * FROM [alexAttendance].[dbo].[DateAttendance] WHERE (email = 'alex@uldahl.dk' AND attendance = 0)";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sqlStatement);
            while (rs.next()) { 
                n++;
            }
        }   catch (SQLException ex) 
            {
                Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        System.out.println(n);
        return n;
    }
}