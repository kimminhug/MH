package com.core.util;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.core.exception.BusinessException;

/**
 * 시스템      :
 * 단위 시스템 :
 * 프로그램 명 :
 * 파일명      : FileUtil.java
 * 설명        :
 */
/**
 * 이력사항
 * CH00 2012. 8. 3. Administrator 최초 작성
 */
@Component
public class FileUtil {

	@Autowired
	private Properties systemProp;
	
    public static final int BUFF_SIZE = 2048;

    private static final Logger logger = Logger.getLogger(FileUtil.class.getName());

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

	//디렉토리 존재 여부 체크 후 없으면 생성 처리
	private void createPathFolder(String path)throws Exception{
		File upldPathFolder = new File(path);
		if (!upldPathFolder.exists()) {
			upldPathFolder.mkdirs();
		}
	}

    /**
     * 첨부파일에 대한 목록 정보를 취득한다.
     * @param request
     * @param tblNm
     * @param upldPathDiv
     * @param upldPath
     * @return
     * @throws Exception
     */
    public List<Map<String, Object>> parseFileInf(HttpServletRequest request, String tblNm, String upldPathDiv, String upldPath, String atFileConnNo, int atFileSeqNo) throws Exception {
    	MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
    	
    	/*기존 로직은 첨부파일 리스트를 들고오지 못해 로직 수정 2014-06-18*/
    	
    	Map<String, MultipartFile> files = multipartRequest.getFileMap();				//  Request 로 넘어오는 파일정보를 모두 획득
    	Iterator<Entry<String, MultipartFile>> filebox = files.entrySet().iterator();	//  Request 로 넘어온것들 중에서 여러개로 나눠진 Input 박스를 나누어 답는다.
    	List<MultipartFile> fileList = new ArrayList<MultipartFile>();					//  File List를 담을 리스트를 생성한다.

    	while(filebox.hasNext())
    	{
    		//파일 박스 안에 있는 파일 들을 리스트로 담는다.
    		List<MultipartFile> FileNameInFileBox = multipartRequest.getFiles(filebox.next().getKey());
    		//사이드만큼 돈다
    		for(int i=0; i<FileNameInFileBox.size(); i++)
    		{
    			//리스트에 집어넣는다
    			fileList.add(FileNameInFileBox.get(i));
    		}
    	}


	    if (fileList.isEmpty()) {
	    	return null;
	    }

    	//매개변수 upldPath 값이 없다면 기본 저장 폴더로 지정
		if (StringUtil.nullValue(upldPath).equals("")) {
			if (StringUtil.nullValue(upldPathDiv).equals("") || StringUtil.nullValue(upldPathDiv).equals("file")) {
				upldPath = systemProp.getProperty("file.upload.path");
			} else if (StringUtil.nullValue(upldPathDiv).equals("image")) {
				upldPath = systemProp.getProperty("image.upload.path");
			}
		}

		//물리적인 저장 폴더가 없다면 저장 폴더 생성
		this.createPathFolder(upldPath);

		//20180307 저장 위치 아래에 /YYYY/MM/ 디렉토리를 생성 후 그 안에 저장하도록 변경
		GregorianCalendar gc = new GregorianCalendar();
		String yearPath = upldPath + String.valueOf(gc.get(Calendar.YEAR)) + "/";
		this.createPathFolder(yearPath);
		upldPath = yearPath +  String.format("%02d", gc.get(Calendar.MONTH) + 1) + "/";
		this.createPathFolder(upldPath);

		List<Map<String, Object>> result  = new ArrayList<Map<String, Object>>();

		if (StringUtil.nullValue(atFileConnNo).equals("") || StringUtil.nullValue(atFileConnNo).equals("0")) {
			atFileConnNo = System.currentTimeMillis() + "";
		}

		MultipartFile file;

		for(int u=0; u<fileList.size(); u++)
		{
			file = fileList.get(u);
		    String realFileNm = file.getOriginalFilename();
		    System.out.println(u + "  : file.getName() : " + file.getName());
		    String ext = FilenameUtils.getExtension(realFileNm);	//파일 확장자 추출

		    Random random = SecureRandom.getInstance("SHA1PRNG");
		    int intRandom = random.nextInt(100);
		    
		    String upldFileNm =  tblNm + "_" + atFileConnNo + atFileSeqNo + "_" + intRandom;

		    //첨부가 되지 않은 input file type
		    if ("".equals(realFileNm)) {
		    	continue;
		    }

		    String fileSize = file.getSize()+"";

		    if(file.getSize() <= 0){
		    	throw new BusinessException("Invalid file size : "+fileSize);
		    }

		    if (!"".equals(realFileNm)) {
				String saveFilePath = upldPath + upldFileNm;
				file.transferTo(new File(saveFilePath));

				//String fileType = realFileNm.substring(realFileNm.lastIndexOf(".")+1, realFileNm.length()).toLowerCase();
				//웹에디터 사진 등록 시...
				/*if(tblNm.equals("EDITOR") && fileType.equals("jpg")){
					InputStream fileInputStream = new FileInputStream(new File(saveFilePath));
					if(fileInputStream != null && fileInputStream.available() > 0){
						int orientation = this.getOrientation(fileInputStream);
						BufferedImage bufferedImage = null;
						if(orientation != 1) {
							//모바일폰(ios, android)에서 촬영한 사진을 업로드 했을때 업로드한 이미지가 90도 회전되어 보이는 현상이 있음.
							//사진의 방향 값을 정방향으로 변경하는 작업 추가(2019.06.24)
							bufferedImage = this.rotateImageForMobile(saveFilePath, orientation);
							ImageIO.write(bufferedImage, fileType, new File(saveFilePath));
						}
					}
				}*/
		    }

		    Map<String, Object> fileMap = new HashMap<String, Object>();
		    fileMap.put("ATFILE_CONN_NO", atFileConnNo+"");
		    fileMap.put("ATFILE_SEQ_NO", atFileSeqNo+"");
		    fileMap.put("TBL_NM", tblNm);
		    fileMap.put("REAL_FILE_NM", realFileNm);
		    fileMap.put("UPLD_FILE_NM", upldFileNm);
		    fileMap.put("UPLD_PATH", upldPath);
		    fileMap.put("EXT", ext);
		    fileMap.put("FILE_SIZE", fileSize);
		    fileMap.put("USE_YN", "Y");

		    setDtoBaseColumn(fileMap, request); //기본정보 셋팅
		    CommonUtil.mapInfo(fileMap);

		    result.add(fileMap);

		    atFileSeqNo++;
		}


		return result;
    }

