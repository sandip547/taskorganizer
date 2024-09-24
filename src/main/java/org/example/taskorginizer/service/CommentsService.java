package org.example.taskorginizer.service;

import org.example.taskorginizer.entity.Comments;
import org.example.taskorginizer.repo.CommentsRepo;
import org.example.taskorginizer.service.impl.CommentsImpel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CommentsService implements CommentsImpel {
    @Autowired
    CommentsRepo commentsRepo;

    public Comments saveComments(String comments,Long id) {
        Comments newComments = new Comments();
        newComments.setComment(comments);
        newComments.setTaskId(id);
        newComments.setCreatedAt(LocalDateTime.now());
        return commentsRepo.save(newComments);
    }
}
