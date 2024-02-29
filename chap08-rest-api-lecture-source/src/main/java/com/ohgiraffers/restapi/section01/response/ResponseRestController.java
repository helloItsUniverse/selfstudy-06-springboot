package com.ohgiraffers.restapi.section01.response;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/* 설명. RestController 는 모든 핸들러 메소드에 @ResponseBody 를 붙여주는 어노테이션이다. */
/* 설명. 이 Controller 의 핸들러 메소드 반환값은 이제 View Resolver 가 처리하지 않는다. (JSON 문자열로 반환된다) */
/* uri : 리소스를 구분하기 위한 구분자 */
@RestController
@RequestMapping("/response")
public class ResponseRestController {

    @GetMapping("/hello")
    public String helloWorld() {
        return "hello world!";
    }

    @GetMapping("/random")
    public int getRandomNumber() {
        return (int) (Math.random() * 10) + 1;
    }

    @GetMapping("message")
    public Message getMessage() {
        return new Message(200, "메시지를 응답합니다.");
    }


    /* 설명.
     *  RestController 에서 반환한 것 (자바의 타입들)은 모두
     *  JSONArray 형태([]), 또는 JSONObject({}) 형태로 바뀌어 JSON 문자열로 반환된다.
     *  1. Map 또는 일반 클래스 타입 -> {} 형태
     *  2. ArrayList -> [] 형태
     *  .
     *  JSON(Javascript Object Notation): 자바스크립트가 인지할 수 있는 객체 형태
     * */
    @GetMapping("test")
    public List<Map<String, Object>> getTest() {
        List<Map<String, Object>> list = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        map.put("test1", new Message(200, "success1"));
        map.put("test2", new Message(200, "success2"));
        map.put("nextPageLink", "http://localhost:8080/hello");
        list.add(map);

        return list;
    }

    @GetMapping("/list")
    public List<String> getList() {
        return List.of(new String[]{"사과", "바나나", "복숭아"});
    }

    @GetMapping("/map")
    public Map<Integer, String> getMapping() {
        List<Message> messageList = new ArrayList<>();
        messageList.add(new Message(200, "정상응답"));
        messageList.add(new Message(404, "페이지를 찾을 수 없습니다."));
        messageList.add(new Message(500, "개발자의 잘못입니다."));

        return messageList.stream().collect(Collectors.toMap(Message::getHttpStatusCode, Message::getMessage));
    }

    /* 설명. 이미지 응답하기
     *  produces 는 response header 의 content-type 설정이다.
     * */
    @GetMapping(value = "/image", produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] getImage() throws IOException {
        return getClass().getResourceAsStream("/static/cc7a76a7-10a1-419e-a45f-b2780b664407.jpeg").readAllBytes();
    }

    @GetMapping("/entity")
    public ResponseEntity<Message> getEntity() {
        return ResponseEntity.ok(new Message(200, "응답 성공"));
    }
}
