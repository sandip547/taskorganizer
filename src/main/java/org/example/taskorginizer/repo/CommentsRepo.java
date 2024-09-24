package org.example.taskorginizer.repo;

import org.example.taskorginizer.entity.Comments;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentsRepo extends JpaRepository<Comments,Long> {
}
