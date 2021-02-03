package com.core.util;

import java.io.IOException;
import java.lang.reflect.Field;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.core.model.DtoMap;

import net.sf.json.JSONObject;


public class CommonUtil {
	
	private static Log logger = LogFactory.getLog(CommonUtil.class);
	
	/**
	 * AJAX 요청인지 검사한다.
	 * 
	 * @param request HttpServletRequest
	 * @return AJAX 요청이면 true, 아니면 false
	 */
	public static boolean isAjaxRequest(HttpServletRequest request) throws Exception {

		if(request.getHeader("AJAX") != null && request.getHeader("AJAX").equals("true")){
			return true;
		}
		if(request.getHeader("x-requested-with") != null && request.getHeader("x-requested-with").equalsIgnoreCase("xmlhttprequest")){
			return true;
		}
		
		return false;
	}
	
	/**
	 * List 객체 값 로그 보기
	 * @param list
	 */
	public static void listInfo(List list) {
		if(list == null) {
			return;
		}
		int len = list.size();
		if(list != null){
			logger.info("########### Map Info Start ###########");
			for(int i=0; i<len; i++){
				DtoMap dtoMap = (DtoMap)list.get(i);
				CommonUtil.mapInfo(dtoMap);
			}
			logger.info("########### Map Info End   ###########");
		}
	}
	
	/**
	 * Map 객체 값 로그 보기
	 * @param map
	 */
	public static void mapInfo(Map map) {
		if(map == null) {
			return;
		}
       
        String tempKey = null;
        String tempValue = null;
        String result = "";
        
        Set<?> keySet =  map.keySet();
        Iterator<?> keyIter = keySet.iterator();
        
        while(keyIter.hasNext()) {
            tempKey = (String) keyIter.next();
            tempValue = StringUtil.nullValue(map.get(tempKey));
            tempValue = parseStringFielterDQ(tempValue);
            result += " "+tempKey+":"+tempValue+",";
        }
        logger.info("Map => "+result);
        
	}
	
	public static void jsonResponse(HttpServletResponse response, String result, String message) throws Exception {
		JSONObject jobj = new JSONObject();
		jobj.put("result", result);  
		jobj.put("message", message);
		String strAll = jobj.toString();
		strAll = parseStringFielter(strAll);
		
		//out.write(obj.toJSONString());
		//response.setContentType("application/json");          
		response.setContentType("application/json;charset=UTF-8");
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(strAll); 
	}
	
	public static void jsonResponse(HttpServletResponse response, String result, String message, Map map) throws Exception {
		String strAll = "";
		String strStart = "{";
		String strEnd = "}";
		String sep = ":";
		String comma = ",";
		
		if(map == null) {
			return;
		}
		
        Set<?> keySet =  map.keySet();
        Iterator<?> keyIter = keySet.iterator();
        
        int i = 0;
        strAll += strStart;
        strAll += "\"result\"";
        strAll += sep;
        strAll += "\""+result+"\"";
        strAll += comma;
        strAll += "\"message\"";
        strAll += sep;
        strAll += "\""+message+"\"";
        while(keyIter.hasNext()) {
        	if (i == 0) {
        		strAll += comma;
        	}
        	String tempKey = (String) keyIter.next();
        	String tempValue = StringUtil.nullValue(map.get(tempKey));
        	tempValue = parseStringFielterDQ(tempValue);
            String key = "\""+tempKey+"\"";
    		String value = "\""+tempValue+"\"";
    		strAll += key+sep+value;
    		if ((++i) < map.size()) {
    			strAll += comma;
    		}
        }
        strAll += strEnd;

		response.setContentType("application/json;charset=UTF-8");
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(strAll);
	}
	
	public static void jsonResponse(HttpServletResponse response, Map map) throws Exception {
		String strAll = "";
		String strStart = "{";
		String strEnd = "}";
		String sep = ":";
		String comma = ",";
		
		if(map == null) {
			return;
		}
        Set<?> keySet =  map.keySet();
        Iterator<?> keyIter = keySet.iterator();
        
        int i = 0;
        strAll += strStart;
        while(keyIter.hasNext()) {
        	String tempKey = (String) keyIter.next();
        	System.out.println( "tempKey : "+tempKey);
        	if (map.get(tempKey) instanceof Map) {
        		strAll += "\""+tempKey+"\" : ";
        		strAll += parseMapToJson((Map) map.get(tempKey));
        	} else if (map.get(tempKey) instanceof List) {
        		strAll += "\""+tempKey+"\" : ";
        		strAll += parseListToJson((List) map.get(tempKey));
        	} else {
        		String tempValue = StringUtil.nullValue(map.get(tempKey));        	
	        	tempValue = parseStringFielterDQ(tempValue);
	            String key = "\""+tempKey+"\"";
	    		String value = "\""+tempValue+"\"";
	    		strAll += key+sep+value;
	    		if ((++i) < map.size()) {
	    			strAll += comma;
	    		}
        	}
        }
        strAll += strEnd;
        System.out.println("jsonResponse:"+strAll);
		response.setContentType("application/json;charset=UTF-8");
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(strAll);
	}
	
