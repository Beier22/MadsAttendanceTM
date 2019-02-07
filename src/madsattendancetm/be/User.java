

package madsattendancetm.be;


public class User {
    
    int id;
    String name;
    String email;
    int isTeacher;
    String password;

    public User(int id, String name, String email, int isTeacher, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.isTeacher = isTeacher;
        this.password = password;
    }

    
    
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
        return name;
    }

    public void setName(String name) {
        this.name = name;
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


}