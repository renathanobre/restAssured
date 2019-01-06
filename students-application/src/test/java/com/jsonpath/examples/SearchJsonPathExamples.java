/**
 * 
 */
package com.jsonpath.examples;

import org.junit.BeforeClass;
import org.junit.Test;

import com.jayway.restassured.RestAssured;
import static com.jayway.restassured.RestAssured.*;

import java.util.HashMap;
import java.util.List;

/**
 * @author Renata.Vieira
 *
 */
public class SearchJsonPathExamples {
	static final String APIKEY = "75e3u4sgb2khg673cbv2gjup";


	@BeforeClass
	public static void init() {

		RestAssured.baseURI="http://api.walmartlabs.com";
		RestAssured.basePath="/v1";
	}

	// 1) Extract numItems
	@Test
	public void test001() {

		int numItems =	given()
				.queryParam("query", "ipod")
				.queryParam("apiKey", APIKEY)
				.queryParam("format", "json")
				.when()
				.get("/search")
				.then().extract().path("numItems");	


		System.out.println("----------------Stargint Test----------------");
		System.out.println("The total number of items are: " + numItems);
		System.out.println("----------------End of Test----------------");
	}


	// 2) Extract query
	@Test
	public void test002() {

		String queryvalue =	given()
				.queryParam("query", "ipod")
				.queryParam("apiKey", APIKEY)
				.queryParam("format", "json")
				.when()
				.get("/search")
				.then().extract().path("query");	

		System.out.println("----------------Stargint Test----------------");
		System.out.println("The search query : " + queryvalue);
		System.out.println("----------------End of Test----------------");
	}


	// 3) Extract first productName by providing list index value
	@Test
	public void test003() {

		String  productName =	given()
				.queryParam("query", "ipod")
				.queryParam("apiKey", APIKEY)
				.queryParam("format", "json")
				.when()
				.get("/search")
				.then().extract().path("items[0].name");	

		System.out.println("----------------Stargint Test----------------");
		System.out.println("The product name is : " + productName);
		System.out.println("----------------End of Test----------------");
	}

	// 4) Extract the gift options for the first product
	@Test
	public void test004() {

		HashMap<String,String> giftOptions =	given()
				.queryParam("query", "ipod")
				.queryParam("apiKey", APIKEY)
				.queryParam("format", "json")
				.when()
				.get("/search")
				.then().extract().path("items[0].giftOptions");	

		System.out.println("----------------Stargint Test----------------");
		System.out.println("The gift options under the first product are : " + giftOptions);
		System.out.println("----------------End of Test----------------");
	}

	// 5) Print the size of items
	@Test
	public void test005() {

		int  sizeItems =	given()
				.queryParam("query", "ipod")
				.queryParam("apiKey", APIKEY)
				.queryParam("format", "json")
				.when()
				.get("/search")
				.then().extract().path("items.size()");	

		System.out.println("----------------Stargint Test----------------");
		System.out.println("The size of the items are : " + sizeItems);
		System.out.println("----------------End of Test----------------");
	}


	// 6) Get all the names
	@Test
	public void test006() {

		List<String>  names =	given()
				.queryParam("query", "ipod")
				.queryParam("apiKey", APIKEY)
				.queryParam("format", "json")
				.when()
				.get("/search")
				.then().extract().path("items.name");	

		System.out.println("----------------Stargint Test----------------");
		System.out.println("The name of the products are : " + names);
		System.out.println("----------------End of Test----------------");
	}

	// 7) Get all the values for Name==Apple iPod touch 32GB
	@Test
	public void test007() {

		List<HashMap<String,Object>>  values =	given()
				.queryParam("query", "ipod")
				.queryParam("apiKey", APIKEY)
				.queryParam("format", "json")
				.when()
				.get("/search")
				.then().extract().path("items.findAll{it.name=='Apple iPod touch 32GB'}");	

		System.out.println("----------------Stargint Test----------------");
		System.out.println("The values for item name Apple iPod touch 32GB : " + values);
		System.out.println("----------------End of Test----------------");
	}

	// 8) Get the sale price for Name==Apple iPod touch 32GB
	@Test
	public void test008() {

		List<Float>  salePrice =	given()
				.queryParam("query", "ipod")
				.queryParam("apiKey", APIKEY)
				.queryParam("format", "json")
				.when()
				.get("/search")
				.then().extract().path("items.findAll{it.name=='Apple iPod touch 32GB'}.salePrice");	

		System.out.println("----------------Stargint Test----------------");
		System.out.println("The sale price is: " + salePrice);
		System.out.println("----------------End of Test----------------");
	}

	// 9) Get the Names which have salePrice less than 150
	@Test
	public void test009() {

		List<String>  names =	given()
				.queryParam("query", "ipod")
				.queryParam("apiKey", APIKEY)
				.queryParam("format", "json")
				.when()
				.get("/search")
				.then().extract().path("items.findAll{it.salePrice<150}.name");	

		System.out.println("----------------Stargint Test----------------");
		System.out.println("The items that are priced less than $150 are: " + names);
		System.out.println("----------------End of Test----------------");
	}

	// 10) Get the msrp of items that Start with name = Apple
	@Test
	public void test010() {

		List<String>  msrpValues =	given()
				.queryParam("query", "ipod")
				.queryParam("apiKey", APIKEY)
				.queryParam("format", "json")
				.when()
				.get("/search")
				.then().extract().path("items.findAll{it.name==~/Apple.*/}.msrp");	

		System.out.println("----------------Stargint Test----------------");
		System.out.println("The msrp of items that start with Apple are: " + msrpValues);
		System.out.println("----------------End of Test----------------");
	}

	// 11) Get the salePrice of items that End with name = GB
	@Test
	public void test011() {

		List<String>  saleprice =	given()
				.queryParam("query", "ipod")
				.queryParam("apiKey", APIKEY)
				.queryParam("format", "json")
				.when()
				.get("/search")
				.then().extract().path("items.findAll{it.name==~/.*GB/}.salePrice");	

		System.out.println("----------------Stargint Test----------------");
		System.out.println("The salePrice of items that End with  GB are : " + saleprice);
		System.out.println("----------------End of Test----------------");
	}
}
