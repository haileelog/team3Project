package kr.or.ksmart.lms.pi.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ksmart.lms.association.vo.Board;
import kr.or.ksmart.lms.index.vo.IndexInstitution;
import kr.or.ksmart.lms.pi.service.PIBoardService;

@Controller
public class PIBoardController {
	@Autowired private PIBoardService piBoardService;
	//	교육원홈페이지 공지사항 view
	@GetMapping("/PINotice")
	public ModelAndView getNoticeByPI (HttpSession session, ModelAndView mav
			, @RequestParam(value="institutionCode", required = true) String institutionCode) {
		IndexInstitution institution = piBoardService.PIIndex(institutionCode);
		mav.addObject("institutionCode", institution.getInstitutionCode());
		mav.addObject("institutionName", institution.getInstitutionName());
		
		mav.setViewName("PI/Board/PINotice");
		return mav;
	}
	//	교육원/협회 공지사항 글쓰기 화면 get요청
	@GetMapping("/PINoticeWrite")
	public ModelAndView piWriteNotice (HttpSession session, ModelAndView mav
			, @RequestParam(value="institutionCode", required = true) String institutionCode) {
		IndexInstitution institution = piBoardService.PIIndex(institutionCode);
		mav.addObject("institutionCode", institution.getInstitutionCode());
		mav.addObject("institutionName", institution.getInstitutionName());
		String memberRank = (String)session.getAttribute("memberRank");
		if (memberRank == null) {
			System.out.println("[PIMemberController memberInfoModify] 로그아웃상태");
			mav.setViewName("redirect:/PILogin?"+institutionCode);
		} else if (memberRank != null) {
			mav.setViewName("PI/Board/PINoticeWrite");
		}
		return mav;
	}
	//	교육원홈페이지 커뮤니티 view
	@GetMapping("/PIBoard")
	public ModelAndView getBoardByPI (HttpSession session, ModelAndView mav
			, @RequestParam(value="institutionCode", required = true) String institutionCode) {
		IndexInstitution institution = piBoardService.PIIndex(institutionCode);
		mav.addObject("institutionCode", institution.getInstitutionCode());
		mav.addObject("institutionName", institution.getInstitutionName());
		List<Board> boardList = piBoardService.getBoardList();
		mav.addObject("boardList", boardList);
		mav.setViewName("PI/Board/PIBoard");
		return mav;
	}
	//	교육원홈페이지 커뮤니티 글쓰기 화면 get요청
	@GetMapping("/PIBoardwrite")
	public ModelAndView piWriteBoard (HttpSession session, ModelAndView mav
			, @RequestParam(value="institutionCode", required = true) String institutionCode) {
		IndexInstitution institution = piBoardService.PIIndex(institutionCode);
		mav.addObject("institutionCode", institution.getInstitutionCode());
		mav.addObject("institutionName", institution.getInstitutionName());
		String memberName = (String)session.getAttribute("memberName");
		if (memberName == null) {
			System.out.println("[PIMemberController memberInfoModify] 로그아웃상태");
			mav.setViewName("redirect:/PILogin?"+institutionCode);
		} else if (memberName != null) {
			mav.setViewName("PI/Board/PIBoardwrite");
		}
		return mav;
	}
	//	교육원홈페이지 커뮤니티 글쓰기 처리 POST 요청
	@PostMapping("/addBoard")
	public ModelAndView addBoard (Board board, HttpSession session, ModelAndView mav
	, @RequestParam(value="institutionCode", required = true) String institutionCode) {
		System.out.println("[PIBoardController addBoard] 글등록 처리요청");
		piBoardService.addBoard(board, session, institutionCode);
		mav.setViewName("PI/Board/PIBoard?institutionCode="+institutionCode);
		return mav;
	}
	
	//	교육원홈페이지 질의응답 view
	@GetMapping("/PIQnA")
	public ModelAndView getBoardByPIQnA (HttpSession session, ModelAndView mav
			, @RequestParam(value="institutionCode", required = true) String institutionCode) {
		IndexInstitution institution = piBoardService.PIIndex(institutionCode);
		mav.addObject("institutionCode", institution.getInstitutionCode());
		mav.addObject("institutionName", institution.getInstitutionName());
		mav.setViewName("PI/Board/PIQnA");
		return mav;
	}
	@GetMapping("/PIqnaWrite")
	public ModelAndView piWriteQna (HttpSession session, ModelAndView mav
			, @RequestParam(value="institutionCode", required = true) String institutionCode) {
		IndexInstitution institution = piBoardService.PIIndex(institutionCode);
		mav.addObject("institutionCode", institution.getInstitutionCode());
		mav.addObject("institutionName", institution.getInstitutionName());
		String memberName = (String)session.getAttribute("memberName");
		if (memberName == null) {
			System.out.println("[PIMemberController memberInfoModify] 로그아웃상태");
			mav.setViewName("redirect:/PILogin?"+institutionCode);
		} else if (memberName != null) {
			mav.setViewName("PI/Board/PIBoardwrite");
		}
		return mav;
	}
	//	교육원홈페이지 자주하는질문 view
	@GetMapping("/PIFAQ")
	public ModelAndView getBoardByPIFAQ (HttpSession session, ModelAndView mav
			, @RequestParam(value="institutionCode", required = true) String institutionCode) {
		IndexInstitution institution = piBoardService.PIIndex(institutionCode);
		mav.addObject("institutionCode", institution.getInstitutionCode());
		mav.addObject("institutionName", institution.getInstitutionName());
		mav.setViewName("PI/Board/PIFAQ");
		return mav;
	}
	@GetMapping("/PIFaqWrite")
	public ModelAndView piWriteFaq (HttpSession session, ModelAndView mav
			, @RequestParam(value="institutionCode", required = true) String institutionCode) {
		IndexInstitution institution = piBoardService.PIIndex(institutionCode);
		mav.addObject("institutionCode", institution.getInstitutionCode());
		mav.addObject("institutionName", institution.getInstitutionName());
		String memberRank = (String)session.getAttribute("memberName");
		if (memberRank == null) {
			System.out.println("[PIMemberController memberInfoModify] 로그아웃상태");
			mav.setViewName("redirect:/PILogin?"+institutionCode);
		} else if (memberRank != null) {
			mav.setViewName("PI/Board/PIFaqWrite");
		}
		return mav;
	}
}
