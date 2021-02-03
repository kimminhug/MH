package com.core.util;

import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.core.util.CommonUtil.CommonUtils;

@Component("fileUtils")
public class fileUtils {
	/*private static final String filePath = "C:\\dev\\file\\";*/
	
	//개발
	/*private static final String filePath = "C:\\dev\\file\\";
	private static final String filePathImg = "C:\\dev\\file\\";
	private static final String filePathVideo = "C:\\dev\\file\\";*/
/*	private static final String filePath = "D:\\file\\";
	private static final String filePathImg = "D:\\file\\img\\";
	private static final String filePathVideo = "D:\\file\\mov\\";
*/	
	//운영
	private static final String filePath = "/home/jahen0725/file/";
	private static final String filePathImg = "/home/jahen0725/img/";
	private static final String filePathVideo = "/home/jahen0725/video/";
    
    public static List<Map<String,Object>> parseInsertFileInfo(Map<String,Object> map, HttpServletRequest request, String type) throws Exception{
    	MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest)request;
    	Iterator<String> iterator = multipartHttpServletRequest.getFileNames();
    	List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
    	Map<String, Object> listMap = null;
    	String now = new SimpleDateFormat("yyyyMMddHmsS").format(new Date());  //현재시간
    	
    	System.out.println("type :: " + type);
    	
    	if (type.equals("vd")) {
             
            MultipartFile multipartFile = null;
            String originalFileName = null;
            String originalFileExtension = null;
            String storedFileName = null;
            
            //String fileId = CommonUtils.getRandomString();
            
            File file = new File(filePathVideo);
            if(file.exists() == false){
                file.mkdirs();
            }
            while(iterator.hasNext()){
                multipartFile = multipartHttpServletRequest.getFile(iterator.next());
                if(multipartFile.isEmpty() == false){
                    originalFileName = multipartFile.getOriginalFilename();
                    originalFileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
                    storedFileName = now+CommonUtils.getRandomString() + originalFileExtension;
                    
                    file = new File(filePathVideo + storedFileName);
                    multipartFile.transferTo(file);
                     
                    listMap = new HashMap<String,Object>();
                   //파일필수칼럼
                    listMap.put("crsvnidx", Integer.parseInt((String) map.get("crsvnidx")));
                    listMap.put("adidx", Integer.parseInt((String) map.get("adidx")));
                    listMap.put("usidx", Integer.parseInt((String) map.get("usidx")));
                    listMap.put("ptidx", Integer.parseInt((String) map.get("ptidx")));
                    listMap.put("clcode", Integer.parseInt((String) map.get("clcode")));
                    listMap.put("realfilename", originalFileName);
                    listMap.put("filename", storedFileName);
                    listMap.put("filepath", filePathVideo);
                    listMap.put("fileid", (String)map.get("fildid"));
                    System.out.println("fileid : " + (String)map.get("fildid"));
                    listMap.put("filesize", multipartFile.getSize());
                    
                    //서브 칼럼
                    listMap.put("subject", (String)map.get("subject"));
                    list.add(listMap);
                }
            }
		}//비디오 파일 저장
    	if (type.equals("gvd")) {
    		
    		MultipartFile multipartFile = null;
    		String originalFileName = null;
    		String originalFileExtension = null;
    		String storedFileName = null;
    		
    		File file = new File(filePathVideo);
    		if(file.exists() == false){
    			file.mkdirs();
    		}
    		while(iterator.hasNext()){
    			multipartFile = multipartHttpServletRequest.getFile(iterator.next());
    			if(multipartFile.isEmpty() == false){
    				originalFileName = multipartFile.getOriginalFilename();
    				originalFileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
    				storedFileName = now+CommonUtils.getRandomString() + originalFileExtension;
    				
    				file = new File(filePathVideo + storedFileName);
    				multipartFile.transferTo(file);
    				
    				listMap = new HashMap<String,Object>();
    				//파일필수칼럼
    				listMap.put("grpchidx", Integer.parseInt((String) map.get("grpchidx")));
    				listMap.put("adidx", Integer.parseInt((String) map.get("adidx")));
    				listMap.put("usidx", Integer.parseInt((String) map.get("usidx")));
    				listMap.put("ptidx", Integer.parseInt((String) map.get("ptidx")));
    				listMap.put("clcode", Integer.parseInt((String) map.get("clcode")));
    				listMap.put("realfilename", originalFileName);
    				listMap.put("filename", storedFileName);
    				listMap.put("filepath", filePathVideo);
    				listMap.put("filesize", multipartFile.getSize());
    				
    				//서브 칼럼
    				listMap.put("subject", (String)map.get("subject"));
    				list.add(listMap);
    			}
    		}
    	}//그룹비디오 파일 저장
    	
