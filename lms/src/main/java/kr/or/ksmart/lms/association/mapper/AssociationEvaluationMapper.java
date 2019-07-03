package kr.or.ksmart.lms.association.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ksmart.lms.association.vo.EvalTotal;
import kr.or.ksmart.lms.association.vo.InfoEvalByAssociation;
import kr.or.ksmart.lms.association.vo.InsertEvalTotal;
import kr.or.ksmart.lms.association.vo.Institution;

@Mapper
public interface AssociationEvaluationMapper {
    //평가를 해야하는 교육원 코드 리스트 mapper
    public List<String> selectInstitutionCodeList();

    //평가를 해야하는 교육원 코드와 교육원 명 리스트 mapper
    public List<Institution> selectInstitutionCodeAndName();

    //교육원 평가 합계 추가 mapper
    public void insertEvalTotal(InsertEvalTotal insertEvalTotal);

    //교육원 평가 합계 수정 mapper
    public void updateEvalTotal(EvalTotal evalTotal);

    //교육원 평가 년도에 따른 입력 조건 출력 mapper
    public List<Integer> selectEvalTotalYear();

    //교육원 평가 합계 한개 출력 mapper
    public EvalTotal selectEvalTotal(String evalTotalCode);

    //교육원 평가 항목에 따른 입력 조건 출력 mapper
    public List<String> selectEvalTotalType();

    //입력조건에 따른 교육원 평가 합계 리스트 출력 mapper
    public List<EvalTotal> selectSerachEvalTotalList(Map<String, Object> map);
    
    //입력조건에 따른 교육원 평가 합계 리스트 출력 mapper
    public List<EvalTotal> selectEvalTotalList();

    //교육원 평가 항목 리스트 출력 mapper
    public List<String> selectInfoEvalByAssociationSortList();

    //교육원 평가 항목에 따른 리스트 출력 mapper
    public List<InfoEvalByAssociation> selectInfoEvalByAssociationList(String infoEvalByAssociationSort);
}
