package com.example.broadcastigmessages;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;
import com.google.android.material.snackbar.Snackbar;



public class BroadcastExample extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        boolean isPlaneMode = intent.getBooleanExtra("state", false);

        if(isPlaneMode == true){
            Toast.makeText(context, "The device has switched to Airplane Mode", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "The device is not in Airplane Mode", Toast.LENGTH_SHORT).show();
        }
//        Snackbar.make(context, "The device has switched to Airplane Mode.", Snackbar.LENGTH_SHORT).setAction("x", new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        }).show();


    }
}
