package scv.DevOpsunity.Reservation.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import scv.DevOpsunity.Reservation.dto.ResDTO;
import scv.DevOpsunity.Reservation.service.ReservationService;
import scv.DevOpsunity.member.dto.MemberDTO;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


@Controller("ReservationController")
public class ReservationController {

    @Autowired
    ReservationService reservationService;

    @Autowired
    MemberDTO memberDTO;

    @GetMapping("/reservations")
    public ModelAndView reservations(HttpSession session, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView();

        // 로그인 여부 확인
        Boolean isLoggedIn = session.getAttribute("userId") != null;

        if (!isLoggedIn) {
            // 현재 요청한 URL을 세션에 저장
            String prevPage = request.getRequestURI();
            session.setAttribute("prevPage", prevPage);
            mav.setViewName("redirect:member/loginForm.do"); // 로그인 페이지로 리다이렉트
            return mav;
        }

        // 로그인 여부를 Model에 추가
        mav.addObject("isLoggedIn", isLoggedIn);

        // 예약 리스트를 가져와서 Model에 추가
        List<ResDTO> reservations = reservationService.getAllReservations();
        mav.addObject("reservations", reservations);

        // 예약 불가능한 좌석 리스트를 가져와서 Model에 추가
        List<ResDTO> unavailableSeats = reservationService.getAllReservations();
        mav.addObject("unavailableSeats", unavailableSeats);

        mav.setViewName("reservations/reservations");
        return mav;
    }



    @GetMapping("/unavailable-seats")
    public ResponseEntity<List<String>> getUnavailableSeats(
            @RequestParam String branch,
            @RequestParam String resDate,
            @RequestParam String resTime) {
        List<ResDTO> reservations = reservationService.getAllReservations();

        List<String> unavailableSeats = reservations.stream()
                .filter(res -> res.getBranch().equals(branch)
                        && res.getResDate().equals(resDate)
                        && res.getResTime().equals(resTime))
                .flatMap(res -> List.of(res.getResSeatNum().split(",")).stream())
                .collect(Collectors.toList());

        return ResponseEntity.ok(unavailableSeats);
    }


    @PostMapping("/reserve")
    public ModelAndView reserve(@RequestParam("resDate") String resDate,
                                @RequestParam("resTime") String resTime,
                                @RequestParam("resSeatNum") String resSeatNum,
                                @RequestParam("branch") String branch,
                                HttpSession session) {
        ModelAndView mav = new ModelAndView();
        String userId = (String) session.getAttribute("user_id");
        if (userId == null) {
            return new ModelAndView("redirect:/member/loginForm.do");
        }

        // 현재 시간을 LocalDateTime으로 가져오기
        LocalDateTime regTime = LocalDateTime.now();

        // LocalDateTime을 문자열로 변환하기
        String regTimeString = regTime.toString();

        ResDTO resDTO = new ResDTO();
        resDTO.setResDate(resDate);
        resDTO.setResTime(resTime);
        resDTO.setResSeatNum(resSeatNum);
        resDTO.setUser_id(userId);
        resDTO.setBranch(branch);
        resDTO.setRegTime(regTime);
        System.out.println("regTime: " + regTime);

        reservationService.addReservation(resDTO);

        mav.addObject("branch", branch);
        mav.addObject("resDate", resDate);
        mav.addObject("resTime", resTime);
        mav.addObject("resSeatNum", resSeatNum);
        mav.addObject("regTime", regTime);
        mav.setViewName("reservations/reservationResult");
        return mav;
    }


    @PostMapping("/reservations/delete")
    public String deleteReservation(@RequestParam int resNum, HttpSession session) {
        String user_id = (String) session.getAttribute("user_id");

        if (user_id == null || user_id.isEmpty()) {
            return "redirect:/member/loginForm.do"; // user_id가 없으면 로그인 페이지로 리디렉션
        }

        reservationService.deleteReservationByResNum(resNum);
        return "redirect:/mypage.do"; // 예약 삭제 후 마이페이지로 리디렉션
    }


    @PostMapping("/reservations/adminDelete")
    public String adminDeleteReservation(@RequestParam int resNum, HttpSession session) {
        String user_id = (String) session.getAttribute("user_id");

        if (user_id == null || user_id.isEmpty()) {
            return "redirect:/member/loginForm.do"; // user_id가 없으면 로그인 페이지로 리디렉션
        }

        reservationService.deleteReservationByResNum(resNum);
        return "redirect:/admin"; // 예약 삭제 후 마이페이지로 리디렉션
    }




    @GetMapping("/reservationResult")
    public ModelAndView reservationResult(@RequestParam("resDate") String resDate,
                                          @RequestParam("resTime") String resTime,
                                          @RequestParam("resSeatNum") String resSeatNum,
                                          @RequestParam("branch") String branch,
                                          @RequestParam("regTime") String regTime) {
        ModelAndView mav = new ModelAndView("reservationResult");
        mav.addObject("resDate", resDate);
        mav.addObject("resTime", resTime);
        mav.addObject("resSeatNum", resSeatNum);
        mav.addObject("branch", branch);
        mav.addObject("regTime", regTime);
        return mav;
    }






}