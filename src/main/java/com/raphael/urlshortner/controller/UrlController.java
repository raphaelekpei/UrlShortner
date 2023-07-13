package com.raphael.urlshortner.controller;

import com.raphael.urlshortner.dtos.request.ShortenUrlRequest;
import com.raphael.urlshortner.dtos.response.ShortenUrlResponse;
import com.raphael.urlshortner.service.UrlService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/url")
public class UrlController {

    private final UrlService urlService;

    // TODO: This endpoint accepts a long URL as input and returns a shortened URL as output.
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/shorten")
    public ShortenUrlResponse shortenUrl(@RequestBody @Valid ShortenUrlRequest shortenUrlRequest){
        return urlService.shortenUrl(shortenUrlRequest);
    }

    // TODO: This endpoint accepts a shortened URL as input and returns a long URL as output.
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{shortUrl}")
    public String redirectToLongUrl(@PathVariable String shortUrl){
        return urlService.redirectToLongUrl(shortUrl);
    }

}
