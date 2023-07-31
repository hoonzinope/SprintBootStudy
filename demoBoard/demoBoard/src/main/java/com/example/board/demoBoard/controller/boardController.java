package com.example.board.demoBoard.controller;

import com.example.board.demoBoard.Dto.InsertPostDto;
import com.example.board.demoBoard.entity.Post;
import com.example.board.demoBoard.repository.PostRepository;
import com.example.board.demoBoard.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class boardController {
    @Autowired
    private PostService postService;

    @GetMapping("/")
    public String getBoardPage(Model model) {
        model.addAttribute("posts",postService.getPostAll());
        return "board";
    }

    @GetMapping("/postDetail")
    public String getPostDetail(@RequestParam(name = "seq") Long seq, Model model) {
        model.addAttribute("postDetail", postService.getPost(seq));
        return "detail";
    }

    @GetMapping("/writePost")
    public String getWriteBoardPage() {
        return "write";
    }

    @PostMapping("/writePost")
    public String insertPost(@RequestBody InsertPostDto postDto, HttpServletRequest request) {

        String ip = request.getHeader("X-FORWARDED-FOR");
        if (ip == null)
            ip = request.getRemoteAddr();

        postDto.setIp(ip);
        System.out.println(postDto);
        Post post = postDto.toEntity();
        postService.savePost(post);
        return "redirect:/";
    }
}
