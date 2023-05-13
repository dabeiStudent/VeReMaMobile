package com.example.vehiclemanager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class Custom extends BaseAdapter {

    private ArrayList<Account> accountArrayList;
    private Context context;
    private int layout;

    public Custom(ArrayList<Account> accountArrayList, Context context, int layout) {
        this.accountArrayList = accountArrayList;
        this.context = context;
        this.layout = layout;
    }

    @Override
    public int getCount() {
        return accountArrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return accountArrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    private class ViewHolder{
        TextView matk,tentk,matkhau,img,quyen;
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = new ViewHolder();
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view= layoutInflater.inflate(layout,null);
        viewHolder.matk=view.findViewById(R.id.matk);
        viewHolder.tentk=view.findViewById(R.id.tentk);
        viewHolder.matkhau=view.findViewById(R.id.matkhau);
        viewHolder.img=view.findViewById(R.id.img);
        viewHolder.quyen=view.findViewById(R.id.quyen);
        Account account = accountArrayList.get(i);
        viewHolder.matk.setText(account.getMa_tk());
        viewHolder.tentk.setText(account.getTen_tk());
        viewHolder.matkhau.setText(account.getMat_khau());
        viewHolder.img.setText(account.getImage());
        viewHolder.quyen.setText(account.getQuyen());
        return view;
    }
}
