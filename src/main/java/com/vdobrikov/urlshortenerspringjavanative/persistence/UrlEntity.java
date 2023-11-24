package com.vdobrikov.urlshortenerspringjavanative.persistence;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity(name = "url")
public class UrlEntity {
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.UUID)
    @Id
    private UUID id;
    @Column(name = "url", unique = true, nullable = false, length = 512)
    private String url;

    public UrlEntity() {
    }

    public UrlEntity(String url) {
        this.url = url;
    }
}
