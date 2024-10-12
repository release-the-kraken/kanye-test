package rtk.kanye.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rtk.kanye.model.Quote;
import rtk.kanye.service.QuoteService;

import java.util.Arrays;

@RestController
@RequestMapping("/v1")
public class QuoteController {
    private static final Logger log = LoggerFactory.getLogger(QuoteController.class);
    private final QuoteService service;

    public QuoteController(QuoteService service) {
        this.service = service;
    }

    @GetMapping("/quote")
    public ResponseEntity<Quote> getQuote(){
        try{
            Quote response = service.getQuote();
            return ResponseEntity.ok(response);
        }catch(Exception e){
            log.warn(Arrays.toString(e.getStackTrace()));
            return ResponseEntity.internalServerError().build();
        }
    }

}
