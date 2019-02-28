package com.sinclairdontcare.synclair.receivers;

import android.bluetooth.BluetoothDevice;
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

        // Check if bluetooth made a connection
        if(intent.getAction().equals("android.bluetooth.device.action.ACL_CONNECTED")) {

            Log.i("MyBluetoothReceiver", "onReceive: ACL Connected");

            // Determine which bluetooth device connected
            BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
            String deviceName = device.getName();
            String deviceHardwareAddress = device.getAddress(); // MAC address

            Log.i("MyBluetoothReceiver" , "Device Name: " + deviceName);
            Log.i("MyBluetoothReceiver" , "Device MacAddress: "  + deviceHardwareAddress);

            if (deviceHardwareAddress.compareToIgnoreCase("FC:58:FA:79:96:B2") == 0 ) {
                // Open music app of choice by package name
                Intent launchIntent = context.getPackageManager().getLaunchIntentForPackage("com.google.android.music");
                context.startActivity(launchIntent);

                Log.i("MyBluetoothReceiver", "Music app of choice found");
            } else {
                Log.i("MyBluetoothReceiver", "Not the correct BT device to launch music app");
            }

        }

    }
}
