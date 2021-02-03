package com.core.util;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class ImageResize {
	public static void imageResizeID(String[] args) {
		try {
			String imgOriginalPath= "C:/dev/file/test.jpg";           // 원본 이미지 파일명
            String imgTargetPath= "C:/dev/file/test_resize.jpg";    // 새 이미지 파일명
            String imgFormat = "jpg";                             // 새 이미지 포맷. jpg, gif 등
            int newWidth = 500;                                  // 변경 할 넓이
            int newHeigt = 700; 
            
         // 원본 이미지 가져오기
            Image image = ImageIO.read(new File(imgOriginalPath));
  
            // 이미지 리사이즈
            // Image.SCALE_DEFAULT : 기본 이미지 스케일링 알고리즘 사용
            // Image.SCALE_FAST    : 이미지 부드러움보다 속도 우선
            // Image.SCALE_REPLICATE : ReplicateScaleFilter 클래스로 구체화 된 이미지 크기 조절 알고리즘
            // Image.SCALE_SMOOTH  : 속도보다 이미지 부드러움을 우선
            // Image.SCALE_AREA_AVERAGING  : 평균 알고리즘 사용
             
            Image resizeImage = image.getScaledInstance(newWidth, newHeigt, Image.SCALE_SMOOTH);
  
            // 새 이미지  저장하기
            BufferedImage newImage = new BufferedImage(newWidth, newHeigt, BufferedImage.TYPE_INT_RGB);
            Graphics g = newImage.getGraphics();
            g.drawImage(resizeImage, 0, 0, null);
            g.dispose();
            ImageIO.write(newImage, imgFormat, new File(imgTargetPath));
            
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
