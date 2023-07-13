package com.raphael.urlshortner.data.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Url {

    // TODO: This model stores the long URLs & their shortened URLs.
    @Id
    @GeneratedValue
    private Long id;
    @Lob
    private String longUrl;
    private String shortUrl;
    private LocalDateTime creationDate;
    private LocalDateTime expirationDate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Url(String longUrl, String shortUrl) {
        this.longUrl = longUrl;
        this.shortUrl = shortUrl;
        this.creationDate = LocalDateTime.now();
        this.expirationDate = (expirationDate != null) ? expirationDate : creationDate.plusSeconds(60);
    }
}
