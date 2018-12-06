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
   // private String urlToImage;
    private String publishedAt;
/*
    public String getUrlToImage() {
        return urlToImage;
    }

    public void setUrlToImage(String urlToImage) {
        this.urlToImage = urlToImage;
    }
*/


    @Ignore
    public NewsItem( String title, String description, String url, String publishedAt) {
      //  this.urlToImage=urlToImage;
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
        //this.urlToImage=urlToImage;
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
