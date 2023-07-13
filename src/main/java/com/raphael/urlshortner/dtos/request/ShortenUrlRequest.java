package com.raphael.urlshortner.dtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.URL;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class ShortenUrlRequest {

    @NotNull
    @NotBlank
    @NotEmpty
    @URL(message = "Invalid url. Please enter a valid url")
    private String longUrl;

    private String expirationDate; // optional
}
