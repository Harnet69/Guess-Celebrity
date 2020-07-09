package com.harnet.guesscelebrity.controller;

import com.harnet.guesscelebrity.model.SourceSite;

import java.util.concurrent.ExecutionException;

public class SiteParserController {
    private SourceSite sourceSite = new SourceSite("imdb.com", "https://www.imdb.com/list/ls052283250/");
    private DownloadWebContentController downloadWebContentController = new DownloadWebContentController();

    public SourceSite getSourceSite() {
        return sourceSite;
    }

    public void parse(){
        try {
            sourceSite.setContext(downloadWebContentController.execute(sourceSite.getLink()).get());
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
