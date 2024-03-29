package kr.or.ksmart.lms.pi.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ksmart.lms.pi.vo.NoticeLecture;
import kr.or.ksmart.lms.pi.service.PILectureService;
import kr.or.ksmart.lms.pi.vo.Institution;
import kr.or.ksmart.lms.pi.vo.LectureSignup;
import kr.or.ksmart.lms.pi.vo.LectureSignupResult;
import kr.or.ksmart.lms.pi.vo.Member;

@Controller
public class PILectureController {

	@Autowired private PILectureService piLectureService;	
	
	// PI layout 강의항목, 과목 리스트 출력 controller
	@GetMapping("/PI/lecture/listSubject")
	public ModelAndView piGetSubjectList(ModelAndView mav, @RequestParam String institutionCode) {
		System.out.println("[PILectureController piGetSubjectList] institutionCode:"+institutionCode);	
		
		System.out.println("[PILectureController piSetSubjectList] 수강생 강의,과목 조회시작");
		mav.setViewName("/PI/lecture/listSubject");
		
		// service에서 메서드 호출하여 mav내부에 값 담아서 뷰에서 활용하자
		Map<String, Object> map = piLectureService.piGetInfoLectureSortList(institutionCode);
		
		// 교육원코드, 교육원명을 mav에 담아 활용
		Institution institution = (Institution)map.get("institution");
		mav.addObject("institutionCode", institution.getInstitutionCode());
		mav.addObject("institutionName", institution.getInstitutionName());
		System.out.println("[PILectureController piSetSubjectList] institutionCode:"+institution.getInstitutionCode());
		System.out.println("[PILectureController piSetSubjectList] institutionName:"+institution.getInstitutionName());
		
		// 강의항목 list mav에 담아 활용 
		mav.addObject("sortList", map.get("list"));	
		System.out.println("[PILectureController piSetSubjectList] sortList : "+ map.get("list"));
		
		return mav;
	}
	
	// PI layout 수강신청을 위한 강의공고 리스트 출력 controller 
	@GetMapping("/PI/lecture/listLectureSignupNoticeLecture")
	public ModelAndView piGetNoticeLectureList(ModelAndView mav, HttpSession session
												, @RequestParam String institutionCode) {
		String memberRank = (String)session.getAttribute(("memberRank"));
		if(memberRank == null) {
			memberRank="로그인 실패";
		}
		if(memberRank.equals("수강생")) {
			System.out.println("수강생");
			
			System.out.println("[PILectureController piGetNoticeLectureList]");
			System.out.println("[PILectureController piGetNoticeLectureList] institutionCode:"+institutionCode);	
			mav.setViewName("PI/lecture/listLectureSignupNoticeLecture");
			
			// service에서 메서드 호출하여 mav내부에 값 담아서 뷰에서 활용하자
			Map<String, Object> map = piLectureService.piGetNoticeLectureList(institutionCode);
			
			// 교육원코드, 교육원명을 mav에 담아 활용
			Institution institution = (Institution)map.get("institution");
			mav.addObject("institutionCode", institution.getInstitutionCode());
			mav.addObject("institutionName", institution.getInstitutionName());
			System.out.println("[PILectureController piGetNoticeLectureList] institutionCode:"+institution.getInstitutionCode());
			System.out.println("[PILectureController piGetNoticeLectureList] institutionName:"+institution.getInstitutionName());
			
			// 강의공고 list mav에 담아 활용 
			mav.addObject("noticeLectureList", map.get("list"));	
			System.out.println("[PILectureController piGetNoticeLectureList] noticeLectureList : "+ map.get("list"));
		}else {
			System.out.println("수강생아님");
			
			mav.setViewName("PI/PILogin");
			
			// 교육원코드, 교육원명을 mav에 담아 활용
			String institutionName = (String)session.getAttribute("institutionName");
			
			mav.addObject("institutionCode", institutionCode);
			mav.addObject("institutionName", institutionName);
		}
		return mav;
	}
	
