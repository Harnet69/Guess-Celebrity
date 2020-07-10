package com.harnet.guesscelebrity.controller;

import android.util.Log;

import com.harnet.guesscelebrity.model.SourceSite;

import java.util.concurrent.ExecutionException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SiteParserController {
    private SourceSite sourceSite = new SourceSite("imdb.com", "https://www.imdb.com/list/ls052283250/",
            "","","");
    private DownloadWebContentController downloadWebContentController = new DownloadWebContentController();

    public SiteParserController() {
        downloadContent();
        parseContent();
    }

    public SourceSite getSourceSite() {
        return sourceSite;
    }

    // downloads content of a source web page
    public void downloadContent(){
        try {
            sourceSite.setContent(downloadWebContentController.execute(sourceSite.getLink()).get());
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    // TODO consistently parse a string and build Celebrity objects with name, lastmane, link to a photo and info
    public void parseContent(){
        Pattern name_lastmane = Pattern.compile("img alt=\"(.*?)\"\n");
        Matcher m = name_lastmane.matcher(sourceSite.getContent());

        while(m.find()){
        Log.i("SITE:", "parseContent: " + m.group(1));
//            System.out.println(m.group(1));
        }
    }
}
