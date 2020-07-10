package com.harnet.guesscelebrity.model;

public class SourceSite {
    private String name;
    private String link;
    private String content;
    private final String PATTERN_NAME_LASTNAME;
    private final String PATTERN_PHOTO_LINK;
    private final String PATTERN_INFO;

    public SourceSite(String name, String link, String patternNameLastname, String patternPhotoLink, String patternInfo) {
        this.name = name;
        this.link = link;
        PATTERN_NAME_LASTNAME = patternNameLastname;
        PATTERN_PHOTO_LINK = patternPhotoLink;
        PATTERN_INFO = patternInfo;
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
