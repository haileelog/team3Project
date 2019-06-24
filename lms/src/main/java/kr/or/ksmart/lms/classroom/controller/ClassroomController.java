package kr.or.ksmart.lms.classroom.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ksmart.lms.classroom.service.ClassroomService;
import kr.or.ksmart.lms.classroom.vo.Classroom;

@Controller
public class ClassroomController {
	@Autowired ClassroomService classroomService;
	// 협회
	// association layout 교육원 검색 controller 
	@GetMapping("/association/classroom/searchInstitution")
	public ModelAndView getInstitutionList(ModelAndView mav, HttpSession session) {
		String memberRank = (String)session.getAttribute(("memberRank"));
		if(memberRank.equals("협회직원")) {
			System.out.println("협회직원");
			
			System.out.println("[ClassroomController getInstitutionList 교육원 검색]");
			mav.setViewName("association/classroom/searchInstitution");
		}else {
			System.out.println("협회직원아님");
			
			mav.setViewName("association/associationLogin");
		}
		return mav;
	}
	
	// association layout 강의실 추가 controller
	@GetMapping("/association/classroom/addClassroom")
	public ModelAndView addClassroom(ModelAndView mav, HttpSession session
									,@RequestParam String institutionCode) {
		String memberRank = (String)session.getAttribute(("memberRank"));
		if(memberRank.equals("협회직원")) {
			System.out.println("협회직원");
			System.out.println("[ClassroomController GET addClassroom]institutionCode: "+institutionCode);
			
			System.out.println("[ClassroomController addClassroom]");
			mav.setViewName("association/classroom/addClassroom");
			
			// map에 담아서 가져온 값들을 model에 넣어 view에서 활용
			Map<String, Object> map = classroomService.getInstitutionByInstCode(institutionCode);	
			mav.addObject("institutionCode", map.get("institutionCode"));
			mav.addObject("institutionName", map.get("institutionName"));
			mav.addObject("institutionLocation", map.get("institutionLocation"));
			mav.addObject("useList", map.get("useList"));
			
			System.out.println("Controller instCode 확인: "+map.get("institutionCode"));
		}else {
			System.out.println("협회직원아님");
			
			mav.setViewName("association/associationLogin");
		}
		return mav;
	}
	// 강의실 추가  
	@PostMapping("/association/classroom/addClassroom")
	public ModelAndView addClassroom(ModelAndView mav, HttpSession session, Classroom classroom) {
		String memberRank = (String)session.getAttribute(("memberRank"));
		if(memberRank.equals("협회직원")) {
			System.out.println("협회직원");
			System.out.println("[ClassroomController addClassroom]");			
			
			classroomService.addClassroom(classroom);
			mav.setViewName("association/classroom/classroomList");
		}else {
			System.out.println("협회직원아님");
			
			mav.setViewName("association/associationLogin");
		}
		return mav;
	}
				
	// 강의실 리스트 조회
	@GetMapping("/association/classroom/classroomList")
	public ModelAndView getClassroomList(ModelAndView mav, HttpSession session) {
		String memberRank = (String)session.getAttribute(("memberRank"));
		if(memberRank.equals("협회직원")) {
			System.out.println("협회직원");
			
			System.out.println("[ClassroomController getClassroomList]");
			mav.setViewName("association/classroom/classroomList");
		}else {
			System.out.println("협회직원아님");
			
			mav.setViewName("association/associationLogin");
		}
		return mav;
	}
	
	

	 
	
}
