package com.woowahan.codesquad001;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/login")
    public String login(String userId, String password, HttpSession session) {
        User user = userRepository.findByUserId(userId);

        if (user.getPassword().equals(password)) {
            session.setAttribute("sessionedUser", user);
            return "redirect:/";
        }
        else
            return "redirect:/login-failed";
    }
}
