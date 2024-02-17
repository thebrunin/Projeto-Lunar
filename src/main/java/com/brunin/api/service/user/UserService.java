package com.brunin.api.service.user;

import com.brunin.api.dto.UserDto;
import com.brunin.api.model.User;
import com.brunin.api.repository.UserRepository;
import com.brunin.api.service.SenderMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;
    @Autowired
    SenderMailService senderMailService;

    public User create(UserDto userDto) {

        BCryptPasswordEncoder crypt = new BCryptPasswordEncoder();
        String cryptPass = crypt.encode(userDto.password());

        User user = new User();
        user.setEmail(userDto.email());
        user.setLogin(userDto.login());
        user.setPassword(cryptPass);

        senderMailService.sendCreateAccountEmail(user);

        return repository.save(user);

    }
}
