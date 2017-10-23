package com.zykov.andrii.azseotools.tasks;

import android.os.AsyncTask;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

/**
 * Created by andrii on 10/21/17.
 */

public class ParseHtmlTask extends AsyncTask<Void, Void, Void> {

    private ParseHTMLTaskListener listener;

    private String url;

    public interface ParseHTMLTaskListener {
        void onSuccess(Document document);
        void onError(String error);
    }

    public ParseHtmlTask(String url, ParseHTMLTaskListener listener) {
        this.listener = listener;
        this.url = url;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Void doInBackground(Void... params) {
        try {
            // Connect to the web site
            Document document = Jsoup.connect("http://" + url).get();
            // Get the html document title
//            title = document.title();
            listener.onSuccess(document);
        } catch (Exception e) {
            listener.onError("Error");
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void result) {}
}
