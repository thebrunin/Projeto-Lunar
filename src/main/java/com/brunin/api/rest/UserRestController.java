package com.brunin.api.rest;

import com.brunin.api.dto.UserDto;
import com.brunin.api.helper.DefaultResponseHelper;
import com.brunin.api.model.User;
import com.brunin.api.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserRestController {

    @Autowired
    private UserService userService;

    @PostMapping
    public DefaultResponseHelper<User> create(@RequestBody UserDto userDto) {
        return new DefaultResponseHelper<User>(true, "Create User", userService.create(userDto));
    }
}
