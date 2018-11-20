package com.robcio.springstuff.configuration;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;

@Configuration
public class RssSimplifierRestTemplateConfiguration {

    @Value("${rsssimplifier.host}")
    private String host;

    @Value("${rsssimplifier.port}")
    private String port;

    @Getter
    @Value("${rsssimplifier.get.rss}")
    private String rssPath;

    @Bean
    public RestTemplate rssRestTemplate() {
        final RestTemplate restTemplate = new RestTemplate();
        restTemplate.setUriTemplateHandler(new DefaultUriBuilderFactory(String.format("%s:%s", host, port)));
        return restTemplate;
    }
}
