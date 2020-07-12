package com.harnet.guesscelebrity.controller;

import com.harnet.guesscelebrity.model.SourceSite;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WebContentController {
    private SourceSite sourceSite = new SourceSite("imdb.com", "https://www.imdb.com/list/ls052283250/",
            "img alt=\"(.*?)\"","src=\"https://m.media-amazon.com/images/M/(.*?)\"","");
    private WebContentDownloader downloadWebContentController = new WebContentDownloader();

    private List<String> celebrityNames = new ArrayList<>();
    private List<String> celebrityPhotoLink = new ArrayList<>();

    public WebContentController() {
        downloadContent();
        parseContent("nameLastname", sourceSite.getPATTERN_NAME_LASTNAME());
        parseContent("photoLink", sourceSite.getPATTERN_PHOTO_LINK());
    }

    public List<String> getCelebrityNames() {
        return celebrityNames;
    }

    public List<String> getCelebrityPhotoLink() {
        return celebrityPhotoLink;
    }

    public SourceSite getSourceSite() {
        return sourceSite;
    }

    // downloads content of a source web page and put it in content field of SourceSite object
    public void downloadContent(){
        try {
            String content = downloadWebContentController.execute(sourceSite.getLink()).get();
            String trimmedContent = trimContent(content, "<div class=\"lister-list\">", "<div class=\"footer filmosearch\">");
            sourceSite.setContent(trimmedContent);
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private String trimContent(String content, String leftRegex, String rightRegex){
//        System.out.println(content);
        String[] leftTrimmedContent = content.split(leftRegex);
        String[] rightTrimmedContent = leftTrimmedContent[1].split(rightRegex);
//        System.out.println(rightTrimmedContent[0]);
        return rightTrimmedContent[0];
    }

    // TODO consistently parse a string and build Celebrity objects with name, lastname, link to a photo and info
    public void parseContent(String dataKind, String regex){
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(sourceSite.getContent());

        while(m.find()){
            switch (dataKind){
                case "nameLastname":
                    celebrityNames.add(m.group(1));
                    break;
                case "photoLink":
                    celebrityPhotoLink.add("https://m.media-amazon.com/images/M/"+m.group(1));
                    break;
            }
        }
    }
}