	/**
	 * @author jdb
	 * @see json response List data multi use
	 * @param response
	 * @param map
	 * @throws Exception
	 */
	public static void jsonResponseMultiList(HttpServletResponse response, Map map) throws Exception {
		String strAll = "";
		String strStart = "{";
		String strEnd = "}";
		String sep = ":";
		String comma = ",";
		
		if(map == null) {
			return;
		}
		//System.out.println("map: :::::::::::::::::::: " + map);
        Set<?> keySet =  map.keySet();
        Iterator<?> keyIter = keySet.iterator();
        
        int i = 0;
        int j = 0;
        strAll += strStart;
        while(keyIter.hasNext()) {
        	String tempKey = (String) keyIter.next();
        	//System.out.println(tempKey + " ::::: " + map.get(tempKey));
        	//System.out.println(i + " sizesize ::::: " + map.size());
        	if (map.get(tempKey) instanceof Map) {
        		strAll += "\""+tempKey+"\" : ";
        		strAll += parseMapToJson((Map) map.get(tempKey));
        	} else if (map.get(tempKey) instanceof List) {
        		if(j!=0 && !map.get(tempKey).equals("")){
        			strAll += comma;
        		}
        		strAll += "\""+tempKey+"\" : ";
        		strAll += parseListToJson((List) map.get(tempKey));
        		j++;
        	} else {
        		String tempValue = StringUtil.nullValue(map.get(tempKey));        	
	        	tempValue = parseStringFielterDQ(tempValue);
	            String key = "\""+tempKey+"\"";
	    		String value = "\""+tempValue+"\"";
	    		strAll += key+sep+value;
	    		if ((++i) < map.size()) {
	    			strAll += comma;
	    		}
        	}
        }
        strAll += strEnd;
        System.out.println("jsonResponse:"+strAll);
		response.setContentType("application/json;charset=UTF-8");
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(strAll);
	}
	
	/*
	 * 2019. 06. 04 Kws
	 * */
	public static void jsonResponseResult(HttpServletResponse response, Map map) throws Exception {
		String strAll = "";
		//String strStart = "{";
		String strStart = "{\"result\":[{";
		//String strEnd = "}";
		String strEnd = "}]}";
		String sep = ":";
		String comma = ",";
		
		if(map == null) {
			return;
		}
        Set<?> keySet =  map.keySet();
        Iterator<?> keyIter = keySet.iterator();
        
        int i = 0;
        strAll += strStart;
        while(keyIter.hasNext()) {
        	String tempKey = (String) keyIter.next();
        	if (map.get(tempKey) instanceof Map) {
        		strAll += "\""+tempKey+"\" : ";
        		strAll += parseMapToJson((Map) map.get(tempKey));
        	} else if (map.get(tempKey) instanceof List) {
        		strAll += "\""+tempKey+"\" : ";
        		strAll += parseListToJson((List) map.get(tempKey));
        	} else {
        		String tempValue = StringUtil.nullValue(map.get(tempKey));        	
	        	tempValue = parseStringFielterDQ(tempValue);
	            String key = "\""+tempKey+"\"";
	    		String value = "\""+tempValue+"\"";
	    		strAll += key+sep+value;
	    		if ((++i) < map.size()) {
	    			strAll += comma;
	    		}
        	}
        }
        strAll += strEnd;
        //System.out.println("jsonResponse:"+strAll);
		response.setContentType("application/json;charset=UTF-8");
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(strAll);
	}
	
	private static String parseStringFielter(String tempValue) {
		tempValue = tempValue.indexOf("\r")!=-1?tempValue.replaceAll("\r", "\\\\r"):tempValue;
		tempValue = tempValue.indexOf("\n")!=-1?tempValue.replaceAll("\n", "\\\\n"):tempValue;	
				
		return tempValue;
	}
	
	private static String parseStringFielterDQ(String tempValue) {
		//System.out.println("& tempValue : "+tempValue);
		
		tempValue = tempValue.indexOf("\\")!=-1?tempValue.replaceAll("\\\\", "\\\\\\\\"):tempValue;
		tempValue = tempValue.indexOf("\r")!=-1?tempValue.replaceAll("\r", "\\\\r"):tempValue;
		tempValue = tempValue.indexOf("\n")!=-1?tempValue.replaceAll("\n", "\\\\n"):tempValue;	
		tempValue = tempValue.indexOf("\"")!=-1?tempValue.replaceAll("\"", "\\\\\""):tempValue;	
		
		//System.out.println("& tempValue : "+tempValue);
		
		return tempValue;
	}
	
	private static String parseMapToJson(Map map) {
		String strAll = "";
		String strStart = "{";
		String strEnd = "}";
		String sep = ":";
		String comma = ",";
		System.out.println("map.size():"+map.size());
		if(map == null) {
			return "\"\"";
		}
		
	    Set<?> keySet =  map.keySet();
	    Iterator<?> keyIter = keySet.iterator();
	    
	    int i = 0;
	    strAll += strStart;
	    while(keyIter.hasNext()) {
	    	String tempKey = (String) keyIter.next();
        	String tempValue = StringUtil.nullValue(map.get(tempKey));        	
        	tempValue = parseStringFielterDQ(tempValue);
            String key = "\""+tempKey+"\"";
    		String value = "\""+tempValue+"\"";
    		strAll += key+sep+value;
    		if ((++i) < map.size()) {
    			strAll += comma;
    		}
	    }
	    strAll += strEnd;
	    
	    return strAll;
	}
	
