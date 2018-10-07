package com.sinclairdontcare.synclair;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Set;

public class MainActivity extends AppCompatActivity {


    // Create object to hold default adapter
    // Required for any bluetooth activity
    BluetoothAdapter ba;
    // Define request codes
    private static final int REQUEST_ENABLE_BT = 0;
    // Define buttons and list
    Button btn_list, btn_open_player;
    ListView lv_content;

    private BroadcastReceiver br;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        ba = BluetoothAdapter.getDefaultAdapter();

        // Check if device supports bluetooth
        if (ba == null) {
            Toast.makeText(this, "No Bluetooth here, :-(", Toast.LENGTH_LONG).show();
            finish();
        } else {
            Toast.makeText(this, "You got bluetooth son, :-)", Toast.LENGTH_LONG).show();
        }


        // Enable Bluetooth
        if (!ba.isEnabled()) {
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
        }


        // Create button and add an onclick listener
        btn_list = (Button) findViewById(R.id.btn_list);
        lv_content = (ListView) findViewById(R.id.lv_content);
        btn_open_player = (Button) findViewById(R.id.btn_open_player);



        btn_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get paired devices
                Set<BluetoothDevice> pairedDevices = ba.getBondedDevices();
                // Create array to hold list
                ArrayList<String> devices = new ArrayList();

                if (pairedDevices.size() > 0) {
                    // There are paired devices. Get the name and address of each paired device.
                    for (BluetoothDevice device : pairedDevices) {
                        String deviceName = device.getName();
                        String deviceHardwareAddress = device.getAddress(); // MAC address

                        devices.add(deviceName + "\nMac: "+deviceHardwareAddress +
                                "\nBT Class: " + device.getBluetoothClass() +
                                "\nBond State: " + device.getBondState() +
                                "\nType: " + device.getType());
                    }
                }


                ArrayAdapter arrayAdapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1, devices);
                lv_content.setAdapter(arrayAdapter);
            }
        });


        // Service
        // https://developer.android.com/reference/android/app/Service.html
        // https://developer.android.com/guide/components/services.html
        // https://developer.android.com/training/run-background-service/create-service.html
        btn_open_player.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // HA

                String packages;
                PackageManager pm;
                pm = getPackageManager(); //getApplicationContext().getPackageManager();
                packages = pm.getInstalledApplications(0).toString();

                TextView textView = findViewById(R.id.textView2);
                textView.setMovementMethod(new ScrollingMovementMethod());
                textView.setText(packages);

                // Open app by package name
                Intent launchIntent = getPackageManager().getLaunchIntentForPackage("com.google.android.music");
                startActivity(launchIntent);

            }
        });


    //    br = new MyBluetoothReceiver();
    //    IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_ACL_CONNECTED);
    //    filter.addAction(BluetoothDevice.ACTION_ACL_DISCONNECTED);
    //    registerReceiver(br, filter);
    }



    public void go(View view) {
        String mds = "asdf\nkjkj";
        TextView textView = findViewById(R.id.textView2);
        textView.setText(mds);

    }



    class MyBluetoothReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            Intent launchIntent = getPackageManager().getLaunchIntentForPackage("com.google.android.music");
            startActivity(launchIntent);
            if(intent.getAction().equals("android.bluetooth.BluetoothDevice.ACTION_ACL_CONNECTED")){

                // code for Bluetooth connect
                // Open app by package name

            }
            if(intent.getAction().equals("android.bluetooth.device.action.ACL_DISCONNECTED")){

                //code for Bluetooth disconnect;
            }

        }
    }


}
