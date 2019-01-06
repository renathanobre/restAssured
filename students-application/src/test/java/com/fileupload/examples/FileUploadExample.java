package com.fileupload.examples;

import static io.restassured.RestAssured.given;
import java.io.File;

import org.junit.Test;

/**
 * @author Renata.Vieira
 *
 */
public class FileUploadExample {

	/**
	 * upload a gif file to zamzar.com and convert it into a png file
	 */
	@Test
	public void uploadFileExample(){
		String apiKey = "09f511fbc5d3a5a579a87b9a9f14bf826265b1b3";

		File inputFile = new File(System.getProperty("user.dir")+ File.separator+"dancing_banana.gif");
		System.out.println(inputFile.length());
		
		String endpoint = "https://sandbox.zamzar.com/v1/jobs";

		given()
		.auth()
		.basic(apiKey,"")
		.multiPart("source_file",inputFile)
		.multiPart("target_format","png")
		.when()
		.post(endpoint)
		.then()
		.log()
		.all();


	}

}
