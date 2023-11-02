package com.student.pantry.studentPantry.entity;

public class PantryUserBuilder {

    public static class Builder {
        private Long userId;
        private String username;
        private String email;
        private String userPasswd;
        private UserRole userRole;

        public Builder userId(Long userId) {
            this.userId = userId;
            return this;
        }

        public Builder username(String username) {
            this.username = username;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder userPasswd(String userPasswd) {
            this.userPasswd = userPasswd;
            return this;
        }

        public Builder userRole(UserRole userRole) {
            this.userRole = userRole;
            return this;
        }

        public PantryUser build() {
            PantryUser user = new PantryUser();
            user.setUserId(this.userId);
            user.setUsername(this.username);
            user.setEmail(this.email);
            user.setUserPasswd(this.userPasswd);
            user.setUserrole(this.userRole);
            return user;
        }
    }

}