    	if (type.equals("plogo")) {
            
            MultipartFile multipartFile = null;
            String originalFileName = null;
            String originalFileExtension = null;
            String storedFileName = null;
            
            
            File file = new File(filePathImg);
            if(file.exists() == false){
                file.mkdirs();
            }
            while(iterator.hasNext()){
                multipartFile = multipartHttpServletRequest.getFile(iterator.next());
                if(multipartFile.isEmpty() == false){
                    originalFileName = multipartFile.getOriginalFilename();
                    originalFileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
                    storedFileName = now+CommonUtils.getRandomString() + originalFileExtension;
                    
                    /*String tmpUrl = "http://115.94.252.194:8888/img/"+storedFileName;*/ // 추후 전역변수로
                    System.out.println(originalFileName);
                    file = new File(filePathImg + storedFileName);
                    multipartFile.transferTo(file);
                    listMap = new HashMap<String,Object>();
                   //파일필수칼럼
                    listMap.put("fileidx", (String)map.get("fileidx"));
                    listMap.put("realfilename", originalFileName);
                    listMap.put("filename", storedFileName);
                    listMap.put("filepath", filePathImg);
                    listMap.put("filesize", multipartFile.getSize());
                   /* listMap.put("tempurl", tmpUrl);*/
                    listMap.put("ptidx", Integer.parseInt((String) map.get("ptidx")));
                    listMap.put("warkip", (String)map.get("warkip"));
                    
                    list.add(listMap);
                }
            }
		}//업체 로고 이미지 저장
    	
    	if (type.equals("pimg")) {
            
            MultipartFile multipartFile = null;
            String originalFileName = null;
            String originalFileExtension = null;
            String storedFileName = null;
            
            
            File file = new File(filePathImg);
            if(file.exists() == false){
                file.mkdirs();
            }
            while(iterator.hasNext()){
                multipartFile = multipartHttpServletRequest.getFile(iterator.next());
                if(multipartFile.isEmpty() == false){
                    originalFileName = multipartFile.getOriginalFilename();
                    originalFileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
                    storedFileName = now+CommonUtils.getRandomString() + originalFileExtension;
                    
                    System.out.println(originalFileName);
                    file = new File(filePathImg + storedFileName);
                    multipartFile.transferTo(file);
                    listMap = new HashMap<String,Object>();
                   //파일필수칼럼
                    listMap.put("fileidx", (String)map.get("fileidx"));
                    listMap.put("realfilename", originalFileName);
                    listMap.put("filename", storedFileName);
                    listMap.put("filepath", filePathImg);
                    listMap.put("filesize", multipartFile.getSize());
                    listMap.put("ptidx", Integer.parseInt((String) map.get("ptidx")));
                    listMap.put("warkip", (String)map.get("warkip"));
                    
                    list.add(listMap);
                }
            }
		}//업체 이미지 저장
    	
    	if (type.equals("ev")) {
    		
    		MultipartFile multipartFile = null;
            String originalFileName = null;
            String originalFileExtension = null;
            String storedFileName = null;
            
            File file = new File(filePathImg);
            if(file.exists() == false){
                file.mkdirs();
            }
            while(iterator.hasNext()){
                multipartFile = multipartHttpServletRequest.getFile(iterator.next());
                if(multipartFile.isEmpty() == false){
                    originalFileName = multipartFile.getOriginalFilename();
                    originalFileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
                    storedFileName =now+CommonUtils.getRandomString() + originalFileExtension;
                    
                    String tmpUrl = "http://115.94.252.194:8888/img/"+storedFileName; // 추후 전역변수로 
                    file = new File(filePathImg + storedFileName);
                    multipartFile.transferTo(file);
                     
                    listMap = new HashMap<String,Object>();
                   //파일필수칼럼
                    if (map.get("evidx") != null) {
                    	listMap.put("evidx", Integer.parseInt((String) map.get("evidx")));
					}
                    listMap.put("adidx", Integer.parseInt((String) map.get("adidx")));
                    listMap.put("noticefn", (String)map.get("noticefn"));
                    listMap.put("mttitle", (String)map.get("mttitle"));
                    listMap.put("conts", (String)map.get("conts"));
                    listMap.put("evstdate", (String)map.get("evstdate"));
                    listMap.put("evetdate", (String)map.get("evetdate"));
                    listMap.put("evpubsection", (String)map.get("evpubsection"));
                    listMap.put("evactivity", (String)map.get("evactivity"));
                    listMap.put("warkip", (String)map.get("warkip"));
                   if (multipartFile.isEmpty() == false) {
                	   listMap.put("realfilename", originalFileName);
                	   listMap.put("filename", storedFileName);
                	   listMap.put("filepath", filePathImg);
                	   listMap.put("tempurl", tmpUrl);
                   }else if(multipartFile.isEmpty() == true){
                	   listMap.put("realfilename", (String)map.get("realfilename"));
                	   listMap.put("filename", (String)map.get("filename"));
                	   listMap.put("filepath", (String)map.get("filepath"));
                	   listMap.put("tempurl", (String)map.get("tempurl"));
                   }
                    /*listMap.put("filesize", multipartFile.getSize());*/ //필요시 사용
                    
                    list.add(listMap);
                }
            }
		}//이벤트 이미지 저장
    	
