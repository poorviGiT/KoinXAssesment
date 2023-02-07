package files;
import io.restassured.path.json.JsonPath;
public class ReadJson {
	
	public static JsonPath rawToJson1(String response) {
		JsonPath js1= new JsonPath(response);
		return js1;
	}

	public static void addTransactionDetailsAPI(JsonPath j) {
		System.out.println(j.getString("created_at"));
		System.out.println(j.getString("sentCoinAmount"));
		System.out.println(j.getString("receivedCoinAmount"));
	}

	public static Double RCMPValue(JsonPath j) {
	int sca= j.getInt("sentCoinAmount");
	
	int rca = j.getInt("receivedCoinAmount");
	
	System.out.println("Sent coin amount & ReceivedcoinAmount: "+sca+" "+rca);
	
	double rcmp= sca%rca;
	
	return rcmp;
		
	}
}
