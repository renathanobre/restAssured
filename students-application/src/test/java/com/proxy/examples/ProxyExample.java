/**
 * Requests via ProxyServer
 */
package com.proxy.examples;

import org.junit.BeforeClass;
import org.junit.Test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.ProxySpecification;
import io.restassured.specification.RequestSpecification;

/**
 * @author Renata.Vieira
 *
 */
public class ProxyExample {

	public static RequestSpecBuilder rspec;
	public static RequestSpecification rp;

	@BeforeClass
	public static void Init(){
		ProxySpecification ps = new ProxySpecification("localhost", 5555, "http");
		RestAssured.baseURI="http://localhost:8080/student";
		RestAssured.proxy(ps);

		rspec = new RequestSpecBuilder();
		rspec.setProxy(ps);

		rp = rspec.build();
	}

	@Test
	public void sendRequest(){
		//ProxySpecification ps = new ProxySpecification("localhost", 5555, "http");

		RestAssured.given()
		.spec(rp)
		.when().get("/list")
		.then()
		.log()
		.body();
	}


}
