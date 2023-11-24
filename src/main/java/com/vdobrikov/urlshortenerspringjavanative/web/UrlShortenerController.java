package com.vdobrikov.urlshortenerspringjavanative.web;

import com.vdobrikov.urlshortenerspringjavanative.exception.IncorrectUrlException;
import com.vdobrikov.urlshortenerspringjavanative.service.UrlService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.MalformedURLException;
import java.net.URL;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/url")
@RestController
public class UrlShortenerController {
    private final UrlService urlService;

    @GetMapping
    public String createShortUrl(@RequestParam String url) {
        log.info("GET createShortUrl. url='{}'", url);

        validate(url);

        return urlService.shortenUrl(url);
    }

    private static void validate(String url) {
        try {
            new URL(url);
        } catch (MalformedURLException e) {
            throw new IncorrectUrlException(url);
        }
    }
}