    	return list;
    }
    
    public static List<Map<String,Object>> parseInsertFileInfoBD(Map<String,Object> map, HttpServletRequest request, String type, String fileId) throws Exception{
    	MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest)request;
    	Iterator<String> iterator = multipartHttpServletRequest.getFileNames();
    	List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
    	Map<String, Object> listMap = null;
    	String now = new SimpleDateFormat("yyyyMMddHmsS").format(new Date());  //현재시간
    	
    	if (type.equals("uf")) {
    		MultipartFile multipartFile = null;
            String originalFileName = null;
            String originalFileExtension = null;
            String storedFileName = null;
            String fileid = fileId;
            
            File file = new File(filePath);
            if(file.exists() == false){
                file.mkdirs();
            }
            while(iterator.hasNext()){
                multipartFile = multipartHttpServletRequest.getFile(iterator.next());
                if(multipartFile.isEmpty() == false){
                    originalFileName = multipartFile.getOriginalFilename();
                    originalFileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
                    storedFileName =now+CommonUtils.getRandomString() + originalFileExtension;
                    
                    file = new File(filePath + storedFileName);
                    multipartFile.transferTo(file);
              
                    System.out.println("▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶");
                    System.out.println("filePath :: " + filePath);
                    System.out.println("storedFileName :: " + storedFileName);
                    System.out.println("file full path :: " + filePath+storedFileName);
                    
                    listMap = new HashMap<String,Object>();
                   //파일필수칼럼
                    listMap.put("realfilename", originalFileName);
                    listMap.put("filename", storedFileName);
                    listMap.put("filepath", filePath);
                    listMap.put("filesize", multipartFile.getSize());
                    listMap.put("fileid", fileid);
                    listMap.put("filesize", multipartFile.getSize());
                    
                    list.add(listMap);
                }
            }
		}//일반공지사항 파일 저장
    	
    	if (type.equals("pdf")) {
    		MultipartFile multipartFile = null;
            String originalFileName = null;
            String originalFileExtension = null;
            String storedFileName = null;
            String fileid = fileId;
            
            File file = new File(filePath);
            if(file.exists() == false){
                file.mkdirs();
            }
            while(iterator.hasNext()){
                multipartFile = multipartHttpServletRequest.getFile(iterator.next());
                if(multipartFile.isEmpty() == false){
                    originalFileName = multipartFile.getOriginalFilename();
                    originalFileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
                    storedFileName =now+CommonUtils.getRandomString() + originalFileExtension;
                    
                    file = new File(filePath + storedFileName);
                    multipartFile.transferTo(file);
              
                    System.out.println("▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶");
                    System.out.println("filePath :: " + filePath);
                    System.out.println("storedFileName :: " + storedFileName);
                    System.out.println("file full path :: " + filePath+storedFileName);
                    
                    listMap = new HashMap<String,Object>();
                   //파일필수칼럼
                    listMap.put("realfilename", originalFileName);
                    listMap.put("filename", storedFileName);
                    listMap.put("filepath", filePath);
                    listMap.put("filesize", multipartFile.getSize());
                    listMap.put("fileid", fileid);
                    listMap.put("filesize", multipartFile.getSize());
                    
                    list.add(listMap);
                }
            }
		}
    	
    	if (type.equals("pf")) {
    		MultipartFile multipartFile = null;
            String originalFileName = null;
            String originalFileExtension = null;
            String storedFileName = null;
            String fileid = fileId;
            
            File file = new File(filePath);
            if(file.exists() == false){
                file.mkdirs();
            }
            while(iterator.hasNext()){
                multipartFile = multipartHttpServletRequest.getFile(iterator.next());
                if(multipartFile.isEmpty() == false){
                    originalFileName = multipartFile.getOriginalFilename();
                    originalFileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
                    storedFileName =now+CommonUtils.getRandomString() + originalFileExtension;
                    
                    file = new File(filePath + storedFileName);
                    multipartFile.transferTo(file);
                     
                    listMap = new HashMap<String,Object>();
                   //파일필수칼럼
                    listMap.put("realfilename", originalFileName);
                    listMap.put("filename", storedFileName);
                    listMap.put("filepath", filePath);
                    listMap.put("filesize", multipartFile.getSize());
                    listMap.put("fileid", fileid);
                    listMap.put("filesize", multipartFile.getSize());
                    
                    list.add(listMap);
                }
            }
		}//파트너스공지사항 파일 저장
    	
    	if (type.equals("rf")) { // 리뷰, 커뮤니티
    		MultipartFile multipartFile = null;
            String originalFileName = null;
            String originalFileExtension = null;
            String storedFileName = null;
            String storedFileName2 = null;
            String fileid = fileId;
            String convertUUID = UUID.randomUUID().toString().replace("-", ""); // 해쉬명 생성
            int filesn = 0;
            String newFileName = null;
            
            File file = new File(filePathImg);
            File newFileImg = null;
            Image tmpImg = null;
            BufferedImage tempImg = null;
            
            if(file.exists() == false){
                file.mkdirs();
            }
            while(iterator.hasNext()){
                multipartFile = multipartHttpServletRequest.getFile(iterator.next());
                if(multipartFile.isEmpty() == false){
                    originalFileName = multipartFile.getOriginalFilename();
                    originalFileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
                    storedFileName =now+CommonUtils.getRandomString() + originalFileExtension;
                    storedFileName2 =now+CommonUtils.getRandomString();
                    file = new File(filePathImg + storedFileName);
                    
                                                                                
                    multipartFile.transferTo(file);                    
                    resize(file.getPath());                   
                    // 이미지 리사이즈
                    /*
                    System.out.println("file : "+ file);
                    //tmpImg = ImageIO.read(new File(filePathImg + storedFileName));
                    tmpImg = ImageIO.read(file);
                    tempImg = resizeImage(tmpImg, 500, 300);
                    newFileName = filePathImg+storedFileName2+"_resize."+originalFileExtension;
                    newFileImg = new File(newFileName);
                    ImageIO.write(tempImg, originalFileExtension, newFileImg);
                    */
                    //multipartFile.transferTo(newFileImg);
                    
                    listMap = new HashMap<String,Object>();
                   //파일필수칼럼                  
                    //System.out.println("fileUtils filesn : " + filesn);
                    
                    listMap.put("realfilename", originalFileName);
                    listMap.put("filename", storedFileName);
                    //listMap.put("filename", storedFileName2+"_resize."+originalFileExtension);
                    listMap.put("filepath", filePathImg);
                    listMap.put("filesize", multipartFile.getSize());
                    listMap.put("fileid", fileid);
                    listMap.put("filesize", multipartFile.getSize());
                    listMap.put("filesn", filesn);
                    list.add(listMap);
                    filesn++;
                }
                
            }
		}//일반리뷰 파일 저장
    	if (type.equals("uvf")) {
    		
    		MultipartFile multipartFile = null;
    		String originalFileName = null;
    		String originalFileExtension = null;
    		String storedFileName = null;
    		String fileid = fileId;
    		int filesn = 0;
    		File file = new File(filePathVideo);
    		if(file.exists() == false){
    			file.mkdirs();
    		}
    		while(iterator.hasNext()){
    			multipartFile = multipartHttpServletRequest.getFile(iterator.next());
    			if(multipartFile.isEmpty() == false){
    				originalFileName = multipartFile.getOriginalFilename();
    				originalFileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
    				storedFileName = now+CommonUtils.getRandomString() + originalFileExtension;
    				
    				file = new File(filePathVideo + storedFileName);
    				multipartFile.transferTo(file);
    				
    				listMap = new HashMap<String,Object>();
    				//파일필수칼럼
    				listMap.put("uvidx", Integer.parseInt((String) map.get("uvidx")));
    				listMap.put("ptidx", Integer.parseInt((String) map.get("ptidx")));
    				listMap.put("fileid", fileid);
    				listMap.put("realfilename", originalFileName);
    				listMap.put("filename", storedFileName);
    				listMap.put("filepath", filePathVideo);
    				listMap.put("filesize", multipartFile.getSize());
    				listMap.put("filesn", filesn);
    				
    				//서브 칼럼
    				listMap.put("subject", (String)map.get("subject"));
    				list.add(listMap);
    				filesn++;
    			}
    		}
    	}//사용자커뮤니티 파일저장
    	if (type.equals("usercomu")) { // 사용자 커뮤니티
    		MultipartFile multipartFile = null;
            String originalFileName = null;
            String originalFileExtension = null;
            String storedFileName = null;
            String fileid = fileId;
            File file = new File(filePathImg);
            if(file.exists() == false){
                file.mkdirs();
            }
            while(iterator.hasNext()){
                multipartFile = multipartHttpServletRequest.getFile(iterator.next());
                if(multipartFile.isEmpty() == false){
                    originalFileName = multipartFile.getOriginalFilename();
                    originalFileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
                    storedFileName =now+CommonUtils.getRandomString() + originalFileExtension;
                    file = new File(filePathImg + storedFileName);
                    multipartFile.transferTo(file);
                    
                    System.out.println(" FILE PATH : "+ file.getPath());
                    
                    
                    resize(file.getPath());
                    
                    
                     
                    listMap = new HashMap<String,Object>();
                   //파일필수칼럼
                  
                    System.out.println("originalFileName" + originalFileName);
                    listMap.put("realfilename", originalFileName);
                    listMap.put("filename", storedFileName);
                    listMap.put("filepath", filePathImg);
                    listMap.put("filesize", multipartFile.getSize());
                    listMap.put("fileid", fileid);
                    listMap.put("filesize", multipartFile.getSize());
                    
                    list.add(listMap);
                }
            }
		}//사용자커뮤니티 파일 저장
    	
    	if (type.equals("ev")) { // 사용자가이드
    		MultipartFile multipartFile = null;
            String originalFileName = null;
            String originalFileExtension = null;
            String storedFileName = null;
            String fileid = fileId;
            
            File file = new File(filePathImg);
            if(file.exists() == false){
                file.mkdirs();
            }
            while(iterator.hasNext()){
                multipartFile = multipartHttpServletRequest.getFile(iterator.next());
                if(multipartFile.isEmpty() == false){
                    originalFileName = multipartFile.getOriginalFilename();
                    originalFileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
                    storedFileName =now+CommonUtils.getRandomString() + originalFileExtension;
                    
                    file = new File(filePathImg + storedFileName);
                    multipartFile.transferTo(file);
                     
                    listMap = new HashMap<String,Object>();
                   //파일필수칼럼
                  
                    System.out.println("originalFileName" + originalFileName);
                    listMap.put("realfilename", originalFileName);
                    listMap.put("filename", storedFileName);
                    listMap.put("filepath", filePathImg);
                    listMap.put("filesize", multipartFile.getSize());
                    listMap.put("fileid", fileid);
                    listMap.put("filesize", multipartFile.getSize());
                    
                    list.add(listMap);
                }
            }
		}//사용자가이드 파일 저장
    	
    	if (type.equals("inf")) { // 사용자 커뮤니티
    		MultipartFile multipartFile = null;
            String originalFileName = null;
            String originalFileExtension = null;
            String storedFileName = null;
            String fileid = fileId;
            
            File file = new File(filePathImg);
            if(file.exists() == false){
                file.mkdirs();
            }
            while(iterator.hasNext()){
                multipartFile = multipartHttpServletRequest.getFile(iterator.next());
                if(multipartFile.isEmpty() == false){
                    originalFileName = multipartFile.getOriginalFilename();
                    originalFileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
                    storedFileName =now+CommonUtils.getRandomString() + originalFileExtension;
                    
                    file = new File(filePathImg + storedFileName);
                    multipartFile.transferTo(file);
                     
                    listMap = new HashMap<String,Object>();
                   //파일필수칼럼
                  
                    System.out.println("originalFileName" + originalFileName);
                    listMap.put("realfilename", originalFileName);
                    listMap.put("filename", storedFileName);
                    listMap.put("filepath", filePathImg);
                    listMap.put("filesize", multipartFile.getSize());
                    listMap.put("fileid", fileid);
                    listMap.put("filesize", multipartFile.getSize());
                    
                    list.add(listMap);
                }
            }
		}//사용자커뮤니티 파일 저장
    	
    	if (type.equals("prf")) { // 업체실적
    		MultipartFile multipartFile = null;
    		MultipartFile multipartFile2 = null;
    		MultipartFile multipartFile3 = null;
            String originalFileName = null;
            String originalFileExtension = null;
            String storedFileName = null;
            String fileid = fileId;
            int filesn1 = 0;
            int filesn2 = 0;
            
            File file = new File(filePathImg);
            if(file.exists() == false){
                file.mkdirs();
            }
            while(iterator.hasNext()){
                multipartFile = multipartHttpServletRequest.getFile(iterator.next());
                //System.out.println("multipartFile.getName() : " + multipartFile.getName());
                String tmp_name = multipartFile.getName();
                String[] tmp_fname = tmp_name.split("_");
                if(tmp_fname[0].equals("file")) {	// 청소전
                	multipartFile2 = multipartFile;
                	if(multipartFile2.isEmpty() == false){
                        originalFileName = multipartFile2.getOriginalFilename();
                        originalFileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
                        storedFileName =now+CommonUtils.getRandomString() + originalFileExtension;
                        
                        file = new File(filePathImg + storedFileName);
                        multipartFile2.transferTo(file);
                         
                        listMap = new HashMap<String,Object>();
                       //파일필수칼럼
                      
                        System.out.println("originalFileName" + originalFileName);
                        listMap.put("pkidx", map.get("pkidx"));
                        listMap.put("realfilename", originalFileName);
                        listMap.put("filename", storedFileName);
                        listMap.put("filepath", filePathImg);
                        listMap.put("filesize", multipartFile2.getSize());
                        listMap.put("fileid", fileid);
                        listMap.put("filesn", filesn1);
                        listMap.put("cleanbeaf", "bef");
                        listMap.put("filesize", multipartFile2.getSize());
                        
                        list.add(listMap);
                    }
                	filesn1++;
                }else if(tmp_fname[0].equals("affile")) {  	// 청소후              	
                	multipartFile3 = multipartFile;
                	if(multipartFile3.isEmpty() == false){
                        originalFileName = multipartFile3.getOriginalFilename();
                        originalFileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
                        storedFileName =now+CommonUtils.getRandomString() + originalFileExtension;
                        
                        file = new File(filePathImg + storedFileName);
                        multipartFile3.transferTo(file);
                         
                        listMap = new HashMap<String,Object>();
                       //파일필수칼럼
                      
                        System.out.println("originalFileName" + originalFileName);
                        listMap.put("pkidx", map.get("pkidx"));
                        listMap.put("realfilename", originalFileName);
                        listMap.put("filename", storedFileName);
                        listMap.put("filepath", filePathImg);
                        listMap.put("filesize", multipartFile3.getSize());
                        listMap.put("fileid", fileid);
                        listMap.put("filesn", filesn2);
                        listMap.put("cleanbeaf", "af");
                        listMap.put("filesize", multipartFile3.getSize());
                        
                        list.add(listMap);
                    }
                	filesn2++;
                }             
            }
		}
    	
    	return list;
    }
    
    public static List<Map<String,Object>> parseInsertFileInfoBD(Map<String,Object> map, HttpServletRequest request, String type, String fileId, String fileSn) throws Exception{
    	MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest)request;
    	Iterator<String> iterator = multipartHttpServletRequest.getFileNames();
    	List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
    	Map<String, Object> listMap = null;
    	String now = new SimpleDateFormat("yyyyMMddHmsS").format(new Date());  //현재시간
    	
    	if (type.equals("uf")) {
    		MultipartFile multipartFile = null;
            String originalFileName = null;
            String originalFileExtension = null;
            String storedFileName = null;
            String fileid = fileId;
            
            File file = new File(filePath);
            if(file.exists() == false){
                file.mkdirs();
            }
            while(iterator.hasNext()){
                multipartFile = multipartHttpServletRequest.getFile(iterator.next());
                if(multipartFile.isEmpty() == false){
                    originalFileName = multipartFile.getOriginalFilename();
                    originalFileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
                    storedFileName =now+CommonUtils.getRandomString() + originalFileExtension;
                    
                    file = new File(filePath + storedFileName);
                    multipartFile.transferTo(file);
              
                    System.out.println("▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶");
                    System.out.println("filePath :: " + filePath);
                    System.out.println("storedFileName :: " + storedFileName);
                    System.out.println("file full path :: " + filePath+storedFileName);
                    
                    listMap = new HashMap<String,Object>();
                   //파일필수칼럼
                    listMap.put("realfilename", originalFileName);
                    listMap.put("filename", storedFileName);
                    listMap.put("filepath", filePath);
                    listMap.put("filesize", multipartFile.getSize());
                    listMap.put("fileid", fileid);
                    listMap.put("filesize", multipartFile.getSize());
                    
                    list.add(listMap);
                }
            }
		}//일반공지사항 파일 저장
    	
    	if (type.equals("pf")) {
    		MultipartFile multipartFile = null;
            String originalFileName = null;
            String originalFileExtension = null;
            String storedFileName = null;
            String fileid = fileId;
            
            File file = new File(filePath);
            if(file.exists() == false){
                file.mkdirs();
            }
            while(iterator.hasNext()){
                multipartFile = multipartHttpServletRequest.getFile(iterator.next());
                if(multipartFile.isEmpty() == false){
                    originalFileName = multipartFile.getOriginalFilename();
                    originalFileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
                    storedFileName =now+CommonUtils.getRandomString() + originalFileExtension;
                    
                    file = new File(filePath + storedFileName);
                    multipartFile.transferTo(file);
                     
                    listMap = new HashMap<String,Object>();
                   //파일필수칼럼
                    listMap.put("realfilename", originalFileName);
                    listMap.put("filename", storedFileName);
                    listMap.put("filepath", filePath);
                    listMap.put("filesize", multipartFile.getSize());
                    listMap.put("fileid", fileid);
                    listMap.put("filesize", multipartFile.getSize());
                    
                    list.add(listMap);
                }
            }
		}//파트너스공지사항 파일 저장
    	
    	if (type.equals("rf")) { // 리뷰, 커뮤니티
    		MultipartFile multipartFile = null;
            String originalFileName = null;
            String originalFileExtension = null;
            String storedFileName = null;
            String storedFileName2 = null;
            String fileid = fileId;
            String filesn = fileSn;
            String convertUUID = UUID.randomUUID().toString().replace("-", ""); // 해쉬명 생성
            //int filesn = 0;
            String newFileName = null;
            
            File file = new File(filePathImg);
            File newFileImg = null;
            Image tmpImg = null;
            BufferedImage tempImg = null;
            
            if(file.exists() == false){
                file.mkdirs();
            }
            while(iterator.hasNext()){
                multipartFile = multipartHttpServletRequest.getFile(iterator.next());
                if(multipartFile.isEmpty() == false){
                    originalFileName = multipartFile.getOriginalFilename();
                    originalFileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
                    storedFileName =now+CommonUtils.getRandomString() + originalFileExtension;
                    storedFileName2 =now+CommonUtils.getRandomString();
                    file = new File(filePathImg + storedFileName);
                    
                                                                                
                    multipartFile.transferTo(file);                    
                    resize(file.getPath());                    
                    // 이미지 리사이즈
                    /*
                    System.out.println("file : "+ file);
                    //tmpImg = ImageIO.read(new File(filePathImg + storedFileName));
                    tmpImg = ImageIO.read(file);
                    tempImg = resizeImage(tmpImg, 500, 300);
                    newFileName = filePathImg+storedFileName2+"_resize."+originalFileExtension;
                    newFileImg = new File(newFileName);
                    ImageIO.write(tempImg, originalFileExtension, newFileImg);
                    */
                    //multipartFile.transferTo(newFileImg);
                    
                    listMap = new HashMap<String,Object>();
                   //파일필수칼럼                  
                    System.out.println("fileUtils filesn : " + filesn);
                    
                    listMap.put("realfilename", originalFileName);
                    listMap.put("filename", storedFileName);
                    //listMap.put("filename", storedFileName2+"_resize."+originalFileExtension);
                    listMap.put("filepath", filePathImg);
                    listMap.put("filesize", multipartFile.getSize());
                    listMap.put("fileid", fileid);
                    listMap.put("filesize", multipartFile.getSize());
                    listMap.put("filesn", filesn);
                    list.add(listMap);
                    //filesn++;
                }
                
            }
		}//일반리뷰 파일 저장
    	if (type.equals("uvf")) {
    		
    		MultipartFile multipartFile = null;
    		String originalFileName = null;
    		String originalFileExtension = null;
    		String storedFileName = null;
    		String fileid = fileId;
    		int filesn = 0;
    		File file = new File(filePathVideo);
    		if(file.exists() == false){
    			file.mkdirs();
    		}
    		while(iterator.hasNext()){
    			multipartFile = multipartHttpServletRequest.getFile(iterator.next());
    			if(multipartFile.isEmpty() == false){
    				originalFileName = multipartFile.getOriginalFilename();
    				originalFileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
    				storedFileName = now+CommonUtils.getRandomString() + originalFileExtension;
    				
    				file = new File(filePathVideo + storedFileName);
    				multipartFile.transferTo(file);
    				
    				listMap = new HashMap<String,Object>();
    				//파일필수칼럼
    				listMap.put("uvidx", Integer.parseInt((String) map.get("uvidx")));
    				listMap.put("ptidx", Integer.parseInt((String) map.get("ptidx")));
    				listMap.put("fileid", fileid);
    				listMap.put("realfilename", originalFileName);
    				listMap.put("filename", storedFileName);
    				listMap.put("filepath", filePathVideo);
    				listMap.put("filesize", multipartFile.getSize());
    				listMap.put("filesn", filesn);
    				
    				//서브 칼럼
    				listMap.put("subject", (String)map.get("subject"));
    				list.add(listMap);
    				filesn++;
    			}
    		}
    	}//사용자커뮤니티 파일저장
    	if (type.equals("usercomu")) { // 사용자 커뮤니티
    		
    		
    		MultipartFile multipartFile = null;
            String originalFileName = null;
            String originalFileExtension = null;
            String storedFileName = null;
            String fileid = fileId;
            
            File file = new File(filePathImg);
            if(file.exists() == false){
                file.mkdirs();
            }
            while(iterator.hasNext()){
                multipartFile = multipartHttpServletRequest.getFile(iterator.next());
                if(multipartFile.isEmpty() == false){
                    originalFileName = multipartFile.getOriginalFilename();
                    originalFileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
                    storedFileName =now+CommonUtils.getRandomString() + originalFileExtension;
                    
                    file = new File(filePathImg + storedFileName);
                    multipartFile.transferTo(file);
                     
                    listMap = new HashMap<String,Object>();
                   //파일필수칼럼
                  
                    System.out.println("originalFileName" + originalFileName);
                    listMap.put("realfilename", originalFileName);
                    listMap.put("filename", storedFileName);
                    listMap.put("filepath", filePathImg);
                    listMap.put("filesize", multipartFile.getSize());
                    listMap.put("fileid", fileid);
                    listMap.put("filesize", multipartFile.getSize());
                    
                    list.add(listMap);
                }
            }
		}//사용자커뮤니티 파일 저장
    	
    	if (type.equals("ev")) { // 사용자가이드
    		MultipartFile multipartFile = null;
            String originalFileName = null;
            String originalFileExtension = null;
            String storedFileName = null;
            String fileid = fileId;
            
            File file = new File(filePathImg);
            if(file.exists() == false){
                file.mkdirs();
            }
            while(iterator.hasNext()){
                multipartFile = multipartHttpServletRequest.getFile(iterator.next());
                if(multipartFile.isEmpty() == false){
                    originalFileName = multipartFile.getOriginalFilename();
                    originalFileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
                    storedFileName =now+CommonUtils.getRandomString() + originalFileExtension;
                    
                    file = new File(filePathImg + storedFileName);
                    multipartFile.transferTo(file);
                     
                    listMap = new HashMap<String,Object>();
                   //파일필수칼럼
                  
                    System.out.println("originalFileName" + originalFileName);
                    listMap.put("realfilename", originalFileName);
                    listMap.put("filename", storedFileName);
                    listMap.put("filepath", filePathImg);
                    listMap.put("filesize", multipartFile.getSize());
                    listMap.put("fileid", fileid);
                    listMap.put("filesize", multipartFile.getSize());
                    
                    list.add(listMap);
                }
            }
		}//사용자가이드 파일 저장
    	
    	if (type.equals("inf")) { // 사용자 커뮤니티
    		MultipartFile multipartFile = null;
            String originalFileName = null;
            String originalFileExtension = null;
            String storedFileName = null;
            String fileid = fileId;
            
            File file = new File(filePathImg);
            if(file.exists() == false){
                file.mkdirs();
            }
            while(iterator.hasNext()){
                multipartFile = multipartHttpServletRequest.getFile(iterator.next());
                if(multipartFile.isEmpty() == false){
                    originalFileName = multipartFile.getOriginalFilename();
                    originalFileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
                    storedFileName =now+CommonUtils.getRandomString() + originalFileExtension;
                    
                    file = new File(filePathImg + storedFileName);
                    multipartFile.transferTo(file);
                     
                    listMap = new HashMap<String,Object>();
                   //파일필수칼럼
                  
                    System.out.println("originalFileName" + originalFileName);
                    listMap.put("realfilename", originalFileName);
                    listMap.put("filename", storedFileName);
                    listMap.put("filepath", filePathImg);
                    listMap.put("filesize", multipartFile.getSize());
                    listMap.put("fileid", fileid);
                    listMap.put("filesize", multipartFile.getSize());
                    
                    list.add(listMap);
                }
            }
		}//사용자커뮤니티 파일 저장
    	
    	return list;
    }
    
    // 이미지 리사이즈
    public static BufferedImage resizeImage(final Image image, int width, int height) {
    	
        final BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        final Graphics2D graphics2D = bufferedImage.createGraphics();
        graphics2D.setComposite(AlphaComposite.Src);
        //below three lines are for RenderingHints for better image quality at cost of higher processing time
        graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        graphics2D.setRenderingHint(RenderingHints.KEY_RENDERING,RenderingHints.VALUE_RENDER_QUALITY);
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        graphics2D.drawImage(image, 0, 0, width, height, null);
        graphics2D.dispose();
        return bufferedImage;
    }
    
    
	public static void resize(String path) {
		try {
			String imgOriginalPath= path;           // 원본 이미지 파일명   // 새 이미지 파일명
            String imgFormat = "jpg";                             // 새 이미지 포맷. jpg, gif 등
            int newWidth = 500;                                  // 변경 할 넓이
            int imageWidth;
            int imageHeight;
            double ratio;
            int w;
            int h;
            
            

         // 원본 이미지 가져오기
            Image image = ImageIO.read(new File(imgOriginalPath));
            imageWidth = image.getWidth(null);
            imageHeight = image.getHeight(null);
            
            
            ratio = (double)newWidth/(double)imageWidth;
            w = (int)(imageWidth * ratio);
            h = (int)(imageHeight * ratio);
            
            
            System.out.println("이미지 리사이즈 테스트 !!!!! ");
            // 이미지 리사이즈
            // Image.SCALE_DEFAULT : 기본 이미지 스케일링 알고리즘 사용
            // Image.SCALE_FAST    : 이미지 부드러움보다 속도 우선
            // Image.SCALE_REPLICATE : ReplicateScaleFilter 클래스로 구체화 된 이미지 크기 조절 알고리즘
            // Image.SCALE_SMOOTH  : 속도보다 이미지 부드러움을 우선
            // Image.SCALE_AREA_AVERAGING  : 평균 알고리즘 사용
             
            Image resizeImage = image.getScaledInstance(w, h, Image.SCALE_SMOOTH);
  
            // 새 이미지  저장하기
            BufferedImage newImage = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
            Graphics g = newImage.getGraphics();
            g.drawImage(resizeImage, 0, 0, null);
            g.dispose();
            ImageIO.write(newImage, imgFormat, new File(path));
            
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
