package com.vdobrikov.urlshortenerspringjavanative.service;

import com.vdobrikov.urlshortenerspringjavanative.exception.UrlEntityNotFoundException;
import com.vdobrikov.urlshortenerspringjavanative.persistence.UrlEntity;
import com.vdobrikov.urlshortenerspringjavanative.persistence.UrlEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class UrlService {
    private final UrlEntityRepository urlEntityRepository;

    public String shortenUrl(String url) {
        final String normalizedUrl = url.trim().toLowerCase();
        UrlEntity urlEntity = urlEntityRepository.findByUrl(normalizedUrl)
                .orElseGet(() -> urlEntityRepository.save(new UrlEntity(normalizedUrl)));

        return ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(urlEntity.getId().toString())
                .build()
                .toUriString();
    }

    public String findUrl(String id) {
        return urlEntityRepository.findById(UUID.fromString(id))
                .map(UrlEntity::getUrl)
                .orElseThrow(() -> new UrlEntityNotFoundException(id));
    }
}
