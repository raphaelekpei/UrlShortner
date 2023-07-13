package com.raphael.urlshortner.service;

import com.google.common.hash.Hashing;
import com.raphael.urlshortner.Exceptions.InvalidUrlException;
import com.raphael.urlshortner.Exceptions.NoSuchUrlExistsException;
import com.raphael.urlshortner.Exceptions.UnableToCreateUrlException;
import com.raphael.urlshortner.Exceptions.UrlExpirationException;
import com.raphael.urlshortner.data.model.Url;
import com.raphael.urlshortner.data.repository.UrlRepository;
import com.raphael.urlshortner.dtos.request.ShortenUrlRequest;
import com.raphael.urlshortner.dtos.response.ShortenUrlResponse;
import io.micrometer.common.util.StringUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Optional;


@Service
@AllArgsConstructor
public class UrlServiceImpl implements UrlService {

    private final UrlRepository urlRepository;

    @Override
    public ShortenUrlResponse shortenUrl(ShortenUrlRequest shortenUrlRequest) {
        // TODO: 1. Get the long url from the request
        // TODO: 2. convert the long url to short url
        // A. Concatenate the long url with the current timestamp as a string before hashing
        // This ensures that the shortened URL generated during hashing is always unique and not reused, even if the same longUrl is submitted multiple times
        // B. Hash the concatenated url using the MurmurHash3_32 hash function from the Google Guava library, with the input string encoded as UTF-8.
        // TODO: 3. Create a new url object
        // TODO: 4. save or persist the url to database
        // TODO: 5. return a response  containing the long url, shortened url & expiration date
        // TODO 6: Make provision if the short url generation process fails

        String longUrl = shortenUrlRequest.getLongUrl();
        String concatenatedUrl = longUrl.concat(LocalDateTime.now().toString());
        String shortUrl = Hashing.murmur3_32().hashString(concatenatedUrl, StandardCharsets.UTF_8).toString();
        Url url = new Url(shortUrl, longUrl);
        Url savedUrl = urlRepository.save(url);

        if (savedUrl != null) throw new UnableToCreateUrlException("There was an error processing your request. please try again");
        return new ShortenUrlResponse(shortUrl, longUrl, url.getExpirationDate());

    }
    @Override
    public String redirectToLongUrl(String shortUrl) {
        // TODO: 1. validate the short url
        if (StringUtils.isEmpty(shortUrl)) throw new InvalidUrlException("Invalid url"); // check this
        // TODO: 2. check if the url exist in the db
        Optional<Url> optionalUrl = urlRepository.findByShortUrl(shortUrl);
        // TODO: 3. if not found, throw an exception
        if (optionalUrl.isEmpty()) throw new NoSuchUrlExistsException("Url does not exist or it might have expired");
        // TODO: 4. if found, get the url from the db
        Url url = optionalUrl.get();
        // TODO: 5. check if the url has expired or not
        if (url.getExpirationDate().isBefore(LocalDateTime.now())) {
            // TODO: if expired, delete it from the db to clean up the db & throw an exception
            urlRepository.delete(url);
            throw new UrlExpirationException("Url Expired. Please try generating a fresh one");
        }
        // TODO: If not expired, return the long url
        return url.getLongUrl();
    }
}