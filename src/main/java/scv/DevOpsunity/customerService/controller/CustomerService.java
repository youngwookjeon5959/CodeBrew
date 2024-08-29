package scv.DevOpsunity.customerService.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("customerService")
public class CustomerService {

    @RequestMapping("/customerService.do")
    public String customerService(Model model) {
        // 필요한 데이터를 모델에 추가
        //model.addAttribute("message", "Welcome to the Customer Service Page!");

        // "customerService"라는 이름의 뷰로 포워딩
        return "customerService/customerService";
    }


}
