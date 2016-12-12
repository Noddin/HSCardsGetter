package com.example.noddin.hscardsgetter;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

/**
 * Created by Noddin on 28/11/2016.
 */

public abstract class MainContainer_Abstract extends AppCompatActivity {

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_navigator, menu); //create a menu, links it to the menu argument
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.all_cards:
                startActivity(new Intent(this,AllCards_Activity.class));
                break;

            case R.id.seek_cards:
                startActivity(new Intent(this, SeekCards_Activity.class));
                break;

            case R.id.sort_classes:
                startActivity(new Intent(this, SeekCards_Activity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
