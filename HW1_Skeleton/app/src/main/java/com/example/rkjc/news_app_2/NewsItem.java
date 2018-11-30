package com.example.rkjc.news_app_2;


import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "news_item")
public class NewsItem {
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String title;
    private String description;
    private String url;

    private String publishedAt;

    @Ignore
    public NewsItem( String title, String description, String url, String publishedAt) {

        this.title=title;
        this.description=description;
        this.url=url;

        this.publishedAt=publishedAt;

    }

    public NewsItem(int id, String title, String description, String url, String publishedAt) {
        this.id=id;
        this.title=title;
        this.description=description;
        this.url=url;

        this.publishedAt=publishedAt;

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

}
