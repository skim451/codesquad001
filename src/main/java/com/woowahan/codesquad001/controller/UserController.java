package com.woowahan.codesquad001.controller;

import com.woowahan.codesquad001.auth.LoginUser;
import com.woowahan.codesquad001.entity.User;
import com.woowahan.codesquad001.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

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
    public String showList(@LoginUser User loginUser, Model model) {
        Iterable<User> userList = userRepository.findAll();
        model.addAttribute("loginUser", loginUser);
        model.addAttribute("userList", userList);
        return "user/list";

    }

    @GetMapping("/{userId}")
    public String show(@PathVariable String userId, @LoginUser User loginUser, Model model) {
        User user = userRepository.findByUserId(userId);
        model.addAttribute("loginUser", loginUser);
        model.addAttribute("user", user);
        return "user/profile";
    }

    @GetMapping("/login")
    public String showLoginPage() {
        return "user/login";
    }

    @PostMapping("/login")
    public String login(String userId, String password, HttpSession session) {
        User user = userRepository.findByUserId(userId);

        if (user == null)
            return "user/login_failed";

        if (user.getPassword() == null)
            return "user/login_failed";

        if (user.getPassword().equals(password)) {
            session.setAttribute("loginUser", user);
            return "redirect:/";
        }

        return "user/login_failed";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("loginUser");
        return "redirect:/";
    }

    @GetMapping("/updateForm")
    public String updateForm(@LoginUser User loginUser, Model model) {
        model.addAttribute("loginUser", loginUser);
        return "user/update_form";
    }

    @PutMapping("/{id}")
    public String updateUser(@PathVariable Long id, User user) {
        user.setId(id);
        userRepository.save(user);
        return "redirect:/";
    }
}
