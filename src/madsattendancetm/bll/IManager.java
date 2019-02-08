/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package madsattendancetm.bll;

import java.util.List;
import madsattendancetm.be.User;

/**
 *
 * @author mads_
 */
public interface IManager {
    public List<User> getAllUsers();
    public void login(String email, String date);
    public void unattendance(String email1, String date1, String email, String date);
    public int attendanceData(String email);
    public int absenseData(String email);
}
