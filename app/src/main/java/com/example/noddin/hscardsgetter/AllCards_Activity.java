package com.example.noddin.hscardsgetter;

import android.content.IntentFilter;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.design.widget.TabLayout;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Noddin on 28/11/2016.
 */

public class AllCards_Activity extends AppCompatActivity implements onListener{

    public static final String CARDS_UPDATE="com.example.noddin.hscardsgetter.action.CARDS_UPDATE";
    public CardsAdapter cardsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_allcards);

        IntentFilter intentFilter = new IntentFilter(CARDS_UPDATE);
        LocalBroadcastManager.getInstance(this).registerReceiver(new CardsUpdate(this), intentFilter);

        GetCardsServices.startActionCards(this);

        final RecyclerView rv = (RecyclerView)findViewById(R.id.rv_card);
        rv.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));

        cardsAdapter = new CardsAdapter(getCardsFromFile());

        rv.setAdapter(cardsAdapter);
    }

    public JSONArray getCardsFromFile(){
        try{
            InputStream is = new FileInputStream(getCacheDir() + "/" + "cards");
            byte[] buffer = new byte[is.available()];
            is.read(buffer);
            is.close();
            return new JSONArray(new String(buffer,"UTF-8"));
        } catch (IOException e){
            e.printStackTrace();
            return new JSONArray();
        } catch (JSONException e){
            e.printStackTrace();
            return new JSONArray();
        }
    }

    public void onFinish() {
        cardsAdapter.setNewCard(getCardsFromFile());
    }
}
