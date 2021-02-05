package com.mh.admin.board.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.mh.admin.board.vo.BoardMngtVO;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

@Repository("BoardMngtDAO")
public class BoardMngtDAO extends EgovAbstractDAO{
	
	/**
	 * 게시판 목록 조회
	 * @param vo
	 * @return
	 */
	public List<?> selectBoardList(BoardMngtVO vo) {
		return (List<?>)list("BoardMngt.selectBoardList", vo);
	}

	/**
	 * 게시판 목록 카운트
	 * @param vo
	 * @return
	 */
	public int selectBoardCount(BoardMngtVO vo) {
		return (int)select("BoardMngt.selectBoardCount", vo);
	}

	/**
	 * 게시판 등록
	 * @param map
	 */
	public void insertBoard(Map<String, Object> map){
		insert("BoardMngt.insertBoard", map);
	}
	
	/**
	 * 게시판 수정
	 * @param map
	 */
	public void updateBoard(Map<String, Object> map){
		update("BoardMngt.updateBoard", map);
	}
	
	/**
	 * 게시판 삭제
	 * @param map
	 */
	public void deleteBoard(Map<String, Object> map){
		update("BoardMngt.deleteBoard", map);
	}
	
	/**
	 * 게시판 조회
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Map<String, Object> selectBoardInfo(Map<String, Object> map) {
		return (Map<String, Object>)select("BoardMngt.selectBoardInfo", map);
	}
	
	/**
	 * 게시판정보 등록
	 * @param map
	 */
	public void insertBoardInfo(Map<String, Object> map){
		insert("BoardMngt.insertBoardInfo", map);
	}
	
	/**
	 * 게시판정보 수정
	 * @param map
	 */
	public void updateBoardInfo(Map<String, Object> map){
		update("BoardMngt.updateBoardInfo", map);
	}
	
	/**
	 * 게시판정보 삭제
	 * @param map
	 */
	public void deleteBoardInfo(Map<String, Object> map){
		update("BoardMngt.deleteBoardInfo", map);
	}
	
}
