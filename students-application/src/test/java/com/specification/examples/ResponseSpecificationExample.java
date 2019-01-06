/**
 * 
 */
package com.specification.examples;

import static io.restassured.RestAssured.given;

import org.junit.BeforeClass;
import org.junit.Test;

import io.restassured.builder.*;

import io.restassured.RestAssured;
import io.restassured.specification.*;

import static org.hamcrest.Matchers.*;


/**
 * @author Renata.Vieira
 *
 */
public class ResponseSpecificationExample {
	static final String APIKEY = "75e3u4sgb2khg673cbv2gjup";
	static RequestSpecBuilder builder;
	static RequestSpecification requestSpec;
	static ResponseSpecBuilder responseBuilder;
	static ResponseSpecification responseSpec;

	@BeforeClass
	public static void init() {

		RestAssured.baseURI="http://api.walmartlabs.com";
		RestAssured.basePath="/v1";
		
		builder = new RequestSpecBuilder();
		builder.addQueryParam("query", "ipod");
		builder.addQueryParam("apiKey", APIKEY);
		builder.addQueryParam("format", "json");
		builder.addQueryParam("facet","on");
		builder.addHeader("Accept","*/*");
		
		requestSpec = builder.build();
		
		responseBuilder = new ResponseSpecBuilder();
		responseBuilder.expectHeader("Content-Type", "application/json; charset=utf-8");
		responseBuilder.expectHeader("Server", "Mashery Proxy");
		responseBuilder.expectStatusCode(200);
		responseBuilder.expectBody("query", equalTo("ipod"));
		responseBuilder.expectBody("numItems", equalTo(10));
		responseBuilder.expectBody("items.name", hasItem("Apple iPod touch 32GB"));
		
		responseSpec = responseBuilder.build();
		
	}

	@Test
	public void test001() {

		given()
		.spec(requestSpec)
		.when()
		.get("/search")
		.then()
		.spec(responseSpec);
	}
	
	@Test
	public void test002() {

		given()
		.spec(requestSpec)
		.when()
		.get("/search")
		.then()
		.spec(responseSpec)
		.body("items.name", hasItem("Apple iPod nano 16GB"));
	}
}
