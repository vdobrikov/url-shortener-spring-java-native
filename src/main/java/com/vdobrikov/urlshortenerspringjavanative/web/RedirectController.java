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

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/")
@Controller
public class RedirectController {
    private final UrlService urlService;

    @GetMapping("/{hash}")
    public ModelAndView redirect(@PathVariable String hash) {
        log.info("GET redirect. hash='{}'", hash);

        validate(hash);

        return new ModelAndView("redirect:" + urlService.findUrlAndIncrementUsage(hash));
    }

    private static void validate(String hash) {
        if (hash == null || hash.isEmpty()) {
            throw new UrlEntityNotFoundException(hash);
        }
    }
}
