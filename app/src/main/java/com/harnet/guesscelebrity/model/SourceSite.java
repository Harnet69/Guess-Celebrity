package com.harnet.guesscelebrity.model;

public class SourceSite {
    private String name;
    private String link;
    private String content;

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
