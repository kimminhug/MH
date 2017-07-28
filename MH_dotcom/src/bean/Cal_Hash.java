package bean;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import com.*;

public class Cal_Hash {
	public void Set_Hash (member_VO vo) throws NoSuchAlgorithmException{
        SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
        byte[] salt_bytes = new byte[32];
        random.nextBytes(salt_bytes);
        
        String hash = vo.getHash();
        String salt = salt_bytes.toString();
        
        hash = hash + salt;		// 해쉬에 소금을 친다!
        
        System.out.println("salt : "+salt);
        
        try{
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash_bytes = digest.digest(hash.getBytes("UTF-8"));
            StringBuffer hexString = new StringBuffer();
 
            for (int i = 0; i < hash_bytes.length; i++) {
                String hex = Integer.toHexString(0xff & hash_bytes[i]);
                
                if(hex.length() == 1) hexString.append('0');
                	hexString.append(hex);
            }
 
            hash = hexString.toString();
            
            // System.out.println("변환된 해시 : "+hash);
        } catch(Exception ex){
            throw new RuntimeException(ex);
        }
        
        vo.setSalt(salt);
        vo.setHash(hash);		// 생성된 소금과 해쉬를 사용자 정보 테이블에 저장
    }
	
	public boolean Test_Hash (member_VO vo, String test_hash) {
		String hash = vo.getHash();
		test_hash = test_hash + vo.getSalt();
		
		try{
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash_bytes = digest.digest(test_hash.getBytes("UTF-8"));
            StringBuffer hexString = new StringBuffer();
 
            for (int i = 0; i < hash_bytes.length; i++) {
                String hex = Integer.toHexString(0xff & hash_bytes[i]);
                
                if(hex.length() == 1) hexString.append('0');
                	hexString.append(hex);
            }
 
            test_hash = hexString.toString();	// 변환된 테스트 해시
            
            System.out.println("<process 해시 테스트 >");
            System.out.println("변환된 테스트 해시 : "+test_hash);
            System.out.println("저장된 해시 : "+hash);
        } catch(Exception ex){
            throw new RuntimeException(ex);
        }
		
		if (hash.equals(test_hash)){
			System.out.println("테스트 결과 : 해쉬가 같습니다!!");
			return true;
		}else{
			System.out.println("테스트 결과 : 해쉬가 다릅니다!");
			return false;
		}
		
	}
}
