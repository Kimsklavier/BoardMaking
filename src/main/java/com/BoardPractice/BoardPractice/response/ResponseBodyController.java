package com.BoardPractice.BoardPractice.response;

import com.BoardPractice.BoardPractice.basic.HelloData;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.IOException;

@Slf4j
@Controller
/*
    @RestController 를 붙이게 되면 모든 컨트롤러에 @ResponseBody를 붙이는 효과
    (return 값으로 http 메시지 바디에 직접 데이터 입력)
*/
public class ResponseBodyController {
    @GetMapping("/response-body-string-v1")
    public void responseBodyV1(HttpServletResponse response) throws IOException
    {
        response.getWriter().write("ok");
    }
    /**
     * HttpEntity, ResponseEntity(Http Status 􀭶􀐾)
     * 중간에 HttpConverter가 메시지 -> 객체, 객체 -> 메시지로 변환해줌
     * http 응답 코드 설정 가능.
     */
    @GetMapping("/response-body-string-v2")
    public ResponseEntity<String> responseBodyV2() {
        return new ResponseEntity<>("ok", HttpStatus.OK);
    }
    /*
        @ResponseBody를 사용하면 view를 찾는 것이 아니고
        HttpConverter를 통해 http 메시지를 입력하는 것.
     */
    @ResponseBody
    @GetMapping("/response-body-string-v3")
    public String responseBodyV3() {
        return "ok";
    }

    @GetMapping("/response-body-json-v1")
    public ResponseEntity<HelloData> responseBodyJsonV1() {
        HelloData helloData = new HelloData();
        helloData.setUsername("userA");
        helloData.setAge(20);
        return new ResponseEntity<>(helloData, HttpStatus.OK);
    }

    /*
        ResponseEntity는 HTTP 응답 코드를 설정할 수 있는데,
        @ResponseBody를 사용하면 Entity에 이런거를 설정하기 까다롭다.
        이 때 @ResponseStatus를 사용하면 응답 코드 설정 가능.
     */
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @GetMapping("/response-body-json-v2")
    public HelloData responseBodyJsonV2() {
        HelloData helloData = new HelloData();
        helloData.setUsername("userA");
        helloData.setAge(20);
        return helloData;
    }
}