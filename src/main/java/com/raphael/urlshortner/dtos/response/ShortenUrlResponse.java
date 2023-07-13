package com.raphael.urlshortner.dtos.response;

import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ShortenUrlResponse {
    private String longUrl;
    private String shortUrl;
    private LocalDateTime expirationDate;
}
