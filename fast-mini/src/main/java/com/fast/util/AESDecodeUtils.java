package com.fast.util;

import java.security.spec.AlgorithmParameterSpec;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class AESDecodeUtils {
	
    public static String decrypt(byte[] key, byte[] iv, byte[] encData) throws Exception {  
        AlgorithmParameterSpec ivSpec = new IvParameterSpec(iv);  
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");  
        SecretKeySpec keySpec = new SecretKeySpec(key, "AES");  
        cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);  
        //解析解密后的字符串  
        return new String(cipher.doFinal(encData),"UTF-8");  
    }  

}