	// PI layout 수강신청을 위한 세부강의공고 출력 controller 
	@GetMapping("/PI/lecture/detailLectureSignupNoticeLecture")
	public ModelAndView piSelectNoticeLectureDetailByNoticeLectureCode(ModelAndView mav, HttpSession session
																	, @RequestParam String noticeLectureCode
																	, @RequestParam String institutionCode) {
		String memberRank = (String)session.getAttribute(("memberRank"));
		if(memberRank == null) {
			memberRank="로그인 실패";
		}
		if(memberRank.equals("수강생")) {
			System.out.println("수강생");
			
			System.out.println("[PILectureController piSelectNoticeLectureDetailByNoticeLectureCode]");
			System.out.println("[PILectureController piSelectNoticeLectureDetailByNoticeLectureCode] noticeLectureCode : "+noticeLectureCode);
			mav.setViewName("PI/lecture/detailLectureSignupNoticeLecture");
			
			// service에서 메서드 호출하여 mav내부에 값 담아서 뷰에서 활용하자
			Map<String, Object> map = piLectureService.piSelectNoticeLectureDetailByNoticeLectureCode(institutionCode, noticeLectureCode);
			
			// 교육원코드, 교육원명을 mav에 담아 활용
			Institution institution = (Institution)map.get("institution");
			mav.addObject("institutionCode", institution.getInstitutionCode());
			mav.addObject("institutionName", institution.getInstitutionName());
			System.out.println("[PILectureController piSelectNoticeLectureDetailByNoticeLectureCode] institutionCode:"+institution.getInstitutionCode());
			System.out.println("[PILectureController piSelectNoticeLectureDetailByNoticeLectureCode] institutionName:"+institution.getInstitutionName());
			
			// 강의공고 list mav에 담아 활용 
			mav.addObject("noticeLecture", map.get("noticeLecture"));	
			System.out.println("[PILectureController piGetNoticeLectureList] noticeLecture : "+ map.get("noticeLecture"));
		}else {
			System.out.println("수강생아님");
			
			mav.setViewName("PI/PILogin");
			
			// 교육원코드, 교육원명을 mav에 담아 활용
			String institutionName = (String)session.getAttribute("institutionName");
			
			mav.addObject("institutionCode", institutionCode);
			mav.addObject("institutionName", institutionName);
		}
		return mav;
	}
	
	// PI layout 수강신청 폼 controller  
	@GetMapping("/PI/lecture/viewLectureSignup")
	public ModelAndView piAddLectureSignup(ModelAndView mav, HttpSession session
																	, @RequestParam String noticeLectureCode
																	, @RequestParam String institutionCode) {
		String memberRank = (String)session.getAttribute(("memberRank"));
		if(memberRank == null) {
			memberRank="로그인 실패";
		}
		if(memberRank.equals("수강생")) {
			System.out.println("수강생");
			
			System.out.println("[PILectureController piAddLectureSignup]");
			System.out.println("[PILectureController piAddLectureSignup] noticeLectureCode : "+noticeLectureCode);
			mav.setViewName("/PI/lecture/viewLectureSignup");
			
			// 세션에 담긴 member정보 가져와서 Service 메서드 호출 시 사용하자
			String memberCode = (String)session.getAttribute("memberCode");
						
			// service에서 메서드 호출하여 mav내부에 값 담아서 뷰에서 활용하자
			Map<String, Object> map = piLectureService.piGetNoticeLectureInfoForLectureSignup(institutionCode, noticeLectureCode, memberCode);
			
			// 교육원코드, 교육원명을 mav에 담아 활용
			Institution institution = (Institution)map.get("institution");
			mav.addObject("institutionCode", institution.getInstitutionCode());
			mav.addObject("institutionName", institution.getInstitutionName());
			System.out.println("[PILectureController piAddLectureSignup] institutionCode:"+institution.getInstitutionCode());
			System.out.println("[PILectureController piAddLectureSignup] institutionName:"+institution.getInstitutionName());
			
			// 강의공고 코드를 이용하여 noticeLecture 정보 가져와서 mav에 담고 뷰에서 사용하자
			NoticeLecture noticeLecture = (NoticeLecture)map.get("noticeLecture");
			mav.addObject("noticeLectureCode", noticeLecture.getNoticeLectureCode());
			mav.addObject("noticeLectureTitle", noticeLecture.getNoticeLectureTitle());
			mav.addObject("noticeLectureStartDate", noticeLecture.getNoticeLectureStartDate());
			mav.addObject("noticeLectureEndDate", noticeLecture.getNoticeLectureEndDate());
			
			// map내부에 담긴 member정보 가져와서 mav에 담고 뷰에서 사용하자
			String memberName = (String)session.getAttribute("memberName");
			Member member = (Member)map.get("member");
			mav.addObject("memberCode", memberCode);
			mav.addObject("memberName", memberName);
			mav.addObject("memberRegistrationNumberFront", member.getMemberRegistrationNumberFront());			
		}else {
			System.out.println("수강생아님");
			
			mav.setViewName("PI/PILogin");
			
			// 교육원코드, 교육원명을 mav에 담아 활용
			String institutionName = (String)session.getAttribute("institutionName");
			
			mav.addObject("institutionCode", institutionCode);
			mav.addObject("institutionName", institutionName);
		}
		return mav;
	}
	
