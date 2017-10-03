package com.app.rbc.employee.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.rbc.employee.R;
import com.app.rbc.employee.adapters.CusotmRequirementsAdapter;
import com.app.rbc.employee.adapters.CustomStockDetailsAdapter;
import com.app.rbc.employee.adapters.CustomTransactionsAdapter;
import com.app.rbc.employee.models.db.models.site_overview.Requirement;
import com.app.rbc.employee.models.db.models.site_overview.Stock;
import com.app.rbc.employee.models.db.models.site_overview.Trans;

import java.util.List;


public class SiteOverviewPlaceholderFragment extends Fragment {

    private View view;
    private RecyclerView recyclerView;
    private List<Stock> stocks;
    private List<Requirement> requirements;
    private List<Trans> transactions;
    public long site;
    public int position;
    private CustomStockDetailsAdapter stockDetailsAdapter;
    private CusotmRequirementsAdapter requirementsAdapter;
    private CustomTransactionsAdapter transactionsAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_site_overview_stock_list, container, false);
        initializeUI();
        return view;
    }


    private void initializeUI() {
        switch (position) {
            case 0 : stocks = Stock.find(Stock.class, "site = ? and statestore != ?", site + "",1+"");
                recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
                RecyclerView.LayoutManager layoutManagerSite = new LinearLayoutManager(getContext());
                recyclerView.setLayoutManager(layoutManagerSite);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
                stockDetailsAdapter = new CustomStockDetailsAdapter(getActivity(), stocks);
                recyclerView.setAdapter(stockDetailsAdapter);
                break;
            case 1:
                requirements = Requirement.find(Requirement.class, "site = ? and statestore != ?", site + "",1+"");
                recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
                RecyclerView.LayoutManager layoutManagerRequirement = new LinearLayoutManager(getContext());
                recyclerView.setLayoutManager(layoutManagerRequirement);
                Log.e("layout","manager");
                requirementsAdapter = new CusotmRequirementsAdapter(getActivity(), requirements);
                recyclerView.setAdapter(requirementsAdapter);
                Log.e("adapter","set");
                break;
            case 2:
                transactions = Trans.find(Trans.class,"source = ? and statestore != ? or destination = ? and statestore != ?",
                        site+"",1+"",site+"",1+"");
                Log.e("count",transactions.size()+"");
                recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
                RecyclerView.LayoutManager layoutManagerTransaction = new LinearLayoutManager(getContext());
                recyclerView.setLayoutManager(layoutManagerTransaction);
                Log.e("layout","manager");
                transactionsAdapter = new CustomTransactionsAdapter(getActivity(), transactions);
                recyclerView.setAdapter(transactionsAdapter);
                Log.e("adapter","set");
                break;
        }
    }
}