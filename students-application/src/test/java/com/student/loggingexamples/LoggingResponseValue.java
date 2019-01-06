/**
 * 
 */
package com.student.loggingexamples;

import com.student.base.TestBase;

import static com.jayway.restassured.RestAssured.*;

import org.junit.Test;

/**
 * @author Renata.Vieira
 *
 */
public class LoggingResponseValue extends TestBase {
	
	
	/**
	 * This test will print out all the response headers
	 */
	@Test
	public void test001() {
		System.out.println("----------------Printing Response headers----------------");
		given()
		.param("programme","Computer Sciense")
		.param("limit",1)
		.when()
		.get("/list")
		.then()
		.log()
		.headers()
		.statusCode(200);
	}
	
	/**
	 * This test will print the response status line
	 */
	@Test
	public void test002() {
		System.out.println("----------------Printing Response status line----------------");
		given()
		.param("programme","Computer Science")
		.param("limit",1)
		.when()
		.get("/list")
		.then()
		.log()
		.status()
		.statusCode(200);
	}
	
	/**
	 * This test will print the response body
	 */
	@Test
	public void test003() {
		System.out.println("----------------Printing Response body----------------");
		given()
		.param("programme","Financial Analysis")
		.param("limit",1)
		.when()
		.get("/list")
		.then()
		.log()
		.body()
		.statusCode(200);
	}
	
	/**
	 * This test will print the response in case of an error 
	 */
	@Test
	public void test004() {
		System.out.println("----------------Printing Response in case of an error----------------");
		given()
		.param("programme","Financial Analysis")
		.param("limit",-1)
		.when()
		.get("/list")
		.then()
		.log()
		.ifError();
	}
}
