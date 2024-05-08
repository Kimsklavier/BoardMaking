package com.BoardPractice.BoardPractice.request;

import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.nio.charset.StandardCharsets;

@Slf4j
@Controller
public class RequestBodyStringController {
    @PostMapping("/request-body-string-v1")
    public void requestBodyString(HttpServletRequest request, HttpServletResponse response) throws IOException {

        // http 메시지 바디의 데이터를 바로 읽을 수 있음.
        ServletInputStream inputStream = request.getInputStream();

        String messageBody = StreamUtils.copyToString(inputStream,
                StandardCharsets.UTF_8);
        log.info("messageBody={}", messageBody);
        response.getWriter().write("ok");
    }

    @PostMapping("/request-body-string-v2")
    public void requestBodyStringV2(InputStream inputStream, Writer responseWriter) throws IOException {

        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
        log.info("messageBody={}", messageBody);
        responseWriter.write("ok");
    }

    /**
     * HttpEntity : HTTP header, body 정보를 편하게 조회할 수 있도록 해주는 객체.
     * HttpMessageConverter가 동작하여 요청 http메시지(ex JSON 등)를 HttpEntity 객체로 변환해준다.
     * 마찬가지로 응답도 HttpMessageConverter에 의해 HttpEntity 객체가 http메시지(JSON 형태)로 변환되어 전송된다.
     * so 응답을 할 때에는 view를 조회하는 것이 아니고 message body의 텍스트를 반환하는 것.
     */
    @PostMapping("/request-body-string-v3")
    public HttpEntity<String> requestBodyStringV3(HttpEntity<String> httpEntity) throws IOException{
        String messageBody = httpEntity.getBody();
        log.info("messageBody={}", messageBody);
        return new HttpEntity<>("ok");
    }

    /*
        @RequestBody 사용시 메시지 바디 직접 조회.
        헤더 정보가 필요하다면 httpEntity 또는 @RequestHeader를 사용하면 됨.
        @ResponseBody 사용시 메시지 바디 정보 직접 반환
        메시지 -> 객체, 객체 -> 메시지로 변환되는 과정에서 HttpConverter 동작.

     */
    @ResponseBody
    @PostMapping("/request-body-string-v4")
    public String requestBodyStringV4(@RequestBody String messageBody) {
        log.info("messageBody={}", messageBody);
        return "ok";
    }
}