package com.sinclairdontcare.synclair.receivers;

import android.content.Context;
import android.content.Intent;
import android.content.BroadcastReceiver;
import android.util.Log;

/**
 * Created by arturo on 3/25/18.
 */

public class MyBluetoothReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        Intent launchIntent = context.getPackageManager().getLaunchIntentForPackage("com.google.android.music");
        context.startActivity(launchIntent);

        if(intent.getAction().equals("android.bluetooth.BluetoothDevice.ACTION_ACL_CONNECTED")){

            Log.i("MyBluetoothReceiver", "onReceive: ACL Connected");
            // code for Bluetooth connect
            // Open app by package name

        }
        if(intent.getAction().equals("android.bluetooth.device.action.ACL_DISCONNECTED")){

            //code for Bluetooth disconnect;
            Log.i("MyBluetoothReceiver", "onReceive: ACL Disconnected");
        }

    }
}
