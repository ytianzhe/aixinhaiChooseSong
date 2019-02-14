package com.org.ixinhai.until;



import javax.crypto.spec.IvParameterSpec;
import javax.crypto.Cipher;  
import javax.crypto.spec.SecretKeySpec;  
  
public class AESCrypt {   

    public static String encrypt(String source, String key) throws Exception { 
         
        byte[] sourceBytes = source.getBytes("UTF-8");  
        byte[] keyBytes = key.getBytes("UTF-8"); 
        
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");   
        IvParameterSpec ivParameterSpec = new IvParameterSpec(keyBytes);
        cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(keyBytes, "AES"),ivParameterSpec);   
         
        byte[] decrypted = cipher.doFinal(sourceBytes);  
        return new sun.misc.BASE64Encoder().encode(decrypted);  
    }  
        
    public static String decrypt(String encryptStr, String key) throws Exception {  
        byte[] sourceBytes = new sun.misc.BASE64Decoder().decodeBuffer(encryptStr);  
        byte[] keyBytes = key.getBytes("UTF-8"); 
        
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");   
        IvParameterSpec ivParameterSpec = new IvParameterSpec(keyBytes); 
        cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(keyBytes, "AES"),ivParameterSpec); 
           
        byte[] decoded = cipher.doFinal(sourceBytes);    
        return new String(decoded, "UTF-8");  
    }        
      
    public static void main(String[] args) throws Exception {
    	//String key = "cf56a07f7cf753ca";
//    	String key = "a07fd996491b9c16";
//    	String source = "李云霞";
//    	String encryptStr = "HEtBpAbwTEeiOjUip9gRbw==";
//		System.out.println(encrypt(source, key));
//		System.out.println(decrypt(encryptStr, key));
//		//System.out.println(Base64Coder.decodeString("5aSo6JC95pez5bCQ5a2p44CCICAgICAgICAgICAgICAgICAg772e"));
//		String [] str = "1".split(",");
//		System.out.println(str[0]);
    	String name="tTjkavTqM6AQ59vTBovjlg==";
    	String name1="1ddYtzJX2e4XvWU9t+BuCQ==";
    	String mobile1="UouUSwo8cpZ4w3EEtTgrpQ==";
    	String key = "a07fd996491b9c16";
    	String trueName=AESCrypt.decrypt(mobile1, key);
    	System.out.println(trueName);
	}
}  
