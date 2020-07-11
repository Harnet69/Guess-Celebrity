package com.harnet.guesscelebrity.model;

public class Celebrity {
    private String name;
    private String lastName;
    private String photoLink;
    private String info;

    public Celebrity(String name, String photoLink ) {
        this.name = name;
        this.photoLink = photoLink;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getInfo() {
        return info;
    }

    public String getPhotoLink() {
        return photoLink;
    }
}