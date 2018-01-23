package server.Models;

import java.util.Date;

public class Profile {
    private String firstName;
    private String lastName;
    private Date birthday;
    private String email;
    private String password;

    public Profile(String firstName, String secondName, Date birthday, String email) {
        this.firstName = firstName;
        this.lastName = secondName;
        this.birthday = birthday;
        this.email = email;
    }

    public Profile() {

    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