	// 수강신청 처리 
	@PostMapping("/PI/lecture/viewLectureSignup")
	public ModelAndView piAddLectureSignup(ModelAndView mav, HttpSession session, LectureSignup lectureSignup
										, @RequestParam String institutionCode, @RequestParam String noticeLectureCode) {
		String memberRank = (String)session.getAttribute(("memberRank"));
		if(memberRank == null) {
			memberRank="로그인 실패";
		}
		if(memberRank.equals("수강생")) {
			System.out.println("수강생");
			
			System.out.println("[PILectureController piAddLectureSignup]");
			mav.setViewName("redirect:/PIIndex");

			// service에서 메서드 호출하여 mav내부에 값 담아서 뷰에서 활용하자
			// INSERT 작업을 위해 service 메서드 호출
			Map<String, Object> map = piLectureService.piAddLectureSignup(institutionCode, noticeLectureCode, lectureSignup);
			
			// 교육원코드, 교육원명을 mav에 담아 활용
			Institution institution = (Institution)map.get("institution");
			mav.addObject("institutionCode", institution.getInstitutionCode());
			mav.addObject("institutionName", institution.getInstitutionName());
			System.out.println("[PILectureController piAddLectureSignup] institutionCode:"+institution.getInstitutionCode());
			System.out.println("[PILectureController piAddLectureSignup] institutionName:"+institution.getInstitutionName());			
		}else {
			System.out.println("수강생아님");
			
			mav.setViewName("PI/PILogin");
			
			// 교육원코드, 교육원명을 mav에 담아 활용
			String institutionName = (String)session.getAttribute("institutionName");
			
			mav.addObject("institutionCode", institutionCode);
			mav.addObject("institutionName", institutionName);
		}
		return mav;
	}
			
	// PI layout 수강신청목록 내역 조회 controller  
	@GetMapping("/PI/myPage/viewLectureSignupList")
	public ModelAndView piViewLectureSignupResult(ModelAndView mav, HttpSession session, @RequestParam String institutionCode) {
		String memberRank = (String)session.getAttribute(("memberRank"));
		if(memberRank == null) {
			memberRank="로그인 실패";
		}
		if(memberRank.equals("수강생")) {
			System.out.println("수강생");
		
			System.out.println("[PILectureController piViewLectureSignupResult]");
			mav.setViewName("/PI/myPage/viewLectureSignupList");
			// 교육원코드, 교육원명을 mav에 담아 활용
			String institutionName = (String)session.getAttribute("institutionName");
			mav.addObject("institutionCode", institutionCode);
			mav.addObject("institutionName", institutionName);
		
			// 수강신청 내역 출력 메서드
			String memberCode = (String)session.getAttribute("memberCode");
			List<LectureSignup> list = piLectureService.piGetLectureSignupListByMemberCode(memberCode);
			// 내역 목록을 mav내부에 담아 뷰에서 활용하기
			mav.addObject("list", list);
		}else {
			System.out.println("수강생아님");
		
			mav.setViewName("PI/PILogin");
		
			// 교육원코드, 교육원명을 mav에 담아 활용
			String institutionName = (String)session.getAttribute("institutionName");
			mav.addObject("institutionCode", institutionCode);
			mav.addObject("institutionName", institutionName);
		}
		return mav;
	}
	
