package com.vpaiva.pranadesha.core;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.vpaiva.pranadesha.core.um.domain.CourseTest;

@RunWith(Suite.class)
@SuiteClasses({ CourseTest.class, UnitA.class })
public class AllTests {

}
