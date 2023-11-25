package com.vdobrikov.urlshortenerspringjavanative.web;

import com.vdobrikov.urlshortenerspringjavanative.persistence.UrlEntity;
import org.springframework.stereotype.Component;

@Component
public class UrlMapper {
    public Url fromEntityToDto(UrlEntity urlEntity) {
        return new Url(urlEntity.getUrl(), urlEntity.getHash(), urlEntity.getUsageCount(), urlEntity.getLastUsed());
    }
}
