package org.example.taskorginizer.repo;

import org.example.taskorginizer.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User,Long> {
    User findByUserName(String userName);
}
