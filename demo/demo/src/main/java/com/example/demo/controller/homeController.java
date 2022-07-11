package com.example.demo.controller;

import com.example.demo.DTO.userDTO;
import com.example.demo.domain.userAccount;
import com.example.demo.repository.userRepository;
import com.example.demo.service.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class homeController {

    @Autowired
    userService userService;

    @RequestMapping("/")
    public String index(Model model) {
        model.addAttribute("hello","hi");
        return "index";
    }
    //@ResponseBody 단순 문자열 리턴
    @RequestMapping(value = "joinUs", method = RequestMethod.GET)
    public String join(Model model) {
        System.out.println("회원가입 페이지");
        return "join";
    }

    @RequestMapping(value = "joinUs.do", method = RequestMethod.POST)
    public String joinDo(userDTO udt, Model model) {
        System.out.println("회원등록");
        Long userId = userService.joinUser(udt);
        System.out.println("userID = "+userId);
        return "index";
    }

    @RequestMapping(value = "login.do", method = RequestMethod.POST)
    public String loginDo(userDTO udt, Model model) {
        System.out.println("회원체크");
        Long userId = userService.findUser(udt);
        if(userId == -1 || userId == null) {
            model.addAttribute("loginMessage","아이디 혹은 비밀번호가 일치 하지 않습니다.");
            System.out.println(userId+" "+model.toString());
            return "index";
        }else{
            return "home";
        }
    }
}
