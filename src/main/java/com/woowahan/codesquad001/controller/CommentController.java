package com.woowahan.codesquad001.controller;

import com.woowahan.codesquad001.entity.Comment;
import com.woowahan.codesquad001.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CommentController {

    @Autowired
    CommentRepository commentRepository;

    @PostMapping
    public String createComment(Comment comment) {
        commentRepository.save(comment);
        return "";
    }
}
