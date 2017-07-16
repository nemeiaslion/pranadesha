package com.vpaiva.pranadesha.security;

import java.security.NoSuchAlgorithmException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class PolicySecurityTest {
	
	private static String[] data = { "menininha", "123456" };
	
	@Parameters(name="test({index})")
	public static String[] data() {
		return data;
	}
	
	private String userPassword;
	
	public PolicySecurityTest(String userPassword) {
		this.userPassword = userPassword;
	}
	
	@Test()
	public void hash() throws NoSuchAlgorithmException {
		org.junit.Assert.assertTrue(userPassword != null);
		PolicySecurity policySecurity = new PolicySecurity();
		String hash = policySecurity.hashPassword(userPassword);
		System.out.printf("%s=%s%n", userPassword, hash);
	}

}
