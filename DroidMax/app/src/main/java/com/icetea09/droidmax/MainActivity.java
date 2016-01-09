package com.icetea09.droidmax;

import android.location.Location;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.icetea09.droidmax.receiver.WifiReceiver;
import com.icetea09.droidmax.rules.location.ExitSpecialLocation;
import com.icetea09.droidmax.rules.location.SpecialLocation;

public class MainActivity extends AppCompatActivity {

    String TAG = MainActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ExitSpecialLocation specialLocation = new ExitSpecialLocation("abc","10.779660", "106.663330");
        Location testLocation = new Location("network");
        testLocation.setLongitude(9.779660);
        testLocation.setLatitude(16.663330);
        specialLocation.setUserCurrentLocation(testLocation);
        Log.d(TAG, specialLocation.isSatisfied() + "");
    }

}
