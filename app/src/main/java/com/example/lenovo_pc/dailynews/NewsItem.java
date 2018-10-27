package com.example.lenovo_pc.dailynews;

public class NewsItem {
    private String newsTitle;
    private String link;
    private String originalDate;
    private String markDate;

    public NewsItem() {
        super();
        newsTitle = "";
        link = "";
        originalDate = "";
        markDate = "";
    }
    public NewsItem(String newsTitle,String link,String originalDate,String markDate) {
        super();
        this.newsTitle = newsTitle;
        this.link = link;
        this.originalDate = originalDate;
        this.markDate = markDate;
    }

    public String getNewsTitle() {
        return newsTitle;
    }

    public void setNewsTitle(String newsTitle) {
        this.newsTitle = newsTitle;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getOriginalDate() {
        return originalDate;
    }

    public void setOriginalDate(String originalDate) {
        this.originalDate = originalDate;
    }

    public String getMarkDate() {
        return markDate;
    }

    public void setMarkDate(String markDate) {
        this.markDate = markDate;
    }
}
