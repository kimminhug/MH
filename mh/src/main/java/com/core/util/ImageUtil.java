package com.core.util;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.PixelGrabber;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class ImageUtil {
	public static final int RATIO = 0;
    public static final int SAME = -1;
    
    public static void resize(File src, File dest, int width, int height, String formatName) throws IOException {
        Image srcImg = null;
        String suffix = src.getName().substring(src.getName().lastIndexOf('.')+1).toLowerCase();
        if (suffix.equals("bmp") || suffix.equals("png") || suffix.equals("gif")) {
            srcImg = ImageIO.read(src);
        } else {
            // BMP가 아닌 경우 ImageIcon을 활용해서 Image 생성
            // 이렇게 하는 이유는 getScaledInstance를 통해 구한 이미지를
            // PixelGrabber.grabPixels로 리사이즈 할때
            // 빠르게 처리하기 위함이다.
            srcImg = new ImageIcon(src.toURL()).getImage();
        }
        
        int srcWidth = srcImg.getWidth(null);
        int srcHeight = srcImg.getHeight(null);
        
        int destWidth = -1, destHeight = -1;
        
        if (width == SAME) {
            destWidth = srcWidth;
        } else if (width > 0) {
            destWidth = width;
        }
        
        if (height == SAME) {
            destHeight = srcHeight;
        } else if (height > 0) {
            destHeight = height;
        }
        
        if (width == RATIO && height == RATIO) {
            destWidth = srcWidth;
            destHeight = srcHeight;
        } else if (width == RATIO) {
            double ratio = ((double)destHeight) / ((double)srcHeight);
            destWidth = (int)((double)srcWidth * ratio);
        } else if (height == RATIO) {
            double ratio = ((double)destWidth) / ((double)srcWidth);
            destHeight = (int)((double)srcHeight * ratio);
        }
        
        Image imgTarget = srcImg.getScaledInstance(destWidth, destHeight, Image.SCALE_SMOOTH); 
        int pixels[] = new int[destWidth * destHeight]; 
        PixelGrabber pg = new PixelGrabber(imgTarget, 0, 0, destWidth, destHeight, pixels, 0, destWidth); 
        try {
            pg.grabPixels();
        } catch (InterruptedException e) {
            throw new IOException(e.getMessage());
        }
        BufferedImage destImg =null;
        if(formatName.toUpperCase().equals("PNG")){
        	destImg = new BufferedImage(destWidth, destHeight, BufferedImage.TYPE_INT_ARGB);	
        }else{
        	destImg = new BufferedImage(destWidth, destHeight, BufferedImage.TYPE_INT_RGB);
        }
        destImg.setRGB(0, 0, destWidth, destHeight, pixels, 0, destWidth); 
        
        ImageIO.write(destImg, formatName, dest);
    }


	public static void resizeTest() {
		try {
			String imgOriginalPath= "C:/dev/file/test.jpg";           // 원본 이미지 파일명
            String imgTargetPath= "C:/dev/file/test_resize.jpg";    // 새 이미지 파일명
            String imgFormat = "jpg";                             // 새 이미지 포맷. jpg, gif 등
            int newWidth = 500;                                  // 변경 할 넓이
            int newHeigt = 700; 
            
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

            ImageIO.write(newImage, imgFormat, new File(imgTargetPath));
            
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}