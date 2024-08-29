package scv.DevOpsunity.notice.noticeservice;


import scv.DevOpsunity.notice.noticedto.NoticeDTO;

import java.util.List;

public interface NoticeService {
    List<NoticeDTO> getAllNotices();
    NoticeDTO getNoticeById(Long id);
    void saveNotice(NoticeDTO notice);
    void updateNotice(NoticeDTO notice);
    void deleteNotice(Long id);
/*    NoticeDTO getNotice(Long id);*/
}