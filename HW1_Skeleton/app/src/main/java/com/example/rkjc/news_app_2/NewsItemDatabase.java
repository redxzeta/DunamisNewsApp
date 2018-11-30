package com.example.rkjc.news_app_2;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities = {NewsItem.class}, version = 1)
public abstract class NewsItemDatabase extends RoomDatabase {
    public abstract NewsItemDao newsitemDao();

    private static volatile NewsItemDatabase INSTANCE;
    static  NewsItemDatabase getDatabase(final Context context) {
        if(INSTANCE == null) {
            synchronized (NewsItemDatabase.class) {
                if(INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),NewsItemDatabase.class, "newsitem_database").build();
                }
            }
        }
        return INSTANCE;
    }
}
