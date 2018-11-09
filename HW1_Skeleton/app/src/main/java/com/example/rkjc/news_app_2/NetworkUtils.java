package com.example.rkjc.news_app_2;

import android.net.Uri;
import android.os.AsyncTask;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class NetworkUtils {

    final static String NEWS_BASE_URL = "https://newsapi.org/v1/articles?source=the-next-web";



    final static String PARAM_SORT = "sortBy";
    final static String sortBy = "latest";

    final static String PARAM_API = "apiKey";
    final static String api = "ada68ac65392490aa649181fee31c3bb";

    public static URL buildUrl() {
        Uri builtUri = Uri.parse(NEWS_BASE_URL).buildUpon()
              // .appendQueryParameter(PARAM_QUERY, query)
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
    class NewsQueryTask extends AsyncTask<URL, String, String> {

        String jsonString;

        @Override
        protected String doInBackground(URL... params) {
            HttpURLConnection connection = null;
            URL url = NetworkUtils.buildUrl();

            try {
                jsonString = NetworkUtils.getResponseFromHttpUrl(url);
            } catch (IOException e) {
                e.printStackTrace();
            }

            return jsonString;
        }
    }
}

