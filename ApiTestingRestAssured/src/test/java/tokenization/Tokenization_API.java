package tokenization;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;

public class Tokenization_API {

	private Map<String, Object> requestData;
	String tokenId;

	@BeforeMethod
	void setup() {
		
		requestData = new HashMap<>();
		requestData.put("customerId", "10");
		requestData.put("tokenId", tokenId);

		Map<String, String> cardInfo = new HashMap<>();
		cardInfo.put("number", "4000000000002002");
		cardInfo.put("cardName", "anant");
		cardInfo.put("year", "2027");
		cardInfo.put("month", "11");
		requestData.put("cardInfo", cardInfo);
		
		Map<String, String> billingAddress = new HashMap<>();
		billingAddress.put("bfirstName", "anant");
		billingAddress.put("blastName", "testing");
		billingAddress.put("bemail", "anant@testing.com");
		billingAddress.put("bmobile", "1234567890");
		requestData.put("billingAddress", billingAddress);
		
		Map<String, String> acquirerToken = new HashMap<>();
		acquirerToken.put("acquirer", "dalenys");
		acquirerToken.put("acquirertoken", "kwr23750-823-3248wuijdsa-");
		acquirerToken.put("month", "10");
		acquirerToken.put("year", "2027");
		acquirerToken.put("cardName", "anant");
		acquirerToken.put("bin", "400000");
		acquirerToken.put("last4", "9999");
		requestData.put("acquirerToken", acquirerToken);

		

	}
	
	@Test (priority=1)
	void saveToken() {

//		RestAssured.registerParser("text/plain", Parser.JSON);
		tokenId=given()
		   .contentType("application/json")
		   .body(requestData)
		.when()
		   .post("http://localhost:4000/savecard")
		   .jsonPath().getString("tokenID");
		   
//		.then()
//		    .statusCode(200)
//		    .body("response.description", equalTo("Card Saved Successfully"))
//		    .body("response.responseCode", equalTo(200))
//		    .body("response.description", equalTo("Card Already Registered"))
//		    .body("response.responseCode", equalTo(1004))
//		    .log().all();
	}
	
	@Test (priority=2)
	void verifyToken() throws JsonMappingException, JsonProcessingException {
		
		RestAssured.registerParser("text/plain", Parser.JSON);
		
		given()
		   .contentType("application/json")
		   .body(requestData)
		.when()
		   .post("http://localhost:4000/verifycard")
		
		.then()
//		  	.log().all()
		    .statusCode(200)
		    .body("cardHolderName", equalTo("anant"))
		    .body("id", equalTo(tokenId))
		    .body("response.responseCode", equalTo(200));
	}
	
	@Test (priority=3)
	void getToken() {
		
		RestAssured.registerParser("text/plain", Parser.JSON);

		given()
		   .contentType("application/json")
		   .body(requestData)
		.when()
		   .get("http://localhost:4000/getsavecards")
		
		.then()
//		 	.log().all()
		 	.statusCode(200)
		 	.body("tokens[0].id", equalTo("400000-567250736185588530-2002"))
	 		.body("tokens[0].issuer", equalTo("MasterCard"));

		 	
	}
	
	@Test (priority = 4)
	void saveAcquirerToken() {
		
		RestAssured.registerParser("text/plain", Parser.JSON);

		tokenId=given()
			.contentType("application/json")
			.body(requestData)
		
		.when()
			.post("http://localhost:4000/savetoken")
			.jsonPath().getString("tokenID");
			
//		.then()
//		  	.log().all()
//		  	.statusCode(200)
//		  	.body("response.description", equalTo("Card Saved Successfully"))
//		  	.body("response.responseCode", equalTo(200));
		
	}
	
	@Test (priority = 5)
	void verifyAcquirerToken() throws JsonMappingException, JsonProcessingException {
		
		RestAssured.registerParser("text/plain", Parser.JSON);
		
		given()
		   .contentType("application/json")
		   .body(requestData)
		.when()
		   .post("http://localhost:4000/verifytoken")
		
		.then()
//		  	.log().all()
		    .statusCode(200)
		    .body("cardHolderName", equalTo("anant"))
		    .body("id", equalTo(tokenId))
		    .body("processorMid", equalTo("dalenys"))
		    .body("cardHolderName", equalTo("anant"))
		    .body("response.responseCode", equalTo(200));
	}
	
	@Test (priority=6)
	void getAcquirerToken() {
		
		RestAssured.registerParser("text/plain", Parser.JSON);

		given()
		   .contentType("application/json")
		   .body(requestData)
		.when()
		   .get("http://localhost:4000/gettoken")
		
		.then()
//		 	.log().all()
		 	.statusCode(200)
	 		.body("tokens[0].issuer", equalTo("VisaCard"));

		 	
	}
	
	@Test (priority = 7)
	void deleteAcquirerToken() throws JsonMappingException, JsonProcessingException {

		RestAssured.registerParser("text/plain", Parser.JSON);
		
		given()
		   .contentType("application/json")
		   .body(requestData)
		   .log().all()
		.when()
		   .delete("http://localhost:4000/deletetoken")
		
		.then()
		  	.log().all()
		    .statusCode(200)
		    .body("response.responseCode", equalTo(181))
	    	.body("response.description", equalTo("Token Removed Successfully"));

	}
}
