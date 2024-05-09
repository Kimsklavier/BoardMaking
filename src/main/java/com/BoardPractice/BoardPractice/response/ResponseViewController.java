package com.BoardPractice.BoardPractice.response;

import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

// html 에 데이터를 넣는 방법.

@Controller
public class ResponseViewController {
    /*
        @ResponseBody가 없으면 뷰리졸버가 response/hello를 찾아 렌더링.
        @ResponseBody가 있으면 뷰리졸버 실행x, Http메시지 바디에 입력
     */
    @RequestMapping("/response-view-v1")
    public ModelAndView responseViewV1() {
        ModelAndView mav = new ModelAndView("response/hello")
                .addObject("data", "hello!");
        return mav;
    }

    @RequestMapping("/response-view-v2")
    public String responseViewV2(Model model) {
        model.addAttribute("data", "hello!!");
        return "response/hello";
    }

    /*
        httpServletResponse나 OutputStream같은 http메시지 바디를 처리하는 파라미터가 없으면
        요청 URL을 참고해서 논리 뷰를 찾아 렌더링. (권장되지 않는 방식)
    */
    @RequestMapping("/response/hello")
    public void responseViewV3(Model model) {
        model.addAttribute("data", "hello!!");
    }


}