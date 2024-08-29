package scv.DevOpsunity.notice.noticedao;


import org.apache.ibatis.annotations.Mapper;
import scv.DevOpsunity.notice.noticedto.NoticeDTO;

import java.util.List;

@Mapper
public interface NoticeDAO {

    List<NoticeDTO> selectAllNotices(int offset, int limit);
    NoticeDTO selectNoticeById(Long id);
    void insertNotice(NoticeDTO notice);
    void updateNotice(NoticeDTO notice);
    void deleteNotice(Long id);
    int getTotalNoticeCount();


}