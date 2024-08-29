package scv.DevOpsunity.notice.controller;



import org.springframework.ui.Model;
import scv.DevOpsunity.notice.noticedto.NoticeDTO;

public interface NoticeController {
    String listNotices(Model model);
    String showNewNoticeForm(Model model);
    String saveNotice(NoticeDTO noticeDTO);
    String showEditNoticeForm(Long id, Model model);
    String updateNotice(Long id, NoticeDTO noticeDTO);
    String deleteNotice(Long id);
}

