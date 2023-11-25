package com.vdobrikov.urlshortenerspringjavanative.web;

import java.time.Instant;

public record Url(String url, String hash, long usageCount, Instant lastUsed) {
}
