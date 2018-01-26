package com.woowahan.codesquad001;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class MainController {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    UserRepository userRepository;

    @GetMapping("")
    public String mainPage(Model model, HttpSession session) {
        logger.debug("mainpage");
        User user = (User) session.getAttribute("sessionedUser");

        if (user != null)
            model.addAttribute("user", user);

        return "index.html";
    }
}
