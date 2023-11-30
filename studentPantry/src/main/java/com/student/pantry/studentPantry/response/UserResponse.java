package com.student.pantry.studentPantry.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.student.pantry.studentPantry.dto.UserDto;

public class UserResponse{
    @JsonProperty("message")
    private String message;
    @JsonProperty("user")
    private UserDto userDto;

    public UserResponse(String message, UserDto userDto){
        this.message=message;
        this.userDto=userDto;

    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public UserDto getUserDto() {
        return userDto;
    }

    public void setUserDto(UserDto userDto) {
        this.userDto = userDto;
    }

}