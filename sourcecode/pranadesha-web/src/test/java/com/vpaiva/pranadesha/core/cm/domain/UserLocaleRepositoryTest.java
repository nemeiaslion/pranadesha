/**
 * 
 */
package com.vpaiva.pranadesha.core.cm.domain;

import java.util.Locale;
import java.util.Set;
import java.util.TreeSet;

import org.junit.Test;

/**
 * @author vinic
 *
 */
public class UserLocaleRepositoryTest {
	@Test
	public void printAvailableLocales() {
		Locale[] locales = Locale.getAvailableLocales();
		Set<UserLocale> userLocales = new TreeSet<UserLocale>();
		for (Locale locale : locales) {
			userLocales.add(new UserLocale(locale.toString()));
		}
		for (UserLocale userLocale : userLocales) {
			System.out.println(userLocale);
		}
	}
}