	private static String parseListToJson(List list) {
		String strAll = "";
		String strStart = "[";
		String strEnd = "]";
		System.out.println("list.size():"+list.size());
		if(list == null) {
			return "\"\"";
		}

	    strAll += strStart;
	    for(int i=0; i<list.size(); i++) {
	    	strAll += parseMapToJson((Map) list.get(i));
	    	if (i < (list.size()-1)) {
	    		strAll += ",";
			}
	    }
	    strAll += strEnd;
	    
	    return strAll;
	}
	
	public static void jsonResponse(HttpServletResponse response, List list) throws Exception {
		JSONObject jobj = new JSONObject();

		response.setContentType("application/json;charset=UTF-8");
		response.setCharacterEncoding("utf-8");
		
		StringBuffer jsonStr = new StringBuffer();
		jsonStr.append("{\"list\" : [");
		response.getWriter().write(jsonStr.toString());

		for(int i=0; i<list.size(); i++) {
			jobj.putAll((Map)list.get(i));
			String tempValue = jobj.toString();
			tempValue = parseStringFielter(tempValue);
			response.getWriter().write(tempValue);
			if (i < (list.size()-1)) {
				response.getWriter().write(",");
			}
			jobj.clear();
		}
		response.getWriter().write("]}");
	}
	
	public static void jsonResponse(HttpServletResponse response, List list, int listCount) throws Exception {
		JSONObject jobj = new JSONObject();

		response.setContentType("application/json;charset=UTF-8");
		response.setCharacterEncoding("utf-8");
		
		StringBuffer jsonStr = new StringBuffer();
		
		if(list.size() > 0) {
			//List<Map<String, Object>> listMap = list;
			
			int rnumMin = 0;
			int rnumMax = 0;
			
			ArrayList<Integer> rnumList = new ArrayList<Integer>();
			
			for(int i=0; i<list.size(); i++) {
				Map<String, Object> map = (Map)list.get(i);
				//System.out.println("ruummm :::::::::::::::::::: " + StringUtil.nullValue(map.get("RNUM").toString()));
				rnumList.add( (int)Double.parseDouble(StringUtil.nullValue(map.get("RNUM").toString())) );
			}
			
			rnumMin = Collections.min(rnumList);
			rnumMax = Collections.max(rnumList);
			
			String startRow = Integer.toString( rnumMin );
			String endRow = Integer.toString( rnumMax );
			
			//System.out.println("startRow :: " + startRow);
			//System.out.println("endRow :: " + endRow);
			
			int lastRow = listCount;
			
			jsonStr.append("{\"startRowNum\" : \""+startRow+"\",");		
			jsonStr.append("\"endRowNum\" : \""+endRow+"\",");		
			jsonStr.append("\"total\" : "+lastRow+",");		
			
			jsonStr.append("\"list\" : [");
		} else {
			jsonStr.append("{\"list\" : [");
		}
		
		response.getWriter().write(jsonStr.toString());

		for(int i=0; i<list.size(); i++) {
			jobj.putAll((Map)list.get(i));
			String tempValue = jobj.toString();
			tempValue = parseStringFielter(tempValue);
			response.getWriter().write(tempValue);
			if (i < (list.size()-1)) {
				response.getWriter().write(",");
			}
			jobj.clear();
		}
		response.getWriter().write("]}");
	}
	
	public static void jsonResponseListResult(HttpServletResponse response, List list) throws Exception {
		JSONObject jobj = new JSONObject();

		response.setContentType("application/json;charset=UTF-8");
		response.setCharacterEncoding("utf-8");
		
		StringBuffer jsonStr = new StringBuffer();
		jsonStr.append("{\"result\":[");
		response.getWriter().write(jsonStr.toString());

		for(int i=0; i<list.size(); i++) {
			jobj.putAll((Map)list.get(i));
			String tempValue = jobj.toString();
			tempValue = parseStringFielter(tempValue);
			response.getWriter().write(tempValue);
			if (i < (list.size()-1)) {
				response.getWriter().write(",");
			}
			jobj.clear();
		}
		response.getWriter().write("]}");
	}	
	public static void jsonResponseListResult2(HttpServletResponse response, List list) throws Exception {
		JSONObject jobj = new JSONObject();

		response.setContentType("application/json;charset=UTF-8");
		response.setCharacterEncoding("utf-8");
		
		StringBuffer jsonStr = new StringBuffer();
		jsonStr.append("[");
		response.getWriter().write(jsonStr.toString());

		for(int i=0; i<list.size(); i++) {
			jobj.putAll((Map)list.get(i));
			String tempValue = jobj.toString();
			tempValue = parseStringFielter(tempValue);
			response.getWriter().write(tempValue);
			if (i < (list.size()-1)) {
				response.getWriter().write(",");
			}
			jobj.clear();
		}
		response.getWriter().write("]");
	}	
	public static void jsonResponse(HttpServletResponse response, List list, String str) throws Exception {
		JSONObject jobj = new JSONObject();

		response.setContentType("application/json;charset=UTF-8");
		response.setCharacterEncoding("utf-8");
		
		StringBuffer jsonStr = new StringBuffer();
		jsonStr.append("{\"str\" : \""+str+"\",");
		jsonStr.append("\"list\" : [");
		response.getWriter().write(jsonStr.toString());

		for(int i=0; i<list.size(); i++) {
			jobj.putAll((Map)list.get(i));
			String tempValue = jobj.toString();
			tempValue = parseStringFielter(tempValue);
			response.getWriter().write(tempValue);
			if (i < (list.size()-1)) {
				response.getWriter().write(",");
			}
			jobj.clear();
		}
		response.getWriter().write("]}");
	}
	
