package payment;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Payment_API {
	private Map<String, Object> requestBody;
	
  @BeforeMethod
  void setup() {
	
	  requestBody = new HashMap<>();
	  requestBody.put("lang", "en");
	  requestBody.put("customerId", "101");
	  requestBody.put("amount", "1.9");

	  requestBody.put("dob", "1997-08-07");
	  requestBody.put("ipAddress", "1.1.1.1");
	  
	  Map<String, String> billingAddress = new HashMap<>();
	  billingAddress.put("bfirstName", "anant");
	  billingAddress.put("blastName", "test");
	  billingAddress.put("bmobile", "1234567890");
	  billingAddress.put("bemail", "anant@test.in");
	  billingAddress.put("baddressLine1", "123test");
	  billingAddress.put("baddressLine2", "testcity");
	  billingAddress.put("bcity", "testing");
	  billingAddress.put("bstate", "test");
	  billingAddress.put("bzip", "12345");
	  billingAddress.put("bcountry", "India");
	  
	  Map<String, String> shippingAddress = new HashMap<>();
	  shippingAddress.put("sfirstName", "test");
	  shippingAddress.put("slastName", "anant");
	  shippingAddress.put("smobile", "0987654321");
	  shippingAddress.put("semail", "test@test.in");
	  shippingAddress.put("saddressLine1", "23423423423teststest");
	  shippingAddress.put("saddressLine2", "23423testcity34234");
	  shippingAddress.put("scity", "demo");
	  shippingAddress.put("sstate", "demo");
	  shippingAddress.put("szip", "5555");
	  shippingAddress.put("scountry", "India");
	  requestBody.put("shippingAddress", shippingAddress);
	  requestBody.put("billingAddress", billingAddress);

	 Map<String, Object> transaction = new HashMap<>();
	 transaction.put("txnAmount", "19.99");
	 transaction.put("currencyCode", "EUR");
	 transaction.put("txnReference", "REF00L00000");
	 transaction.put("orderId", "ORD00L0000");
	 transaction.put("payout", "false");
	 transaction.put("isApp", true);
	 transaction.put("executionDate", "2022-04-20 14:23:02");
	 transaction.put("pageTag", "test_page");
	 transaction.put("midTag", "midtag_value");
	 transaction.put("fallbackMidTag", "fallback_midtag_value");
	 
	 Map<String, Object> threeDSecure = new HashMap<>();
     threeDSecure.put("challengeIndicator", "01");
     threeDSecure.put("challengeWindowSize", "05");
     
     Map<String, Boolean> exemptions = new HashMap<>();
     exemptions.put("lowValue", true);
     exemptions.put("tra", true);
     exemptions.put("trustedBeneficiary", true);
     exemptions.put("secureCorporatePayment", true);
     exemptions.put("delegatedAuthentication", true);
     exemptions.put("recurringMITExemptionSameAmount", true);
     exemptions.put("recurringMITExemptionOther", true);
     
     requestBody.put("exemptions", exemptions);
     requestBody.put("threeDSecure", threeDSecure);
     requestBody.put("transaction", transaction);

	  Map<String, String> dynamicdescriptor = new HashMap<>();
	  dynamicdescriptor.put("ddname", "ABC Merchant");
	  dynamicdescriptor.put("ddemail", "abc@xyz.com");
	  dynamicdescriptor.put("ddmobile", "1234567890");
	  requestBody.put("dynamicdescriptor", dynamicdescriptor);
	  
	  Map<String, String> urlObj = new HashMap<>();
	  urlObj.put("successUrl", "http://www.domain.com/SuccessResponse.html");
	  urlObj.put("failUrl", "http://www.domain.com/FailResponse.html");
	  urlObj.put("cancelUrl", "http://www.domain.com/CancelResponse.html");
	  urlObj.put("showConfirmationPage", "true");
	  urlObj.put("cartURL", "http://www.domain.com/Cart.html");
	  urlObj.put("productURL", "http://www.domain.com/Product.html");
	  urlObj.put("iFrame", "false");
	  requestBody.put("urlObj", urlObj);
	  
	  Map<String, String> cardInfo = new HashMap<>();
	  cardInfo.put("cardNumber", "4350770096122177");
	  cardInfo.put("cardName", "anant");
	  cardInfo.put("cardCVV", "123");
	  cardInfo.put("cardYear", "2026");
	  cardInfo.put("cardMonth", "10");
	  
	  requestBody.put("cardInfo", cardInfo);

	 Map<String, Object> summary = new HashMap<>();
	 summary.put("total", "19.07");
	  
	 Map<String, String> details = new HashMap<>();
	 summary.put("subtotal", "19.41");
	 summary.put("tax", "0.03");
	 summary.put("shippingcharge", "0.55");
	 
	 Map<String, String> discount = new HashMap<>();
	 discount.put("discountValue", "0.40");
	 discount.put("couponCode", "FIRST40");
	 discount.put("couponCodeDetails", "Get $0.4 off on every transaction. *T&C apply");
	 
	  requestBody.put("summary", summary);
	  summary.put("details", details);
	  summary.put("discount", discount);
	  
	 List<Map<String,String>> items = new ArrayList<>();
	 Map<String, String> item1 = new HashMap<>();
     item1.put("itemName", "RBK fitness shoes");
     item1.put("itemId", "ITM001");
     item1.put("itemPricePerUnit", "2.49");
     item1.put("itemQuantity", "2");
     items.add(item1);
     
     Map<String, String> item2 = new HashMap<>();
     item2.put("itemName", "Nike DriFit T-shirt");
     item2.put("itemId", "ITM002");
     item2.put("itemPricePerUnit", "1.99");
     item2.put("itemQuantity", "1");
     items.add(item2);
     requestBody.put("items", items);
     
	 Map<String, String> customData = new HashMap<>();
	 customData.put("customData1", "1");
	 customData.put("customData2", "2");
	 customData.put("customData3", "3");
	 customData.put("customData4", "4");
	 customData.put("customData5", "5");
	 customData.put("site", "site");
     requestBody.put("customData", customData);

     Map<String, Object> industry = new HashMap<>();
     Map<String, Object> travel = new HashMap<>();
     Map<String, String> accommodation = new HashMap<>();
     accommodation.put("propertyName", "Valamar Meteor Hotel");
     accommodation.put("propertyReservationNumber", "KH124GH");
     accommodation.put("dateCheckIn", "2023-10-23T00:00:00.000Z");
     accommodation.put("dateCheckOut", "2023-10-28T00:00:00.000Z");
     accommodation.put("bookingFirstName", "John");
     accommodation.put("bookingLastName", "Doe");
     
     Map<String, Object> crypto = new HashMap<>();
     crypto.put("walletAddress", "L2hjTJNhjpUTdAVMArh3UqmnTXEVx6J6Faui8cUXCPpyQMUEkJ54");
     crypto.put("publicAddressIndicator", true);
     crypto.put("walletSanctionChecked", true);
     crypto.put("isWalletSanctioned", true);
     crypto.put("cryptoCurrency", "BTC");
     requestBody.put("industry", industry);
     industry.put("travel", travel);
     travel.put("accommodation", accommodation);
     industry.put("crypto", crypto);

  }
  
//	@Test
	void hpp() {
		
		given()
		    .contentType("application/json")
		    .body(requestBody)
		
		.when()
		     .post("http://localhost:4000/hpp")
		
		.then()
		     .statusCode(200)
		     .log().all();
	}
	
//	@Test
	void whppSync()
	{
		given()
		  .contentType("application/json")
		  .body(requestBody)
		  
		.when()
		  .post("http://localhost:4000/whppSync")
		
		.then()
		   .statusCode(200)
		   .log().all();
	}
	
//	@Test
	void whppAsync()
	{
		given()
		  .contentType("application/json")
		  .body(requestBody)
		  
		.when()
		  .post("http://localhost:4000/whppAsync")
		
		.then()
		   .statusCode(200)
		   .log().all();
	}
	
//	@Test
	void pluginCheckout()
	{
		given()
		  .contentType("application/json")
		  .body(requestBody)
		  
		.when()
		  .post("http://localhost:4000/plugincheckout")
		
		.then()
		   .statusCode(200)
		   .log().all();
	}
	
	@Test
	void pluginDetails()
	{
		given()
		  
		.when()
		  .post("http://localhost:4000/plugindetails")
		
		.then()
		   .statusCode(200)
		   .log().all();
	}

}
