package rtk.kanye.httpclient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.net.URI;
import java.net.URISyntaxException;

@Component
public class KanyeRestClient {
    private static final Logger log = LoggerFactory.getLogger(KanyeRestClient.class);

    private final RestClient restClient = RestClient.create();

    public String fetchRestResponse() throws URISyntaxException {
            URI uri = new URI("https://api.kanye.rest");

            String response = restClient.get()
                    .uri(uri)
                    .retrieve()
                    .body(String.class);
            log.info("Quote fetched successfully. [%s]".formatted(response));
            return response;
    }
}
