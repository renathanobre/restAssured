/**
 * JSONAssert Library for asserting on complete JSON Responses
 */
package com.jsonassert.examples;

import org.json.JSONException;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;

import io.restassured.RestAssured;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


/**
 * @author Renata.Vieira
 *
 */

public class JsonAssertExamples {

	@Test
	public void getStudents() throws IOException, JSONException{

		String expectedValue = new String(Files.readAllBytes((Paths.get(System.getProperty("user.dir")+
				File.separator+"file.txt"))));
		String actualValue= RestAssured.given()
				.when()
				.get("http://localhost:8085/student/list")
				.asString();
		
		System.out.println(expectedValue);
		System.out.println(actualValue);

		//old way to verify tests
		//Assert.assertEquals(expectedValue, actualValue);
		
		JSONAssert.assertEquals(expectedValue, actualValue, JSONCompareMode.LENIENT);
	}
	
	@Test
	public void getStudentsStrict() throws IOException, JSONException{

		String expectedValue = new String(Files.readAllBytes((Paths.get(System.getProperty("user.dir")+
				File.separator+"difforder.txt"))));
		String actualValue= RestAssured.given()
				.when()
				.get("http://localhost:8085/student/list")
				.asString();
		System.out.println(expectedValue);
		System.out.println(actualValue);

		JSONAssert.assertEquals(expectedValue, actualValue, JSONCompareMode.STRICT);
	}


}
