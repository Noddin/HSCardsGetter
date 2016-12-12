package com.example.noddin.hscardsgetter;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Noddin on 28/11/2016.
 */

public class AllCards_Activity extends MainContainer_Abstract {


    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_allcards);
    }
}
