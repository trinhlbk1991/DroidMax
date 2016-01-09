package com.icetea09.droidmax;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.icetea09.droidmax.receiver.WifiReceiver;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new WifiReceiver();
    }

}
