package com.woowahan.codesquad001.controller;

import com.woowahan.codesquad001.auth.LoginUser;
import com.woowahan.codesquad001.entity.Comment;
import com.woowahan.codesquad001.entity.User;
import com.woowahan.codesquad001.repository.CommentRepository;
import com.woowahan.codesquad001.repository.QuestionRepository;
import com.woowahan.codesquad001.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("questions/{questionId}/comments")
public class CommentController {
    
    private static final Logger log = LoggerFactory.getLogger(CommentController.class);

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @PostMapping("")
    public String createComment(@PathVariable long questionId, @LoginUser User loginUser, String content) {
        log.debug("{}", questionId);
        Comment comment = new Comment()
                .setContent(content)
                .setAuthor(loginUser)
                .setQuestion(questionRepository.findOne(questionId));
        commentRepository.save(comment);
        return "redirect:/questions/" + questionId;
    }

    @DeleteMapping("/{id}")
    public String updateComment(@PathVariable long questionId, @PathVariable long id) {

        Comment comment = commentRepository.findOne(id);
        comment.setDeleted(true);
        commentRepository.save(comment);

        return "redirect:/questions/" + questionId;
    }
}
