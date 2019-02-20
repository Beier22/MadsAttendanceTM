package madsattendancetm.be;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import madsattendancetm.dal.UserDAO;


public class User {
    
    private int id;
    private StringProperty name = new SimpleStringProperty();
    private String email;
    private int isTeacher;
    private String password;

    public User(int id, String name, String email, int isTeacher, String password) {
        this.id = id;
        this.name.set(name);
        this.email = email;
        this.isTeacher = isTeacher;
        this.password = password;
    }

    UserDAO dao = new UserDAO();
    
    @Override
    public String toString() {
        return email;
    }
   
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getIsTeacher() {
        return isTeacher;
    }

    public void setIsTeacher(int isTeacher) {
        this.isTeacher = isTeacher;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public StringProperty nameProperty(){
        return name;
    }

    


    
    
}