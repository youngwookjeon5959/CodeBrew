package scv.DevOpsunity.toss.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import scv.DevOpsunity.member.dto.MemberDTO;
import scv.DevOpsunity.toss.dto.ProductsDTO;
import scv.DevOpsunity.toss.service.TossService;

import java.util.*;

@RestController
public class TossController {

    @Autowired
    private TossService tossService;



    @GetMapping({"/toss.do"})
    public ModelAndView toss(HttpSession session,HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {


        // 제품을 담을 리스트 객체들
        List<Map<String, Object>> mugCup = new ArrayList<>();
        List<Map<String, Object>> tumbler = new ArrayList<>();
        List<Map<String, Object>> coffeeBeans = new ArrayList<>();
        List<Map<String, Object>> caps = new ArrayList<>();
        List<Map<String, Object>> mashine = new ArrayList<>();


        // 객체들을 다 담을 products
        List<List<Map<String, Object>>> products = new ArrayList<>();

        String userId = (String) session.getAttribute("userId");





        /* 머그컵 */
        Map<String, Object> mugCup1 = new HashMap<>();
        mugCup1.put("amount", 1980000);
        mugCup1.put("orderName", "머그컵 우드 스타일");
        mugCup1.put("customerName", userId);
        mugCup1.put("orderId", "order-MC1");
        mugCup1.put("image", "/images/mug1.png");

        Map<String, Object> mugCup2 = new HashMap<>();
        mugCup2.put("amount", 2000000);
        mugCup2.put("orderName", "머그컵 아메리칸");
        mugCup2.put("customerName", userId);
        mugCup2.put("orderId", "order-MC2");
        mugCup2.put("image", "/images/mug2.jpg");

        Map<String, Object> mugCup3 = new HashMap<>();
        mugCup3.put("amount", 1850000);
        mugCup3.put("orderName", "머그컵 아트 스타일");
        mugCup3.put("customerName", userId);
        mugCup3.put("orderId", "order-MC3");
        mugCup3.put("image", "/images/mug3.jpg");

        Map<String, Object> mugCup4 = new HashMap<>();
        mugCup4.put("amount", 1780000);
        mugCup4.put("orderName", "머그컵 팟 스타일");
        mugCup4.put("customerName", userId);
        mugCup4.put("orderId", "order-MC4");
        mugCup4.put("image", "/images/mug4.jpg");

        Map<String, Object> mugCup5 = new HashMap<>();
        mugCup5.put("amount", 1250000);
        mugCup5.put("orderName", "머그컵 모던");
        mugCup5.put("customerName", userId);
        mugCup5.put("orderId", "order-MC5");
        mugCup5.put("image", "/images/mug5.jpg");


        mugCup.add(mugCup1);
        mugCup.add(mugCup2);
        mugCup.add(mugCup3);
        mugCup.add(mugCup4);
        mugCup.add(mugCup5);






        /* 텀블러 */
        Map<String, Object> tumbler1 = new HashMap<>();
        tumbler1.put("amount", 1990000);
        tumbler1.put("orderName", "텀블러 스트리트 스타일");
        tumbler1.put("customerName", userId);
        tumbler1.put("orderId", "order-T1");
        tumbler1.put("image", "/images/tumbler1.png");

        Map<String, Object> tumbler2 = new HashMap<>();
        tumbler2.put("amount", 1530000);
        tumbler2.put("orderName", "텀블러 체리");
        tumbler2.put("customerName", userId);
        tumbler2.put("orderId", "order-T2");
        tumbler2.put("image", "/images/tumbler2.jpg");

        Map<String, Object> tumbler3 = new HashMap<>();
        tumbler3.put("amount", 1680000);
        tumbler3.put("orderName", "텀블러 유로피안 파크");
        tumbler3.put("customerName", userId);
        tumbler3.put("orderId", "order-T3");
        tumbler3.put("image", "/images/tumbler3.jpg");

        Map<String, Object> tumbler4 = new HashMap<>();
        tumbler4.put("amount", 1880000);
        tumbler4.put("orderName", "텀블러 그래스");
        tumbler4.put("customerName", userId);
        tumbler4.put("orderId", "order-T4");
        tumbler4.put("image", "/images/tumbler4.jpg");

        Map<String, Object> tumbler5 = new HashMap<>();
        tumbler5.put("amount", 1405000);
        tumbler5.put("orderName", "텀블러 실버핸드");
        tumbler5.put("customerName", userId);
        tumbler5.put("orderId", "order-T5");
        tumbler5.put("image", "/images/tumbler5.jpg");

        tumbler.add(tumbler1);
        tumbler.add(tumbler2);
        tumbler.add(tumbler3);
        tumbler.add(tumbler4);
        tumbler.add(tumbler5);




        /* 원두 */
        Map<String, Object> coffeeBeans1 = new HashMap<>();
        coffeeBeans1.put("amount", 990000);
        coffeeBeans1.put("orderName", "에티오피아 예가체프(1kg)");
        coffeeBeans1.put("customerName", userId);
        coffeeBeans1.put("orderId", "order-CB1");
        coffeeBeans1.put("image", "/images/cafebeen1.png");

        Map<String, Object> coffeeBeans2 = new HashMap<>();
        coffeeBeans2.put("amount", 945000);
        coffeeBeans2.put("orderName", "골드 셀렉션(1kg)");
        coffeeBeans2.put("customerName", userId);
        coffeeBeans2.put("orderId", "order-CB2");
        coffeeBeans2.put("image", "/images/cafebeen2.jpg");

        Map<String, Object> coffeeBeans3 = new HashMap<>();
        coffeeBeans3.put("amount", 978000);
        coffeeBeans3.put("orderName", "맥심 시그니처 블랜드(1kg)");
        coffeeBeans3.put("customerName", userId);
        coffeeBeans3.put("orderId", "order-CB3");
        coffeeBeans3.put("image", "/images/cafebeen3.png");

        Map<String, Object> coffeeBeans4 = new HashMap<>();
        coffeeBeans4.put("amount", 1000000);
        coffeeBeans4.put("orderName", "마에스트로 커피빈(1kg)");
        coffeeBeans4.put("customerName", userId);
        coffeeBeans4.put("orderId", "order-CB4");
        coffeeBeans4.put("image", "/images/cafebeen4.png");

        Map<String, Object> coffeeBeans5 = new HashMap<>();
        coffeeBeans5.put("amount", 1050000);
        coffeeBeans5.put("orderName", "우에시마 모카 블랜드(1kg)");
        coffeeBeans5.put("customerName", userId);
        coffeeBeans5.put("orderId", "order-CB5");
        coffeeBeans5.put("image", "/images/cafebeen5.png");


        coffeeBeans.add(coffeeBeans1);
        coffeeBeans.add(coffeeBeans2);
        coffeeBeans.add(coffeeBeans3);
        coffeeBeans.add(coffeeBeans4);
        coffeeBeans.add(coffeeBeans5);



        /* 커피 캡슐 */
        Map<String, Object> caps1 = new HashMap<>();
        caps1.put("amount", 95000);
        caps1.put("orderName", "아라비카(10개입)");
        caps1.put("customerName", userId);
        caps1.put("orderId", "order-CC1");
        caps1.put("image", "/images/caps1.jpg");

        Map<String, Object> caps2 = new HashMap<>();
        caps2.put("amount", 105000);
        caps2.put("orderName", "콜롬비아(10개입)");
        caps2.put("customerName", userId);
        caps2.put("orderId", "order-CC2");
        caps2.put("image", "/images/caps2.jpg");

        Map<String, Object> caps3 = new HashMap<>();
        caps3.put("amount", 115000);
        caps3.put("orderName", "아르떼(10개입)");
        caps3.put("customerName", userId);
        caps3.put("orderId", "order-CC3");
        caps3.put("image", "/images/caps3.jpg");

        Map<String, Object> caps4 = new HashMap<>();
        caps4.put("amount", 100000);
        caps4.put("orderName", "프렌치 바닐라(10개입)");
        caps4.put("customerName", userId);
        caps4.put("orderId", "order-CC4");
        caps4.put("image", "/images/caps4.jpg");

        Map<String, Object> caps5 = new HashMap<>();
        caps5.put("amount", 85000);
        caps5.put("orderName", "디카페이나토 커피(10개입)");
        caps5.put("customerName", userId);
        caps5.put("orderId", "order-CC5");
        caps5.put("image", "/images/caps5.jpg");


        caps.add(caps1);
        caps.add(caps2);
        caps.add(caps3);
        caps.add(caps4);
        caps.add(caps5);




        /* 커피머신 */
        Map<String, Object> mashine1 = new HashMap<>();
        mashine1.put("amount", 500);
        mashine1.put("orderName", "화이트 펄");
        mashine1.put("customerName", userId);
        mashine1.put("orderId", "order-MS1");
        mashine1.put("image", "/images/mashine1.png");

        Map<String, Object> mashine2 = new HashMap<>();
        mashine2.put("amount", 100);
        mashine2.put("orderName", "모던 클래식");
        mashine2.put("customerName", userId);
        mashine2.put("orderId", "order-MS2");
        mashine2.put("image", "/images/mashine2.jpg");

        Map<String, Object> mashine3 = new HashMap<>();
        mashine3.put("amount", 2500);
        mashine3.put("orderName", "블랙 엘레강스");
        mashine3.put("customerName", userId);
        mashine3.put("orderId", "order-MS3");
        mashine3.put("image", "/images/mashine3.jpg");

        Map<String, Object> mashine4 = new HashMap<>();
        mashine4.put("amount", 2500);
        mashine4.put("orderName", "블루 웨이브");
        mashine4.put("customerName", userId);
        mashine4.put("orderId", "order-MS4");
        mashine4.put("image", "/images/mashine4.jpg");

        Map<String, Object> mashine5 = new HashMap<>();
        mashine5.put("amount", 1000);
        mashine5.put("orderName", "슬릭 브루어");
        mashine5.put("customerName", userId);
        mashine5.put("orderId", "order-MS5");
        mashine5.put("image", "/images/mashine5.jpg");

        mashine.add(mashine1);
        mashine.add(mashine2);
        mashine.add(mashine3);
        mashine.add(mashine4);
        mashine.add(mashine5);






        // 모든 상품 객체를 products 객체로 add
        products.add(mugCup);
        products.add(tumbler);
        products.add(coffeeBeans);
        products.add(caps);
        products.add(mashine);


        session.setAttribute("products", products);

        model.addAttribute("products", products);

        ModelAndView mav = new ModelAndView();
        mav.setViewName("pay/pay");
        return mav;
    }


    
    @GetMapping("/toss/payInfo.do")
    public ModelAndView tossparam(@RequestParam("amount") int amount,
                                  @RequestParam("orderName") String orderName,
                                  @RequestParam("customerName") String customerName,
                                  @RequestParam("image") String image,
                                  HttpSession session) {
        MemberDTO member = (MemberDTO) session.getAttribute("member");


        session.setAttribute("userName", member.getName());
        session.setAttribute("userPhNum", member.getPhoneNum());
        session.setAttribute("userAdrr", member.getAddress());
        session.setAttribute("userDetailAddress", member.getDetailAddress());
        session.setAttribute("postCode", member.getPostcode());






        ModelAndView mav = new ModelAndView();
        mav.addObject("amount", amount);
        mav.addObject("orderName", orderName);
        mav.addObject("customerName", customerName);
        mav.addObject("image", image);

        mav.addObject("userName", member.getName());
        mav.addObject("userPhNum", member.getPhoneNum());
        mav.addObject("userAdrr", member.getAddress());
        mav.addObject("userDetailAddress", member.getDetailAddress());
        mav.addObject("postCode", member.getPostcode());

        mav.setViewName("pay/payInfo");
        return mav;
    }



    @GetMapping("/toss/success.do")
    public ModelAndView success(@RequestParam("amount") int amount,
                                @RequestParam("orderName") String orderName,
                                @RequestParam(value = "orderDate", required = false) String orderDate,
                                @RequestParam("customerName") String customerName
                                ) {
        ProductsDTO product = new ProductsDTO(amount, orderName, orderDate, customerName);
        tossService.insertProduct(product);
        ModelAndView mav = new ModelAndView();
        mav.setViewName("pay/success");
        mav.addObject("purchase", product);
        return mav;
    }

    @GetMapping("/toss/fail.do")
    public ModelAndView fail() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("pay/fail");
        return mav;
    }


}