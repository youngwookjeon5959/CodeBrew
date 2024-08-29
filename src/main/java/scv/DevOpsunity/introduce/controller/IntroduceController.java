package scv.DevOpsunity.introduce.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller("introduceController")
public class IntroduceController {

    @RequestMapping("/introduce")
    public ModelAndView introduce(){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("/introduce/introduce");
        return mav;
    }



}
