package kr.or.ksmart.lms.member.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ksmart.lms.institution.vo.Institution;
import kr.or.ksmart.lms.member.service.MemberService;
import kr.or.ksmart.lms.member.vo.Member;
import kr.or.ksmart.lms.member.vo.MemberOnline;

@Controller
public class MemberController {
	@Autowired MemberService memberService;
	
	//	회원등록 join.html(회원)
	@GetMapping("join")
	public ModelAndView memberJoin(ModelAndView mav) {
		mav.setViewName("LE/Join");
		return mav;
	}
	//	회원등록 처리
	@PostMapping("memberInsert")
	public ModelAndView memberInsert(Member member, MemberOnline memberOnline, String institutionCode, ModelAndView mav) {
		System.out.println("[MemberController memberInsert] member:" + member);
		memberService.insertMember(member, memberOnline, institutionCode);
		mav.setViewName("redirect:/memberList");
		return mav;
	}
	//	회원리스트 조회 (미완성)
	@GetMapping("memberList")
	public ModelAndView memberList(ModelAndView mav) {
		mav.setViewName("member/memberList");
		return mav;
	}
	//	회원가입(수강생)
	@GetMapping("LEJoin")
	public ModelAndView insertOnlineMember (ModelAndView mav) {
		System.out.println("[MemberController insertOnlineMember] 호출");
		mav.setViewName("LE/LEJoin");
		return mav;
	}
}