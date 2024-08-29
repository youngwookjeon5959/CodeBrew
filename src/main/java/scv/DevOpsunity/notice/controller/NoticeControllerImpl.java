package scv.DevOpsunity.notice.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import org.springframework.web.bind.annotation.*;
import scv.DevOpsunity.notice.noticedto.NoticeDTO;
import scv.DevOpsunity.notice.noticeservice.NoticeServiceImpl;


import jakarta.servlet.http.HttpSession;

import javax.validation.Valid;
import java.util.List;

@Controller
public class NoticeControllerImpl {


    @Autowired
    private NoticeServiceImpl noticeService;


    private boolean isLoggedIn(HttpSession session) {
        return session.getAttribute("userId") != null;
    }

    @GetMapping("/notices")
    public String listNotices(@RequestParam(defaultValue = "1") int page,
                              @RequestParam(defaultValue = "10") int size,
                              Model model) {
        List<NoticeDTO> notices = noticeService.getAllNotices(page, size);
        int totalPages = noticeService.getTotalPageCount(size);

        model.addAttribute("notices", notices);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);

        return "notices/list";
    }

    @GetMapping("/notices/new")

    public String showNewNoticeForm(Model model, HttpSession session) {
        if (!isLoggedIn(session)) {
            return "redirect:/member/loginForm.do";
        }

        model.addAttribute("notice", new NoticeDTO());
        return "notices/new";
    }

    @PostMapping("/notices/save")

    public String saveNotice(@Valid @ModelAttribute NoticeDTO noticeDTO,
                             BindingResult bindingResult,
                             HttpSession session) {
        if (!isLoggedIn(session)) {
            return "redirect:/member/loginForm.do";
        }
        if (bindingResult.hasErrors()) {
            return "notices/new";
        }
        String author = (String) session.getAttribute("userId");
        noticeDTO.setAuthor(author);
        noticeService.saveNotice(noticeDTO);

        return "redirect:/notices";
    }

    @GetMapping("/notices/edit/{id}")

    public String showEditNoticeForm(@PathVariable("id") Long id, Model model, HttpSession session) {
        if (!isLoggedIn(session)) {
            return "redirect:/member/loginForm.do";
        }
        NoticeDTO notice = noticeService.getNoticeById(id);
        if (!notice.getAuthor().equals(session.getAttribute("userId"))) {
            return "redirect:/notices";
        }
        model.addAttribute("noticeForm", notice);

        return "notices/edit";
    }

    @PostMapping("/notices/update/{id}")

    public String updateNotice(@PathVariable("id") Long id,
                               @Valid @ModelAttribute NoticeDTO noticeDTO,
                               BindingResult bindingResult,
                               HttpSession session) {
        if (!isLoggedIn(session)) {
            return "redirect:/loginForm";
        }
        if (bindingResult.hasErrors()) {
            return "notices/edit";
        }
        NoticeDTO existingNotice = noticeService.getNoticeById(id);
        if (!existingNotice.getAuthor().equals(session.getAttribute("userId"))) {
            return "redirect:/notices";
        }
        noticeDTO.setId(id);
        noticeDTO.setAuthor((String) session.getAttribute("userId"));
        noticeService.updateNotice(noticeDTO);

        return "redirect:/notices";
    }

    @GetMapping("/notices/delete/{id}")

    public String deleteNotice(@PathVariable("id") Long id, HttpSession session) {
        if (!isLoggedIn(session)) {
            return "redirect:/loginForm";
        }
        NoticeDTO notice = noticeService.getNoticeById(id);
        if (!notice.getAuthor().equals(session.getAttribute("userId"))) {
            return "redirect:/notices";
        }

        noticeService.deleteNotice(id);
        return "redirect:/notices";
    }


    @GetMapping("/notices/{id}")
    public String getNotice(@PathVariable("id") Long id, Model model) {

        NoticeDTO notice = noticeService.getNoticeById(id);
        model.addAttribute("notice", notice);
        return "notices/detail";
    }

}
