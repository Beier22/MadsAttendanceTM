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

public interface IManager {
    
    public List<User> getAllUsers() throws SQLException;
    public void login(String email, String date) throws SQLException;
    public void unattendance(String date) throws SQLException;
    public int attendanceData(String email);
    public int absenceData(String email);
    public int studentLogon(String email, String password);
    public int teacherLogon(String email, String password);
}
