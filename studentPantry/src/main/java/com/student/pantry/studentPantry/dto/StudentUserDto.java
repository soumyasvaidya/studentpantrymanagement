package com.student.pantry.studentPantry.dto;

import java.util.Objects;

public class StudentUserDto extends UserDto {
    private Long studentId;

    public StudentUserDto() {
    }

    public StudentUserDto(Long studentId) {
        this.studentId = studentId;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof StudentUserDto)) return false;
        if (!super.equals(object)) return false;
        StudentUserDto that = (StudentUserDto) object;
        return java.util.Objects.equals(getStudentId(), that.getStudentId());
    }

    public int hashCode() {
        return Objects.hash(super.hashCode(), getStudentId());
    }

    @java.lang.Override
    public java.lang.String toString() {
        return "StudentDTO{" +
                "studentId=" + studentId +
                '}';
    }
}