	public static void jsonResponse(HttpServletResponse response, Map info, List list) throws Exception {
		JSONObject jobj = new JSONObject();

		response.setContentType("application/json;charset=UTF-8");
		response.setCharacterEncoding("utf-8");
		
		String page = StringUtil.nullValue(info.get("page"));
		String total = StringUtil.nullValue(info.get("total"));
		String records = StringUtil.nullValue(info.get("records"));
		
		StringBuffer jsonStr = new StringBuffer();
		jsonStr.append("{\"page\" : \""+page+"\",");
		jsonStr.append("\"total\" : "+total+",");		
		jsonStr.append("\"records\" : \""+records+"\",");
		jsonStr.append("\"user\" : [");
		response.getWriter().write(jsonStr.toString());
		
		for(int i=0; i<list.size(); i++) {
			jobj.putAll((Map)list.get(i));
			String tempValue = jobj.toString();
			tempValue = parseStringFielter(tempValue);
			response.getWriter().write(tempValue);
			if (i < (list.size()-1)) {
				response.getWriter().write(",");
			}
			jobj.clear();
		}
		response.getWriter().write("]}");
	}
	
	public static void mblJsonResponseUserInfo(HttpServletResponse response, Map userInfo) throws Exception {
		JSONObject jobj = new JSONObject();

		response.setContentType("application/json;charset=UTF-8");
		response.setCharacterEncoding("utf-8");
		
		String userId = StringUtil.nullValue(userInfo.get("USER_ID"));
		String userNm = StringUtil.nullValue(userInfo.get("USER_NM"));
		String relPsnNo = StringUtil.nullValue(userInfo.get("REL_PSN_NO"));
		String mdmUseYn = StringUtil.nullValue(userInfo.get("MDM_USE_YN"));
		
		StringBuffer jsonStr = new StringBuffer();
		jsonStr.append("{\"result\" : \"SUCCESS\",\"userInfo\" :");
		jsonStr.append("{\"userId\" : \""+userId+"\", ");
		jsonStr.append("\"userNm\" : \""+userNm+"\", ");	
		jsonStr.append("\"relPsnNo\" : \""+relPsnNo+"\", ");	
		jsonStr.append("\"mdmUseYn\" : \""+mdmUseYn+"\"}}");	
		
		response.getWriter().write(jsonStr.toString());
		
		/*jsonStr.append("\"sttsList\" : [");
		
		
		for(int i=0; i<sttsList.size(); i++) {
			jobj.putAll((Map)sttsList.get(i));
			String tempValue = jobj.toString();
			tempValue = parseStringFielter(tempValue);
			response.getWriter().write(tempValue);
			if (i < (sttsList.size()-1)) {
				response.getWriter().write(",");
			}
			jobj.clear();
		}*/
		
		/*response.getWriter().write("],\"sttsDcdList\" : [");
		
		for(int i=0; i<sttsDcdList.size(); i++) {
			jobj.putAll((Map)sttsDcdList.get(i));
			String tempValue = jobj.toString();
			tempValue = parseStringFielter(tempValue);
			response.getWriter().write(tempValue);
			if (i < (sttsDcdList.size()-1)) {
				response.getWriter().write(",");
			}
			jobj.clear();
		}*/
		
		/*response.getWriter().write("]}}");*/
	}
	
	public static void mblJsonResponseLogin(HttpServletResponse response, Map userInfo, List sttsList
											, List menulist, String levelColNm, String labelColNm, String relColNm, String idColNm) throws Exception {
		JSONObject jobj = new JSONObject();

		response.setContentType("application/json;charset=UTF-8");
		response.setCharacterEncoding("utf-8");
		
		String userId = StringUtil.nullValue(userInfo.get("USER_ID"));
		String userNm = StringUtil.nullValue(userInfo.get("USER_NM"));
		
		StringBuffer jsonStr = new StringBuffer();
		jsonStr.append("{\"result\" : \"SUCCESS\"");
		response.getWriter().write(jsonStr.toString());
		
		/*jsonStr.append("\"userInfo\" :");
		jsonStr.append("{\"userId\" : \""+userId+"\",");
		jsonStr.append("\"userNm\" : "+userNm+",");		
		
		jsonStr.append("\"sttsList\" : [");
		response.getWriter().write(jsonStr.toString());
		
		for(int i=0; i<sttsList.size(); i++) {
			jobj.putAll((Map)sttsList.get(i));
			String tempValue = jobj.toString();
			tempValue = parseStringFielter(tempValue);
			response.getWriter().write(tempValue);
			if (i < (sttsList.size()-1)) {
				response.getWriter().write(",");
			}
			jobj.clear();
		}
		
		response.getWriter().write("]},");*/
		
		if(menulist.size() > 0){
			StringBuffer jsonStrTree = new StringBuffer();
			jsonStrTree.append(",\"menuTree\" :");
			
			String json = "";
			json = getJsonTree(menulist, levelColNm, labelColNm, relColNm, idColNm);
			
			jsonStrTree.append(json);
			
			response.getWriter().write(jsonStrTree.toString());
		}
		
		response.getWriter().write("}");
	}
	
	
	
