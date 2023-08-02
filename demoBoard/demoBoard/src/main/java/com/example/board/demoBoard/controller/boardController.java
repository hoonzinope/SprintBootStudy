package com.example.board.demoBoard.controller;

import com.example.board.demoBoard.Dto.InsertCommentDto;
import com.example.board.demoBoard.Dto.InsertPostDto;
import com.example.board.demoBoard.entity.Comment;
import com.example.board.demoBoard.entity.Post;
import com.example.board.demoBoard.repository.PostRepository;
import com.example.board.demoBoard.service.CommentService;
import com.example.board.demoBoard.service.PostService;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
public class boardController {
    @Autowired
    private PostService postService;

    @Autowired
    private CommentService commentService;

    @GetMapping("/")
    public String getBoardPage(Model model) {
        model.addAttribute("posts",postService.getPostAll());
        return "board";
    }
    @GetMapping("/writePost")
    public String getWriteBoardPage() {
        return "write";
    }

    @PostMapping("/writePost")
    @ResponseStatus
    public ResponseEntity<Post> insertPost(@RequestBody InsertPostDto postDto, HttpServletRequest request) {
        String ip = request.getHeader("X-FORWARDED-FOR");
        if (ip == null)
            ip = request.getRemoteAddr();

        postDto.setIp(ip);
        System.out.println(postDto);
        Post post = postDto.toEntity();
        postService.savePost(post);
        return ResponseEntity.status(200).build();
    }

    @GetMapping("/postDetail")
    public String getPostDetail(@RequestParam(name = "seq") Long seq, Model model) {
        Post post = postService.getPostEntity(seq);
        JSONArray comments = commentService.getComments(post);

        model.addAttribute("postDetail", post.getPostJson());
        model.addAttribute("comments", comments);
        return "detail";
    }
    @PostMapping("/commentInsert")
    @ResponseStatus
    public ResponseEntity<Comment> uploadComment(@RequestBody InsertCommentDto commentDto, HttpServletRequest request){
        String ip = request.getHeader("X-FORWARDED-FOR");
        if (ip == null)
            ip = request.getRemoteAddr();
        commentDto.setIp(ip);
        System.out.println(commentDto);

        Post postEntity = postService.getPostEntity(commentDto.getPostSeq());
        commentDto.setPost(postEntity);

        // parent 존재시
        if(commentDto.getParentSeq() != null){
            Comment comment = commentService.getCommentBySeq(commentDto.getParentSeq());
            commentDto.setParent(comment);
        }

        commentService.saveComment(commentDto);
        return ResponseEntity.status(200).build();
    }

    @GetMapping("/getComments")
    @ResponseBody
    public String getCommentByPostSeq(@RequestParam(name = "postSeq") Long postSeq) {
        Post post = postService.getPostEntity(postSeq);
        return commentService.getComments(post).toString();
    }
}
