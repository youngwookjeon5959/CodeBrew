package scv.DevOpsunity.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import scv.DevOpsunity.Reservation.dto.ResDTO;
import scv.DevOpsunity.Reservation.service.ReservationService;
import scv.DevOpsunity.admin.dto.AdminMemberDTO;
import scv.DevOpsunity.admin.service.AdminService;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Controller("adminController")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private ReservationService reservationService;

    @GetMapping("/admin")
    public String adminMain(Model model) {
        return "redirect:/admin/dashboard.do";
    }

    @GetMapping("/admin/dashboard.do")
    public String dashBoard(Model model) {
        long boardCount = adminService.getAllBoards().size();
        long memberCount = adminService.getTotalMembers();
        long commentCount = adminService.getAllComments().size();

        model.addAttribute("boards", boardCount);
        model.addAttribute("members", memberCount);
        model.addAttribute("comments", commentCount);

        List<AdminMemberDTO> members = adminService.getAllMembers();
        List<AdminMemberDTO> formattedMembers = members.stream()
                .map(this::formatMemberDate)
                .collect(Collectors.toList());
        model.addAttribute("membersList", formattedMembers);


        // 예약 리스트를 가져와서 Model에 추가
        List<ResDTO> reservations = reservationService.getAllReservations();
        model.addAttribute("reservations", reservations);

        System.out.println("Dashboard data - Boards: " + boardCount + ", Members: " + memberCount + ", Comments: " + commentCount);

        return "admin/dashBoard";
    }

    @GetMapping("/admin/members.do")
    public String members(Model model) {
        List<AdminMemberDTO> members = adminService.getAllMembers();
        List<AdminMemberDTO> formattedMembers = members.stream()
                .map(this::formatMemberDate)
                .collect(Collectors.toList());  // 여기도 수정되었습니다.
        model.addAttribute("membersList", formattedMembers);
        System.out.println(formattedMembers);
        return "member/listMembers";
    }

    @GetMapping("/admin/boards")
    public String boards(Model model) {
        model.addAttribute("boards", adminService.getAllBoards());
        return "admin/boards";
    }

    @GetMapping("/admin/comments")
    public String comments(Model model) {
        model.addAttribute("comments", adminService.getAllComments());
        return "admin/comments";
    }

    @PostMapping("/admin/deleteBoard")
    @ResponseBody
    public String deleteBoard(@RequestParam int freeArticleNo) {
        adminService.deleteBoard(freeArticleNo);
        return "success";
    }

    @PostMapping("/admin/deleteMember")
    @ResponseBody
    public String deleteMember(@RequestParam String id) {
        if (id == null || id.isEmpty()) {
            return "fail: 유효하지 않은 ID"; // 유효하지 않은 id 추가 메시지
        }
        try {
            adminService.deleteMember(id);
            return "success";
        } catch (Exception e) {
            System.out.println("삭제 실패: " + e.getMessage());
            return "fail: " + e.getMessage(); // 에러 메시지 반환
        }
    }

    @PostMapping("/admin/deleteComment")
    @ResponseBody
    public String deleteComment(@RequestParam int commentNo) {
        adminService.deleteComment(commentNo);
        return "success";
    }

    // 날짜 포맷팅을 위한 유틸리티 메소드
    private AdminMemberDTO formatMemberDate(AdminMemberDTO member) {
        if (member.getJoinDate() != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            member.setFormattedJoinDate(sdf.format(member.getJoinDate()));
        } else {
            member.setFormattedJoinDate("");
        }
        return member;
    }
}