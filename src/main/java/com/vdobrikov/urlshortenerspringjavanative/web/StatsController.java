package com.vdobrikov.urlshortenerspringjavanative.web;

import com.vdobrikov.urlshortenerspringjavanative.service.UrlService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Controller
public class StatsController {
    private final UrlService urlService;
    private final UrlMapper urlMapper;

    @GetMapping("/stats")
    public String getStats(Model model) {
        log.info("GET /stats");

        List<Url> urls = urlService.listAllUrls().stream()
                .map(urlMapper::fromEntityToDto)
                .collect(Collectors.toList());
        model.addAttribute("urls", urls);

        return "stats";
    }
}
