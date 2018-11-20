package com.robcio.springstuff.service;

import com.robcio.springstuff.configuration.RssSimplifierRestTemplateConfiguration;
import com.robcio.springstuff.dto.FeedModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class RssService {

    private final RssSimplifierRestTemplateConfiguration configuration;

    private final RestTemplate rssRestTemplate;

    @Autowired
    public RssService(final RssSimplifierRestTemplateConfiguration configuration,
                      @Qualifier("rssRestTemplate") final RestTemplate restTemplate) {
        this.configuration = configuration;
        this.rssRestTemplate = restTemplate;
    }

    public List<FeedModel> getRss() {
        final ResponseEntity<List<FeedModel>> responseEntity = rssRestTemplate.exchange(configuration.getRssPath(),
                                                                                        HttpMethod.GET, null,
                                                                                        new ParameterizedTypeReference<List<FeedModel>>() {
                                                                                        });
        return responseEntity.getBody();
    }
}
