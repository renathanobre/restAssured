/**
 * Filters in Rest-Assured
 */
package com.filter.examples;

import static io.restassured.RestAssured.given;

import java.io.PrintStream;
import java.io.StringWriter;

import org.apache.commons.io.output.WriterOutputStream;
import org.junit.Before;
import org.junit.Test;

import com.jayway.restassured.filter.log.ErrorLoggingFilter;
import com.student.base.TestBase;

import io.restassured.filter.Filter;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;

/**
 * @author Renata.Vieira
 *
 */
public class FilterExamples extends TestBase {

	public static StringWriter requestWriter;
	public static PrintStream requestCapture;
	
	public static StringWriter responseWriter;
	public static PrintStream responseCapture;
	
	public static StringWriter errorWriter;
	public static PrintStream errorCapture;

	@Before
	public void beforeEachTest(){
		requestWriter = new StringWriter();
		requestCapture = new PrintStream(new WriterOutputStream(requestWriter), true);
		
		responseWriter = new StringWriter();
		responseCapture = new PrintStream(new WriterOutputStream(responseWriter), true);
		
		errorWriter = new StringWriter();
		errorCapture = new PrintStream(new WriterOutputStream(errorWriter), true);
	}

	@Test
	public void getStudent(){
		given()
		.when()
		.get("/list")
		.asString();

		given()
		.filter(new RequestLoggingFilter(requestCapture))
		.filter(new ResponseLoggingFilter(responseCapture))
		.when()
		.get("/list");

		System.err.println(requestWriter.toString());
		System.err.println(responseWriter.toString());
		
		given()
		.filter((Filter) new ErrorLoggingFilter(errorCapture))
		.when()
		.get("/list");
		
		System.err.println(errorWriter.toString().toUpperCase());
	}
}
