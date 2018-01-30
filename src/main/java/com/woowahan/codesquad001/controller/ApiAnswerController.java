package com.woowahan.codesquad001.controller;

import com.woowahan.codesquad001.auth.LoginUser;
import com.woowahan.codesquad001.dto.Result;
import com.woowahan.codesquad001.entity.Comment;
import com.woowahan.codesquad001.entity.Question;
import com.woowahan.codesquad001.entity.User;
import com.woowahan.codesquad001.repository.CommentRepository;
import com.woowahan.codesquad001.repository.QuestionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/questions/{questionId}/comments")
public class ApiAnswerController {

    private static final Logger log = LoggerFactory.getLogger(ApiAnswerController.class);
    
    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    CommentRepository commentRepository;

    @PostMapping("")
    public Comment create(@PathVariable long questionId, @LoginUser User loginUser, String content) {

        Comment comment = new Comment()
                .setAuthor(loginUser)
                .setQuestion(questionRepository.findOne(questionId))
                .setContent(content);

        return commentRepository.save(comment);
    }

    @DeleteMapping("/{id}")
    public Result deleteComment(@PathVariable long id) {
        Comment comment = commentRepository.findOne(id);
        comment.setDeleted(true);
        commentRepository.save(comment);

        return Result.ok();
    }

}
