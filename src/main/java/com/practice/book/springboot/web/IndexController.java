package com.practice.book.springboot.web;

import com.practice.book.springboot.config.auth.LoginUser;
import com.practice.book.springboot.config.auth.dto.SessionUser;
import com.practice.book.springboot.service.posts.PostsService;
import com.practice.book.springboot.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;

    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user) {
        model.addAttribute("posts", postsService.findAllDesc());
        //SessionUser user = (SessionUser) httpSession.getAttribute("user");
        // @LoginUser SessionUser user: 기존(User)에 HttpSession.getAttribute("user")로 가져오던 세션 정보 값이 개선
        // @LoginUser만 사용하면 세션 정보를 가져올 수 있게 됨

        if (user != null) {
            model.addAttribute("userName", user.getName());
        }
        return "index";
    }

    @GetMapping("/posts/save")
    public String postsSave() {
        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model) {
        PostsResponseDto postsResponseDto = postsService.findById(id);
        model.addAttribute("post", postsResponseDto);

        return "posts-update";
    }
}
