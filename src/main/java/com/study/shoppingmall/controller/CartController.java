package com.study.shoppingmall.controller;

import com.study.shoppingmall.Service.CartService;
import com.study.shoppingmall.Service.MachineService;
import com.study.shoppingmall.Service.UserService;
import com.study.shoppingmall.entity.Cart;
import com.study.shoppingmall.entity.Machine;
import com.study.shoppingmall.entity.User;
import com.study.shoppingmall.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class CartController {
    @Autowired
    private CartService cartService;

    @Autowired
    private UserService userService;

    @Autowired
    private MachineService machineService;

    @GetMapping("/cart")
    public String cart(@PageableDefault(page=0,size=6,sort="id",direction = Sort.Direction.DESC) Pageable pageable,
                       HttpSession session, Model model){
        List<Integer> list = null;

        //세션이용
        String userID = (String)session.getAttribute("loginCheck");
        User user = userService.findUser(userID);
        list = cartService.findMachineID(user.getId());

        List<Machine> machineList = new ArrayList<Machine>();

        int sum=0;

        for (int i = 0; i < list.size(); i++) {
            machineList.add(machineService.viewMachine(list.get(i)));
        }

        model.addAttribute("machineList", machineList);
        return "cart";
    }

    @ResponseBody
    @PostMapping("/putInCart")
    public boolean putInCart(String userID, int machineID) {
        User user = userService.findUser(userID);

        Cart cart = new Cart();
        cart.setUserID(user.getId());
        cart.setMachineID(machineID);
        Cart ret = null;
        ret = cartService.findCart(cart);

        if (ret != null) { //이미 있음
            cartService.removeCart(ret.getId());
            return false;
        } else {
            cartService.putInCart(cart);
            return true;
        }
    }
}
