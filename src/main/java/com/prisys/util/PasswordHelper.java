package com.prisys.util;

import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

import com.prisys.entity.UserFormMap;

public class PasswordHelper {
	private RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();
	private String algorithmName = "md5";
	private int hashIterations = 2;

	public void encryptPassword(UserFormMap userFormMap) {
		String salt=randomNumberGenerator.nextBytes().toHex();
		userFormMap.put("credentialsSalt", salt);
		//String newPassword = new SimpleHash(algorithmName, userFormMap.get("password"), ByteSource.Util.bytes(userFormMap.get("accountName")+salt), hashIterations).toHex();
		String pwd=userFormMap.get("password").toString();
		System.out.println(pwd);
		String newPassword = null;
		try {
			newPassword = MD5Util.md5Encode(pwd);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		userFormMap.put("password", newPassword); 
		System.out.println(newPassword);
	}
	public static void main(String[] args) {
		PasswordHelper passwordHelper = new PasswordHelper();
		UserFormMap userFormMap = new UserFormMap();
		userFormMap.put("password","password");
		userFormMap.put("accountName","zzz");
		passwordHelper.encryptPassword(userFormMap);
		System.out.println(userFormMap);
	}
}
