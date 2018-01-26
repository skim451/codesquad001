package com.woowahan.codesquad001;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("")
    public String create(User user) {
        userRepository.save(user);
        return "redirect:/";
    }

    @GetMapping("")
    public String showList(Model model) {
        Iterable<User> userList = userRepository.findAll();
        model.addAttribute("userList", userList);
        return "user/list";

    }

    @GetMapping("/{id}")
    public String show(@PathVariable long id, Model model) {
        User user = userRepository.findOne(id);
        model.addAttribute("user", user);
        return "user/profile";
    }

    @PostMapping("/login")
    public String login(String userId, String password, HttpSession session) {
        User user = userRepository.findByUserId(userId);

        if (user == null)
            return "user/login_failed";

        if (user.getPassword() == null)
            return "user/login_failed";

        if (user.getPassword().equals(password)) {
            session.setAttribute("sessionedUser", user);
            return "redirect:/";
        }

        return "user/login_failed";
    }
}
