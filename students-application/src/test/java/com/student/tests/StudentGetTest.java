/**
 * 
 */
package com.student.tests;

import org.junit.Test;

import com.jayway.restassured.response.Response;
import com.student.base.TestBase;

import static com.jayway.restassured.RestAssured.*;


/**
 * @author Renata.Vieira
 *
 */
public class StudentGetTest extends TestBase{

	@Test
	public void getAllStudentsInformation() {
		/**
		 * given()
		 * set cookies, add auth, adding parameters, setting header info
		 * .when()
		 * GET, POST, PUT. DELETE.. etc
		 * .then()
		 * Validate status code, extract response, extract headers, cookies, extract the response body
		 * 
		 */
		Response response =given()
		.when()
		.get("/list");
		
		System.out.println(response.body().prettyPrint());
		
		//Validate the status code
		given()
		.when()
		.get("/list")
		.then()
		.statusCode(200);
	}
	
	@Test
	public void getStudentInfo() {
		Response response =given()
		.when()
		.get("/1");
		
		System.out.println(response.body().prettyPrint());
		
		given()
		.when()
		.get("/1")
		.then()
		.statusCode(200);	
	}
	
	@Test
	public void getStudentFromFA() {
		Response response = given()
		.when()
		.get("localhost:8085/student/list?programme=Financial Analysis&limit=2");
		
		System.out.println(response.body().prettyPeek());
		
		Response responseTwo =  given()
		.param("programme", "Financial Analysis")
		.param("limit",2)
		.when()
		.get("/list");
		
		System.out.println(responseTwo.body().prettyPeek());
		
	}
}
