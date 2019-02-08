package madsattendancetm.gui.model;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import madsattendancetm.be.User;
import madsattendancetm.bll.Manager;



public class Model {

    
    Manager manager = new Manager();
    String str;
    
    List<User> allUsers;
    
    public List<User> getAllUsers() throws SQLException {
        //allUsers = manager.getAllUsers();
        return allUsers;
    }

    public void login(String email, String date) throws SQLException
    {
        //manager.login(email, date);
    }
    
    public void unattendance(String email1, String date1, String email, String date) throws SQLException
    {
        //manager.unattendance(email1, date1, email, date);
    }
    
    public int attendanceData(String loginEmail, String loginPass) {
        //return manager.attendanceData();
        return 0;
    }
    
    public int absense(String loginEmail, String loginPass){
        //return manager.absenseData();
        return 0;
    }
}