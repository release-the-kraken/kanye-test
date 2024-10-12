package rtk.kanye.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import rtk.kanye.httpclient.KanyeRestClient;
import rtk.kanye.model.Quote;

import java.util.Arrays;

@Service
public class QuoteService {
    private static String BAD_RESPONSE_MSG = """
            {"quote":"Something went wrong. Kanye has no quote for you :("}""";
    private static final Logger log = LoggerFactory.getLogger(QuoteService.class);
    private final KanyeRestClient kanyeRestClient;
    private final ObjectMapper objectMapper;

    public QuoteService() {
        this.kanyeRestClient = new KanyeRestClient();
        this.objectMapper = new ObjectMapper();
    }

    public Quote getQuote() throws JsonProcessingException {
        try{
            String response = kanyeRestClient.fetchRestResponse();
            return objectMapper.readValue(response, Quote.class);
        }catch(Exception e){
            log.warn(Arrays.toString(e.getStackTrace()));
            return objectMapper.readValue(BAD_RESPONSE_MSG, Quote.class);
        }
    }
}
