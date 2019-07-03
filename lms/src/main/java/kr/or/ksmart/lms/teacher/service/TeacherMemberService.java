package kr.or.ksmart.lms.teacher.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.ksmart.lms.pi.vo.Member;
import kr.or.ksmart.lms.institution.vo.Institution;
import kr.or.ksmart.lms.pi.vo.MemberOnline;
import kr.or.ksmart.lms.teacher.mapper.TeacherMemberMapper;


@Service
public class TeacherMemberService {
	@Autowired private TeacherMemberMapper teacherMemberMapper;
	
	//	강사 회원 가입 화면에서 select박스에 institutionList 출력
	public List<Institution> insertTeacher() {
		System.out.println("[TeacherMemberService insertTeacher]");
		List<Institution> instList = teacherMemberMapper.selectInstList();
		return instList;
	}
	//	강사회원 insert 처리
	public void insertTeacher(Member member, MemberOnline memberOnline, String institutionCode) {
		//	member 테이블에 insert 준비. membercode 생성
		SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");//날짜
		Date now = new Date(); 
		String nowDate = dateFormat.format(now);
		nowDate = nowDate.substring(0, 13);
		nowDate = nowDate.toString().replace("-", "");
		nowDate = nowDate.toString().replace(" ", "");
		System.out.println(nowDate);
		int randomNo1 = (int)(Math.random()*10000);
		int randomNo2 = (int)(Math.random()*1000);
		int randomNo3 = (int)(Math.random()*100);
		int randomNo = randomNo1 + randomNo2 + randomNo3;
		if(randomNo > 10000) {
			randomNo = randomNo/10;
		}
		String memberCode="M"+nowDate+randomNo;
		member.setMemberCode(memberCode);	//Member VO 내 memberCode set
		//	member 테이블에 insert
		teacherMemberMapper.insertTeacher(member);	//	mapper 실행
		
		//	member_online_insertid 테이블에 member_online_id insert
		teacherMemberMapper.insertMemberOnlineId(memberOnline);
		
		//	member_online 테이블에 insert
		
		String memberOnlineCode = "MO"+nowDate+randomNo;	//	memberOnlineCode 생성
		System.out.println("[MemberService insertMemberOnline] memberOnlineCode : " + memberOnlineCode);
		memberOnline.setMemberOnlineCode(memberOnlineCode);	//	memberOnline VO 내 memberOnlineCode set
		memberOnline.setMemberCode(memberCode);
		String institutionName = teacherMemberMapper.selectInstitutionName(institutionCode);
		memberOnline.setInstitutionName(institutionName);
		
		teacherMemberMapper.insertMemberOnline(memberOnline);	//mapper 실행
	}
}
