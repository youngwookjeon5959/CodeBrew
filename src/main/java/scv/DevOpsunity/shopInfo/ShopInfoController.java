package scv.DevOpsunity.shopInfo;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Base64;

@Controller
public class ShopInfoController {

    @GetMapping("/shopInfo.do")
    public String shopInfo(Model model) {
        // Base64 인코딩된 스크립트 태그
        String base64EncodedScript = "PHNjcmlwdCBzcmM9Imh0dHBzOi8vZGFwaS5rYWthby5jb20vdjIvbWFwcy9zZGsuanM/YXBwa2V5PWI4ODhiOWZiZTg5NWQyY2Y1NjJmNDE3YTM2YmQ5OTFkJmxpYnJhcmllcz1zZXJ2aWNlcyI+PC9zY3JpcHQ+";

        // Base64 디코딩
        byte[] decodedBytes = Base64.getDecoder().decode(base64EncodedScript);
        String decodedScript = new String(decodedBytes);

        // 디코딩된 스크립트 태그를 모델에 추가
        model.addAttribute("script", decodedScript);

        return "shopInfo/shopInfo";
    }

    @GetMapping("/menu.do")
    public ModelAndView menuInfo(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("shopInfo/menu");
        return mav;
    }
}