/**
 * 
 */
package com.vpaiva.pranadesha.core.cm.domain;

import java.util.Locale;
import java.util.Set;
import java.util.TimeZone;
import java.util.TreeSet;

import org.junit.Test;

/**
 * @author vinic
 *
 */
public class UserTimezoneRepositoryTest {
	@Test
	public void printAvailableTimezones() {
		Locale locale = Locale.getDefault();
		String[] ids = TimeZone.getAvailableIDs();
		Set<UserTimeZone> userTimeZones = new TreeSet<UserTimeZone>();
		for (String id : ids) {
			UserTimeZone userTimeZone = new UserTimeZone(id, locale);
			userTimeZones.add(userTimeZone);
		}
		for (UserTimeZone userTimeZone : userTimeZones) {
			System.out.println(userTimeZone);
		}
	}
}
