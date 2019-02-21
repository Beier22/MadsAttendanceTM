package madsattendancetm.gui.model;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import madsattendancetm.be.User;
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
    
    public String attendanceDay(String email, String date) {
        return dao.attendanceDay(email, date);
    }
    public HashMap att(String date) {
        return dao.att(date);
    }

}