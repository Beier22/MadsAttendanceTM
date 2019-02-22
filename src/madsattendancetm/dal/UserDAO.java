package madsattendancetm.dal;


import madsattendancetm.be.User;
import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    
    public String attendanceDay(String email, String date)
    {
        int n = -1;
        try (Connection con = ds.getConnection()) {
            String sqlStatement = "SELECT attendance FROM [alexAttendance].[dbo].[DateAttendance] WHERE (email = '" + email + "' AND date = '" + date + "')";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sqlStatement);
            while(rs.next()){
                n = rs.getInt("attendance");
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        String m = n+"";
        return m;
    }
    
    public HashMap att(String date)
    {
        Map <String, Integer> yada = new HashMap<>();
        try (Connection con = ds.getConnection()) {
            String sql = "SELECT [dbo].DateAttendance.email, [dbo].DateAttendance.attendance FROM [alexAttendance].[dbo].[DateAttendance] WHERE date = '"+date+"'";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while(rs.next()) {
                yada.put(rs.getString(1), rs.getInt(2));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return (HashMap) yada;
    }
    
    public HashMap summarizedAttendance()
    {
        Map<String, Integer> map = new HashMap<>();
        
        try (Connection con = ds.getConnection()) 
        {
            int x = 0;
            //ArrayList<Integer> array = new ArrayList<>();
            //String email;
            //String arrayString;
            //String sql = "SELECT DISTINCT [dbo].[devAttendance].email, sum([dbo].[devAttendance].attendance) AS a, sum([dbo].[devAttendance].att) AS e FROM [dbo].devAttendance GROUP BY [dbo].[devAttendance].email ORDER BY e DESC";
            String sql = "SELECT DISTINCT [dbo].[devAttendance].email, sum([dbo].[devAttendance].att) AS e FROM [dbo].devAttendance GROUP BY [dbo].[devAttendance].email ORDER BY e DESC";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while(rs.next()) {
                //email = rs.getString(1);
                //array.add(rs.getInt(3));
                //array.add(rs.getInt(3));
                //arrayString = array.toString();
                map.put(rs.getString(1), rs.getInt(2));
                //array.clear();
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return (HashMap) map;
    }
    
    public List<Integer> studentSummary(String email) {
        List<Integer> list = new ArrayList<>();
        try (Connection con = ds.getConnection()) {
            String sqlStatement = "SELECT TOP (1000) [date] ,[attendance] FROM [alexAttendance].[dbo].[DateAttendance] WHERE email = '"+email+"'";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sqlStatement);
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            Calendar t = Calendar.getInstance();

            int a = 0, b = 0, c = 0, d = 0, e = 0, f = 0, g = 0;
            
            while (rs.next()) 
            { 
                if (rs.getInt(2) == 0) 
                {
                    Date newDate = df.parse(rs.getString(1));
                    t.setTime(newDate);
                    int k = t.get(Calendar.DAY_OF_WEEK);
                    if (k == 1)
                        a++;
                    else if (k == 2)
                        b++;
                    else if (k == 3)
                        c++;
                    else if (k == 4)
                        d++;
                    else if (k == 5)
                        e++;
                    else if (k == 6)
                        f++;
                    else if (k == 7)
                        g++;
                }
            }
                list.add(a);
                list.add(b);
                list.add(c);
                list.add(d);
                list.add(e);
                list.add(f);
                list.add(g);
            }   catch (SQLException ex) 
            {
                Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParseException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    public void request(String email, String date)
    {
        String sql = "INSERT INTO [alexAttendance].dbo.Requests (email, date) VALUES (?, ?)";
        try (Connection con = ds.getConnection()) {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, email);
            ps.setString(2, date);
            ps.addBatch();
            ps.executeBatch();
        } catch (SQLException ex) 
        {

        }
    }
    
    public List<String> studentRequests()
    {
        List<String> list = new ArrayList<>();
        try (Connection con = ds.getConnection()) {
            String sqlStatement = "SELECT * FROM [alexAttendance].[dbo].[Requests]";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sqlStatement);
            while(rs.next())
            {
                list.add(rs.getString(1)+"      DATE:   "+rs.getString(2));
            }
    }   catch (SQLServerException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
}