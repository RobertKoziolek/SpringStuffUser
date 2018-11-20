package com.robcio.springstuff.controller;

import com.robcio.springstuff.service.RssService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/rss")
@Api(value = "/rss", description = "Operations about RSS")
public class RssController {

    private final RssService rssService;

    @Autowired
    public RssController(final RssService rssService) {
        this.rssService = rssService;
    }

    @GetMapping(path = "/view")
    @ApiOperation(value = "Gets RSS feed from another service")
    public String getRssView(final Model model) {
        model.addAttribute("rssList", rssService.getRss());
        return "rss";
    }
}
