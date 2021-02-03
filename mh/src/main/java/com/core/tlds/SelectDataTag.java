package com.core.tlds;

import java.io.IOException;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.core.tlds.dao.CmnCodeDAO;
import com.core.tlds.vo.CmnCodeVO;
import com.core.util.CommonUtil;
import com.core.util.StringUtil;

/**
 * TagSupport to support to selectData Tag Library
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
public class SelectDataTag extends TagSupport {
	private static final long serialVersionUID = 1L;

	private String resType;
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
	private String addCol;
	
	public String getResType() {
		return resType;
	}
	public void setResType(String resType) {
		this.resType = resType;
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
	public String getAddCol() {
		return addCol;
	}
	public void setAddCol(String addCol) {
		this.addCol = addCol;
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

	public String cmnCode() throws Exception {
		WebApplicationContext wac = WebApplicationContextUtils.getWebApplicationContext(pageContext.getServletContext());
		
		CmnCodeDAO dao = (CmnCodeDAO) wac.getBean("CmnCodeDAO");
		//CmnCodeDAO dao = new CmnCodeDAOImpl(comDao);
		
		//SqlMapClientTemplate sqlmap = (SqlMapClientTemplate) wac.getBean("sqlMapClientTemplate");
		//CmnCodeDAO dao = new CmnCodeDAO(sqlmap);
		
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
		vo.setAddCol(addCol);
		
		List<?> list = dao.selectList(vo);
		
		String result = "";
		if (StringUtil.nullValue(resType).equals("") || StringUtil.nullValue(resType).equals("jqgrid")) {
			result = CommonUtil.stringSelectDataJqgrid(list);
		}else if(StringUtil.nullValue(resType).equals("jqgridFilter")){
			result = CommonUtil.stringSelectDataJqgridFilter(list);
		}
		else {
			result = "";
		}
		
		return result;
	}
}