    /**
     * 첨부파일에 대한 목록 정보를 취득한다.
     * @param request
     * @param tblNm
     * @param upldPathDiv
     * @param upldPath
     * @return
     * @throws Exception
     */
    public List<Map<String, Object>> parseMultiFileInf(HttpServletRequest request, String tblNm, String upldPathDiv, String upldPath, String atFileConnNo, int atFileSeqNo, String fileNamesParam) throws Exception {
    	MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
    	
    	Map<String, MultipartFile> files = multipartRequest.getFileMap();				//  Request 로 넘어오는 파일정보를 모두 획득
    	Iterator<Entry<String, MultipartFile>> filebox = files.entrySet().iterator();	//  Request 로 넘어온것들 중에서 여러개로 나눠진 Input 박스를 나누어 답는다.
    	List<MultipartFile> fileList = new ArrayList<MultipartFile>();					//  File List를 담을 리스트를 생성한다.
    	String paramKey = null;
    	
    	while(filebox.hasNext()){
    		String fileboxHasNextKey = filebox.next().getKey();
    		if(fileboxHasNextKey.equals(fileNamesParam)){
    			String splitNextKey[] = fileboxHasNextKey.split("_");
    			paramKey = splitNextKey[1];
    			//System.out.println("fileboxHasNextKey : " + fileboxHasNextKey);
	    		//파일 박스 안에 있는 파일 들을 리스트로 담는다.
	    		List<MultipartFile> FileNameInFileBox = multipartRequest.getFiles(fileboxHasNextKey);
	    		//사이드만큼 돈다
	    		for(int i=0; i<FileNameInFileBox.size(); i++){
	    			//리스트에 집어넣는다
	    			fileList.add(FileNameInFileBox.get(i));
	    		}
    		}
    	}
    	//System.out.println(paramKey + " fileList :: " + fileList);
    	if (fileList.isEmpty()) {
    		return null;
    	}
    	
    	//매개변수 upldPath 값이 없다면 기본 저장 폴더로 지정
    	if (StringUtil.nullValue(upldPath).equals("")) {
    		if (StringUtil.nullValue(upldPathDiv).equals("") || StringUtil.nullValue(upldPathDiv).equals("file")) {
    			upldPath = systemProp.getProperty("file.upload.path");
    		} else if (StringUtil.nullValue(upldPathDiv).equals("image")) {
    			upldPath = systemProp.getProperty("image.upload.path");
    		}
    	}
    	
    	//물리적인 저장 폴더가 없다면 저장 폴더 생성
    	this.createPathFolder(upldPath);
    	
    	//20180307 저장 위치 아래에 /YYYY/MM/ 디렉토리를 생성 후 그 안에 저장하도록 변경
    	GregorianCalendar gc = new GregorianCalendar();
    	String yearPath = upldPath + String.valueOf(gc.get(Calendar.YEAR)) + "/";
    	this.createPathFolder(yearPath);
    	upldPath = yearPath +  String.format("%02d", gc.get(Calendar.MONTH) + 1) + "/";
    	this.createPathFolder(upldPath);
    	
    	List<Map<String, Object>> result  = new ArrayList<Map<String, Object>>();
    	
    	if (StringUtil.nullValue(atFileConnNo).equals("") || StringUtil.nullValue(atFileConnNo).equals("0")) {
    		atFileConnNo = ( System.currentTimeMillis() + StringUtil.toInt(paramKey) ) + "";
    	}
    	//System.out.println("util atFileConnNo :: " + atFileConnNo);
    	MultipartFile file;
    	
    	for(int u=0; u<fileList.size(); u++)
    	{
    		file = fileList.get(u);
    		String realFileNm = file.getOriginalFilename();
    		//System.out.println(u + "  : file.getName() : " + file.getName());
    		String ext = FilenameUtils.getExtension(realFileNm);	//파일 확장자 추출
    		
    		Random random = SecureRandom.getInstance("SHA1PRNG");
    		int intRandom = random.nextInt(100);
    		
    		String upldFileNm =  tblNm + "_" + atFileConnNo + atFileSeqNo + "_" + intRandom;
    		
    		//첨부가 되지 않은 input file type
    		if ("".equals(realFileNm)) {
    			continue;
    		}
    		
    		String fileSize = file.getSize()+"";
    		
    		if(file.getSize() <= 0){
    			throw new BusinessException("Invalid file size : "+fileSize);
    		}
    		
    		if (!"".equals(realFileNm)) {
    			String saveFilePath = upldPath + upldFileNm;
    			file.transferTo(new File(saveFilePath));
    		}
    		
    		Map<String, Object> fileMap = new HashMap<String, Object>();
    		fileMap.put("ATFILE_KEY", paramKey);
    		fileMap.put("ATFILE_CONN_NO", atFileConnNo+"");
    		fileMap.put("ATFILE_SEQ_NO", atFileSeqNo+"");
    		fileMap.put("TBL_NM", tblNm);
    		fileMap.put("REAL_FILE_NM", realFileNm);
    		fileMap.put("UPLD_FILE_NM", upldFileNm);
    		fileMap.put("UPLD_PATH", upldPath);
    		fileMap.put("EXT", ext);
    		fileMap.put("FILE_SIZE", fileSize);
    		fileMap.put("USE_YN", "Y");
    		
    		setDtoBaseColumn(fileMap, request); //기본정보 셋팅
    		CommonUtil.mapInfo(fileMap);
    		
    		result.add(fileMap);
    		
    		atFileSeqNo++;
    	}
    	
    	
    	return result;
    }

