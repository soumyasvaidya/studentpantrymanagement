package com.student.pantry.studentPantry.entity;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "PantryUser")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    private String username;
    private String email;
    private String userPasswd;
    private UserRole Userrole;
    


    public User() {
    }


    public User(Long id, String username, String email, String password, UserRole role) {
        this.userId = id;
        this.username = username;
        this.email = email;
        this.userPasswd = password;
        this.Userrole = role;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long id) {
        this.userId = id;
    }

    public String getUsername() {
        return username;
    }

    public UserRole getUserrole() {
        return Userrole;
    }

    public void setUserrole(UserRole role) {
        this.Userrole = role;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserPasswd() {
        return userPasswd;
    }

    public void setUserPasswd(String password) {
        this.userPasswd = password;
    }

    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof User)) return false;
        if (!super.equals(object)) return false;
        User user = (User) object;
        return java.util.Objects.equals(getUserId(), user.getUserId()) && java.util.Objects.equals(getUsername(), user.getUsername()) && java.util.Objects.equals(getEmail(), user.getEmail()) && java.util.Objects.equals(getUserPasswd(), user.getUserPasswd());
    }

    public int hashCode() {
        return Objects.hash(super.hashCode(), getUserId(), getUsername(), getEmail(), getUserPasswd());
    }



}
