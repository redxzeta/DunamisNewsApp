package com.example.rkjc.news_app_2;

import android.net.Uri;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class NetworkUtils {
    //final static String NEWS_BASE_URL = "https://newsapi.org/v2/everything?q=blizzard&from=latest6&sortBy=publishedAt&apiKey=ada68ac65392490aa649181fee31c3bb";
    final static String NEWS_BASE_URL = "https://newsapi.org/v2/everything?";
    final static String PARAM_QUERY = "q";
    final static String query = "blizzard";


    final static String PARAM_SORT = "sortBy";
    final static String sortBy = "publishedAt";
    final static String PARAM_API = "apiKey";
    final static String api = "ada68ac65392490aa649181fee31c3bb";

    public static URL buildUrl() {
        Uri builtUri = Uri.parse(NEWS_BASE_URL).buildUpon()
                .appendQueryParameter(PARAM_QUERY, query)
                .appendQueryParameter(PARAM_SORT, sortBy)
                .appendQueryParameter(PARAM_API, api)

                .build();

        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return url;
    }
    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if(hasInput) {
                return scanner.next();
            } else {
                return null;
            }

        } finally {
            urlConnection.disconnect();
        }
    }
}
