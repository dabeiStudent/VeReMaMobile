package com.example.vehiclemanager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class Customstaffadapter extends BaseAdapter {
    private ArrayList<Staff> staffArrayList;
    private Context context;
    private int layout;

    public Customstaffadapter(ArrayList<Staff> staffArrayList, Context context, int layout) {
        this.staffArrayList = staffArrayList;
        this.context = context;
        this.layout = layout;
    }

    @Override
    public int getCount() {
        return staffArrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return staffArrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }
    private class ViewHolder{
        TextView manv,tennv,gioitinh,diachi,sdt,ngaysinh,ngaygianhap,luong,tentk;
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = new ViewHolder();
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = layoutInflater.inflate(layout,null);
        viewHolder.manv=view.findViewById(R.id.manv);
        viewHolder.tennv=view.findViewById(R.id.tennv);
        viewHolder.gioitinh=view.findViewById(R.id.gioitinh);
        viewHolder.diachi=view.findViewById(R.id.diachi);
        viewHolder.sdt=view.findViewById(R.id.sdt);
        viewHolder.ngaysinh=view.findViewById(R.id.ngaysinh);
        viewHolder.ngaygianhap=view.findViewById(R.id.ngaygianhap);
        viewHolder.luong=view.findViewById(R.id.luong);
        viewHolder.tentk=view.findViewById(R.id.tentk);

        Staff staff = staffArrayList.get(i);

        viewHolder.manv.setText("Mã nhân viên: "+staff.getMa_nv());
        viewHolder.tennv.setText("Tên nhân viên: "+staff.getTen_nv());
        viewHolder.gioitinh.setText("Giới tính: "+staff.getGioi_tinh());
        viewHolder.diachi.setText("Địa chỉ: "+staff.getDia_chi());
        viewHolder.sdt.setText("Số điện thoại: "+staff.getSdt());
        viewHolder.ngaysinh.setText("Ngày sinh: "+staff.getNgay_sinh());
        viewHolder.ngaygianhap.setText("Ngày gia nhập: "+staff.getNgay_gianhap());
        viewHolder.luong.setText("Lương: "+staff.getLuong());
        viewHolder.tentk.setText("Tên tài khoản: "+staff.getTen_tk());

        return view;
    }
}
