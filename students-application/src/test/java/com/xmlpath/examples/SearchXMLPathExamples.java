/**
 * 
 */
package com.xmlpath.examples;

import org.junit.BeforeClass;
import org.junit.Test;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.internal.path.xml.NodeChildrenImpl;

import static com.jayway.restassured.RestAssured.*;
import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.path.xml.XmlPath.*;

import java.util.List;


/**
 * @author Renata.Vieira
 *
 */
public class SearchXMLPathExamples {

	static final String APIKEY = "75e3u4sgb2khg673cbv2gjup";


	@BeforeClass
	public static void init() {

		RestAssured.baseURI="http://api.walmartlabs.com";
		RestAssured.basePath="/v1";
	}

	// 1) Extract numItems
	@Test
	public void test001() {

		String numItems =	given()
				.queryParam("query", "ipod")
				.queryParam("apiKey", APIKEY)
				.queryParam("format", "xml")
				.when()
				.get("/search")
				.then().extract().path("searchresponse.numItems");	


		System.out.println("----------------Stargint Test----------------");
		System.out.println("The total number of items are: " + numItems);
		System.out.println("----------------End of Test----------------");
	}

	// 2) Extract first productName by providing list index value
	@Test
	public void test002() {

		String productName =	given()
				.queryParam("query", "ipod")
				.queryParam("apiKey", APIKEY)
				.queryParam("format", "xml")
				.when()
				.get("/search")
				.then().extract().path("searchresponse.items.item[0].name");	


		System.out.println("----------------Stargint Test----------------");
		System.out.println("The product name is: " + productName);
		System.out.println("----------------End of Test----------------");
	}

	// 3) Extract the gift options for the first product
	@Test
	public void test003() {

		String xml  =	given()
				.queryParam("query", "ipod")
				.queryParam("apiKey", APIKEY)
				.queryParam("format", "xml")
				.when()
				.get("/search").asString();

		String giftOptions  =	with(xml).getString("searchresponse.items.item[0].giftOptions");			


		System.out.println("----------------Stargint Test----------------");
		System.out.println("The gift options under the first product are : " + giftOptions);
		System.out.println("----------------End of Test----------------");
	}

	// 4) Get the size of items
	@Test
	public void test004() {
		NodeChildrenImpl val =	given()
				.queryParam("query", "ipod")
				.queryParam("apiKey", APIKEY)
				.queryParam("format", "xml")
				.when()
				.get("/search")
				.then().extract().path("searchresponse.items.item");	

		System.out.println("----------------Stargint Test----------------");
		System.out.println("The size of the items are : " + val.size());
		System.out.println("----------------End of Test----------------");
	}

	// 5) Get all the names
	@Test
	public void test005() {
		String xml  =	given()
				.queryParam("query", "ipod")
				.queryParam("apiKey", APIKEY)
				.queryParam("format", "xml")
				.when()
				.get("/search").asString();

		List<String>  names =	with(xml).getList("searchresponse.items.item.name");	

		System.out.println("----------------Stargint Test----------------");
		System.out.println("The name of the products are : " + names);
		System.out.println("----------------End of Test----------------");
	}

	// 6) Get the sale price for Name==Apple iPod touch 32GB
	@Test
	public void test006() {
		String xml  =	given()
				.queryParam("query", "ipod")
				.queryParam("apiKey", APIKEY)
				.queryParam("format", "xml")
				.when()
				.get("/search").asString();

		List<String> salePrice= with(xml).getList("searchresponse.items.item.findAll{it.name=='Apple iPod touch 32GB'}.salePrice");	

		System.out.println("----------------Stargint Test----------------");
		System.out.println("The sale price is: " + salePrice);
		System.out.println("----------------End of Test----------------");
	}

	// 7) Deep search  in XML Path
	@Test
	public void test007() {
		String xml  =	given()
				.queryParam("query", "ipod")
				.queryParam("apiKey", APIKEY)
				.queryParam("format", "xml")
				.when()
				.get("/search").asString();

		List<String> salePrice= with(xml).getList("**.findAll{it.name=='Apple iPod touch 32GB'}.salePrice");	

		System.out.println("----------------Stargint Test----------------");
		System.out.println("The sale price is: " + salePrice);
		System.out.println("----------------End of Test----------------");
	}
}
