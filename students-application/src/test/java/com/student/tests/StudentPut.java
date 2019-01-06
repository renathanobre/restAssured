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
public class StudentPut extends TestBase{
	@Test
	public void updateStudent() {
		ArrayList<String> courses = new ArrayList<>();
		courses.add("Java");
		courses.add("C++");
		courses.add("C#");
		
		Student student = new Student();
		student.setFirstName("Mark");
		student.setLastName("Taylor");
		student.setEmail("xyz@gmail.com");
		student.setProgramme("Computer Science");
		student.setCourses(courses);
		
		given()
		.contentType(ContentType.JSON)
		.when()
		.body(student)
		.put("/101")
		.then()
		.statusCode(200);
		
	}
}
