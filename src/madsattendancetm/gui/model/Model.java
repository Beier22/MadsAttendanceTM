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
    private UserDAO dao = new UserDAO();
    private Manager manager = new Manager();
    
    private List<User> allUsers;
    
    public List<User> getAllUsers() throws SQLException {
        allUsers = dao.getAllUsers();
        return allUsers;
    }

    public void login(String email, String date) throws SQLException
    {
        dao.login(email, date);
    }
    
    public void unattendance(String date) throws SQLException
    {
        dao.unattendance(date);
    }
    
    public List attendanceData(String email) {
        return dao.attendanceData(email);
    }
    
    public List absenceData(String email) {
        return dao.absenceData(email);
    }

    public int studentLogon(String email, String password) {
        return dao.studentLogon(email, password);
    }
    
    public int teacherLogon(String email, String passord) {
        return dao.teacherLogon(email, passord);
    }

}