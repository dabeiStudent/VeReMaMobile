package com.example.vehiclemanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class DetailOrder extends AppCompatActivity {
    private TextView tvVehiclename, tvVehicleid, tvOrderid, tvStaffid, tvCustomer,tvSubmitday,tvRepairday,tvExpecttime,tvIdservice,tvCost, tvStatus;
    private ImageView ivVehicle, ivSetting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_order);
        getSupportActionBar().hide();
        tvVehiclename=(TextView) findViewById(R.id.tvVehiclename);
        tvVehicleid=(TextView)findViewById(R.id.tvVehicleid);
        ivVehicle=(ImageView) findViewById(R.id.ivVehicle);
        ivSetting=(ImageView)findViewById(R.id.ivSetting);
        tvOrderid=(TextView)findViewById(R.id.tvIdorder);
        tvStaffid=(TextView)findViewById(R.id.tvNv);
        tvCustomer=(TextView)findViewById(R.id.tvKh);
        tvSubmitday=(TextView)findViewById(R.id.tvNgaynhan);
        tvRepairday=(TextView)findViewById(R.id.tvNgaysua);
        tvExpecttime=(TextView)findViewById(R.id.tvThoigian);
        tvIdservice=(TextView)findViewById(R.id.tvDichvu);
        tvCost=(TextView)findViewById(R.id.tvTongtien);
        tvStatus=(TextView)findViewById(R.id.tvStatus) ;
        Bundle bundle = getIntent().getExtras();
        if(bundle == null)
        {
            return;
        }
        Order order = (Order) bundle.get("object_order");
        if(order != null) {
            if(order.getImg() != null){
                Glide.with(getApplicationContext()).load(order.getImg()).into(ivVehicle);
            }
            tvVehiclename.setText(order.getTen_xe());
            tvVehicleid.setText(order.getBien_so());
            tvOrderid.setText("Mã phiếu: "+order.getMa_psc());
            tvStaffid.setText(String.valueOf(order.getMa_nv()));
            tvCustomer.setText(order.getTen_kh());
            tvSubmitday.setText(order.getNgay_nhan());
            if(order.getNgay_sua()!= null) {
                tvRepairday.setText(order.getNgay_sua());
            }else{
                tvRepairday.setText("(Chưa tiến hành sửa)");
            }
            tvExpecttime.setText(order.getTg_du_kien());
            if(order.getId_dv() == 2) {
                tvIdservice.setText("Thay thế");
            }else{
                tvIdservice.setText("Bảo trì");
            }
            tvCost.setText("Tổng tiền: "+order.getTong_tien()+" VND");
            if(order.getTrang_thai().equals("DaSua"))
            {
                tvStatus.setText("Đã sửa");
            }
        }
        ivSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),FinishActivity.class);
                //Luu du lieu vao bundle
                Bundle bundle = new Bundle();
                bundle.putSerializable("object_order",order);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }
}