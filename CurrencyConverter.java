package Task3;
	import java.io.BufferedReader;

	import java.io.IOException;
	import java.io.InputStreamReader;
	import java.net.HttpURLConnection;
	import java.net.URL;

	public class CurrencyConverter {

	    private static final String API_KEY = "YOUR_EXCHANGE_RATE_API_KEY";
	    private static final String API_URL = "https://open.er-api.com/v6/latest/";

	    public static void main(String[] args) {
	        try {
	            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

	            // Step 1: Allow the user to choose the base currency and the target currency.
	            System.out.print("Enter the base currency code (e.g., USD): ");
	            String baseCurrency = reader.readLine().toUpperCase();

	            System.out.print("Enter the target currency code (e.g., EUR): ");
	            String targetCurrency = reader.readLine().toUpperCase();

	            // Step 2: Fetch real-time exchange rates from the API.
	            double exchangeRate = getExchangeRate(baseCurrency, targetCurrency);
	            if (exchangeRate == -1) {
	                System.out.println("Failed to fetch exchange rates. Please try again later.");
	                return;
	            }

	            // Step 3: Take input from the user for the amount they want to convert.
	            System.out.print("Enter the amount to convert: ");
	            double amountToConvert = Double.parseDouble(reader.readLine());

	            // Step 4: Convert the input amount from the base currency to the target currency.
	            double convertedAmount = amountToConvert * exchangeRate;

	            // Step 5: Display Result.
	            System.out.println("Converted amount: " + convertedAmount + " " + targetCurrency);

	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }

	    private static double getExchangeRate(String baseCurrency, String targetCurrency) {
	        try {
	            URL url = new URL(API_URL + baseCurrency + "?apikey=" + API_KEY);
	            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
	            connection.setRequestMethod("GET");

	            int responseCode = connection.getResponseCode();

	            if (responseCode == HttpURLConnection.HTTP_OK) {
	                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
	                StringBuilder response = new StringBuilder();
	                String line;

	                while ((line = reader.readLine()) != null) {
	                    response.append(line);
	                }

	                reader.close();

	                // Parse JSON response and extract exchange rate
	                String jsonResponse = response.toString();
	                double exchangeRate = parseExchangeRate(jsonResponse, targetCurrency);
	                return exchangeRate;
	            } else {
	                return -1;
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	            return -1;
	        }
	    }

	    private static double parseExchangeRate(String jsonResponse, String targetCurrency) {
	        // Parse JSON and extract exchange rate for the target currency
	        // Example JSON response: {"conversion_rates":{"USD":1,"EUR":0.85,"GBP":0.72, ...}}
	        try {
	            int startIndex = jsonResponse.indexOf(targetCurrency) + 6; // Position after targetCurrency code
	            int endIndex = jsonResponse.indexOf(",", startIndex);
	            String rateSubstring = jsonResponse.substring(startIndex, endIndex);
	            return Double.parseDouble(rateSubstring);
	        } catch (NumberFormatException e) {
	            e.printStackTrace();
	            return -1;
	        }
	    }
	}

