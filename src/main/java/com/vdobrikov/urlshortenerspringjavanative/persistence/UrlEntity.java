package com.vdobrikov.urlshortenerspringjavanative.persistence;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "url")
public class UrlEntity {
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.UUID)
    @Id
    private UUID id;

    @Column(name = "url", unique = true, nullable = false, length = 512)
    private String url;

    @Column(name = "hash", unique = true, nullable = false, length = 10)
    private String hash;

    @Builder.Default
    @Column(name = "count", nullable = false)
    private long usageCount = 0;

    @Builder.Default
    @Column(name = "last_used", nullable = false)
    private Instant lastUsed = Instant.now();
}
