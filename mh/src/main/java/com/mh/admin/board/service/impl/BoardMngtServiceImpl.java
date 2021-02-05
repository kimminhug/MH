package com.mh.admin.board.service.impl;

import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import com.core.model.Bind;
import com.core.model.GridBind;
import com.core.util.CommonUtil;
import com.mh.admin.board.service.BoardMngtService;
import com.mh.admin.board.vo.BoardMngtVO;
//import com.mh.web.file.service.FileService;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

@Service("BoardMngtService")
public class BoardMngtServiceImpl extends EgovAbstractServiceImpl implements BoardMngtService{
	@Autowired
	BoardMngtDAO dao;

	//@Autowired
	//protected FileService fileService;
	
	public List<?> selectBoardList(BoardMngtVO vo) throws Exception{
		return dao.selectBoardList(vo);
	}
	public int selectBoardCount(BoardMngtVO vo) throws Exception{
		return dao.selectBoardCount(vo);
	}
	public Map<String, Object> selectBoardInfo(Map<String, Object> map) throws Exception{
		return dao.selectBoardInfo(map);
	}
	
	public void insertBoard(Map<String, Object> map) throws Exception{
		dao.insertBoard(map);
	}
	public void updateBoard(Map<String, Object> map) throws Exception{
		dao.updateBoard(map);
	}
	public void deleteBoard(Map<String, Object> map) throws Exception{
		dao.deleteBoard(map);
	}
	
	public void insertBoardInfo(Map<String, Object> map) throws Exception{
		dao.insertBoardInfo(map);
	}
	public void updateBoardInfo(Map<String, Object> map) throws Exception{
		dao.updateBoardInfo(map);
	}
	public void deleteBoardInfo(Map<String, Object> map) throws Exception{
		dao.deleteBoardInfo(map);
	}

	/**
	 * 관리자 게시판관리 목록 조회
	 * @param vo
	 * @param model
	 * @param request
	 * @param response
	 * @param out
	 * @throws Exception
	 */
	public void boardListServiceCall(HttpServletRequest request, HttpServletResponse response, BoardMngtVO vo, ModelMap model, Writer out) throws Exception {
		List<?> selectList = this.selectBoardList(vo);
		int selectCount = this.selectBoardCount(vo);
		
		//System.out.println("selectCount :::: " + selectCount);
		//System.out.println("selectList :::: " + selectList);
		
		CommonUtil.jsonResponse(response, selectList, selectCount);
	}
	
	/**
	 * 관리자 게시판관리 
	 * @param model
	 * @param request
	 * @param response
	 * @param out
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public void boardSaveServiceCall(HttpServletRequest request, HttpServletResponse response, ModelMap model, Writer out) throws Exception {
		GridBind bind = new GridBind(request); //GridBind
		List<?> dtoList = bind.getDtoList();
		String result = "success";
		String msg = "";
		
		if(dtoList.size() > 0) {
			for(int i=0; i<dtoList.size(); i++) {
				Map<String, Object> map = (Map<String, Object>)dtoList.get(i);
				
				if (map.get("STATUS").toString().equals("C")) {
					this.insertBoard(map);
					msg = "정상적으로 저장되었습니다.";
				}else if (map.get("STATUS").toString().equals("U")) {
					this.updateBoard(map);
					msg = "정상적으로 수정되었습니다.";
				}else if (map.get("STATUS").toString().equals("D")) {
					this.deleteBoard(map);
					msg = "정상적으로 삭제되었습니다.";
				}
			}
		}
		
		CommonUtil.jsonResponse(response, result, msg);
	}

	/**
	 * 관리자 게시판관리 폼 조회
	 * @param vo
	 * @param model
	 * @param request
	 * @param response
	 * @param out
	 * @throws Exception
	 */
	public void boardInfoServiceCall(HttpServletRequest request, HttpServletResponse response, ModelMap model, Writer out) throws Exception {
		Bind bind = new Bind(request);
		Map<String, Object> bindMap = bind.getDto();
		
		Map<String, Object> map = this.selectBoardInfo(bindMap);

		//첨부파일 처리
		//int fileCnt = 16;	//해당 테이블의 파일 컬럼 갯수만큼 반복
		//map.put("fileCnt", fileCnt);	//view 처리용 파일갯수 카운트(반드시 'fileCnt' 키값으로 사용)
		//Map<String, Object> fileListMap = fileService.selectListMultiFile(map, fileCnt);
		//map.putAll(fileListMap);
		//CommonUtil.mapInfo(map);

		CommonUtil.jsonResponseMultiList(response, map);
	}
	
	/**
	 * 관리자 게시판관리 폼데이터 저장
	 * @param model
	 * @param request
	 * @param response
	 * @param out
	 * @throws Exception
	 */
	public void boardInfoSaveServiceCall(HttpServletRequest request, HttpServletResponse response, ModelMap model, Writer out) throws Exception {
		Bind bind = new Bind(request); //Bind
		Map<String, Object> map = bind.getDto();

		String result = "success";
		String msg = "";
		Map<String, Object> resultFileNo = new HashMap<String, Object>();
		
		//map.put("ATFILE_CONN_NO", resultFileNo);
		CommonUtil.mapInfo(map);
		if (map.get("STATUS").toString().equals("C")) {
			
			//resultFileNo = fileService.insertMultiFile(request, "file", "li_board");
			//map.putAll(resultFileNo);
			
			this.insertBoardInfo(map);
			msg = "정상적으로 저장되었습니다.";
		}else if (map.get("STATUS").toString().equals("U")) {
			
			//resultFileNo = fileService.updateMultiFile(request, "file", "li_board");
			//map.putAll(resultFileNo);

			this.updateBoardInfo(map);
			msg = "정상적으로 수정되었습니다.";
		}else if (map.get("STATUS").toString().equals("D")) {
			this.deleteBoardInfo(map);
			msg = "정상적으로 삭제되었습니다.";
		}

		
		CommonUtil.jsonResponse(response, result, msg);
	}
}
