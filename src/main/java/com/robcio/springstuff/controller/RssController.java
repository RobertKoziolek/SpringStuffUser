package com.robcio.springstuff.controller;

import com.robcio.springstuff.dto.FeedModel;
import com.robcio.springstuff.service.RssService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.ResourceAccessException;

import java.util.List;

@Controller
@RequestMapping(path = "/rss")
@Api(value = "/rss", description = "Operations about RSS")
public class RssController {

    private static final Logger logger = LoggerFactory.getLogger(RssController.class);

    private final RssService rssService;

    @Autowired
    public RssController(final RssService rssService) {
        this.rssService = rssService;
    }

    @GetMapping(path = "/view")
    @ApiOperation(value = "Gets RSS feed from another service")
    public String getRssView(final Model model) {
        try {
            final List<FeedModel> rssList = rssService.getRss();
            model.addAttribute("rssList", rssList);
        } catch (final ResourceAccessException e) {
            final String errorMessage = "RSS server could not be reached";
            logger.error(errorMessage + e.getLocalizedMessage());
            model.addAttribute("error", errorMessage);
        }
        return "rss";
    }
}
