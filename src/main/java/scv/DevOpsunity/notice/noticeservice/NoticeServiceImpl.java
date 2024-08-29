package scv.DevOpsunity.notice.noticeservice;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import scv.DevOpsunity.notice.noticedao.NoticeDAO;
import scv.DevOpsunity.notice.noticedto.NoticeDTO;

import java.util.List;

@Service

public class NoticeServiceImpl  {


    @Autowired
    private NoticeDAO noticeDAO;



    public List<NoticeDTO> getAllNotices(int page, int size) {
        int offset = (page - 1) * size;
        return noticeDAO.selectAllNotices(offset, size);
    }

    public NoticeDTO getNoticeById(Long id) {
        return noticeDAO.selectNoticeById(id);
    }

    public void saveNotice(NoticeDTO notice) {
        noticeDAO.insertNotice(notice);
    }

    public void updateNotice(NoticeDTO notice) {
        noticeDAO.updateNotice(notice);
    }

    public void deleteNotice(Long id) {
        noticeDAO.deleteNotice(id);
    }



    public int getTotalPageCount(int size) {
        int totalCount = noticeDAO.getTotalNoticeCount();
        return (int) Math.ceil((double) totalCount / size);
    }
}


