package files;

import static io.restassured.RestAssured.given;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.apache.http.client.utils.URLEncodedUtils;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.restassured.RestAssured;
import io.restassured.config.EncoderConfig;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;

public class Transaction {
	
	public static String ID=null;
	SoftAssert sa= new SoftAssert();
	
	//using data provider to fetch different values
	@Test(dataProvider = "TransactionData")
	public void addTransaction(String c1, String c2) throws UnsupportedEncodingException {
		
		RestAssured.urlEncodingEnabled=false;
		//form base url
		RestAssured.baseURI="https://x8ki-letl-twmt.n7.xano.io/api:gHPd8le5";
		
		//to store response as string
		String r=
				//rest implementation
				RestAssured.given().log().all()
				
				//adding headers
				.header("Content-type","application/json")
				.header("accept","application/json").
				
				//passing request body
				body(payload.transDetails(c1, c2))
				
				//define post call with resource
				.when().post("transaction")
		.then().log().all().assertThat()
		
		//check statuscode
		.statusCode(200)
		
		//store response as string
		.extract().response().asString();
		
		//print the result
		System.out.println("Response: "+r);
		
		
		//parse json response
		JsonPath jpt= ReadJson.rawToJson1(r);
		
		//assert coin values
		sa.assertEquals(jpt.getString("sentCoinAmount"), c1,"coin 1 amopunt assert");
		sa.assertEquals(jpt.getString("receivedCoinAmount"), c2,"coin 2 amount assert");
		
		//store id-value to get transaction
		
		 ID= jpt.getString("id");
		
				//print response values
		System.out.println("id="+ID);

		ReadJson.addTransactionDetailsAPI(jpt);
		
		///////////testing GET-route
		
		//to store response as string
				String r1=
						//rest implementation
						RestAssured.given().log().all()
						
						//adding headers
						.header("accept","application/json")
											
						//define post call with resource
						.when().get("transaction/"+ID)
				.then().log().all().assertThat()
				
				//check statuscode
				.statusCode(200)
				
				//store response as string
				.extract().response().asString();
				
				//print the result
				System.out.println("Response: "+r1);
		
				//parse json response
				JsonPath jpt1= ReadJson.rawToJson1(r);
				
				//assert ID
		sa.assertEquals(jpt1.getString("id"), ID,"ID assert");
		
		//calculate receivedCoinMarketPrice - value & assert
		sa.assertEquals(jpt1.getString("receivedCoinMarketPrice"),ReadJson.RCMPValue(jpt1) );
		
	}

	@DataProvider(name="TransactionData")
	public Object[][] getData() {
		
	return new Object[][] {{"919","8"}};
	}
	
}
