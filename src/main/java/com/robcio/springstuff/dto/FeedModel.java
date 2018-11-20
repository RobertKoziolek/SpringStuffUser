package com.robcio.springstuff.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class FeedModel {
    private String title;
    private String link;
    private String imgUrl;
    private String content;
}
