package green.study.presentation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    // 마이페이지 요청 처리
    @GetMapping("/mypage")
    public String myPage() {
        return "mypage/mypage";
    }

    // 동영상 등록 페이지 요청 처리
    @GetMapping("/upload")
    public String uploadPage() {
        return "video/upload"; //
    }
}
