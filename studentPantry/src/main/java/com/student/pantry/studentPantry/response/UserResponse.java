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

}