package com.core.model;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import com.core.util.StringUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

public class Bind {
	
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

	private int dto_len = 0;
	
	/**
	 * @param request
	 * @throws Exception
	 */
	public Bind(HttpServletRequest request) throws Exception {
		/**************************************************************************
         * Set Request Parameters
         **************************************************************************/
		DtoMap<String, Object> map = new DtoMap<String, Object>();
		DtoMap<String, Object> tmp_map = new DtoMap<String, Object>();
		
		//Parameter
		Enumeration<?> enumeration = request.getParameterNames();

		//Parameters Map
		while(enumeration.hasMoreElements())
		{
		  String key = (String)enumeration.nextElement();
		  String[] values = request.getParameterValues(key);
			if(values!=null){
				if (dto_len < values.length) {
					dto_len = values.length;
				}
//				System.out.print(values.length+"/");
				if (values.length > 1) {
					tmp_map.put(key, values);
					//tmp_map.put(getChgColNm(key), values);
				} else {
					tmp_map.put(key, values[0]);
					//tmp_map.put(getChgColNm(key), values[0]);
				}
				map.put(key, values[0]);
				//map.put(getChgColNm(key), values[0]);
				setDtoBaseColumn(map, request); //기본 컬럼 셋팅
				/*
				if (key.equals("TABLEID")) {
					for(int x=0; x<values.length; x++) {
						keyDelimeters.add(values[x].split(",")[0]);
					}
				}*/
//				System.out.print(key+":"+values[0]+",");				
			}
		}
		
//		System.out.println();		
		System.out.println("dto_len:"+dto_len);
		
		
		//Parameters Map List
		List<Object> list = new ArrayList<Object>();
		for(int i=0; i<dto_len; i++) {			
			if(tmp_map != null && tmp_map.size() > 0)
	        {
				Map<String, Object> new_map = new DtoMap<String, Object>();
	            Set<?> keySet = tmp_map.keySet();
	            String tempKey = null;
	            String tempValue = null;
	            Iterator<?> keyIter = keySet.iterator();
	            while(keyIter.hasNext()) {
	                tempKey = (String) keyIter.next();
	                tempValue = "";
	                Object obj = tmp_map.get(tempKey);
	                if (obj instanceof String[]) {
	                	if (((String[])obj).length-1 < i)
	                		tempValue = "";
	                	else
	                		tempValue = ((String[])obj)[i];
	                } else if (obj instanceof String) {
	                	tempValue = (String)obj;	                	
	                }
	                new_map.put(tempKey, tempValue);
//	                System.out.print(tempKey+":"+tempValue+",");
	            }
	            setDtoBaseColumn(new_map, request); //기본 컬럼 셋팅
	            list.add(new_map);
//	            System.out.println();
	        }
		}

		setDto(map);
		setDtoList(list);
	}
	
	/**
	 * @param request
	 * @param objName
	 * @throws Exception
	 */
	public Bind(HttpServletRequest request, String objName) throws Exception {	
		request.setCharacterEncoding("UTF-8");
		
		JSONObject jObj = (JSONObject) JSONSerializer.toJSON(request.getParameter("reqData")); 
		Iterator it = jObj.keys();

		while(it.hasNext())
		{
			String key = it.next().toString();
			
			if(objName.equals(key))
			{
				JSONArray o = jObj.getJSONArray(key); 

			    List<Object> list = new ArrayList<Object>();
				for(int i=0; i<o.size(); i++) {			
					Map<String, Object> new_map = new DtoMap<String, Object>();
					Iterator it2 = o.getJSONObject(i).keys();
			        
					while(it2.hasNext())
					{
						String key2 = it2.next().toString();
						new_map.put(key2, o.getJSONObject(i).get(key2).toString());
					}
					
			        setDtoBaseColumn(new_map, request); //기본 컬럼 셋팅
			        list.add(new_map);
				}
				
				setDtoList(list);
			    break;
			}
		    
		}
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
