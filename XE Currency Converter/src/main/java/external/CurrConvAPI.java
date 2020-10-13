package external;

import helper.ExchangePair;

import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static helper.ParseRate.parseRateToString;

public class CurrConvAPI {

//        private static final String API_KEY = System.getenv("API_KEY");
    private static final String API_KEY = "c7399460a498ce795615";

    public String fetchExchangeRateFor(ExchangePair exchangePair) {

        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(exchangeRateUrlForCurrencies(exchangePair.getFromCurrency(), exchangePair.getToCurrency()))
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            return parseRateToString(response.body());
        } catch (Exception e) {

            throw new RuntimeException("something went wrong during HTTP request to external API");
        }
    }


    private URI exchangeRateUrlForCurrencies(String from, String to) {

        String url = String.format(
                "https://free.currconv.com/api/v7/convert?apiKey=%s&q=%s_%s&compact=ultra",
                API_KEY,
                from,
                to);
        try {

            return new URI(url);

        } catch (URISyntaxException e) {

            throw new IllegalArgumentException("failed to build URI");
        }
    }

}
