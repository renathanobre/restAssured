/**
 * Sending SOAP Requests in Rest-Assured
 */
package com.soapws.examples;

import org.junit.Test;

import io.restassured.RestAssured;

/**
 * @author Renata.Vieira
 *
 */
public class HollidayWsExample {

	@Test
	public void getHolidays(){

		String wsdlUrl = "http://www.holidaywebservice.com//HolidayService_v2/HolidayService2.asmx?wsdl";
		String payload="<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:hs=\"http://www.holidaywebservice.com/HolidayService_v2/\">\r\n <soapenv:Body>\r\n <hs:GetHolidaysAvailable>\r\n <hs:countryCode>Canada</hs:countryCode>\r\n </hs:GetHolidaysAvailable>\r\n </soapenv:Body>\r\n</soapenv:Envelope>";

		RestAssured.given()
		.contentType("text/xml")
		.body(payload)
		.post(wsdlUrl)
		.then()
		.log()
		.all();

	}

}
