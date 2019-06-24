package kr.or.ksmart.lms.institution.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ksmart.lms.institution.service.AnnualFeeService;
import kr.or.ksmart.lms.institution.vo.PaymentAnnualFee;

@Controller
public class AnnualFeeController {
	@Autowired
	AnnualFeeService annualFeeService;

	//교육원 사용권한 리스트 controller
	@GetMapping("/institution/annualFee/annualFee")
	public ModelAndView getAvailableInstitution(HttpSession session, ModelAndView mav) {
		System.out.println("[AnnualFeeController getAvailableInstitution] 호출");
		String memberRank = (String)session.getAttribute("memberRank");
		if(memberRank.equals("교육원직원")) {
			mav.setViewName("institution/annualFee/annualFee");
			String institutionCode = (String)session.getAttribute("institutionCode");
			Map<String, Object> map = annualFeeService.getAnnualFee(institutionCode);
			mav.addObject("paymentList", map.get("paymentList"));
			mav.addObject("availableInstitution", map.get("availableInstitution"));
		} else {
			System.out.println("[AnnualFeeController getAvailableInstitution] 교육원직원 아님");
			mav.setViewName("institution/institutionLogin");
		}
		return mav;
	}

	//교육원 연회비 결제 폼 controller
	@GetMapping("/institution/annualFee/paymentAnnualFee")
	public ModelAndView getPaymentAnnualFee(HttpSession session, ModelAndView mav) {
		System.out.println("[AnnualFeeController getPaymentAnnualFee] 호출");
		String memberRank = (String)session.getAttribute("memberRank");
		if(memberRank.equals("교육원직원")) {
			mav.setViewName("institution/annualFee/paymentAnnualFee");
			String institutionCode = (String)session.getAttribute("institutionCode");
			Map<String, Object> map = annualFeeService.getPaymentAnnualFee(institutionCode);
			mav.addObject("infoAnnualFee", map.get("infoAnnualFee"));
			mav.addObject("startDay", map.get("startDay"));
			mav.addObject("oneYearLaterDay", map.get("oneYearLaterDay"));
		} else {
			System.out.println("[AnnualFeeController getPaymentAnnualFee] 교육원직원 아님");
			mav.setViewName("institution/institutionLogin");
		}
		return mav;
	}
	
	//교육원 연회비 결제 액션 controller
	@PostMapping("/institution/annualFee/paymentAnnualFee")
	public ModelAndView addPaymentAnnualFee(HttpSession session, ModelAndView mav, PaymentAnnualFee paymentAnnualFee) {
		System.out.println("[AnnualFeeController addPaymentAnnualFee] 호출");
		String memberRank = (String)session.getAttribute("memberRank");
		if(memberRank.equals("교육원직원")) {
			annualFeeService.addPaymentAnnualFee(paymentAnnualFee);
			mav.setViewName("redirect:/institution/annualFee/annualFee");
		} else {
			System.out.println("[AnnualFeeController addPaymentAnnualFee] 교육원직원 아님");
			mav.setViewName("institution/institutionLogin");
		}
		return mav;
	}
}