package com.student.pantry.studentPantry.dto;

import java.util.Objects;

public class AdminUserDto extends UserDto {
    private Long adminId;

    public AdminUserDto() {
    }

    public AdminUserDto(Long adminId) {
        this.adminId = adminId;
    }

    public Long getAdminId() {
        return adminId;
    }

    public void setAdminId(Long adminId) {
        this.adminId = adminId;
    }

    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof AdminUserDto)) return false;
        if (!super.equals(object)) return false;
        AdminUserDto adminDTO = (AdminUserDto) object;
        return java.util.Objects.equals(getAdminId(), adminDTO.getAdminId());
    }

    public int hashCode() {
        return Objects.hash(super.hashCode(), getAdminId());
    }

    @java.lang.Override
    public java.lang.String toString() {
        return "AdminDTO{" +
                "adminId=" + adminId +
                '}';
    }
}