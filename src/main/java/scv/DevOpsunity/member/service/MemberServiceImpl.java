package scv.DevOpsunity.member.service;

import jakarta.mail.internet.MimeMessage;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import scv.DevOpsunity.member.dao.MemberDAO;
import scv.DevOpsunity.member.dto.MemberDTO;
import scv.DevOpsunity.member.util.MailUtils;
import scv.DevOpsunity.member.util.TempKey;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

@Service("memberService")
public class MemberServiceImpl implements MemberService {
   
   @Autowired
   private MemberDAO memberDAO;

   @Autowired
   private JavaMailSender sender;

    @Autowired
    private MailService mailService;

    @Override
   public List listMembers() throws DataAccessException {
      List membersList = memberDAO.selectAllMembersList();
      return membersList;
   }

    @Override
    public void addMember(MemberDTO memberDTO) throws DataAccessException {

            memberDAO.insertMember(memberDTO);

    }

    @Override
    public MemberDTO findMember(String id) throws DataAccessException {
        MemberDTO memberDTO = memberDAO.selectMemberById(id);
        return memberDTO;
    }

    @Override
    public void updateMember(MemberDTO memberDTO) throws DataAccessException {
        memberDAO.updateMember(memberDTO);

    }

    @Override
    public void delMember(String id) throws DataAccessException {
        memberDAO.delMember(id);

    }

    @Override
    public MemberDTO login(MemberDTO memberDTO) throws DataAccessException {
        return memberDAO.loginCheck(memberDTO);
    }

    @Override
    public int idCheck(String id) {
        int cnt = memberDAO.idCheck(id);
        return cnt;
    }


    @Override
    public String findIdByEmail(String email) {
        List<String> ids = memberDAO.findIdByEmail(email);
        if (ids.isEmpty()) {
            return null;
        } else if (ids.size() > 1) {
            throw new RuntimeException("여러 결과가 반환되었습니다.");
        } else {
            return ids.get(0);
        }
    }

    @Override
    public void findPw(String email, String id) throws Exception {
        TempKey tempKey = new TempKey();
        String pw = tempKey.getKey(6, false);
        String memberPw = BCrypt.hashpw(pw, BCrypt.gensalt());

        memberDAO.findPw(email, id, pw);

        try {
            MailUtils sendMail = new MailUtils(sender);
            sendMail.setSubject("[Code Brew 임시 비밀번호 입니다.]");
            sendMail.setText(
                    "<h1>임시비밀번호 발급</h1>" +
                            "<br/>" + id + "님 " +
                            "<br/>비밀번호 찾기를 통한 임시 비밀번호입니다." +
                            "<br/>임시비밀번호 : <h2>" + pw + "</h2>" +
                            "<br/>로그인 후 비밀번호 변경을 해주세요." +
                            "<a href='http://localhost:8090/member/loginForm'>로그인 페이지</a>");
            sendMail.setFrom("your-email@example.com", "Code Brew");
            sendMail.setTo(email);
            sendMail.send();
        } catch (Exception e) {
            throw new RuntimeException("이메일 전송 실패", e);
        }
    }

    @Override
    public int findPwCheck(MemberDTO memberDTO) throws Exception {
        return memberDAO.findPwCheck(memberDTO);
    }

    @Override
    public MemberDTO getMemberById(String id) {
        return memberDAO.getMemberById(id);
    }

}






