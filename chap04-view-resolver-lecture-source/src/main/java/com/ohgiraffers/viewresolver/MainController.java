package com.ohgiraffers.viewresolver;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/*")       // 설명. 도메인과 상관 없이 요청 경로 상 첫 번째 경로 스텝을 처리하는 컨트롤러가 된다.
public class MainController {

    /* 설명.
     *  ViewResolver(뷰 리졸버) 인터페이스를 구현한 ThymeleafViewResolver가 현재 처리하게 된다.
     *  접두사(prefix): resources/templates/
     *  접미사(suffix): .html
     *  핸들러 메소드가 반환하는 String 값 앞뒤에 접두사와 접미사가 붙어 view 를 찾게 된다.
    * */
    @RequestMapping(value={"/", "main"})
    public String main() {
        return "main";
    }
}
