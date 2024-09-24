package org.example.taskorginizer.service.impl;

import org.example.taskorginizer.entity.User;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserImpel {
    public User saveUser(User user);
}
