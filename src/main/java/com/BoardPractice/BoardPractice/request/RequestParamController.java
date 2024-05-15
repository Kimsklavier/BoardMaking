package com.BoardPractice.BoardPractice.request;

import com.BoardPractice.BoardPractice.basic.HelloData;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.Map;

@Slf4j
@Controller
public class RequestParamController {
    @RequestMapping("/request-param-v1")
    public void requestParamV1(HttpServletRequest request, HttpServletResponse
            response) throws IOException {
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));
        log.info("username={}, age={}", username, age);
        response.getWriter().write("ok");
    }

    /*
        @RequestParam 사용해서 요청 파라미터값 가져오기.

        만약 파라미터이름과 변수명이 같으면
        1. @RequestParam String userName, @RequestParam int age
        또는
        2. 단순타입일 경우에 한하여 String username, int age 만 써도 됨 (but @RequestParam은 쓰는게 좋음)

        @Controller 사용시 return 값은 ok라는 뷰 파일을 찾게 되므로
        @RestController 로 바꿔주거나
        !! 메서드에 @Responsebody !! 를 써주면 됨
     */
    @ResponseBody
    @RequestMapping("/request-param-v2")
    public String requestParamV2(
            @RequestParam("username") String memberName,
            @RequestParam("age") int memberAge) {
        log.info("username={}, age={}", memberName, memberAge);
        return "ok";
    }
    @ResponseBody
    @RequestMapping("/request-param-required")
    public String requestParamV5(
            @RequestParam(required = true) String username,
            @RequestParam(required = false) Integer age) {
        // 중요한 건 변수에 null 값이 들어올 수 있는 타입만 써야 한다 (int 대신 Integer)
        // null 대신 default value 쓰고 싶으면
        // @RequestParam(required = true, defaultValue= "guest") 이런 식으로 default value 써주면 됨.
        // 빈문자 "" 도 default로 해줌.
        log.info("username={}, age={}", username, age);
        return "ok";
    }

    /**
     * @RequestParam Map, MultiValueMap
     * Map(key=value)
     * MultiValueMap(key=[value1, value2, ...]) ex) (key=userIds, value=[id1, id2])
     */
    @ResponseBody
    @RequestMapping("/request-param-map")
    // @RequestParam으로 하나하나 가져오는 것이 아니고, Map으로 받아올 수 있음.
    public String requestParamMap(@RequestParam Map<String, Object> paramMap) {
        log.info("username={}, age={}", paramMap.get("username"),
                paramMap.get("age"));
        return "ok";
    }


    @ResponseBody
    @RequestMapping("/model-attribute-v1")
    public String modelAttributeV1(@ModelAttribute HelloData helloData) {
        
        /* 
            @ModelAttribute를 쓰면 아래의 과정을 모두 해결해줌. (서블릿 파라미터의 @ModelAttribute 생략 가능)
            @RequestParam도 생략 가능하고 @ModelAttribute도 생략 가능하면 스프링은 어떻게 판단하는가?
            1. String, int, Integer 같은 단순 타입은 @RequestParam으로 인식하고,
            2. 나머지는 @ModelAttribute로 파악한다.

            HelloData helloData = new HelloData();
            helloData.setUsername(username);
            helloData.setAge(age);
            
            즉, HelloData 객체를 생성하고.
                요청 파라미터 이름과 일치하는 HelloData 객체의 프로퍼티를 찾아 setter를 호출해서 각 값을 set해줌.
         */
        log.info("username={}, age={}", helloData.getUsername(),
                helloData.getAge());
        log.info("helloData={}", helloData); // info : helloData=HelloData(username=asdf, age=33)
        return "ok";
    }
}