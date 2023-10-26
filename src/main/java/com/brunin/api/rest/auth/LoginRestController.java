package com.brunin.api.rest.auth;

import com.brunin.api.dto.UserDto;
import com.brunin.api.helper.DefaultResponseHelper;
import com.brunin.api.model.User;
import com.brunin.api.security.helper.TokenHelper;
import com.brunin.api.security.service.TokenService;
import com.brunin.api.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class LoginRestController {
    @Autowired
    private AuthenticationManager manager;
    @Autowired
    private UserService userService;
    @Autowired
    TokenService tokenService;
    @PostMapping()
    public ResponseEntity login(@RequestBody UserDto userDto) {
        try {
            var authToken = new UsernamePasswordAuthenticationToken(userDto.login(), userDto.password());
            var authentication = manager.authenticate(authToken);

            var tokenJWT = tokenService.generateToken((User) authentication.getPrincipal());
            return ResponseEntity.ok(new TokenHelper(tokenJWT));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/new")
    public DefaultResponseHelper<User> create(@RequestBody UserDto userDto) {
        return new DefaultResponseHelper<User>(true, "Create User", userService.create(userDto));
    }
}
