package com.core.tlds.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.core.tlds.vo.CmnCodeVO;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

@Repository("CmnCodeDAO")
public class CmnCodeDAO extends EgovAbstractDAO {
	/**
	 * 공통코드 조회
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List<?> selectList(CmnCodeVO vo) throws Exception {
		System.out.println("CmnCode.selectListCmnCode.selectListCmnCode.selectListCmnCode.selectList");
		//return list("CmnCode.selectList", vo);
		return (List<?>)list("CmnCode.selectList", vo);
		//return (List<?>)list("CdeMngt.selectCodeList", vo);
	}
	
}
