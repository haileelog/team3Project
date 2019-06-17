package kr.or.ksmart.lms.license.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ksmart.lms.license.service.InfoQualificationService;
import kr.or.ksmart.lms.login.vo.LoginRequest;
import kr.or.ksmart.lms.login.vo.MemberOnline;

@Controller
public class InfoQualificationController {
	@Autowired
	InfoQualificationService infoQualificationService;
	
	@GetMapping("/login")
	public ModelAndView loginForm(ModelAndView mav) {
		mav.setViewName("login");
		return mav;
	
			}
	}

