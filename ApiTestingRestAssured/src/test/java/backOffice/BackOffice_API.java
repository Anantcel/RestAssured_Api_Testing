package backOffice;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.parsing.Parser;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class BackOffice_API {

	private Map<String, Object> requestData;
	
	@BeforeMethod
	void setUp() {
		
		requestData = new HashMap<>();
		requestData.put("txnamount", 1);
		requestData.put("txnId", "REF1737117483224");
		
		requestData.put("refundAmount", "1");
		requestData.put("refundComment", "refund 1");
		requestData.put("refundInvoiceNo", "12");
		
		requestData.put("fromdate","2025-01-16" );
		requestData.put("todate","2025-01-17" );
		
		Map<String, String> dynamicDescriptor = new HashMap<>();
		dynamicDescriptor.put("mobile","1234567890");
		dynamicDescriptor.put("email","abc@xyz.com");
		dynamicDescriptor.put("name", "ABC Merchant");
		requestData.put("dynamicDescriptor", dynamicDescriptor);
		
	}
	
//	@Test
	void capturePayment() {
		
		RestAssured.registerParser("text/plain", Parser.JSON);
		
		given()
			.contentType("application/json")
			.body(requestData)
			
		.when()
			.post("http://localhost:4000/capture")
			
		.then()
			.log().all()
			.statusCode(200)
//			.body("response.responseCode", equalTo(200));
			.body("response.responseCode", equalTo(1049));
	}
	
//	@Test
	void voidPayment() {
		
		RestAssured.registerParser("text/plain", Parser.JSON);
		
		given()
			.contentType("application/json")
			.body(requestData)
			
		.when()
			.post("http://localhost:4000/void")
			
		.then()
			.log().all()
			.statusCode(200)
//			.body("response.responseCode", equalTo(200));
			.body("response.responseCode", equalTo(1056));
	}
	
//	@Test
	void refundTransaction() {
		
		RestAssured.registerParser("text/plain", Parser.JSON);
		
		given()
			.contentType("application/json")
			.body(requestData)
			
		.when()
			.post("http://localhost:4000/refundtransaction")
		
		.then()
			.log().all()
			.statusCode(200);
	}
	
//	@Test
	void refundDetails() {
		
		RestAssured.registerParser("text/plain", Parser.JSON);
		
		given()
			.contentType("application/json")
			.body(requestData)
			
		.when()
			.post("http://localhost:4000/refunddetails")
		
		.then()
			.log().all()
			.statusCode(200)
			.body("totalTxnAmount", equalTo("10.00"))
			.body("refund[0].comments", equalTo("refund 1"))
			.body("refund[0].status", equalTo("Successful"));
	}
	
//	@Test
	void transactionStatus() {
		
		RestAssured.registerParser("text/plain", Parser.JSON);
		given()
			.contentType("application/json")
			.body(requestData)
			
		.when()
			.post("http://localhost:4000/transactionstatus")
			
		.then()
			.log().all()
			.statusCode(200)
			.body("respCode", equalTo(200))
			.body("status", equalTo("Successful"))
			.body("txnReference", equalTo("REF1737117483224"))
			.body("OriginalTxnStatus", equalTo("Successful"));
		
	}
	
	@Test
	void transactionReport() {
		
		RestAssured.registerParser("text/plain", Parser.JSON);
		given()
			.contentType("application/json")
			.body(requestData)
			
		.when()
			.post("http://localhost:4000/transactionreport")
			
		.then()
			.log().all()
			.statusCode(200);
		
	}
}
