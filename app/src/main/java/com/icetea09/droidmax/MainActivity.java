package com.icetea09.droidmax;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.AnimRes;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.icetea09.droidmax.actions.WifiAction;
import com.icetea09.droidmax.actions.IAction;
import com.icetea09.droidmax.actions.PhoneModeActions;
import com.icetea09.droidmax.actions.NotificationAction;
import com.icetea09.droidmax.fragment.MainRuleFragment;
import com.icetea09.droidmax.model.Rule;
import com.icetea09.droidmax.rules.IRule;
import com.icetea09.droidmax.rules.battery.BatteryRule;
import com.icetea09.droidmax.rules.location.LocationRule;
import com.icetea09.droidmax.rules.network.WifiRule;
import com.icetea09.droidmax.rules.weatherforecast.WeatherForecastRule;
import com.icetea09.droidmax.utils.NotificationUtil;

import java.util.List;


public class MainActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getSimpleName();

    private FragmentManager mFragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mFragmentManager = getSupportFragmentManager();
        setFragment(MainRuleFragment.newInstance(), false);
    }

    public void setFragment(Fragment fragment, boolean isAddToBackStack) {
        setFragment(fragment, isAddToBackStack, R.anim.slide_in_from_right, R.anim.slide_out_to_left,
                R.anim.slide_in_from_left, R.anim.slide_out_to_right);
    }

    public void setFragment(Fragment fragment, boolean isAddToBackStack,
                            @AnimRes int enter, @AnimRes int exit, @AnimRes int popEnter,
                            @android.support.annotation.AnimRes int popExit) {
        try {
            FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
            fragmentTransaction.setCustomAnimations(enter, exit, popEnter, popExit);
            fragmentTransaction.add(R.id.fragment_container, fragment, fragment.getClass().getSimpleName());
            if (isAddToBackStack) {
                fragmentTransaction.addToBackStack(fragment.getClass().getSimpleName());
            }
            fragmentTransaction.commit();
        } catch (Exception ex) {
            Log.e(TAG, ex.getMessage());
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    private boolean isFragmentVisible(String tag) {
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        return fragment != null && fragment.getClass().getSimpleName().equalsIgnoreCase(tag);
    }

    public static void doCheckAutoTasks(final Context context, final Intent intent, final List<Rule> rules) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (Rule rule : rules) {
                    Log.d(TAG, "Checking rule: " + rule.getName());
                    setRequiredProperties(rule, context, intent);
                    boolean isSatisfied = true;
                    for (IRule condition : rule.getConditions()) {
                        isSatisfied = condition.isSatisfied();
                        if (!isSatisfied) {
                            break;
                        }
                    }

                    if (isSatisfied) {
                        for (IAction action : rule.getActions()) {
                            action.perform();
                        }
                        NotificationUtil.showNotification(context, rule.getName(), rule.getDescription());
                    }
                }
            }
        }).start();
    }

    @SuppressWarnings("deprecation")
    private static void setRequiredProperties(Rule rule, Context context, Intent intent) {
        for (IRule condition : rule.getConditions()) {
            if (condition instanceof BatteryRule) {
                ((BatteryRule) condition).setIntent(intent);
            } else if (condition instanceof LocationRule) {
                if (!(ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                        ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)) {
                    LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
                    ((LocationRule) condition).setCurrentLocation(locationManager.getLastKnownLocation("network"));
                }
            } else if (condition instanceof WifiRule) {
                ((WifiRule) condition).setContext(context);
            } else if (condition instanceof WeatherForecastRule) {
                if (!(ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                        ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)) {
                    LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
                    Location currentLocation = locationManager.getLastKnownLocation("network");
                    if (currentLocation != null) {
                        ((WeatherForecastRule) condition).setCurrentLocation(currentLocation.getLongitude(), currentLocation.getLatitude());
                    } else {
                        ((WeatherForecastRule) condition).setCurrentLocation(106, 10);
                    }
                }
            }
        }

        for (IAction action : rule.getActions()) {
            if (action instanceof WifiAction) {
                ((WifiAction) action).setContext(context);
            } else if (action instanceof PhoneModeActions) {
                ((PhoneModeActions) action).setContext(context);
            } else if (action instanceof NotificationAction) {
                ((NotificationAction) action).setContext(context);
            }
        }
    }

}
