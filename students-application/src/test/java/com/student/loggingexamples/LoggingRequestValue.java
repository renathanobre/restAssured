/**
 * 
 */
package com.student.loggingexamples;

import com.jayway.restassured.http.ContentType;
import com.student.base.TestBase;
import com.student.model.Student;

import static com.jayway.restassured.RestAssured.*;

import java.util.ArrayList;

import org.junit.Test;

/**
 * @author Renata.Vieira
 *
 */
public class LoggingRequestValue extends TestBase {
	
	/**
	 * This test will print out all the request headers
	 */
	@Test
	public void test001() {
		System.out.println("----------------Printing Request Headers----------------");
		given()
		.log()
		.headers()
		.when()
		.get("/1")
		.then()
		.statusCode(200);
	}

	/**
	 * This test will print out all the request parameters
	 */
	@Test
	public void test002() {
		System.out.println("----------------Printing Request Parameters----------------");
		given()
		.param("programme","Computer Sciense")
		.param("limit",1)
		.log()
		.params()
		.when()
		.get("/list")
		.then()
		.statusCode(200);
	}
	
	/**
	 * This test will print out the request body
	 */
	@Test
	public void test003()  {
		System.out.println("----------------Printing Request Body----------------");
		
		ArrayList<String> courses = new ArrayList<>();
		courses.add("Java");
		courses.add("C++");
		
		Student student = new Student();
		student.setFirstName("Tej");
		student.setLastName("Hedge");
		student.setEmail("xyz@gmail.com");
		student.setProgramme("Computer Science");
		student.setCourses(courses);
		
		given()
		.contentType(ContentType.JSON)
		.log()
		.body()
		.when()
		.body(student)
		.post();
	}
	/**
	 * This test will print out All the details
	 */
	@Test
	public void test004()  {
		System.out.println("----------------Printing All the request details----------------");
		
		ArrayList<String> courses = new ArrayList<>();
		courses.add("Java");
		courses.add("C++");
		
		Student student = new Student();
		student.setFirstName("Tej");
		student.setLastName("Hedge");
		student.setEmail("xyz@gmail.com");
		student.setProgramme("Computer Science");
		student.setCourses(courses);
		
		given()
		.contentType(ContentType.JSON)
		.log()
		.all()
		.when()
		.body(student)
		.post();
		
	}
	
	/**
	 * This test will print request details if validation fails
	 */
	@Test
	public void test005()  {
		System.out.println("----------------Printing Request details if validation fails----------------");
		
		ArrayList<String> courses = new ArrayList<>();
		courses.add("Java");
		courses.add("C++");
		
		Student student = new Student();
		student.setFirstName("Tej");
		student.setLastName("Hedge");
		student.setEmail("abc@gmail.com");
		student.setProgramme("Computer Science");
		student.setCourses(courses);
		
		given()
		.contentType(ContentType.JSON)
		.log()
		.ifValidationFails()
		.when()
		.body(student)
		.post()
		.then()
		.statusCode(201);
	}
}
