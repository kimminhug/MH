package com.core.tlds;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.core.tlds.dao.CmnCodeDAO;
import com.core.tlds.vo.CmnCodeVO;
import com.core.util.StringUtil;

/**
 * TagSupport to support to cmnCombo Tag Library
 * @author Flash21
 * @since 2020.01.13
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일        수정자       수정내용
 *  -------       --------    ---------------------------
 *   2020.01.13	표준프레임워크센터	최초 생성
 *
 * </pre>
 */
public class CmnCodeTag extends TagSupport {
	private static final long serialVersionUID = 1L;
	
	private String idName;
	private String tabIndex;
	private String allYn;
	private String event;
	private String initVal;
	private String addOption;
	private String addAttr;
	private String tableNm;
	private String codeCol;
	private String nameCol;
	private String whereCol1;
	private String whereVal1;
	private String whereCompare1;
	private String whereCol2;
	private String whereVal2;
	private String whereCompare2;
	private String whereCol3;
	private String whereVal3;
	private String whereCompare3;
	private String sortCol;
	private String sortType;
	private String prefixUnionSql;
	private String suffixUnionSql;
	private String directQuery;
	private String whereORCol1;
	private String whereORCol2;
	private String whereORCol3;
	private String keyCol;
	private String generalYn;
	
	public String getIdName() {
		return idName;
	}
	public void setIdName(String idName) {
		this.idName = idName;
	}
	public String getTabIndex() {
		return tabIndex;
	}
	public void setTabIndex(String tabIndex) {
		this.tabIndex = tabIndex;
	}
	public String getAllYn() {
		return allYn;
	}
	public void setAllYn(String allYn) {
		this.allYn = allYn;
	}
	public String getEvent() {
		return event;
	}
	public void setEvent(String event) {
		this.event = event;
	}
	public String getInitVal() {
		return initVal;
	}
	public void setInitVal(String initVal) {
		this.initVal = initVal;
	}
	public String getAddOption() {
		return addOption;
	}
	public void setAddOption(String addOption) {
		this.addOption = addOption;
	}
	public String getAddAttr() {
		return addAttr;
	}
	public void setAddAttr(String addAttr) {
		this.addAttr = addAttr;
	}
	public String getTableNm() {
		return tableNm;
	}
	public void setTableNm(String tableNm) {
		this.tableNm = tableNm;
	}
	public String getCodeCol() {
		return codeCol;
	}
	public void setCodeCol(String codeCol) {
		this.codeCol = codeCol;
	}
	public String getNameCol() {
		return nameCol;
	}
	public void setNameCol(String nameCol) {
		this.nameCol = nameCol;
	}
	public String getWhereCol1() {
		return whereCol1;
	}
	public void setWhereCol1(String whereCol1) {
		this.whereCol1 = whereCol1;
	}
	public String getWhereVal1() {
		return whereVal1;
	}
	public void setWhereVal1(String whereVal1) {
		this.whereVal1 = whereVal1;
	}
	public String getWhereCompare1() {
		return whereCompare1;
	}
	public void setWhereCompare1(String whereCompare1) {
		this.whereCompare1 = whereCompare1;
	}
	public String getWhereCol2() {
		return whereCol2;
	}
	public void setWhereCol2(String whereCol2) {
		this.whereCol2 = whereCol2;
	}
	public String getWhereVal2() {
		return whereVal2;
	}
	public void setWhereVal2(String whereVal2) {
		this.whereVal2 = whereVal2;
	}
	public String getWhereCompare2() {
		return whereCompare2;
	}
	public void setWhereCompare2(String whereCompare2) {
		this.whereCompare2 = whereCompare2;
	}
	public String getWhereCol3() {
		return whereCol3;
	}
	public void setWhereCol3(String whereCol3) {
		this.whereCol3 = whereCol3;
	}
	public String getWhereVal3() {
		return whereVal3;
	}
	public void setWhereVal3(String whereVal3) {
		this.whereVal3 = whereVal3;
	}
	public String getWhereCompare3() {
		return whereCompare3;
	}
	public void setWhereCompare3(String whereCompare3) {
		this.whereCompare3 = whereCompare3;
	}
	public String getSortCol() {
		return sortCol;
	}
	public void setSortCol(String sortCol) {
		this.sortCol = sortCol;
	}
	public String getSortType() {
		return sortType;
	}
	public void setSortType(String sortType) {
		this.sortType = sortType;
	}
	public String getPrefixUnionSql() {
		return prefixUnionSql;
	}
	public void setPrefixUnionSql(String prefixUnionSql) {
		this.prefixUnionSql = prefixUnionSql;
	}
	public String getSuffixUnionSql() {
		return suffixUnionSql;
	}
	public void setSuffixUnionSql(String suffixUnionSql) {
		this.suffixUnionSql = suffixUnionSql;
	}
	public String getDirectQuery() {
		return directQuery;
	}
	public void setDirectQuery(String directQuery) {
		this.directQuery = directQuery;
	}
	public String getWhereORCol1() {
		return whereORCol1;
	}
	public void setWhereORCol1(String whereORCol1) {
		this.whereORCol1 = whereORCol1;
	}
	public String getWhereORCol2() {
		return whereORCol2;
	}
	public void setWhereORCol2(String whereORCol2) {
		this.whereORCol2 = whereORCol2;
	}
	public String getWhereORCol3() {
		return whereORCol3;
	}
	public void setWhereORCol3(String whereORCol3) {
		this.whereORCol3 = whereORCol3;
	}
	public String getKeyCol() {
		return keyCol;
	}
	public void setKeyCol(String keyCol) {
		this.keyCol = keyCol;
	}
	public String getGeneralYn() {
		return generalYn;
	}
	public void setGeneralYn(String generalYn) {
		this.generalYn = generalYn;
	}
	
