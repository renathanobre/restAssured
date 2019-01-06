/**
 * Assertions in REST Assured using Hamcrest Library
 */
package com.assertions.examples;

import static io.restassured.RestAssured.given;

import org.junit.BeforeClass;
import org.junit.Test;


import static org.hamcrest.Matchers.*;

import io.restassured.RestAssured;

/**
 * @author Renata.Vieira
 *
 */
public class AssertionExamples {
	static final String APIKEY = "75e3u4sgb2khg673cbv2gjup";


	@BeforeClass
	public static void init() {

		RestAssured.baseURI="http://api.walmartlabs.com";
		RestAssured.basePath="/v1";
	}

	// 1) Verify if the number of items is equal to 10
	@Test
	public void test001() {
		given()
		.queryParam("query", "ipod")
		.queryParam("apiKey", APIKEY)
		.queryParam("format", "json")
		.when()
		.get("/search")
		.then()
		.body("numItems", equalTo(10));
	}

	// 2) Verify query
	@Test
	public void test002() {
		given()
		.queryParam("query", "ipod")
		.queryParam("apiKey", APIKEY)
		.queryParam("format", "json")
		.when()
		.get("/search")
		.then()
		.body("query", equalToIgnoringCase("IPOD"));
	}

	// 3) Check single name in ArrayList
	@Test
	public void test003() {
		given()
		.queryParam("query", "ipod")
		.queryParam("apiKey", APIKEY)
		.queryParam("format", "json")
		.when()
		.get("/search")
		.then()
		.body("items.name", hasItem("Apple iPod touch 32GB"));
	}

	// 4) Check Multiple name in ArrayList
	@Test
	public void test004() {
		given()
		.queryParam("query", "ipod")
		.queryParam("apiKey", APIKEY)
		.queryParam("format", "json")
		.when()
		.get("/search")
		.then()
		.body("items.name", hasItems("Apple iPod touch 32GB","Apple iPod touch 128GB"));
	}

	// 5) Check the gift options for the first product(Apple iPod touch 32GB)
	@Test
	public void test005() {
		given()
		.queryParam("query", "ipod")
		.queryParam("apiKey", APIKEY)
		.queryParam("format", "json")
		.when()
		.get("/search")
		.then()
		.body("items[0].giftOptions", hasKey("allowGiftWrap"));
	}

	// 6) Check hashmap values inside a list
	@SuppressWarnings("unchecked")
	@Test
	public void test006() {
		given()
		.queryParam("query", "ipod")
		.queryParam("apiKey", APIKEY)
		.queryParam("format", "json")
		.when()
		.get("/search")
		.then()
		.body("items.findAll{it.name=='Apple iPod touch 32GB'}", hasItems(hasEntry("name","Apple iPod touch 32GB")));
	}

	// 7) Checking multiple values in the same statement
	@Test
	public void test007() {
		given()
		.queryParam("query", "ipod")
		.queryParam("apiKey", APIKEY)
		.queryParam("format", "json")
		.when()
		.get("/search")
		.then()
		.body("items.name", hasItem("Apple iPod touch 32GB"))
		.body("items.name", hasItems("Apple iPod touch 32GB","Apple iPod touch 128GB"))
		.statusCode(200);
	}

	// 8) Logical Assertions
	@Test
	public void test08() {
		given()
		.queryParam("query", "ipod")
		.queryParam("apiKey", APIKEY)
		.queryParam("format", "json")
		.when()
		.get("/search")
		.then()
		.body("items.size()", equalTo(10))
		.body("items.size()", greaterThan(5))
		.body("items.size()", lessThan(11))
		.body("items.size()", greaterThanOrEqualTo(10))
		.body("items.size()", lessThanOrEqualTo(10));
	}
}
