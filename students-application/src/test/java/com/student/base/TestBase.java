/**
 * 
 */
package com.student.base;

import org.junit.BeforeClass;

import com.jayway.restassured.RestAssured;

/**
 * @author Renata.Vieira
 *
 */
public class TestBase {

	@BeforeClass
    public static void init() {
		
		RestAssured.baseURI="http://localhost";
		RestAssured.port=8085;
		RestAssured.basePath="student";
	}
}
