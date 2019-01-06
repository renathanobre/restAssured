package com.filedownload.examples;

import static io.restassured.RestAssured.given;
import java.io.File;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;


/**
 * @author Renata.Vieira
 *
 */
public class FileDownloadExample {

	//
	
	/**
	 * Download a file & compare it with an Expected file
	 * Check the size of the file
	 */
	@Test
	public void verifyDownloadedFile(){
		//Read the input file
		File inputFile = new File(System.getProperty("user.dir")+ File.separator+"geckodriver-v0.11.1-arm7hf.tar.gz");

		//Length of input file
		int expectedSize = (int)inputFile.length();

		//1,708,237 bytes
		System.out.print("The size of the expected file is : " + expectedSize + "  bytes");

		//https://github.com/mozilla/geckodriver/releases/download/v0.11.1/geckodriver-v0.11.1-arm7hf.tar.gz

		//Read Downdloaded file
		byte[] actualValue = given()
		.when()
		.get("http://github.com/mozilla/geckodriver/releases/download/v0.11.1/geckodriver-v0.11.1-arm7hf.tar.gz")
		.then()
		.extract()
		.asByteArray();
		
		System.out.println("The size of the actual file is : " + actualValue.length + "  bytes");
		
		assertThat(expectedSize,equalTo(actualValue.length));
		
	}

}
