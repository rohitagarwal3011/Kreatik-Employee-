package com.app.rbc.employee.fragments;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.app.rbc.employee.R;
import com.app.rbc.employee.activities.AddVehicleActivity;
import com.app.rbc.employee.activities.HomeActivity;
import com.app.rbc.employee.interfaces.ApiServices;
import com.app.rbc.employee.utils.AppUtil;
import com.app.rbc.employee.utils.RetrofitClient;
import com.dd.processbutton.iml.ActionProcessButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.pedant.SweetAlert.SweetAlertDialog;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Dispatch_Vehicle extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    public static final String TAG = "Dispatch_Vehicle";
    @BindView(R.id.vehicle_number)
    EditText vehicleNumber;
    @BindView(R.id.driver_name)
    EditText driverName;
    @BindView(R.id.challan_no)
    EditText challanNo;
    Unbinder unbinder;
    @BindView(R.id.submit_button)
    ActionProcessButton submitButton;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    String rq_id, destination,source,  parent_id, category;
    String vehicle_number, driver, challan;


    public Dispatch_Vehicle() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Dispatch_Vehicle.
     */
    // TODO: Rename and change types and number of parameters
    public static Dispatch_Vehicle newInstance(String param1, String param2) {
        Dispatch_Vehicle fragment = new Dispatch_Vehicle();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dispatch__vehicle, container, false);
        unbinder = ButterKnife.bind(this, view);


        Bundle bundle = ((AddVehicleActivity)getActivity()).finalform;
        if(bundle != null) {
            vehicleNumber.setText(bundle.getString("vehiclenumber"));
            driverName.setText(bundle.getString("drivername"));
            challanNo.setText(bundle.getString("challannumber"));
        }

        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Dispatch Vehicle");


        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setData();

    }

    @Override
    public void onPause() {
        if(((AddVehicleActivity)getActivity()).finalform == null) {
            ((AddVehicleActivity)getActivity()).finalform = new Bundle();
        }
        Bundle bundle = ((AddVehicleActivity) getActivity()).finalform;
        bundle.putString("vehiclenumber", vehicleNumber.getText().toString());
        bundle.putString("drivername", driverName.getText().toString());
        bundle.putString("challannumber", challanNo.getText().toString());

        super.onPause();
    }


    JSONArray prod_list = new JSONArray();

    public void setData()
    {
        for (String key : Product_selection.product_grid.keySet()) {
            JSONObject product1 = new JSONObject();
            try {
            product1.put("product", key);

                product1.put("quantity", Integer.parseInt(Product_selection.product_grid.get(key)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            prod_list.put(product1);
        }
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.submit_button)
    public void onViewClicked() {

        submitButton.setProgress(1);
        submitButton.setEnabled(false);
        if(Cat_Des_Selection.vehicle_for_po) {
            parent_id = Cat_Des_Selection.po_number;
            rq_id = mParam2;
        }
        else {
            parent_id = mParam2;
            rq_id = "";
        }
        source = Cat_Des_Selection.source.toString();
        destination = Cat_Des_Selection.destination.toString();
        category = mParam1;



        final ApiServices apiServices = RetrofitClient.getApiService();

        Call<ResponseBody> call = apiServices.add_vehicle_info(rq_id,destination,source,parent_id,category,prod_list,vehicleNumber.getText().toString(),driverName.getText().toString(),challanNo.getText().toString());

     //   AppUtil.logger(TAG, "Creation Request : " + call.request().toString() + " Product :" + prod_list + " \n " + " Employee ID  :" + to_user + " \n " + "Assigned by :" + from_user + " \n " + "Deadline :" + deadline + " \n ");
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                // progress.dismiss();

                int status = 0;
                try {

                    try {

                        JSONObject obj = new JSONObject(response.body().string());
                        AppUtil.logger(TAG, obj.toString());
                        status= obj.getJSONObject("meta").getInt("status");

                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                submitButton.setEnabled(true);
                submitButton.setProgress(0);
                if(status==2) {

                    final SweetAlertDialog pDialog = new SweetAlertDialog(getContext(), SweetAlertDialog.SUCCESS_TYPE);
                    pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
                    pDialog.setTitleText("Vehicle Dispatched");
                    pDialog.setContentText("Dispatched Vehicle info has been saved.");
                    pDialog.setCancelable(false);
                    pDialog.show();

                    pDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                            pDialog.dismiss();
                            Intent intent = new Intent(getContext(), HomeActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                            getActivity().finish();
                            //((RequirementDetailActivity) getContext()).get_data();

                        }
                    });
                }


            }


            @Override
            public void onFailure(Call<ResponseBody> call1, Throwable t) {
                //progress.dismiss();
                submitButton.setEnabled(true);
                submitButton.setProgress(0);
                AppUtil.showToast(getContext(), "Network Issue. Please check your connectivity and try again");
            }
        });

    }
}
