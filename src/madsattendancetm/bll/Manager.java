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
public class Manager implements IManager{

    private UserDAO userdao;
    
    @Override
    public List<User> getAllUsers() {
        return userdao.getAllUsers();
    }

    @Override
    public void login(String email, String date) {
        userdao.login(email, date);
    }

    @Override
    public void unattendance(String email1, String date1, String email, String date) {
        userdao.unattendance(email1, date1, email, date);
    }

    @Override
    public int attendanceData(String email) {
        return userdao.attendanceData(email);
    }

    @Override
    public int absenseData(String email) {
        return userdao.absenseData(email);
    }

    
    
    
}
