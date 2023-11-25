package com.vdobrikov.urlshortenerspringjavanative.web;

import com.vdobrikov.urlshortenerspringjavanative.exception.IncorrectUrlException;
import com.vdobrikov.urlshortenerspringjavanative.service.UrlService;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collection;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/url")
@RestController
public class UrlApiController {
    private final UrlService urlService;
    private final UrlMapper urlMapper;

    @GetMapping("/short")
    public String createShortUrl(@RequestParam @NotBlank @Size(max = 512) String url) {
        log.info("GET createShortUrl. url='{}'", url);

        validate(url);

        return urlService.shortenUrl(url);
    }

    @GetMapping
    public Collection<Url> listAllUrls() {
        return urlService.listAllUrls().stream()
                .map(urlMapper::fromEntityToDto)
                .toList();
    }

    private static void validate(String url) {
        try {
            new URL(url).toURI();
        } catch (Exception e) {
            throw new IncorrectUrlException(url);
        }
    }
}
