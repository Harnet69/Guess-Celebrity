package com.harnet.knowyourstaff.model;

public class Person {
    private String name;
    private String photoLink;
    private Boolean isGuessed = null;

    public Person(String name, String photoLink ) {
        this.name = name;
        this.photoLink = photoLink;
    }

    public String getName() {
        return name;
    }

    public String getPhotoLink() {
        return photoLink;
    }

    public Boolean isGuessed() {
        return isGuessed;
    }

    public void setGuessed(Boolean guessed) {
        isGuessed = guessed;
    }
}