	public static void jsonResponse(HttpServletResponse response, List list, int page_num, int page_size, int count) throws Exception {
		JSONObject jobj = new JSONObject();

		//response.setContentType("application/json;charset=UTF-8");
		
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		String userAgent = request.getHeader("User-Agent");

		if(userAgent!=null && (userAgent.indexOf("MSIE 9")!=-1 || userAgent.indexOf("MSIE 8")!=-1 || userAgent.indexOf("MSIE 7")!=-1)) {
			response.setContentType("text/plain; charset=UTF-8");
		} else {
			response.setContentType("application/json;charset=UTF-8");
		}
		
		response.setCharacterEncoding("utf-8");
		
		int page = page_num;
		String total = ((count/page_size)+(count%page_size==0?0:1))+"";
		String records = count+"";
		
		StringBuffer jsonStr = new StringBuffer();
		jsonStr.append("{\"page\" : \""+page+"\",");
		jsonStr.append("\"total\" : "+total+",");		
		jsonStr.append("\"records\" : \""+records+"\",");
		jsonStr.append("\"user\" : [");
		response.getWriter().write(jsonStr.toString());
		logger.debug(list.size()+"/"+page+"/"+total+"/"+records);
		for(int i=0; i<list.size(); i++) {
			jobj.putAll((Map)list.get(i));
			String tempValue = jobj.toString();
			tempValue = parseStringFielter(tempValue);
			response.getWriter().write(tempValue);
			if (i < (list.size()-1)) {
				response.getWriter().write(",");
			}
			jobj.clear();
		}
		response.getWriter().write("]}");
	}
	
	public static void jsonResponse2(HttpServletResponse response, List list, String page_num, int page_size, int count) throws Exception {
		JSONObject jobj = new JSONObject();

		response.setContentType("application/json;charset=UTF-8");
		response.setCharacterEncoding("utf-8");
		
		String page = page_num;
		String total = ((count/page_size)+(count%page_size==0?0:1))+"";
		String records = count+"";
		
		StringBuffer jsonStr = new StringBuffer();
		jsonStr.append("{\"page\" : \""+page+"\",");
		jsonStr.append("\"total\" : "+total+",");		
		jsonStr.append("\"records\" : \""+records+"\",");
		jsonStr.append("\"user\" : [");
		response.getWriter().write(jsonStr.toString());
		logger.debug(list.size()+"/"+page+"/"+total+"/"+records);
		for(int i=0; i<list.size(); i++) {
			Object tObj = list.get(i);
			Map tMap = null;
			tMap = ConverObjectToMap(tObj);
//			jobj.putAll((Map)list.get(i));
			jobj.putAll(tMap);
			String tempValue = jobj.toString();
			tempValue = parseStringFielter(tempValue);
			response.getWriter().write(tempValue);
			if (i < (list.size()-1)) {
				response.getWriter().write(",");
			}
			jobj.clear();
		}
		response.getWriter().write("]}");
	}
	
	public static void mblJsonResponse(HttpServletResponse response, List list, String page_num, int page_size, int count) throws Exception {
		JSONObject jobj = new JSONObject();

		response.setContentType("application/json;charset=UTF-8");
		response.setCharacterEncoding("utf-8");
		
		String page = page_num;
		String total = ((count/page_size)+(count%page_size==0?0:1))+"";
		String records = count+"";
		
		StringBuffer jsonStr = new StringBuffer();
		jsonStr.append("{\"pageNum\" : \""+page+"\",");
		jsonStr.append("\"pageSize\" : "+page_size+",");
		jsonStr.append("\"totalCount\" : \""+records+"\",");
		jsonStr.append("\"list\" : [");
		response.getWriter().write(jsonStr.toString());
		logger.debug(list.size()+"/"+page+"/"+total+"/"+records);
		for(int i=0; i<list.size(); i++) {
			jobj.putAll((Map)list.get(i));
			String tempValue = jobj.toString();
			tempValue = parseStringFielter(tempValue);
			response.getWriter().write(tempValue);
			if (i < (list.size()-1)) {
				response.getWriter().write(",");
			}
			jobj.clear();
		}
		response.getWriter().write("]}");
	}
	
	
	public static void mblJsonResponse(HttpServletResponse response, String result) throws Exception {
		JSONObject jobj = new JSONObject();

		response.setContentType("application/json;charset=UTF-8");
		response.setCharacterEncoding("utf-8");
		
		StringBuffer jsonStr = new StringBuffer();
		jsonStr.append("{\"result\" : \""+result+"\"}");
		
		response.getWriter().write(jsonStr.toString());
	}
	
