package com.example.vehiclemanager;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ListAccAdapter extends BaseAdapter {
    private String[] accounts;
    private Activity activity;
    public ListAccAdapter (Activity activity, String[] accounts){
        this.activity=activity;
        this.accounts=accounts;
    }
    @Override
    public int getCount() {
        return accounts.length;
    }

    @Override
    public Object getItem(int i) {
        return accounts[i];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = activity.getLayoutInflater();
        view = inflater.inflate(R.layout.item_name,null);
        TextView tvName = (TextView) view.findViewById(R.id.tv_name);
        tvName.setText(accounts[i]);
        return view;
    }
}
