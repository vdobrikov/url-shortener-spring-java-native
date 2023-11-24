package com.vdobrikov.urlshortenerspringjavanative.web;

import com.vdobrikov.urlshortenerspringjavanative.exception.UrlEntityNotFoundException;
import com.vdobrikov.urlshortenerspringjavanative.service.UrlService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/")
@Controller
public class RedirectController {
    private final UrlService urlService;

    @GetMapping("/{id}")
    public ModelAndView redirect(@PathVariable String id) {
        log.info("GET redirect. id='{}'", id);

        validate(id);

        return new ModelAndView("redirect:" + urlService.findUrl(id));
    }

    private static void validate(String id) {
        try {
            UUID.fromString(id);
        } catch (IllegalArgumentException e) {
            throw new UrlEntityNotFoundException(id);
        }
    }
}
