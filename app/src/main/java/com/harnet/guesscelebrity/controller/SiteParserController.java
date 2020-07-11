package com.harnet.guesscelebrity.controller;

import android.util.Log;

import com.harnet.guesscelebrity.model.SourceSite;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SiteParserController {
    private SourceSite sourceSite = new SourceSite("imdb.com", "https://www.imdb.com/list/ls052283250/",
            "img alt=\"(.*?)\"","src=\"https://m.media-amazon.com/images/M/(.*?)\"","");
    private DownloadWebContentController downloadWebContentController = new DownloadWebContentController();
    private List<String> celebrityNames = new ArrayList<>();
    private List<String> celebrityPhotos = new ArrayList<>();

    public SiteParserController() {
        downloadContent();
        parseContent("nameLastname", sourceSite.getPATTERN_NAME_LASTNAME());
        parseContent("photoLink", sourceSite.getPATTERN_PHOTO_LINK());
//        parseContent("<img alt=\"(.*?)\">");
    }

    public List<String> getCelebrityNames() {
        return celebrityNames;
    }

    public List<String> getCelebrityPhotos() {
        return celebrityPhotos;
    }

    public SourceSite getSourceSite() {
        return sourceSite;
    }

    // downloads content of a source web page
    public void downloadContent(){
        try {
            String content = downloadWebContentController.execute(sourceSite.getLink()).get();
            //TODO print all page content
//            System.out.println(content);
            sourceSite.setContent(content);
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    // TODO consistently parse a string and build Celebrity objects with name, lastname, link to a photo and info
    public void parseContent(String dataKind, String regex){
//        Log.i("Regex", "parseContent: " + regex);
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(sourceSite.getContent());

        while(m.find()){
            switch (dataKind){
                case "nameLastname":
                    celebrityNames.add(m.group(1));
                    break;
                case "photoLink":
                    celebrityPhotos.add("https://m.media-amazon.com/images/M/"+m.group(1));
                    break;

            }
//        Log.i("SITE:", "parseContent: " + m.group(1));
//            System.out.println(m.group(1));
        }
    }
}
