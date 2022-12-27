package com.example.gateway.dto;

import com.example.gateway.domain.User;
import lombok.Getter;

@Getter
public class UserDto {

    String userName;
    String userEmail;

    public UserDto(String userName, String userEmail) {
        this.userName = userName;
        this.userEmail = userEmail;
    }

    public static UserDto FROM(User user) {
        return new UserDto(user.getUserName(), user.getEmail());
    }
}
