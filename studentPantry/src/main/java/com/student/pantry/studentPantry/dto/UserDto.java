package com.student.pantry.studentPantry.dto;

import java.util.Objects;

public class UserDto{
    private Long id;
    private String username;
    private String email;
    private String userPasswd;
    private UserRole userrole;

    public UserDto() {
    }

    public UserDto(Long id, String username, String email, String password, UserRole role) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.userPasswd = password;
        this.userrole = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public UserRole getUserrole() {
        return userrole;
    }

    public void setUserrole(UserRole role) {
        this.userrole = role;
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
        if (!(object instanceof UserDto)) return false;
        if (!super.equals(object)) return false;
        UserDto userDTO = (UserDto) object;
        return java.util.Objects.equals(getId(), userDTO.getId()) && java.util.Objects.equals(getUsername(), userDTO.getUsername()) && java.util.Objects.equals(getEmail(), userDTO.getEmail()) && java.util.Objects.equals(getUserPasswd(), userDTO.getUserPasswd());
    }

    public int hashCode() {
        return Objects.hash(super.hashCode(), getId(), getUsername(), getEmail(), getUserPasswd());
    }

    @java.lang.Override
    public java.lang.String toString() {
        return "UserDTO{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + userPasswd + '\'' +
                '}';
    }
}
