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

    public void addCelebrityToList(String name, String lastName, String photoLink, String info){
        celebrities.add(new Celebrity( name, lastName, photoLink, info));
    }
}
