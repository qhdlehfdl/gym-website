package com.study.shoppingmall.controller;

import com.study.shoppingmall.Service.CartService;
import com.study.shoppingmall.Service.MachineService;
import com.study.shoppingmall.Service.UserService;
import com.study.shoppingmall.entity.Machine;
import com.study.shoppingmall.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class MachineController {
    @Autowired
    private MachineService machineService;

    @Autowired
    private UserService userService;

    @Autowired
    private CartService cartService;

    @GetMapping("/machineList")
    public String machineList(Model model,
                              @PageableDefault(page=0,size=6,sort="id",direction = Sort.Direction.DESC)Pageable pageable,
                              String part, String brand, String price, HttpSession session) {
        Page<Machine> list = null;
        if((part==null && brand==null && price==null) || (part.equals("-1") && brand.equals("") && price.equals("-1"))) {
            list = machineService.machineList(pageable);
        }else {
            Integer minPrice = null, maxPrice = null;
            if (!price.equals("-1")) {
                if(price.equals("0")){
                    minPrice=0;
                    maxPrice=1000000;
                } else if (price.equals("1")) {
                    minPrice=1000000;
                    maxPrice=5000000;
                } else if (price.equals("2")) {
                    minPrice=5000000;
                    maxPrice=10000000;
                } else if (price.equals("3")) {
                    minPrice=1000000;
                }
            }
            list = machineService.machineSearchList(Integer.parseInt(part), brand, minPrice, maxPrice, pageable);
        }
        //세션이용
        String userID = (String)session.getAttribute("loginCheck");

        if (userID != null) {
            //장바구니 유무로 버튼 다르게
            User user = userService.findUser(userID);
            List<Integer> cartList = cartService.findMachineID(user.getId());
            model.addAttribute("cartList", cartList);
        }

        int nowPage = list.getPageable().getPageNumber() + 1;
        int startPage = Math.max(nowPage - 4, 1);
        int endPage = Math.min(nowPage + 5, list.getTotalPages());
        model.addAttribute("list", list);
        model.addAttribute("nowPage", nowPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);

        return "machineList";
    }

    @GetMapping("/loadMachine")
    public String loadMachine() {
        return "loadMachine";
    }

    @PostMapping("/loadMachineAction")
    public String loadMachineAction(Model model, Machine machine, MultipartFile file) throws Exception{
        machineService.write(machine, file);
        model.addAttribute("message", "상품이 등록되었습니다.");
        model.addAttribute("searchUrl", "/machineList");
        return "message";
    }

    @GetMapping("/viewMachine")
    public String viewMachine(Model model, Integer machineID, HttpSession session) {
        model.addAttribute("machine", machineService.viewMachine(machineID));

        //세션이용
        String userID = (String)session.getAttribute("loginCheck");

        if (userID != null) {
            //장바구니 유무로 버튼 다르게
            User user = userService.findUser(userID);
            List<Integer> cartList = cartService.findMachineID(user.getId());
            model.addAttribute("cartList", cartList);
        }

        Integer cartCount = cartService.machineCartCount(machineID);
        model.addAttribute("cartCount", cartCount);
        return "viewMachine";
    }
}
