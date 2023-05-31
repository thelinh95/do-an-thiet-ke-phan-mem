package com.baouyen.doan.converter;

import com.baouyen.doan.dto.UserDto;
import com.baouyen.doan.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {
    public User dtoToEntity(UserDto userDto) {
        User result = new User();
        result.setId(userDto.getId());
        result.setUsername(userDto.getName());

        return result;
    }


    public UserDto entityToDto(User user) {
        UserDto result = new UserDto();
        result.setId(user.getId());
        result.setName(user.getUsername());

        return result;
    }
}