	/**
	 * Tree 목록 데이터 Json 데이터 타입으로 클라이언트에 전송
	 * @param response
	 * @param list
	 * @param levelColNm
	 * @param labelColNm
	 * @throws Exception
	 */
	public static void jsonResponseTree(HttpServletResponse response, List list, String levelColNm, String labelColNm) throws Exception {
		//String json ="[{\"data\":\"Anode\",\"metadata\":{\"id\":\"1\"},\"children\":[{\"data\":\"child1\",\"metadata\":{\"id\":\"2\"}},{\"data\":\"A child2\",\"metadata\":{\"id\":\"3\"}}]}]";
		
		//String newline = "\n";
		String newline = "";
		String comma = ",";
		String json = "";
		json += "[{";
		int preLevel = 0;
		int i = 0;
		
		Iterator<?> it = list.iterator();
		while(it.hasNext()) {
			Map map = (Map) it.next();
			int treeLevel = Integer.parseInt(StringUtil.nullValue(map.get(levelColNm)));
			String treeLabel = StringUtil.nullValue(map.get(labelColNm));
			
			if (preLevel > 0) {
				if (preLevel+1 == treeLevel) {
					json += newline+comma+"\"children\":[{";
				} else {
					for(int k=preLevel; k>treeLevel; k--) {
						json += newline+"}]";
					}
					if ((++i) < list.size()) {
		            	json += "}"+comma+"{";
		    		}
				}
			}
			
			json += newline+"\"data\":\""+treeLabel+"\"";
			json += comma;
			json += "\"metadata\":{";
			
			Set<?> keySet =  map.keySet();
	        String tempKey = null;
	        String tempValue = null;
	        Iterator<?> keyIter = keySet.iterator();
	        
	        int j = 0;
	        while(keyIter.hasNext()) {
	            tempKey = (String) keyIter.next();
	            tempValue = StringUtil.nullValue(map.get(tempKey));
	            tempValue = parseStringFielterDQ(tempValue);
	            json += "\""+tempKey+"\":\""+tempValue+"\"";
	            if ((++j) < map.size()) {
	            	json += comma;
	    		}
	        }
	        
	        json += "}";

	        preLevel = treeLevel;
		}
		
		for(int l=preLevel; l>0; l--) {
			json += newline+"}]";
		}

		//System.out.println("json:"+json);
		
		response.setContentType("application/json");
		response.setContentType("application/json;charset=UTF-8");
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(json);
	}
	
	private static String getJsonTree(List list, String levelColNm, String labelColNm, String relColNm, String idColNm){
		String newline = "";
		String comma = ",";
		String json = "";
		json += "[{";
		int preLevel = 0;
		int i = 0;
		
		Iterator<?> it = list.iterator();
		while(it.hasNext()) {
			Map map = (Map) it.next();
			int treeLevel = Integer.parseInt(StringUtil.nullValue(map.get(levelColNm)));
			String treeLabel = StringUtil.nullValue(map.get(labelColNm));
			
			if (preLevel > 0) {
				if (preLevel+1 == treeLevel) {
					json += newline+comma+"\"children\":[{";
				} else {
					for(int k=preLevel; k>treeLevel; k--) {
						json += newline+"}]";
					}
					if ((++i) < list.size()) {
		            	json += "}"+comma+"{";
		    		}
				}
			}
			
			json += newline+"\"data\":\""+treeLabel+"\"";
			json += comma;
			json += "\"metadata\":{";
			
			Set<?> keySet =  map.keySet();
	        String tempKey = null;
	        String tempValue = null;
	        Iterator<?> keyIter = keySet.iterator();
	        
	        int j = 0;
	        while(keyIter.hasNext()) {
	            tempKey = (String) keyIter.next();
	            tempValue = StringUtil.nullValue(map.get(tempKey));
	            tempValue = parseStringFielterDQ(tempValue);
	            json += "\""+tempKey+"\":\""+tempValue+"\"";
	            if ((++j) < map.size()) {
	            	json += comma;
	    		}
	        }
	        
	        json += "}";
	        
	        json += comma;
			json += "\"attr\":{";
			
			tempValue = StringUtil.nullValue(map.get(idColNm));
			json += "\"id\":\""+tempValue+"\"";
			
			json += comma;
			
			tempValue = StringUtil.nullValue(map.get(relColNm));
			json += "\"rel\":\""+tempValue+"\"";
			
			json += "}";
			

	        preLevel = treeLevel;
		}
		
		for(int l=preLevel; l>0; l--) {
			json += newline+"}]";
		}
		
		return json;
	}
	
	public static void jsonResponseTree(HttpServletResponse response, List list, String levelColNm, String labelColNm, String relColNm, String idColNm) throws Exception {
		//String json ="[{\"data\":\"Anode\",\"metadata\":{\"id\":\"1\"},\"children\":[{\"data\":\"child1\",\"metadata\":{\"id\":\"2\"}},{\"data\":\"A child2\",\"metadata\":{\"id\":\"3\"}}]}]";
		
		//String newline = "\n";
		/*String newline = "";
		String comma = ",";
		String json = "";
		json += "[{";
		int preLevel = 0;
		int i = 0;
		
		Iterator<?> it = list.iterator();
		while(it.hasNext()) {
			Map map = (Map) it.next();
			int treeLevel = Integer.parseInt(StringUtil.nullValue(map.get(levelColNm)));
			String treeLabel = StringUtil.nullValue(map.get(labelColNm));
			
			if (preLevel > 0) {
				if (preLevel+1 == treeLevel) {
					json += newline+comma+"\"children\":[{";
				} else {
					for(int k=preLevel; k>treeLevel; k--) {
						json += newline+"}]";
					}
					if ((++i) < list.size()) {
		            	json += "}"+comma+"{";
		    		}
				}
			}
			
			json += newline+"\"data\":\""+treeLabel+"\"";
			json += comma;
			json += "\"metadata\":{";
			
			Set<?> keySet =  map.keySet();
	        String tempKey = null;
	        String tempValue = null;
	        Iterator<?> keyIter = keySet.iterator();
	        
	        int j = 0;
	        while(keyIter.hasNext()) {
	            tempKey = (String) keyIter.next();
	            tempValue = StringUtil.nullValue(map.get(tempKey));
	            tempValue = parseStringFielterDQ(tempValue);
	            json += "\""+tempKey+"\":\""+tempValue+"\"";
	            if ((++j) < map.size()) {
	            	json += comma;
	    		}
	        }
	        
	        json += "}";
	        
	        json += comma;
			json += "\"attr\":{";
			
			tempValue = StringUtil.nullValue(map.get(idColNm));
			json += "\"id\":\""+tempValue+"\"";
			
			json += comma;
			
			tempValue = StringUtil.nullValue(map.get(relColNm));
			json += "\"rel\":\""+tempValue+"\"";
			
			json += "}";
			

	        preLevel = treeLevel;
		}
		
		for(int l=preLevel; l>0; l--) {
			json += newline+"}]";
		}*/
		
		String json = "";
		json = getJsonTree(list, levelColNm, labelColNm, relColNm, idColNm);

		//System.out.println("json:"+json);
		
		response.setContentType("application/json");
		response.setContentType("application/json;charset=UTF-8");
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(json);
	}
	
