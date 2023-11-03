package com.study.shoppingmall.controller;

import com.study.shoppingmall.Service.UserService;
import com.study.shoppingmall.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/main")
    public String shoppingMain() {
        return "main";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/logout")
    public String logout(Model model, HttpSession session){
        session.removeAttribute("loginCheck");
        return "main";
    }

    @GetMapping("/join")
    public String join(){
        return "join";
    }

    @PostMapping("/loginAction")
    public String loginAction(Model model, String userID, String userPassword, HttpSession session) {
        User user = null;
        user = userService.userLogin(userID, userPassword);
        if (user == null) {
            model.addAttribute("message", "아이디 또는 비밀번호를 잘못 입력하셨습니다.");
            model.addAttribute("searchUrl", "/login");
            return "message";
        } else {
            model.addAttribute("loginCheck", userID);
            session.setAttribute("loginCheck", userID);
            return "redirect:/main";
        }
    }

    @ResponseBody
    @PostMapping("/IDCheck")
    public boolean IDCheck(String userID){
        return userService.IDCheck(userID);//true이면 아이디 중복
    }

    @PostMapping("/joinAction")
    public String joinAction(User user, Model model) {
        userService.userJoin(user);
        model.addAttribute("message", "가입되었습니다." +
                "로그인해주세요.");
        model.addAttribute("searchUrl", "/login");
        return "message";
    }
}
