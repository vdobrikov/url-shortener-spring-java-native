package com.vdobrikov.urlshortenerspringjavanative.service;

import com.vdobrikov.urlshortenerspringjavanative.exception.UrlEntityNotFoundException;
import com.vdobrikov.urlshortenerspringjavanative.persistence.UrlEntity;
import com.vdobrikov.urlshortenerspringjavanative.persistence.UrlEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.time.Instant;
import java.util.zip.CRC32;

@RequiredArgsConstructor
@Service
public class UrlService {
    private final UrlEntityRepository urlEntityRepository;

    public String shortenUrl(String url) {
        final String normalizedUrl = url.trim().toLowerCase();
        UrlEntity urlEntity = urlEntityRepository.findByUrl(normalizedUrl)
                .orElseGet(() -> urlEntityRepository.save(createNewUrlEntity(normalizedUrl)));

        return ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(urlEntity.getHash())
                .build()
                .toUriString();
    }

    public String findUrlAndIncrementUsage(String hash) {
        return urlEntityRepository.findByHash(hash)
                .map(this::incrementUsage)
                .map(UrlEntity::getUrl)
                .orElseThrow(() -> new UrlEntityNotFoundException(hash));
    }

    private UrlEntity incrementUsage(UrlEntity urlEntity) {
        urlEntity.setCount(urlEntity.getCount() + 1);
        return urlEntityRepository.save(urlEntity);
    }

    private UrlEntity createNewUrlEntity(String url) {
        return UrlEntity.builder()
                .url(url)
                .hash(generateHash(url))
                .count(0)
                .lastUsed(Instant.now())
                .build();
    }

    private String generateHash(String url) {
        CRC32 crc32 = new CRC32();
        crc32.update(url.getBytes());
        return Long.toHexString(crc32.getValue());
    }
}