	public static String comboCodeName(List list) throws Exception {
		String strAll = "";
		for(int i=0; i<list.size(); i++) {
			Map map = (Map) list.get(i);
			strAll += StringUtil.nullValue(map.get("CODE")) + ":" + StringUtil.nullValue(map.get("NAME"));
			if (i < (list.size()-1)) {
				strAll += ";";
			}
		}
		return strAll;
	}
	
	public static String stringSelectDataJqgrid(List<?> list) throws Exception {
		String strAll = "";
		for(int i=0; i<list.size(); i++) {
			Map map = (Map) list.get(i);
			strAll += StringUtil.nullValue(map.get("CODE")) + ":" + StringUtil.nullValue(map.get("NAME"));
			if (i < (list.size()-1)) {
				strAll += ";";
			}
		}
		return strAll;
	}
	
	public static String stringSelectDataJqgridFilter(List<?> list) throws Exception {
		
		StringBuilder strAll = new StringBuilder();
		String key;
		Iterator iter;
		for(int i=0; i<list.size(); i++) {
			Map map = (Map) list.get(i);
			
			iter = map.keySet().iterator();			
			while(iter.hasNext()){
				key = (String)iter.next();
				strAll.append(key).append(":").append(map.get(key));
				if(iter.hasNext()) strAll.append("^");
			}
			
			if (i < (list.size()-1)) {
				strAll.append(";");
			}
		}
		
		return strAll.toString();
	}
	
	/**
	 * 
	 * @param paramString
	 * @param strDiv1
	 * @param strDiv2
	 * @return
	 */
	public static Map<String, String> getParamUser(String paramString, String strDiv1, String strDiv2) {
		Map<String, String> paramUser = new HashMap<String, String>();
		String[] params = paramString.split(strDiv1);
		for(int i=0; i<params.length; i++) {
			try {
				String[] entry = params[i].split(strDiv2);
				paramUser.put(entry[0], entry[1]);
			} catch(Exception ex) { }
		}
		return paramUser;
	}
	
	
	/**
	 * 
	 * @param response
	 * @param userInfo
	 * @param sttsList
	 * @param menulist
	 * @param levelColNm
	 * @param labelColNm
	 * @param relColNm
	 * @param idColNm
	 * @return
	 * @throws Exception
	 */
	public static String mblJsonResponseLoginSso(HttpServletResponse response, Map userInfo, List sttsList, List menulist, String levelColNm,
			String labelColNm, String relColNm, String idColNm) throws Exception {
		JSONObject jobj = new JSONObject();

		response.setContentType("application/json;charset=UTF-8");
		response.setCharacterEncoding("utf-8");
		
		String userId = StringUtil.nullValue(userInfo.get("USER_ID"));
		String userNm = StringUtil.nullValue(userInfo.get("USER_NM"));
		
		StringBuffer jsonStr = new StringBuffer();
		jsonStr.append("{\"result\" : \"SUCCESS\"");
		
		
		/*jsonStr.append("\"userInfo\" :");
		jsonStr.append("{\"userId\" : \""+userId+"\",");
		jsonStr.append("\"userNm\" : "+userNm+",");		
		
		jsonStr.append("\"sttsList\" : [");
		response.getWriter().write(jsonStr.toString());
		
		for(int i=0; i<sttsList.size(); i++) {
			jobj.putAll((Map)sttsList.get(i));
			String tempValue = jobj.toString();
			tempValue = parseStringFielter(tempValue);
			response.getWriter().write(tempValue);
			if (i < (sttsList.size()-1)) {
				response.getWriter().write(",");
			}
			jobj.clear();
		}
		
		response.getWriter().write("]},");*/
		String json = "";
		StringBuffer jsonStrTree = null;
		if(menulist.size() > 0){
			jsonStrTree = new StringBuffer();
			jsonStrTree.append(",\"menuTree\" :");
			
			json = getJsonTree(menulist, levelColNm, labelColNm, relColNm, idColNm);
			
			jsonStrTree.append(json);
			
		}
		
		jsonStr.append(jsonStrTree).append("}");
		return jsonStr.toString();
		
	}

	
	public static void jsonResponseAppDownloadInfo(HttpServletResponse response, Map<String, Object> appDownloadInfo) throws IOException
	{
		JSONObject jobj = new JSONObject();

		response.setContentType("application/json;charset=UTF-8");
		response.setCharacterEncoding("utf-8");
		
		String appVersion = StringUtil.nullValue(appDownloadInfo.get("APP_VERSION"));
		
		StringBuffer jsonStr = new StringBuffer();
		jsonStr.append("{\"result\" : \"SUCCESS\",\"appDownloadInfo\" :");
		jsonStr.append("{\"appVersion\" : \""+appVersion+"\"}}");
		
		response.getWriter().write(jsonStr.toString());
	}
	
