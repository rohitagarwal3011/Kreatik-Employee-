package com.app.rbc.employee.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.app.rbc.employee.R;
import com.app.rbc.employee.activities.HomeActivity;
import com.app.rbc.employee.api.APIController;
import com.app.rbc.employee.models.db.models.Categoryproduct;
import com.app.rbc.employee.models.db.models.Employee;
import com.app.rbc.employee.models.db.models.Vendor;

public class InitialSyncFragment extends Fragment {

    private View view;
    private ProgressBar progressBar;
    private APIController controller;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_initial_sync, container, false);
        initializeUI();
        return view;
    }

    private void initializeUI() {
        progressBar = (ProgressBar) view.findViewById(R.id.progressBar);
        callInitialSyncApis();
    }

    private void callInitialSyncApis() {
        if(Employee.count(Employee.class)
                == 0) {
            controller = new APIController(getContext(),0,1);
            controller.fetchEmp();
        }
        else {
            publichApiResponse(2,0);
        }
    }

    public void publichApiResponse(int status,int code,String... message) {
        switch (code) {
            case 0:
                if(status == 2) {
                    controller = new APIController(getContext(),
                            1,
                            1);
                    controller.fetchSites();
                }
                else {
                    publichApiResponse(2,1);
                }
                break;
            case 1:
                if(status == 2) {
                    if(Vendor.count(Vendor.class) == 0) {
                        controller = new APIController(getContext(),2,1);
                        controller.fetchVendors();
                    }
                    else {
                        publichApiResponse(2,2);
                    }
                }
                else {
                    publishError();
                }
                break;
            case 2:
                if(status == 2) {
                    if(Categoryproduct.count(Categoryproduct.class) == 0) {
                        controller = new APIController(getContext(),3,1);
                        controller.fetchCategoriesProducts();
                    }
                    else {
                        publichApiResponse(2,3);
                    }
                }
                else {
                    publishError();
                }
                break;
            case 3:
                if(status == 2) {
                    Intent intent = new Intent(getActivity(),
                            HomeActivity.class);
                    startActivity(intent);
                    getActivity().finish();
                }
                else {
                    publishError();
                }
                break;

        }
    }

    public void publishError() {
        Toast.makeText(getContext(),
                "Service ecountered an error",
                Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getActivity(),
                HomeActivity.class);
        startActivity(intent);
        getActivity().finish();

    }

}
