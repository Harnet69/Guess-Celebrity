package com.harnet.guesscelebrity.model;

public class Celebrity {
    private String name;
    private String lastName;
    private String photoLink;
    private String info;

    public Celebrity(String name, String lastName, String photoLink, String info) {
        this.name = name;
        this.lastName = lastName;
        this.photoLink = photoLink;
        this.info = info;
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