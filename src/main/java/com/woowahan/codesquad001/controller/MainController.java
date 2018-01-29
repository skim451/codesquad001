package com.woowahan.codesquad001.controller;

import com.woowahan.codesquad001.auth.LoginUser;
import com.woowahan.codesquad001.entity.Question;
import com.woowahan.codesquad001.entity.User;
import com.woowahan.codesquad001.repository.QuestionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    private static final Logger log = LoggerFactory.getLogger(MainController.class);
    
    @Autowired
    QuestionRepository questionRepository;

    @GetMapping("")
    public String mainPage(@LoginUser User loginUser, Model model) {
        Iterable<Question> questions = questionRepository.findAll();

        model.addAttribute("loginUser", loginUser);
        model.addAttribute("questions", questions);

        return "index/index";
    }
}