    /**
     * 파일 다운로드
     * @param request
     * @param response
     * @throws Exception
     */
    public static void downloadFile(HttpServletRequest request, HttpServletResponse response, Map<String, Object> map) throws Exception {
    	File uFile = new File(StringUtil.nullValue(map.get("UPLD_PATH")), StringUtil.nullValue(map.get("UPLD_FILE_NM")));
	    int fSize = (int) uFile.length();

	    if (fSize > 0) {

	    	String fileNm = StringUtil.nullValue(map.get("REAL_FILE_NM"));
	    	String fileType = fileNm.substring(fileNm.lastIndexOf(".")+1, fileNm.length()).toLowerCase();

	    	// 2017.05.24 Mac 에서 한글 파일명 깨지고 .exe 확장자가 강제로 붙는 현상 때문에 변경
	    	// String mimetype = "application/x-msdownload";
	    	String mimetype = "application/octet-stream";
	    	//String mimetype = request.getSession(false).getServletContext().getMimeType(fileNm)

	    	if(fileType.equals("jpg")){
	    		mimetype = "image/jpeg";
	    	}else if(fileType.equals("gif")){
	    		mimetype = "image/gif";
	    	}else if(fileType.equals("bmp")){
	    		mimetype = "image/bmp";
	    	}else if(fileType.equals("png")){
	    		mimetype = "image/png";

	    	// 2017.05.24 Mac 에서 한글 파일명 깨지고 .exe 확장자가 강제로 붙는 현상 때문에 추가
	    	}else if(fileType.equals("pdf")){
	    		mimetype = "application/pdf";
	    	}else if(fileType.equals("hwp")){
	    		mimetype = "application/x-hwp";
	    	}else if(fileType.equals("doc")){
	    		mimetype = "application/msword";
	    	}else if(fileType.equals("xlsx")){
	    		mimetype = "application/vnd.ms-excel";
	    	}else if(fileType.equals("xls")){
	    		mimetype = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
	    	}else if(fileType.equals("ppt")){
	    		mimetype = "application/vnd.ms-powerpoint";
	    	}else if(fileType.equals("pptx")){
	    		mimetype = "application/vnd.openxmlformats-officedocument.presentationml.presentation";
	    	}

	    	response.setContentType(mimetype);

			//response.setHeader("Content-Disposition", "attachment; filename=\"" + URLEncoder.encode(fvo.getOrignlFileNm(), "utf-8") + "\"");
			setDisposition(fileNm, request, response);
			response.setContentLength(fSize);

			BufferedInputStream in = null;
			BufferedOutputStream out = null;

			try {
			    in = new BufferedInputStream(new FileInputStream(uFile));
			    out = new BufferedOutputStream(response.getOutputStream());
			    FileCopyUtils.copy(in, out);
			    out.flush();
			} catch (Exception ex) {
			    logger.debug("IGNORED: " + ex.getMessage());
			} finally {
			    if (in != null) {
					try {
					    in.close();
					} catch (Exception ignore) {
					    logger.debug("IGNORED: " + ignore.getMessage());
					}
			    }
			    if (out != null) {
					try {
					    out.close();
					} catch (Exception ignore) {
					    logger.debug("IGNORED: " + ignore.getMessage());
					}
			    }
			}
	    }
    }

