package kr.or.ksmart.lms.institution.vo;

import java.util.List;

public class LectureSignupResult {
	private List<String> lectureSignupCode;
	private List<Integer> lectureSignupResult;
	private String noticeLectureCode;
	private String noticeLectureResultDate;
	private String institutionCode;
	private String institutionName;
	private String noticeLectureStartDate;
	private String lectureSignupResultDate;
	
	public List<String> getLectureSignupCode() {
		return lectureSignupCode;
	}
	public void setLectureSignupCode(List<String> lectureSignupCode) {
		this.lectureSignupCode = lectureSignupCode;
	}
	public List<Integer> getLectureSignupResult() {
		return lectureSignupResult;
	}
	public void setLectureSignupResult(List<Integer> lectureSignupResult) {
		this.lectureSignupResult = lectureSignupResult;
	}
	public String getNoticeLectureCode() {
		return noticeLectureCode;
	}
	public void setNoticeLectureCode(String noticeLectureCode) {
		this.noticeLectureCode = noticeLectureCode;
	}
	public String getNoticeLectureResultDate() {
		return noticeLectureResultDate;
	}
	public void setNoticeLectureResultDate(String noticeLectureResultDate) {
		this.noticeLectureResultDate = noticeLectureResultDate;
	}
	public String getInstitutionCode() {
		return institutionCode;
	}
	public void setInstitutionCode(String institutionCode) {
		this.institutionCode = institutionCode;
	}
	public String getInstitutionName() {
		return institutionName;
	}
	public void setInstitutionName(String institutionName) {
		this.institutionName = institutionName;
	}
	public String getNoticeLectureStartDate() {
		return noticeLectureStartDate;
	}
	public void setNoticeLectureStartDate(String noticeLectureStartDate) {
		this.noticeLectureStartDate = noticeLectureStartDate;
	}
	public String getLectureSignupResultDate() {
		return lectureSignupResultDate;
	}
	public void setLectureSignupResultDate(String lectureSignupResultDate) {
		this.lectureSignupResultDate = lectureSignupResultDate;
	}
	
	@Override
	public String toString() {
		return "LectureSignupResult [lectureSignupCode="
				+ lectureSignupCode + ", lectureSignupResult=" + lectureSignupResult + ", noticeLectureCode="
				+ noticeLectureCode + ", noticeLectureResultDate=" + noticeLectureResultDate + ", institutionCode="
				+ institutionCode + ", institutionName=" + institutionName + ", noticeLectureStartDate="
				+ noticeLectureStartDate + ", lectureSignupResultDate=" + lectureSignupResultDate + "]";
	}	
}
