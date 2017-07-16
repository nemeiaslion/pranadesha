package com.vpaiva.pranadesha.security;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PolicySecurity {
	
	public String hashPassword(String userPassword) throws NoSuchAlgorithmException {
	    SecureRandom secureRandom = new SecureRandom();
	    byte[] salt = new byte[4];
	    secureRandom.nextBytes(salt);
	    //
		Charset charset = Charset.forName("ISO-8859-1");
		byte[] input = userPassword.getBytes(charset);
		//
		MessageDigest md = MessageDigest.getInstance("SHA-1");
		md.reset();
		md.update(input);
		md.update(salt);
		byte[] hash = md.digest();
		//
	    byte[] hashPlusSalt = new byte[hash.length + salt.length];
	    System.arraycopy(hash, 0, hashPlusSalt, 0, hash.length);
	    System.arraycopy(salt, 0, hashPlusSalt, hash.length, salt.length);
	    //
	    StringBuilder sb = new StringBuilder();
	    sb.append("{SSHA}");
	    sb.append(Base64.getEncoder().encodeToString(hashPlusSalt));
	    return sb.toString();
	}

}