    /**
     * 파일 다운로드
     * @param request
     * @param response
     * @throws Exception
     */
    public static void downloadFile2(HttpServletRequest request, HttpServletResponse response, Map<String, Object> map) throws Exception {
    	File uFile = new File(StringUtil.nullValue(map.get("UPLD_PATH")), StringUtil.nullValue(map.get("UPLD_FILE_NM")));
	    int fSize = (int) uFile.length();

	    if (fSize > 0) {

	    	String fileNm = StringUtil.nullValue(map.get("REAL_FILE_NM"));
	    	String fileType = fileNm.substring(fileNm.lastIndexOf(".")+1, fileNm.length()).toLowerCase();

	    	String mimetype = "image/jpeg";
	    	//String mimetype = request.getSession(false).getServletContext().getMimeType(fileNm)

	    	if(fileType.equals("jpg")){
	    		mimetype = "image/jpeg";
	    	}else if(fileType.equals("gif")){
	    		mimetype = "image/gif";
	    	}else if(fileType.equals("bmp")){
	    		mimetype = "image/bmp";
	    	}else if(fileType.equals("png")){
	    		mimetype = "image/png";
	    	}


	    	response.setContentType(mimetype);

			//response.setHeader("Content-Disposition", "attachment; filename=\"" + URLEncoder.encode(fvo.getOrignlFileNm(), "utf-8") + "\"");
			setDisposition(fileNm, request, response);
			response.setContentLength(fSize);

			BufferedInputStream in = null;
			BufferedOutputStream out = null;

			try {
			    in = new BufferedInputStream(new FileInputStream(uFile));
			    out = new BufferedOutputStream(response.getOutputStream());
			    FileCopyUtils.copy(in, out);
			    out.flush();
			} catch (Exception ex) {
			    logger.debug("IGNORED: " + ex.getMessage());
			} finally {
			    if (in != null) {
					try {
					    in.close();
					} catch (Exception ignore) {
					    logger.debug("IGNORED: " + ignore.getMessage());
					}
			    }
			    if (out != null) {
					try {
					    out.close();
					} catch (Exception ignore) {
					    logger.debug("IGNORED: " + ignore.getMessage());
					}
			    }
			}
	    }
    }

