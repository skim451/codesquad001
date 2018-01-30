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
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/questions")
public class QuestionController {

    private static final Logger log = LoggerFactory.getLogger(QuestionController.class);

    @Autowired
    private QuestionRepository questionRepository;

    @PostMapping("")
    public String insert(Question question) {
        log.debug(question.toString());
        questionRepository.save(question);
        return "redirect:/";
    }

    @GetMapping("")
    public String showAll(@LoginUser User loginUser, Model model) {
        Iterable<Question> questions = questionRepository.findAll();
        model.addAttribute("loginUser", loginUser);
        model.addAttribute("questions", questions);
        return "qna/show";
    }

    @GetMapping("/{id}")
    public String showOne(@PathVariable long id, @LoginUser User loginUser, Model model) {
        Question question = questionRepository.findOne(id);
        model.addAttribute("loginUser", loginUser);
        model.addAttribute("question", question);

        return "qna/show";
    }

    @DeleteMapping("/{id}")
    public String deleteOne(@PathVariable long id) {
        Question question = questionRepository.findOne(id);
        question.setDeleted(true);
        question
                .getComments()
                .forEach(i -> i.setDeleted(true));
        questionRepository.save(question);

        return "redirect:/";
    }

    @PutMapping("/{id}")
    public String updateOne(@PathVariable long id, Question question) {
        log.debug("{}, {}", id, question);
        question.setId(id);
        questionRepository.save(question);
        return "redirect:/questions/" + id;
    }

    @GetMapping("/{id}/form")
    public String getUpdateForm(@PathVariable long id, @LoginUser User loginUser, Model model) {
        model.addAttribute("loginUser", loginUser);
        Question question = questionRepository.findOne(id);
        log.debug("{}", question);
        model.addAttribute("question", question);
        return "qna/update_form";
    }
}
