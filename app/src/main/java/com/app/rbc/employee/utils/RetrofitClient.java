package com.app.rbc.employee.utils;



import com.app.rbc.employee.interfaces.ApiServices;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class RetrofitClient {


    private static Retrofit getRetrofitInstance() {
        return new Retrofit.Builder()
                .baseUrl("http://plethron.pythonanywhere.com/eagle/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    /**
     * Get API Service
     *
     * @return API Service
     */
    public static ApiServices getApiService() {
        return getRetrofitInstance().create(ApiServices.class);
    }
}
