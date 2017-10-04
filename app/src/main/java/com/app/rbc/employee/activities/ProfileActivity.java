package com.app.rbc.employee.activities;

import android.support.multidex.MultiDex;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.app.rbc.employee.R;
import com.app.rbc.employee.fragments.MyDetailsFragment;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.orm.SugarContext;

public class ProfileActivity extends AppCompatActivity {

    private MyDetailsFragment myDetailsFragment;
    private FragmentManager fm;
    private Toolbar toolbar;
    public static final int ACTIVITY = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        SugarContext.init(this);
        Fresco.initialize(this);
        MultiDex.install(this);
        setFragment(1);
    }

    private void setFragment(int code) {
        fm = getSupportFragmentManager();
        switch (code ){
            case 1: myDetailsFragment = new MyDetailsFragment();
                fm.beginTransaction()
                        .replace(R.id.fragment_container, myDetailsFragment)
                        .commit();
                break;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId())
        {
            case android.R.id.home :
                onBackPressed();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }




    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }

    public void popBackStack() {
        getSupportFragmentManager().popBackStackImmediate();
    }



    public void publishAPIResponse(int status,int code,String... message) {
        switch (code) {
            case 12:
            case 14:
            case 15:
                myDetailsFragment.publishAPIResponse(status,code,message[0]);
                break;

        }
    }
}
