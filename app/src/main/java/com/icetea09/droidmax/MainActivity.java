package com.icetea09.droidmax;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.icetea09.droidmax.actions.BluetoothAction.BlueToothAction;
import com.icetea09.droidmax.actions.ChangeWifiAction.WifiAction;
import com.icetea09.droidmax.actions.GPSAction.GpsAction;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
}
