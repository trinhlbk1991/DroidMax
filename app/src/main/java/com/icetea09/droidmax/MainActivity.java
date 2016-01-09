package com.icetea09.droidmax;

import android.os.Bundle;
import android.support.annotation.AnimRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.icetea09.droidmax.fragment.MainRuleFragment;


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

}
