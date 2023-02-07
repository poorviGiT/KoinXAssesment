package files;public class payload {
	
////to pass request body
	public static String transDetails(String a1, String a2) {
		
		String trans="{\r\n"
				+ "\"coin1\": \"INR\",\r\n"
				+ "\"coin2\": \"USDT\",\r\n"
				+ "\"coin1Amount\": "+a1+",\r\n"
				+ "\"coin2Amount\": "+a2+"\r\n"
				+ "}";
		return trans;
		
	}
}
