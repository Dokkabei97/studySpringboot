package com.hello.boot.springboot.web;

import com.hello.boot.springboot.config.auth.LoginUser;
import com.hello.boot.springboot.config.auth.dto.SessionUser;
import com.hello.boot.springboot.service.posts.PostsService;
import com.hello.boot.springboot.web.dto.PostsResponseDto;
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
    private final HttpSession httpSession;

    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user) {
        model.addAttribute("posts", postsService.findAllDesc());
        // CustomOAuth2UserService에서 로그인 성공 시 세션에 SessionUser를 저장
        if (user != null) { // 세션에 저장돈 값이 있을 때만 model에 userName으로 등록
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
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post", dto);

        return "posts-update";
    }

}