    /**
     * TEXT 파일 다운로드
     * @param request
     * @param response
     * @throws Exception
     */
    public static void downloadTextFile(String fileName, String[] keys, String div, List data, HttpServletRequest request, HttpServletResponse response) throws Exception {

    	//파일명이 없으면
    	if(StringUtil.nullValue(fileName).equals("")){
    		fileName = "file.txt";
    	}

    	//데이터가 널이거나, 없으면
    	if(data == null || 0 == data.size()){
    		return;
    	}

    	//구분자
    	div = StringUtil.nullValue(div);

//    	String mimetype = "application/x-msdownload";
//    	response.setContentType(mimetype);

    	setDisposition(fileName, request, response);

    	PrintWriter pw = null;

    	Iterator iter = data.iterator();
    	Map map = null;
    	Iterator keyIter = null;

    	int i=0;
    	int length = 0;

    	try {

    		pw = response.getWriter();

    		//키값이 있으면
    		if(keys != null){
    			length = keys.length;
    			while(iter.hasNext()){
			    	map = (Map)iter.next();
			    	for(i=0; i < length; i++){
			    		pw.write(StringUtil.nullValue(map.get(keys[i])));
			    		if(i != (length-1)) pw.write(div);
			    	}
			    	pw.println();
			    }
    		}else{
    			while(iter.hasNext()){
			    	map = (Map)iter.next();
			    	keyIter = map.keySet().iterator();
			    	while(keyIter.hasNext()){
			    		pw.write(StringUtil.nullValue(map.get(keyIter.next())));
			    		if(keyIter.hasNext()) pw.write(div);
			    	}
			    	pw.println();
			    }
    		}

		    pw.flush();
    	}catch(Exception ex){
    		logger.debug("IGNORED: " + ex.getMessage());
    	}
    }

