package com.BoardPractice.BoardPractice.requestmapping;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class MappingController {
    private Logger log = LoggerFactory.getLogger(getClass());

    /**
     * 기본 요청
     * 둘다 허용 /hello-basic, /hello-basic/
     * HTTP 메서드 모두 허용 GET, HEAD, POST, PUT, PATCH, DELETE
     */
    @RequestMapping("/hello-basic")

    // 만약 요청 타입 정하려면
    // @RequestMapping(value = "/mapping-get-v1", method = RequestMethod.GET)
    /**
     * 축약 표현
     * @GetMapping, @PostMapping, @PutMapping, @DeleteMapping, @PatchMapping
     */
    public String helloBasic() {
        log.info("helloBasic");
        return "ok";
    }

    // @PathVariable : url에 담긴 변수를 가져오는 어노테이션
    // @PathVariable 이름과 파라미터 이름이 같으면 (@PathVariable String userId) 처럼 생략 가능
    // 주석 간지나게 쓰는 법 /** 후 엔터
    @GetMapping("/mapping/{userId}")
    public String mappingPath(@PathVariable("userId") String data) {
        log.info("mappingPath userId={}", data);
        return "okk";
    }

    // 특정 파라미터 조건 매핑.
    // 파라미터가 mode=debug가 아니면 Bad Request
    @GetMapping(value = "/mapping-param", params = "mode=debug")
    public String mappingParam() {
        log.info("mappingParam");
        return "I'm OK";
    }
}
