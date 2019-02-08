/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package madsattendancetm.bll;

import java.util.List;
import madsattendancetm.be.User;
import madsattendancetm.dal.UserDAO;

/**
 *
 * @author mads_
 */
public class Manager implements IManager {

    private UserDAO userdao;
    List<User> allUsers;
    
    

    public List<User> getAllUsers() {
        allUsers = userdao.getAllUsers();
        return allUsers;
    }


    public void login(String email, String date) {
        userdao.login(email, date);
    }


    public void unattendance(String email1, String date1, String email, String date) {
        userdao.unattendance(email1, date1, email, date);
    }


    public int attendanceData(String email) {
        return userdao.attendanceData(email);
    }

    public int absenceData(String email) {
        return userdao.absenceData(email);
    }

    
    
    
}
