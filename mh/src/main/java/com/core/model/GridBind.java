package com.core.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

public class GridBind {
	
	private DtoMap<String, Object> dto;
	private List<?> dtoList;

	public DtoMap<String, Object> getDto() {
		return dto;
	}

	public void setDto(DtoMap<String, Object> dto) {
		this.dto = dto;
	}

	public List<?> getDtoList() {
		return dtoList;
	}

	public void setDtoList(List<?> dtoList) {
		this.dtoList = dtoList;
	}

	/**
	 * @param request
	 * @throws Exception
	 */
	public GridBind(HttpServletRequest request) throws Exception {
		String jsonString = request.getParameter("reqData").replaceAll("&quot;", "\"").replaceAll("&amp;", "&").replaceAll("&apos;","\'").replaceAll("&lt;","<").replaceAll("&gt;",">");
	
		ObjectMapper objectMapper = new ObjectMapper();
		List<Map<String, Object>> readValue = objectMapper.readValue(jsonString, new TypeReference<List<Map<String, Object>>>() {});

		List<Object> list = new ArrayList<Object>();

		for (Map<String, Object> map : readValue) {
			setDtoBaseColumn(map, request); //기본 컬럼 셋팅
	        list.add(map);
		}
		
		setDtoList(list);
	}
	
	/**
	 * 기본정보 셋팅
	 * @param map
	 * @param request
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	private void setDtoBaseColumn(Map<String, Object> map, HttpServletRequest request) throws Exception {
		if (request.getAttribute("baseInfo") != null) {
			map.putAll((Map<String, Object>) request.getAttribute("baseInfo")); 
		}
	}

}
