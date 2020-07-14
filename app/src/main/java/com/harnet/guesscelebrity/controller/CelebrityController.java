package com.harnet.guesscelebrity.controller;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.harnet.guesscelebrity.model.Celebrity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class CelebrityController {
    private static CelebrityController instance;
    private List<Celebrity> celebrities = new ArrayList<>();
    private WebContentController siteParserController;

    private CelebrityController() {
        siteParserController = new WebContentController();
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

    public WebContentController getSiteParserController() {
        return siteParserController;
    }

    public void makeCelebritiesList(){
        for(int i=0; i< siteParserController.getCelebrityNames().size(); i++){
            addCelebrityToList(siteParserController.getCelebrityNames().get(i), siteParserController.getCelebrityPhotoLink().get(i));
        }
    }

    public void addCelebrityToList(String name, String photoLink){
        celebrities.add(new Celebrity( name, photoLink));
    }

    // mark a celebrity as guessed
    @RequiresApi(api = Build.VERSION_CODES.N)
    public Celebrity getCelebrityByName(final String celebrityName){
        Optional<Celebrity> celebrityOpt = CelebrityController.getInstance().getCelebrities().stream()
                .filter(p -> p.getName().equals(celebrityName))
                .findFirst();
        return celebrityOpt.orElse(null);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public List<Celebrity> getListOfCelebritiesByGuess(Boolean isGuessed){
        return celebrities.stream()
                .filter(p -> p.isGuessed() == isGuessed)
                .collect(Collectors.toList());
    }
}
