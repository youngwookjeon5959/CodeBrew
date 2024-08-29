package scv.DevOpsunity.myPage.controller;


import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import scv.DevOpsunity.Reservation.dto.ResDTO;
import scv.DevOpsunity.Reservation.service.ReservationService;
import scv.DevOpsunity.member.dto.MemberDTO;
import scv.DevOpsunity.toss.dto.ProductsDTO;
import scv.DevOpsunity.toss.service.TossService;

import java.util.List;

@Controller
public class myPage {

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private TossService tossService;


    @GetMapping("/mypage.do")
    public String getReservations(HttpSession session, Model model) {
        String user_id = (String) session.getAttribute("user_id");
        String customerName = (String) session.getAttribute("userId");

        if (user_id == null || user_id.isEmpty()) {
            System.out.println("user_id is null or empty");
            return "redirect:/member/loginForm.do"; // user_id가 없으면 로그인 페이지로 리디렉션
        }

        List<ResDTO> reservations = reservationService.getReservationsByUserId(user_id);
        List<ProductsDTO> orders = tossService.getOrderDetails(customerName);
        model.addAttribute("reservations", reservations);
        model.addAttribute("user_id", user_id); // user_id를 모델에 추가
        model.addAttribute("orders", orders);

        MemberDTO member = (MemberDTO) session.getAttribute("member");
        if (member != null) {
            model.addAttribute("member", member);
        } else {
            return "redirect:/member/loginForm.do"; // 로그인 페이지로 리디렉션
        }

        return "mypage/mypage";
    }





}
