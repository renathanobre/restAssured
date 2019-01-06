/**
 * Writing Tests using REST Assured
 */
package com.student.tests;

import static com.jayway.restassured.RestAssured.given;

import org.junit.Test;

import com.student.base.TestBase;

/**
 * @author Renata.Vieira
 *
 */
public class StudentDelete extends TestBase {
	@Test
	public void deleteStudent() {
		given()
		.when()
		.delete("/101")
		.then()
		.statusCode(204);
	}
}