	public int doStartTag() throws JspException {
		try {
			pageContext.getOut().print(cmnCode());
		}catch(IOException e) {
			throw new JspException(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SKIP_BODY;
	}
	
	@SuppressWarnings("rawtypes")
	public String cmnCode() throws Exception {
		WebApplicationContext wac = WebApplicationContextUtils.getWebApplicationContext(pageContext.getServletContext());
		CmnCodeDAO dao = (CmnCodeDAO) wac.getBean("CmnCodeDAO");
		/*
		 WebApplicationContext wac = WebApplicationContextUtils.getWebApplicationContext(pageContext.getServletContext());
		//ComDao comDao = (ComDao) wac.getBean("comDao");
		//CmnCodeDAO dao = new CmnCodeDAOImpl(comDao);
		
		SqlMapClientTemplate sqlmap = (SqlMapClientTemplate) wac.getBean("sqlMapClientTemplate");
		//CmnCodeDAO dao = new CmnCodeDAOImpl(sqlmap);
		CmnCodeDAO dao = new CmnCodeDAO();
		 */		
		
		CmnCodeVO vo = new CmnCodeVO();
		vo.setTableNm(tableNm);
		vo.setCodeCol(codeCol);
		vo.setNameCol(nameCol);
		vo.setWhereCol1(whereCol1);
		vo.setWhereVal1(whereVal1);
		vo.setWhereCompare1(whereCompare1);
		vo.setWhereCol2(whereCol2);
		vo.setWhereVal2(whereVal2);
		vo.setWhereCompare2(whereCompare2);
		vo.setWhereCol3(whereCol3);
		vo.setWhereVal3(whereVal3);
		vo.setWhereCompare3(whereCompare3);
		vo.setSortCol(sortCol);
		vo.setSortType(sortType);
		vo.setPrefixUnionSql(prefixUnionSql);
		vo.setSuffixUnionSql(suffixUnionSql);
		vo.setDirectQuery(directQuery);
		vo.setWhereORCol1(whereORCol1);
		vo.setWhereORCol2(whereORCol2);
		vo.setWhereORCol3(whereORCol3);
		vo.setKeyCol(keyCol);
		
		List<?> list = dao.selectList(vo);

		String attrTabIndex = "";
		if (!StringUtil.nullValue(tabIndex).equals("")) {
			attrTabIndex = "tabindex=\'"+tabIndex+"\'";
		}
		
		String tagAllOption = "";
		if (StringUtil.nullValue(allYn).equals("Y")) {
			tagAllOption = "<option value=\'\'>- 전체 -</option>";
		}
		
		String tagAddOption = "";
		if (!StringUtil.nullValue(addOption).equals("")) {
			tagAddOption = StringUtil.nullValue(addOption);
		}

		String strTag = "";
		strTag += "<select id=\'"+idName+"\' name=\'"+idName+"\' class=\'form-control\' "+StringUtil.nullValue(event)+" "+attrTabIndex+" "+StringUtil.nullValue(addAttr)+">";
		strTag += tagAllOption;
		strTag += tagAddOption;
		
		String scriptData = "";
		for(int i=0; i<list.size(); i++) {
			Map map = (Map) list.get(i);
			/*if (keyCol != null) {
				scriptData += StringUtil.nullValue(map.get("KEYCOL"))+":"+StringUtil.nullValue(map.get("CODE"))+":"+StringUtil.nullValue(map.get("NAME"))+";";
			} else {*/
				String attrSelected = StringUtil.nullValue(map.get("CODE")).equals(initVal)?"selected=selected":"";
				strTag += "<option key=\'"+StringUtil.nullValue(map.get("KEYCOL"))+"\' value=\'"+StringUtil.nullValue(map.get("CODE"))+"\' "+attrSelected+" >"+StringUtil.nullValue(map.get("NAME"))+"</option>";
			/*}*/
		}
		strTag += "</select>";
		
		if (keyCol != null) {
			strTag += "<script type=\"text/javascript\">";
			strTag += "var "+idName+"='"+scriptData+"';";
			strTag += "</script>";
		}
		
		System.out.println("----------------------------------");
		System.out.println(strTag);
		return strTag;
	}
	
}
