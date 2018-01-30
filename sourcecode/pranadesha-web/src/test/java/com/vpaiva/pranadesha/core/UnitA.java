/**
 * 
 */
package com.vpaiva.pranadesha.core;

import java.text.FieldPosition;
import java.text.MessageFormat;
import java.util.Date;

import org.junit.Test;

/**
 * @author vinicius
 *
 */
public class UnitA {
	
	@Test
	public void caseA() {
		org.junit.Assert.assertTrue("Not true", false);
	}
	
	@Test
	public void caseB() {
		String pattern = "text1={0};text2={1};date1={2, date, short}";
		MessageFormat mf = new MessageFormat(pattern);
		StringBuffer sb = new StringBuffer();
		mf.format(new Object[] { "a" , "b", new Date() }, sb, new FieldPosition(0));
		System.out.println(sb.toString());
	}

}
