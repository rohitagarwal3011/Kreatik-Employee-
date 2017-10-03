package com.app.rbc.employee.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.app.rbc.employee.R;
import com.app.rbc.employee.fragments.Attendance_all;
import com.app.rbc.employee.fragments.Attendance_emp_wise;
import com.app.rbc.employee.utils.AppUtil;
import com.app.rbc.employee.utils.ChangeFragment;
import com.app.rbc.employee.utils.TagsPreferences;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.rackspira.kristiawan.rackmonthpicker.RackMonthPicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class AttendanceActivity extends AppCompatActivity {

    public  FloatingActionButton fab;
    private String TAG = "AttendanceActivity";
    RackMonthPicker rackMonthPicker;

    Toolbar toolbar;
    Context context = AttendanceActivity.this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.hide();
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final com.app.rbc.employee.fragments.Attendance_all info = (com.app.rbc.employee.fragments.Attendance_all) getSupportFragmentManager().findFragmentByTag(com.app.rbc.employee.fragments.Attendance_all.TAG);
                info.show_mark_dialog();

            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Intent intent = getIntent();
        String date_send;
        try {


            if (intent.getStringExtra("date").length() > 0) {
                date_send = intent.getStringExtra("date");
            } else {
                date_send = String.valueOf(new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime()));
            }
        }
        catch (Exception e)
        {
            date_send = String.valueOf(new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime()));
            e.printStackTrace();
        }

        ChangeFragment.changeFragment(getSupportFragmentManager(),R.id.frame_main, new Attendance_emp_wise().newInstance(AppUtil.getString(context, TagsPreferences.USER_ID),AppUtil.getString(context,TagsPreferences.NAME),date_send),Attendance_emp_wise.TAG);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_attendance, menu);

        return true;
    }

    @Override
    public void onBackPressed() {
        if(getSupportFragmentManager().findFragmentByTag(com.app.rbc.employee.fragments.Attendance_emp_wise.TAG).isVisible()) {
            Intent intent = new Intent(AttendanceActivity.this, HomeActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        }
        else
            super.onBackPressed();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId())
        {
            case android.R.id.home :
                com.app.rbc.employee.utils.AppUtil.logger(TAG,"Home pressed");
                onBackPressed();
                return true;
            case R.id.search_attendance:
//                if(getSupportFragmentManager().findFragmentByTag(com.app.rbc.employee.fragments.Attendance_all.TAG).isVisible()){
//                    final com.app.rbc.employee.fragments.Attendance_all info = (com.app.rbc.employee.fragments.Attendance_all) getSupportFragmentManager().findFragmentByTag(com.app.rbc.employee.fragments.Attendance_all.TAG);
//                    info.show_dialog();
//
//                }
//                else if (getSupportFragmentManager().findFragmentByTag(com.app.rbc.employee.fragments.Attendance_emp_wise.TAG).isVisible()){
                final com.app.rbc.employee.fragments.Attendance_emp_wise info = (com.app.rbc.employee.fragments.Attendance_emp_wise) getSupportFragmentManager().findFragmentByTag(com.app.rbc.employee.fragments.Attendance_emp_wise.TAG);
                info.show_dialog();

//                }


            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void setToolbar(String title)
    {
        toolbar.setTitle(title);
    }

}