	public static Map ConverObjectToMap(Object obj){
		try {
			//Field[] fields = obj.getClass().getFields(); //private field는 나오지 않음.
			Field[] fields = obj.getClass().getDeclaredFields();
			Map resultMap = new HashMap();
			for(int i=0; i<=fields.length-1;i++){
				fields[i].setAccessible(true);
				resultMap.put(fields[i].getName(), fields[i].get(obj));
			}
			return resultMap;
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static boolean isUrlExist(String s){
		boolean result = false;
		
		try{
			URL url = new URL(s); 
			URLConnection con = url.openConnection(); 
			HttpURLConnection exitCode = (HttpURLConnection)con;
			
			if(exitCode.getResponseCode() == 200){
				result = true;
			}
		}catch(Exception e) {
			result = false;
		}
		
		return result;
	}
	
	public static String arrayJoin(String array[], String glue) {
		String result = "";

		for (int i = 0; i < array.length; i++) {
			result += array[i];
			if (i < array.length - 1)
				result += glue;
		}
		return result;
	}
	
    /**
     * 클라이언트의 IP주소를 구한다.
     * 
     * @param request HttpServletRequest
     * @return 클라이언트의 IP주소
     */
    public static String getClientIpAddr( HttpServletRequest request ) {
        String clientIp = null;
        clientIp = request.getHeader( "CLIENT_IP" );
        if(clientIp != null) {
            return clientIp;
        } else {
            clientIp = request.getHeader("X-Forwarded-For");
            if(clientIp != null) return clientIp;
        }
        
        return request.getRemoteAddr();
    }	
	
    //서버에 저장될 파일이름 랜덤생성
    public static class CommonUtils {
        public static String getRandomString(){
            return UUID.randomUUID().toString().replaceAll("-", "");
        }
    }
    
    // 전화번호 자르기
    public static String[] getTelPhoneSpliter(String noStr) throws Exception{
		
    	Pattern tellPattern = Pattern.compile( "^(01\\d{1}|02|0505|0502|0506|0\\d{1,2})-?(\\d{3,4})-?(\\d{4})");
		
		if(noStr == null) return new String[]{ "", "", ""};
		 
		Matcher matcher = tellPattern.matcher( noStr);
		
		if(matcher.matches()) {
			return new String[]{ matcher.group( 1), matcher.group( 2), matcher.group( 3)};
		}else{
			String str1 = noStr.substring(0, 3);
			String str2 = noStr.substring(3, 7);
			String str3 = noStr.substring(7, 11);
			
			return new String[]{ str1, str2, str3};
		}
	}

    /**
	  * 모바일,타블렛,PC구분
	  * @param req
	  * @return
	  */
	 public static String getDevice(HttpServletRequest req) {
		 String userAgent = req.getHeader("User-Agent").toUpperCase();
		 //System.out.println("userAgentuserAgentuserAgentuserAgent::::::" + userAgent);
		 if(userAgent.indexOf("MOBILE") > -1 || userAgent.indexOf("ANDROID") > -1) {
			 if(userAgent.indexOf("IPHONE") > -1){
				 return "IOS";
			 }else if(userAgent.indexOf("ANDROID") > -1){
				 return "ANDROID";
			 }else{
				 return "TABLET";
			 } 
		 }else{
			 return "PC";
		 }
	 }
	 
	 //URL 정보 추출
	 /**
		 group(0) = https://goodidea.tistory.com:8888/qr/aaa/ddd.html?abc=def&ddd=fgf#sharp
		 group(1) = https
		 group(2) = goodidea.tistory.com
		 group(3) = :8888
		 group(4) = 8888
		 group(5) = /qr/aaa
		 group(6) = /aaa
		 group(7) = ddd.html
		 group(8) = ?abc=def&ddd=fgf
		 group(9) = abc=def&ddd=fgf
		 group(10) = #sharp
		 group(11) = sharp
	 **/

	 public static Map<String, String> extractUrlParts(String url) {
		 String testurl = url;
		 //http:       //  도메인명             :포트                     경로                                파일명                    쿼리                         앵커
		 
		 Pattern urlPattern = Pattern.compile("^(https?):\\/\\/([^:\\/\\s]+)(:([^\\/]*))?((\\/[^\\s/\\/]+)*)?\\/([^#\\s\\?]*)(\\?([^#\\s]*))?(#(\\w*))?$");
		 Matcher mc = urlPattern.matcher(testurl);

		 Map<String, String> returnVal = new HashMap<String, String>();
		   
		 if(mc.matches()){
			 for(int i=0;i<=mc.groupCount();i++){
				 returnVal.put("URL"+i, mc.group(i));
			 }
		 } else{
			 returnVal.put("URL0", "not found");
		 }
		 
		 return returnVal;
	 }
}