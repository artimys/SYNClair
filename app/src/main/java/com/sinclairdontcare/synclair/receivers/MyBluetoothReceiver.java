package com.sinclairdontcare.synclair.receivers;

import android.content.Context;
import android.content.Intent;
import android.content.BroadcastReceiver;

/**
 * Created by arturo on 3/25/18.
 */

class MyBluetoothReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        Intent launchIntent = context.getPackageManager().getLaunchIntentForPackage("com.google.android.music");
        context.startActivity(launchIntent);

        if(intent.getAction().equals("android.bluetooth.BluetoothDevice.ACTION_ACL_CONNECTED")){

            // code for Bluetooth connect
            // Open app by package name

        }
        if(intent.getAction().equals("android.bluetooth.device.action.ACL_DISCONNECTED")){

            //code for Bluetooth disconnect;
        }

    }
}
