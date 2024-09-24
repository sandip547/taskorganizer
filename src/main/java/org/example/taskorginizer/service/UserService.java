package org.example.taskorginizer.service;

import org.example.taskorginizer.entity.CustomUserDetails;
import org.example.taskorginizer.entity.User;
import org.example.taskorginizer.exception.UserAlreadyExistException;
import org.example.taskorginizer.repo.UserRepo;
import org.example.taskorginizer.service.impl.UserImpel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService, UserImpel {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    PasswordEncoder passwordEncoder;

    //load the user from the database based on username
    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = userRepo.findByUserName(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username");
        }
        return new CustomUserDetails(user);
    }

    //save the uesr
    public User saveUser(User user) {
        if (userRepo.findByUserName(user.getUserName())!= null) {
            throw new UserAlreadyExistException("User with this username already exists");
        }
        user.setUserPassword(passwordEncoder.encode(user.getUserPassword()));
        return userRepo.save(user);
    }
}
