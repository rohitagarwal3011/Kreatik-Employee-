package com.app.rbc.employee.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.app.rbc.employee.R;
import com.app.rbc.employee.models.RequirementDetails;
import com.app.rbc.employee.models.db.models.site_overview.Order;
import com.app.rbc.employee.utils.ChangeFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RequirementDetailActivity extends AppCompatActivity {



    public RequirementDetails requirementDetails;
    public Toolbar toolbar;
    public static String rq_id;
    public static String req_site_name;
    public static String req_site_id;
    public static List<String> product_list = new ArrayList<>();
    @BindView(R.id.frame_main)
    FrameLayout frameMain;

    private String category_selected;

    public List<Order> orders = new ArrayList<>();
    public Bundle finalform;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_requirement_detail);
        ButterKnife.bind(this);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        rq_id = intent.getStringExtra("rq_id");
        category_selected = intent.getStringExtra("category_selected");
        ChangeFragment.changeFragment(getSupportFragmentManager(), R.id.frame_main, com.app.rbc.employee.fragments.RequirementDetails.newInstance(category_selected), com.app.rbc.employee.fragments.RequirementDetails.TAG);

    }


    public void  setToolbar(String title)
    {
        toolbar.setTitle(title);
    }


    @Override
    public void onBackPressed() {

        Fragment mFragment = getSupportFragmentManager().findFragmentById(R.id.frame_main);
        if (mFragment instanceof com.app.rbc.employee.fragments.RequirementDetails)
        {
            getSupportFragmentManager().popBackStackImmediate();
        }

            super.onBackPressed();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_requirement, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if(id == android.R.id.home)
        {
            onBackPressed();
        }

        return super.onOptionsItemSelected(item);
    }


}
