package com.vdobrikov.urlshortenerspringjavanative.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UrlEntityRepository extends JpaRepository<UrlEntity, UUID> {
    Optional<UrlEntity> findByUrl(String url);
    Optional<UrlEntity> findByHash(String hash);
}
