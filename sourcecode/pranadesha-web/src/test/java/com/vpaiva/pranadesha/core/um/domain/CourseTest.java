/**
 * 
 */
package com.vpaiva.pranadesha.core.um.domain;

import org.junit.Test;

/**
 * @author vinicius
 *
 */
public class CourseTest {
	@Test
	public void comparator() {
		Course a = new Course("Curso Básico de Terapia Prânica");
		Course b = new Course("Curso Avançado de Terapia Prânica", a);
		Course c = new Course("Curso de Psicoterapia Prânica", b);
		org.junit.Assert.assertTrue("Courses not equal", c.getPrerequisite() == b);
	}

}
