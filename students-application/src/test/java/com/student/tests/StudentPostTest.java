/**
 * 
 */
package com.student.tests;

import static com.jayway.restassured.RestAssured.*;

import java.util.ArrayList;

import org.junit.Test;

import com.jayway.restassured.http.ContentType;
import com.student.base.TestBase;
import com.student.model.Student;




/**
 * @author Renata.Vieira
 *
 */
public class StudentPostTest extends TestBase {
	@Test
	public void creatNewStudent()  {
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
		.when()
		.body(student)
		.post()
		.then()
		.statusCode(201);
		
	}
}
