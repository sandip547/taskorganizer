package org.example.taskorginizer.service.impl;

import org.example.taskorginizer.entity.Comments;

public interface CommentsImpel {
    public Comments saveComments(String comments, Long id);
}
