package com.st.sa.controllers;


import com.st.sa.TypeSentiment;
import com.st.sa.entities.Client;
import com.st.sa.entities.Sentiment;
import com.st.sa.services.SentimentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "sentiment", produces = MediaType.APPLICATION_JSON_VALUE)
public class SentimentController {

    private SentimentService sentimentService;

    public SentimentController(SentimentService sentimentService) {
        this.sentimentService = sentimentService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createSentiment(@RequestBody Sentiment sentiment) {
        this.sentimentService.createSentiment(sentiment);
    }

//    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<Sentiment> createSentiment(@RequestBody Sentiment sentiment) {
//        return ResponseEntity.ok(this.sentimentService.createSentiment(sentiment));
//    }

    @GetMapping
    public @ResponseBody List<Sentiment> getSentiments(@RequestParam(required = false) TypeSentiment typeSentiment) {
        return this.sentimentService.getSentiments(typeSentiment);
    }


    @ResponseStatus(HttpStatus.ACCEPTED) //202
    @DeleteMapping(path = "{id}")
    public void delete(Integer id) {
        this.sentimentService.delete(id);
    }
}
