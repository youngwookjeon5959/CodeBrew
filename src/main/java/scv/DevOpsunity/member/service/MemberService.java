package scv.DevOpsunity.member.service;

import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Transactional;
import scv.DevOpsunity.member.dto.MemberDTO;

import java.util.List;

public interface MemberService {
	
	public List listMembers() throws DataAccessException;
	
	public void addMember(MemberDTO memberDTO) throws DataAccessException;
	
	public MemberDTO findMember(String id) throws DataAccessException;
	
	public void updateMember(MemberDTO memberDTO) throws DataAccessException;
	
	public void delMember(String id) throws DataAccessException;
	
	public MemberDTO login(MemberDTO memberDTO) throws DataAccessException;

	public int idCheck(String id);

	public String findIdByEmail(String email) throws DataAccessException;

	public void findPw(String memberEmail,String memberId)throws Exception;

	public int findPwCheck(MemberDTO memberDTO)throws Exception;

	//mypage
	public MemberDTO getMemberById(String id);

}
