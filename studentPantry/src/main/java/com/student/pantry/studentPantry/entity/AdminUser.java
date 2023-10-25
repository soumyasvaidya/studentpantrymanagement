package com.student.pantry.studentPantry.entity;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table
public class AdminUser extends User {
    private Long adminId;


    public AdminUser() {
    }

    public AdminUser(Long adminId) {
        this.adminId = adminId;
    }

    public Long getAdminId() {
        return adminId;
    }

    public void setAdminId(Long adminId) {
        this.adminId = adminId;
    }

    @java.lang.Override
    public java.lang.String toString() {
        return "Admin{" +
                "adminId=" + adminId +
                '}';
    }

    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof AdminUser)) return false;
        if (!super.equals(object)) return false;
        AdminUser adminUser = (AdminUser) object;
        return java.util.Objects.equals(getAdminId(), adminUser.getAdminId());
    }

    public int hashCode() {
        return Objects.hash(super.hashCode(), getAdminId());
    }
}