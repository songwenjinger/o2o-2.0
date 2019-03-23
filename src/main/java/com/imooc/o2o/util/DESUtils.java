package com.imooc.o2o.util;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import java.security.Key;
import java.security.SecureRandom;

public class DESUtils {

	private static Key key;
	//密钥
	private static String KEY_STR = "myKey";
	private static String CHARSETNAME = "UTF-8";
	//使用java自带的des
	private static String ALGORITHM = "DES";

	static {
		try {
			//生成DES算法对象
			KeyGenerator generator = KeyGenerator.getInstance(ALGORITHM);
			//运用SHA1安全策略
			SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
			//设置上密钥种子
			secureRandom.setSeed(KEY_STR.getBytes());
			//初始化基于SHA1的算法对象
			generator.init(secureRandom);
			//生成密钥对象
			key = generator.generateKey();
			generator = null;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	//获取加密后的信息
	public static String getEncryptString(String str) {
		BASE64Encoder base64encoder = new BASE64Encoder();
		try {
			//获取string类型的字节组
			byte[] bytes = str.getBytes(CHARSETNAME);
			Cipher cipher = Cipher.getInstance(ALGORITHM);
			//初始化加密对象
			cipher.init(Cipher.ENCRYPT_MODE, key);
			byte[] doFinal = cipher.doFinal(bytes);
			return base64encoder.encode(doFinal);
		} catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}
	}
//调用base64进行解密

	public static String getDecryptString(String str) {
		BASE64Decoder base64decoder = new BASE64Decoder();
		try {
			byte[] bytes = base64decoder.decodeBuffer(str);
			Cipher cipher = Cipher.getInstance(ALGORITHM);
			cipher.init(Cipher.DECRYPT_MODE, key);
			byte[] doFinal = cipher.doFinal(bytes);
			return new String(doFinal, CHARSETNAME);
		} catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}
	}
	
	public static void main(String[] args) {
		System.out.println(getEncryptString("root"));
		System.out.println(getEncryptString("mysql"));
		/*System.out.println(getEncryptString("wxd7f6c5b8899fba83"));
		System.out.println(getEncryptString("665ae80dba31fc91ab6191e7da4d676d"));*/
	}

}