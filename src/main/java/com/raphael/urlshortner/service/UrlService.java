package com.raphael.urlshortner.service;

import com.raphael.urlshortner.dtos.request.ShortenUrlRequest;
import com.raphael.urlshortner.dtos.response.ShortenUrlResponse;

public interface UrlService {

    ShortenUrlResponse shortenUrl(ShortenUrlRequest shortenUrlRequest);

    String redirectToLongUrl(String shortUrl);

}
