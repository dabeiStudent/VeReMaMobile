package com.example.vehiclemanager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class Customcustomeradapter extends BaseAdapter {

    private ArrayList<Customer> customerArrayList;
    private Context context;
    private int layout;

    public Customcustomeradapter(ArrayList<Customer> customerArrayList, Context context, int layout) {
        this.customerArrayList = customerArrayList;
        this.context = context;
        this.layout = layout;
    }

    @Override
    public int getCount() {
        return customerArrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return customerArrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    private class ViewHolder{
        TextView makh,tenkh,diachi,sdt,tentk;
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = new ViewHolder();
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = layoutInflater.inflate(layout,null);
        viewHolder.makh=view.findViewById(R.id.makh);
        viewHolder.tenkh=view.findViewById(R.id.tenkh);
        viewHolder.diachi=view.findViewById(R.id.diachi);
        viewHolder.sdt=view.findViewById(R.id.sdt);
        viewHolder.tentk=view.findViewById(R.id.tentk);
        Customer customer = customerArrayList.get(i);
        viewHolder.makh.setText("Mã khách hàng: "+customer.getMa_kh());
        viewHolder.tenkh.setText("Tên khách hàng: "+customer.getTen_kh());
        viewHolder.diachi.setText("Địa chỉ: "+customer.getDia_chi());
        viewHolder.sdt.setText("Số điện thoại: "+customer.getSdt());
        viewHolder.tentk.setText("Tên tài khoản: "+customer.getTen_tk());
        return view;
    }
}
