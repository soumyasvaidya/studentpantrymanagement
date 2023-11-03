package com.student.pantry.studentPantry.entity;


import java.util.Objects;

import javax.persistence.Entity;

@Entity
public class StudentUser extends User {
    private Long studentId;

    public StudentUser() {
    }

    public StudentUser(Long studentId) {
        this.studentId = studentId;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    @java.lang.Override
    public java.lang.String toString() {
        return "Student{" +
                "studentId=" + studentId +
                '}';
    }

    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof StudentUser)) return false;
        if (!super.equals(object)) return false;
        StudentUser student = (StudentUser) object;
        return java.util.Objects.equals(getStudentId(), student.getStudentId());
    }

    public int hashCode() {
        return Objects.hash(super.hashCode(), getStudentId());
    }
}