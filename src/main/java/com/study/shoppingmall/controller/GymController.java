package com.study.shoppingmall.controller;

import com.study.shoppingmall.Service.GymService;
import com.study.shoppingmall.Service.UserService;
import com.study.shoppingmall.entity.Gym;
import com.study.shoppingmall.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class GymController {
    @Autowired
    private GymService gymService;

    @Autowired
    private UserService userService;

    @GetMapping("/search")
    public String search(Model model) {
        List<Gym> list = null;
        list = gymService.findAll();
        model.addAttribute("list", list);
        return "search";
    }

    @GetMapping("/loadGym")
    public String loadGym(){
        return "loadGym";
    }

    @PostMapping("/loadGymAction")
    public String loadGymAction(Model model, HttpSession session, String gymAddress, String gymName, String dayPrice) {
        Gym gym = new Gym();
        gym.setGymAddress(gymAddress);
        gym.setGymName(gymName);
        gym.setDayPrice(Integer.parseInt(dayPrice));

        //세션이용
        String userID = (String)session.getAttribute("loginCheck");

        if (userID != null) {
            //장바구니 유무로 버튼 다르게
            User user = userService.findUser(userID);
            gym.setHostID(user.getId());
        }

        gymService.loadGymAction(gym);

        model.addAttribute("message", "업체 등록이 되었습니다");
        model.addAttribute("searchUrl", "/search");

        return "message";
    }

    @GetMapping("/viewGym")
    public String viewGym(Integer gymID, Model model) {
        model.addAttribute("gym", gymService.findGym(gymID));
        return "viewGym";
    }
}
