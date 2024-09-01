package com.handson.chatbot.controller;

import com.handson.chatbot.service.ImdbService;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@Service
@RestController
@RequestMapping("/bot")
public class BotController {

    @Autowired
    ImdbService imdbService;

    @RequestMapping(value = "/imdb", method = RequestMethod.GET)
    public ResponseEntity<?> getProduct(@RequestParam String keyword) throws IOException {
        return new ResponseEntity<>(imdbService.searchProducts(keyword), HttpStatus.OK);
    }
}