    /**
     * 접속 브라우저별 파일 인코딩
     * @param filename
     * @param request
     * @param response
     * @throws Exception
     */
    private static void setDisposition(String filename, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String browser = getBrowser(request);
		String dispositionPrefix = "attachment; filename=";
		String encodedFilename = null;

		if (browser.equals("MSIE")) {
		    encodedFilename = URLEncoder.encode(filename, "UTF-8").replaceAll("\\+", "%20");
		} else if (browser.equals("Firefox")) {
		    encodedFilename = "\"" + new String(filename.getBytes("UTF-8"), "8859_1") + "\"";
		} else if (browser.equals("Opera")) {
		    encodedFilename = "\"" + new String(filename.getBytes("UTF-8"), "8859_1") + "\"";
		} else if (browser.equals("Safari")) {	// 20170525 Mac safari
			encodedFilename = "\"" + new String(filename.getBytes("UTF-8"), "UTF-8") + "\"";
		} else if (browser.equals("Chrome")) {
		    StringBuffer sb = new StringBuffer();
		    for (int i = 0; i < filename.length(); i++) {
			char c = filename.charAt(i);
			if (c > '~') {
			    sb.append(URLEncoder.encode("" + c, "UTF-8"));
			} else {
			    sb.append(c);
			}
		    }
		    //encodedFilename = sb.toString();
		    //20170706 크롬 다운로드 헤더 오류 증상 수정
		    encodedFilename = "\"" + sb.toString() + "\"";

		} else {
		    throw new IOException("Not supported browser");
		}

		response.setHeader("Content-Disposition", dispositionPrefix + encodedFilename);

		if ("Opera".equals(browser)){
		    response.setContentType("application/octet-stream;charset=UTF-8");
		}
    }

    /**
     * 접속 브라우저 체크
     * @param request
     * @return
     */
    private static String getBrowser(HttpServletRequest request) {
        String header = request.getHeader("User-Agent");
        if (header.indexOf("MSIE") > -1 || header.indexOf("Trident") > -1 || header.indexOf("rv") > -1) {
            return "MSIE";
        } else if (header.indexOf("Chrome") > -1) {
            return "Chrome";
        } else if (header.indexOf("Opera") > -1) {
            return "Opera";
	    } else if (header.indexOf("Safari") > -1) {	// 20170524 cjh
	    	return "Safari";
	    }

        return "Firefox";
    }

    /**
     * 파일 패스 가져오기
     * @return
     * @throws Exception
     */
    public String getFilePath() throws Exception {
    	return systemProp.getProperty("file.upload.path");
    }


    public static BufferedImage rotateImageForMobile(String imageFilePath, int orientation) throws IOException {

        File f = new File(imageFilePath);
        BufferedImage bi = ImageIO.read(f);

        if(orientation == 6){ //정위치
        	BufferedImage newImage = rotateImage(bi, 90);
        	if(newImage != null){
        		bi = newImage;
			}
        } else if (orientation == 1){ //왼쪽으로 눞였을때
            return bi;
        } else if (orientation == 3){//오른쪽으로 눞였을때
        	BufferedImage newImage = rotateImage(bi, 180);
            if(newImage != null){
        		bi = newImage;
			}
        } else if (orientation == 8){//180도
        	BufferedImage newImage = rotateImage(bi, 270);
            if(newImage != null){
        		bi = newImage;
			}
        }

        return bi;
    }

    public static BufferedImage rotateImage(BufferedImage orgImage,int radians) {
        BufferedImage newImage;
        if(radians==90 || radians==270){
            newImage = new BufferedImage(orgImage.getHeight(),orgImage.getWidth(),orgImage.getType());
        } else if (radians==180){
            newImage = new BufferedImage(orgImage.getWidth(),orgImage.getHeight(),orgImage.getType());
        } else{
            return orgImage;
        }
        Graphics2D graphics = (Graphics2D) newImage.getGraphics();
        graphics.rotate(Math. toRadians(radians), newImage.getWidth() / 2, newImage.getHeight() / 2);
        graphics.translate((newImage.getWidth() - orgImage.getWidth()) / 2, (newImage.getHeight() - orgImage.getHeight()) / 2);
        graphics.drawImage(orgImage, 0, 0, orgImage.getWidth(), orgImage.getHeight(), null );

        return newImage;
    }

}
