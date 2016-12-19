package com.example.noddin.hscardsgetter;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by Selim on 17/12/2016.
 */

public class CardsUpdate extends BroadcastReceiver {
    onListener listener;

    CardsUpdate(onListener listener){
        this.listener = listener;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, R.string.download_complete, Toast.LENGTH_LONG).show();
        listener.onFinish();
    }
}
