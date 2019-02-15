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

    public void unattendance(String date) {
    String sql = "INSERT INTO [alexAttendance].dbo.DateAttendance (email, date, attendance) SELECT [User].email, ?, 0 FROM [User] WHERE [User].isTeacher=0";
    try (Connection con = ds.getConnection()) {
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, date);
        ps.addBatch();
        ps.executeBatch();
    } catch (SQLException ex) 
        {
            System.out.println("unattendance taken");
        }
    }
    public List attendanceData(String email)
    {
        List<String> days = new ArrayList<>();
        try (Connection con = ds.getConnection()) {
            String sqlStatement = "SELECT * FROM [alexAttendance].[dbo].[DateAttendance] WHERE (email = '"+email+"' AND attendance = 1)";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sqlStatement);
            while (rs.next()) { 
                days.add(rs.getString("date"));
            }
        }   catch (SQLException ex) 
            {
                Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        return days;
    }
    
    public List absenceData(String email)
    {
        List<String> days = new ArrayList<>();
        try (Connection con = ds.getConnection()) {
            String sqlStatement = "SELECT * FROM [alexAttendance].[dbo].[DateAttendance] WHERE (email = '"+email+"' AND attendance = 0)";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sqlStatement);
            while (rs.next()) { 
                days.add(rs.getString("date"));
            }
        }   catch (SQLException ex) 
            {
                Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        for (String day : days) {
        }
        return days;
    }
    
    public int studentLogon(String email, String password)
    {
        int n = 0;
        try (Connection con = ds.getConnection()) {
            String sqlStatement = "SELECT * FROM [alexAttendance].[dbo].[User] WHERE email = '"+email+"' AND password = '"+password+"' AND isTeacher = 0";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sqlStatement);
            while (rs.next()) { 
                n++;
            }
        }   catch (SQLException ex) 
            {
                Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        if (n == 1)
            return n;
        else
            return -1;
    }
    
    public int teacherLogon(String email, String password)
    {
        int n = 0;
        try (Connection con = ds.getConnection()) {
            String sqlStatement = "SELECT * FROM [alexAttendance].[dbo].[User] WHERE email = '"+email+"' AND password = '"+password+"' AND isTeacher = 1";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sqlStatement);
            while (rs.next()) { 
                n++;
            }
        }   catch (SQLException ex) 
            {
                Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        if (n == 1)
            return n;
        else
            return -1;
    }
}