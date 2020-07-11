package com.harnet.guesscelebrity.controller;

import com.harnet.guesscelebrity.model.Celebrity;

import java.util.ArrayList;
import java.util.List;

public class CelebrityController {
    private static CelebrityController instance;
    private List<Celebrity> celebrities = new ArrayList<>();
    private SiteParserController siteParserController;

    private CelebrityController() {
        siteParserController = new SiteParserController();
        makeCelebritiesList();
    }

    public static CelebrityController getInstance() {
        if(instance == null){
            instance = new CelebrityController();
        }
        return instance;
    }

    public List<Celebrity> getCelebrities() {
        return celebrities;
    }

    public SiteParserController getSiteParserController() {
        return siteParserController;
    }

    public void makeCelebritiesList(){
        for(int i=0; i< siteParserController.getCelebrityNames().size(); i++){
            addCelebrityToList(siteParserController.getCelebrityNames().get(i), siteParserController.getCelebrityPhotos().get(i));
        }
    }

    public void addCelebrityToList(String name, String photoLink){
        celebrities.add(new Celebrity( name, photoLink));
    }
}
