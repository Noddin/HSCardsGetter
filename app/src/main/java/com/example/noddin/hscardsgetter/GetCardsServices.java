package com.example.noddin.hscardsgetter;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class GetCardsServices extends IntentService {

    private static final String ACTION_GET_ALL_CARDS = "com.example.noddin.hscardsgetter.action.GET_ALL_CARDS";

    public GetCardsServices() {
        super("GetCardsServices");
    }

    public static void startActionCards(Context context) {
        Intent intent = new Intent(context, GetCardsServices.class);
        intent.setAction(ACTION_GET_ALL_CARDS);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_GET_ALL_CARDS.equals(action)) {
                handleActionCards();
                LocalBroadcastManager.getInstance(this).sendBroadcast(new Intent(AllCards_Activity.CARDS_UPDATE));
            }
        }
    }

    private void handleActionCards () {
        URL url = null;
        try {
            url = new URL("https://omgvamp-hearthstone-v1.p.mashape.com/cards/classes/Hunter");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestProperty ("X-Mashape-Key", "AYI7bfnzl7mshjUxuIIuAGgDjxNQp1cVJkVjsnM16XQb6t9Xuo");
            conn.setRequestMethod("GET");
            conn.connect();
            if (HttpURLConnection.HTTP_OK == conn.getResponseCode()) {
                copyInputStreamToFile(conn.getInputStream(), new File(getCacheDir(), "allCard.json"));
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Log.i("tag", "Message");
    }

    private void copyInputStreamToFile(InputStream in, File file) {
        try {
            OutputStream out = new FileOutputStream(file);
            byte[] buf = new byte[1024];
            int len;
            while ((len = in.read(buf)) > 0) {
                out.write(buf, 0, len);
            }
            out.close();
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
