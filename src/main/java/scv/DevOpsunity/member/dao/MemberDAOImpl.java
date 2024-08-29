package scv.DevOpsunity.member.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import scv.DevOpsunity.member.dto.MemberDTO;

import java.util.HashMap;
import java.util.Map;


@Repository
public abstract class MemberDAOImpl implements MemberDAO {

    @Autowired
    private SqlSession sqlSession;

    @Override
    public int findPwCheck(MemberDTO memberDTO) throws Exception {
        return sqlSession.selectOne("scv.DevOpsunity.member.dao.MemberDAO.findPwCheck", memberDTO);
    }

    @Override
    public void findPw(String email, String id, String pw) throws Exception {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("email", email);
        paramMap.put("id", id);
        paramMap.put("pw", pw);
        sqlSession.update("scv.DevOpsunity.member.dao.MemberDAO.findPw", paramMap);
    }

}