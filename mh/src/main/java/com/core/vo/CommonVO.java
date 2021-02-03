package com.core.vo;

import java.io.Serializable;

public class CommonVO extends PagingVO implements Serializable  {
	private static final long serialVersionUID = 4829684178121022508L;
	
	private String menuNo = "";
	
	private boolean chkSearchOnlyJisa;
	private boolean chkSearchOnlyMine;
	private String sessionDeptCode = "";
	private String sessionEmpNum = "";
	private boolean chkSearchExceptJisa;
	private String searchMngtCode = "";
	
	public String getMenuNo() {
		return menuNo;
	}
	public void setMenuNo(String menuNo) {
		this.menuNo = menuNo;
	}
	public boolean isChkSearchOnlyJisa() {
		return chkSearchOnlyJisa;
	}
	public void setChkSearchOnlyJisa(boolean chkSearchOnlyJisa) {
		this.chkSearchOnlyJisa = chkSearchOnlyJisa;
	}
	public boolean isChkSearchOnlyMine() {
		return chkSearchOnlyMine;
	}
	public void setChkSearchOnlyMine(boolean chkSearchOnlyMine) {
		this.chkSearchOnlyMine = chkSearchOnlyMine;
	}
	public String getSessionDeptCode() {
		return sessionDeptCode;
	}
	public void setSessionDeptCode(String sessionDeptCode) {
		this.sessionDeptCode = sessionDeptCode;
	}
	public String getSessionEmpNum() {
		return sessionEmpNum;
	}
	public void setSessionEmpNum(String sessionEmpNum) {
		this.sessionEmpNum = sessionEmpNum;
	}
	public boolean isChkSearchExceptJisa() {
		return chkSearchExceptJisa;
	}
	public void setChkSearchExceptJisa(boolean chkSearchExceptJisa) {
		this.chkSearchExceptJisa = chkSearchExceptJisa;
	}
	public String getSearchMngtCode() {
		return searchMngtCode;
	}
	public void setSearchMngtCode(String searchMngtCode) {
		this.searchMngtCode = searchMngtCode;
	}
	 
	
}
