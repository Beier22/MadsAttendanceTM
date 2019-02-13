/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package madsattendancetm.bll;

import java.sql.SQLException;
import java.util.List;
import madsattendancetm.be.User;
import madsattendancetm.dal.UserDAO;

/**
 *
 * @author mads_
 */
public class Manager implements IManager {

    private UserDAO dao;
    List<User> allUsers;

    @Override
    public List<User> getAllUsers() throws SQLException {
        allUsers = dao.getAllUsers();
        return allUsers;
    }
    
    @Override
    public void login(String email, String date) throws SQLException
    {
        dao.login(email, date);
    }
    
    @Override
    public void unattendance(String date) throws SQLException
    {
        dao.unattendance(date);
    }
    
    @Override
    public int attendanceData(String email) {
        return dao.attendanceData(email);
    }
    
    @Override
    public int absenceData(String email) {
        return dao.absenceData(email);
    }
    
    @Override
    public int studentLogon(String email, String password) {
        return dao.studentLogon(email, password);
    }
    
    @Override
    public int teacherLogon(String email, String passord) {
        return dao.teacherLogon(email, passord);
    }    

    
    
    
}
