

package madsattendancetm.model;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import madsattendancetm.be.User;
import madsattendancetm.dal.UserDAO;


public class Model {

    UserDAO userDao = new UserDAO();
    String str;
    
    List<User> allUsers;
    
    public List<User> getAllUsers() throws SQLException {
        allUsers = userDao.getAllUsers();
        return allUsers;
    }

    public void login(String email, String date) throws SQLException
    {
        userDao.login(email, date);
    }
    
    public void unattendance(String email1, String date1, String email, String date) throws SQLException
    {
        userDao.unattendance(email1, date1, email, date);
    }
    
}