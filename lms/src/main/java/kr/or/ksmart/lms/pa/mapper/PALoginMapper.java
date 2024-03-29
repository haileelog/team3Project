package kr.or.ksmart.lms.pa.mapper;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ksmart.lms.pa.vo.MemberOnline;
import kr.or.ksmart.lms.pa.vo.IndexInstitution;
import kr.or.ksmart.lms.pa.vo.LoginRequest;

@Mapper
public interface PALoginMapper {
	//회원 온라인 select mapper 
	public MemberOnline selectMemberOnline(LoginRequest loginRequest);

	//인덱스 교육원 select mapper
	public IndexInstitution selectInstitution(String institutionCode);

}
