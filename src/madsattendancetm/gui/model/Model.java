package madsattendancetm.gui.model;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import madsattendancetm.be.User;
import madsattendancetm.bll.IManager;
import madsattendancetm.bll.Manager;
import madsattendancetm.dal.UserDAO;



public class Model {
    
    Manager manager = new Manager();
    UserDAO dao = new UserDAO();
    
    List<User> allUsers;
    
    public List<User> getAllUsers() throws SQLException {
        allUsers = dao.getAllUsers();
        return allUsers;
    }

    public void login(String email, String date) throws SQLException
    {
        dao.login(email, date);
    }
    
    public void unattendance(String email1, String date1, String email, String date) throws SQLException
    {
        dao.unattendance(email1, date1, email, date);
    }
    
    public int attendanceData(String email) {
        return dao.attendanceData(email);
    }
    
    public int absenceData(String email){
        return dao.absenceData(email);
    }


}