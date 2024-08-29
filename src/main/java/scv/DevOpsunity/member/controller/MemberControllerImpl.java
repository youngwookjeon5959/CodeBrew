package scv.DevOpsunity.member.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import scv.DevOpsunity.Reservation.dto.ResDTO;
import scv.DevOpsunity.member.dto.MemberDTO;
import scv.DevOpsunity.member.service.MemberServiceImpl;

import java.util.List;

@Controller("memberController")
public class MemberControllerImpl implements MemberController{
	
	@Autowired //자동주입 어노테이션
	private MemberServiceImpl memberService;
	@Autowired
	private MemberDTO memberDTO;
	@Autowired
	private ResDTO resDTO;

	@GetMapping("/member/listMembers.do")
	public ModelAndView listMembers(HttpServletRequest request , HttpServletResponse response) throws Exception {
		List membersList = memberService.listMembers();
		ModelAndView mav = new ModelAndView();
		mav.setViewName("member/listMembers");
		mav.addObject("membersList",membersList);
		return mav;
	}

	@Override
	@PostMapping("/member/addMember.do")
	public ModelAndView addMember(@ModelAttribute("memberDTO") MemberDTO memberDTO,
								  HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.setCharacterEncoding("utf-8");
		memberService.addMember(memberDTO);
		ModelAndView mav=new ModelAndView("redirect:/main.do");
		return mav;
	}


	@Override
	@GetMapping("/member/memberForm.do")
	public ModelAndView memberForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("member/memberForm");
		return mav;
	}

	 @Override
	   @GetMapping("/member/modMemberForm.do")
	   public ModelAndView modMemberForm(@RequestParam("id") String id, HttpServletRequest request, HttpServletResponse response)
	         throws Exception {
	        memberDTO=memberService.findMember(id);
	        ModelAndView mav=new ModelAndView("member/modMemberForm");
	        mav.addObject("member",memberDTO);
	      return mav;
	   }

	@Override
	@PostMapping("/member/updateMember.do")
	public ModelAndView updateMember(@ModelAttribute("memberDTO") MemberDTO memberDTO,
									 HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.setCharacterEncoding("utf-8");
		memberService.updateMember(memberDTO);

		// Update session information
		HttpSession session = request.getSession();
		MemberDTO sessionMember = (MemberDTO) session.getAttribute("member");
		if (sessionMember != null && sessionMember.getId().equals(memberDTO.getId())) {
			session.setAttribute("member", memberDTO);
		}

		ModelAndView mav = new ModelAndView("redirect:/mypage/mypage.do");
		return mav;
	}

	   @Override
	   @GetMapping("/member/delMember.do")
	   public ModelAndView delMember(String id, HttpServletRequest request, HttpServletResponse response)
	         throws Exception {
	          memberService.delMember(id);
	          ModelAndView mav=new ModelAndView("redirect:/member/listMembers.do");
	      return mav;
	   }
	   
	   @Override
	   @GetMapping("/member/loginForm.do")
	   public ModelAndView loginForm(@ModelAttribute("member") MemberDTO member,
				@RequestParam(value = "action" , required = false) String action,
				@RequestParam(value = "result", required = false) String result,
			   HttpServletRequest request, HttpServletResponse response) throws Exception{
		   HttpSession session = request.getSession();	//  getSession() : 세션이 있다면 가져오고 없다면 생성
		   session.setAttribute("action", action);

		   ModelAndView mav = new ModelAndView();
		   mav.addObject("result", result);
		   mav.setViewName("member/loginForm");
		   return mav;
	   }

	@PostMapping("/member/login.do")
	public ModelAndView login(@ModelAttribute("member") MemberDTO member, RedirectAttributes rAttr, HttpServletRequest request,
							  HttpServletResponse response) throws Exception {
		MemberDTO memberDTO = memberService.login(member);
		ModelAndView mav = new ModelAndView();
		if (memberDTO != null) {
			HttpSession session = request.getSession();
			session.setAttribute("member", memberDTO);
			session.setAttribute("isLogOn", true);
			session.setAttribute("userId", memberDTO.getId());
			session.setAttribute("user_id", memberDTO.getId());

			String action = (String) session.getAttribute("action");

			if (action != null) {
				mav.setViewName("redirect:" + action);
			} else {
				mav.setViewName("redirect:/main.do");
			}
		} else {
			rAttr.addAttribute("result", "아이디나 비밀번호가 틀립니다. 다시 로그인해 주세요");
			mav.setViewName("redirect:/member/loginForm.do");
		}
		return mav;
	}


	@Override
	@GetMapping("/member/logout.do")
	public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		session.removeAttribute("member");
		session.removeAttribute("isLogOn");
		session.removeAttribute("userId");
		session.removeAttribute("user_id");
		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:/main.do");
		return mav;
	}


	//아이디 중복체크
	@PostMapping("/member/idCheck.do")
	@ResponseBody
	public int idCheck(@RequestParam("id") String id ,HttpServletRequest request, HttpServletResponse response)throws Exception {
		int cnt = memberService.idCheck(id);
		return cnt;
	}

	
	
	
	
	// 아이디,비밀번호 찾기



	//아이디
	@GetMapping("/member/findIdForm.do")
	public String showFindIdForm() {
		return "member/findIdForm";
	}

	// 아이디 찾기
	@PostMapping("/member/findId.do")
	public String findId(@RequestParam String email, Model model) {
		String id = memberService.findIdByEmail(email);
		if (id != null) {
			model.addAttribute("foundId", id);
		} else {
			model.addAttribute("error", "해당 이메일로 등록된 아이디가 없습니다.");
		}
		return "member/findIdResult";
	}


	//비밀번호 찾기
	@RequestMapping(value = "/findPw", method = RequestMethod.GET)
	public String findPw(@RequestParam(required = false) String email,
						 @RequestParam(required = false) String id,
						 Model model) throws Exception {
		// 파라미터가 없는 경우 (최초 페이지 로드)
		if (email == null && id == null) {
			return "member/findPwView";
		}

		// 파라미터가 비어있는 경우
		if (email == null || email.trim().isEmpty() || id == null || id.trim().isEmpty()) {
			model.addAttribute("msg", "아이디와 이메일을 모두 입력해주세요.");
			return "member/findPwView";
		}

		System.out.println("mpw: " + id);

		MemberDTO memberDTO = new MemberDTO();
		memberDTO.setEmail(email);
		memberDTO.setId(id);

		if (memberService.findPwCheck(memberDTO) == 0) {
			System.out.println("memberPWCheck failed");
			model.addAttribute("msg", "입력한 정보와 일치하는 계정이 없습니다.");
			return "member/findPwView";
		} else {
			memberService.findPw(email, id);
			model.addAttribute("email", email);
			return "member/findPw";
		}
	}

	@RequestMapping(value = "/findPwView", method = RequestMethod.GET)
	public String findPwView() throws Exception {
		return "member/findPwView";
	}


	@GetMapping("/mypage/mypage.do")
	public String getMyPage(HttpSession session, Model model) {
		MemberDTO sessionMember = (MemberDTO) session.getAttribute("member");
		if (sessionMember != null) {
			String id = sessionMember.getId();
			MemberDTO member = memberService.getMemberById(id);
			model.addAttribute("member", member);
			return "mypage/mypage";
		} else {
			// 로그인되지 않은 경우 처리
			return "redirect:/member/loginForm.do";
		}

	}

}
