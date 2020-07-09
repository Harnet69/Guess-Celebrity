package com.harnet.guesscelebrity.model;

public class SourceSite {
    private String name;
    private String link;
    private String context;

    public SourceSite(String name, String link) {
        this.name = name;
        this.link = link;
    }

    public String getName() {
        return name;
    }

    public String getLink() {
        return link;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }
}