	// PI layout 해당수강신청 상세내역 조회 controller  
	@GetMapping("/PI/myPage/detailLectureSignupResult")
	public ModelAndView piGetDetailLectureSignupResult(ModelAndView mav, HttpSession session
														, @RequestParam String institutionCode
														, @RequestParam String noticeLectureTitle
														, @RequestParam String lectureSignupCode
														, @RequestParam String lectureSignupDate) {
		String memberRank = (String)session.getAttribute(("memberRank"));
		if(memberRank == null) {
			memberRank="로그인 실패";
		}
		if(memberRank.equals("수강생")) {
			System.out.println("수강생");
		
			System.out.println("[PILectureController piGetDetailLectureSignupResult]");
			System.out.println("[PILectureController piGetDetailLectureSignupResult] noticeLectureTitle: "+noticeLectureTitle);
			System.out.println("[PILectureController piGetDetailLectureSignupResult] lectureSignupCode: "+lectureSignupCode);
			System.out.println("[PILectureController piGetDetailLectureSignupResult] lectureSignupDate: "+lectureSignupDate);
			mav.setViewName("/PI/myPage/detailLectureSignupResult");
			// 교육원코드, 교육원명을 mav에 담아 활용
			String institutionName = (String)session.getAttribute("institutionName");
			mav.addObject("institutionCode", institutionCode);
			mav.addObject("institutionName", institutionName);
			// 강의공고명, 수강신청일자를 mav에 담아 보여주자
			mav.addObject("noticeLectureTitle", noticeLectureTitle);
			mav.addObject("lectureSignupDate", lectureSignupDate);
		
			// 해당 수강신청 상세조회 출력 메서드
			LectureSignupResult result = piLectureService.piGetLectureSignupResultByLectureSignupCode(lectureSignupCode);
			// 내역 목록을 mav내부에 담아 뷰에서 활용하기
			mav.addObject("result", result);
			mav.addObject("lectureSignupResult", result.getLectureSignupResult());
		}else {
			System.out.println("수강생아님");
		
			mav.setViewName("PI/PILogin");
		
			// 교육원코드, 교육원명을 mav에 담아 활용
			String institutionName = (String)session.getAttribute("institutionName");
			mav.addObject("institutionCode", institutionCode);
			mav.addObject("institutionName", institutionName);
		}
		return mav;
	}
	
	// PI layout 강의결제 화면 출력 controller  
	@GetMapping("/PI/myPage/viewPaymentLecture")
	public ModelAndView piAddPaymentLecture(ModelAndView mav, HttpSession session
														, @RequestParam String institutionCode
														, @RequestParam String noticeLectureCode) {
		String memberRank = (String)session.getAttribute(("memberRank"));
		if(memberRank == null) {
			memberRank="로그인 실패";
		}
		if(memberRank.equals("수강생")) {
			System.out.println("수강생");
		
			System.out.println("[PILectureController piAddPaymentLecture]");
			System.out.println("[PILectureController piAddPaymentLecture] noticeLectureCode: "+noticeLectureCode);
			mav.setViewName("/PI/myPage/viewPaymentLecture");
			
			// 교육원코드, 교육원명을 mav에 담아 활용
			String institutionName = (String)session.getAttribute("institutionName");
			mav.addObject("institutionCode", institutionCode);
			mav.addObject("institutionName", institutionName);
			
			// session에서 회원코드 가져와서 service메서드 호출 시 사용(memberCode-> 회원정보, noticeLectureCode->결제할 강의 정보)
			String memberCode = (String)session.getAttribute("memberCode");
			String memberOnlineId = (String)session.getAttribute("memberOnlineId");
			// 강의결제 화면 출력을 위한 준비
			Map<String, Object> map = piLectureService.piAddPaymentLecture(memberCode, noticeLectureCode);
			// 내역 목록을 mav내부에 담아 뷰에서 활용하기
			mav.addObject("member", map.get("member"));
			mav.addObject("member", map.get("noticeLecture"));
			mav.addObject("memberOnlineId", memberOnlineId);
		}else {
			System.out.println("수강생아님");
		
			mav.setViewName("PI/PILogin");
		
			// 교육원코드, 교육원명을 mav에 담아 활용
			String institutionName = (String)session.getAttribute("institutionName");
			mav.addObject("institutionCode", institutionCode);
			mav.addObject("institutionName", institutionName);
		}
		return mav;
	}
}