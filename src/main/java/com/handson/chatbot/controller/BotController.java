package com.handson.chatbot.controller;

import com.handson.chatbot.service.ChuckJokeService;
import com.handson.chatbot.service.ImdbService;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;

@Service
@RestController
@RequestMapping("/bot")
public class BotController {

    @Autowired
    ImdbService imdbService;
    @Autowired
    ChuckJokeService chuckJokeService;

    @RequestMapping(value = "/imdb", method = RequestMethod.GET)
    public ResponseEntity<?> getProduct(@RequestParam String keyword) throws IOException {
        return new ResponseEntity<>(imdbService.searchProducts(keyword), HttpStatus.OK);
    }

    @RequestMapping(value = "/jokes", method = RequestMethod.GET)//query jokes with "keyword"
    public ResponseEntity<?> getJoke(@RequestParam String keyword) throws IOException {
        return new ResponseEntity<>(chuckJokeService.getJoke(keyword), HttpStatus.OK);
    }

    @RequestMapping(value = "", method = { RequestMethod.POST})
    public ResponseEntity<?> getBotResponse(@RequestBody BotQuery query) throws IOException {
        HashMap<String, String> params = query.getQueryResult().getParameters();
        String res = "Not found";
        if (!params.isEmpty()) {
            res = chuckJokeService.getJoke(params.get("jokeParam"));
        }
        else
        {
            res = imdbService.searchProducts(params.get("product"));
        }
        return new ResponseEntity<>(BotResponse.of(res), HttpStatus.OK);
    }

    static class BotQuery {
        QueryResult queryResult;

        public QueryResult getQueryResult() {
            return queryResult;
        }
    }

    static class QueryResult {
        HashMap<String, String> parameters;

        public HashMap<String, String> getParameters() {
            return parameters;
        }
    }

    static class BotResponse {
        String jokeParam;
        String source = "BOT";

        public String getFulfillmentText() {
            return jokeParam;
        }

        public String getSource() {
            return source;
        }

        public static BotResponse of(String jokeParam) {
            BotResponse res = new BotResponse();
            res.jokeParam = jokeParam;
            return res;
        }
    }
}