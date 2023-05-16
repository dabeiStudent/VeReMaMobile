package com.example.vehiclemanager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddNewOrder extends AppCompatActivity {
    private EditText etTenkh, etDiachi, etSodt, etTentk, etMatkhau, etTenxe, etSoxe, etMota, etThoigian, etDichvu, etTongtien,etManv;
    private TextView etNgaynhan;
    private CalendarView cNgaynhan;
    private Button btnSubmit;
    private APIService mapiService;
    private String selected;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_order);
        getSupportActionBar().hide();
        Anhxa();
        cNgaynhan.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int date) {
                selected=year+"-"+(month+1)+"-"+date;
                etNgaynhan.setText(selected);
            }
        });
        btnSubmit=findViewById(R.id.btnSubmitOrder);
        btnSubmit.setOnClickListener(view -> submitorder());
    }
    private void Anhxa(){
        etTenkh=findViewById(R.id.etTenkh);
        etDiachi= findViewById(R.id.etDiachi);
        etSodt=findViewById(R.id.etSdt);
        etTentk = findViewById(R.id.etTentk);
        etMatkhau=findViewById(R.id.etMatkhau);
        etTenxe=findViewById(R.id.etTenxe);
        etSoxe=findViewById(R.id.etSoxe);
        etMota=findViewById(R.id.etMota);
        cNgaynhan=findViewById(R.id.cNgaynhan);
        etThoigian=findViewById(R.id.etThoigian);
        etDichvu=findViewById(R.id.etDichvu);
        etTongtien=findViewById(R.id.etTongtien);
        etManv=findViewById(R.id.etManv);
        etNgaynhan=findViewById(R.id.etNgaynhan);
    }
    private void submitorder() {
            int maNv = Integer.parseInt(etManv.getText().toString().trim());
            String soXe = etSoxe.getText().toString().trim();
            String tenXe = etTenxe.getText().toString().trim();
            String moTa = etMota.getText().toString().trim();
            String tenKh = etTenkh.getText().toString().trim();
            String ngayNhan= etNgaynhan.getText().toString().trim();
            //String ngayNhan = selected;
            String thoiGian = etThoigian.getText().toString().trim();
            int dichVu = Integer.parseInt(etDichvu.getText().toString().trim());
            String tongTien = etTongtien.getText().toString().trim();
            String diaChi = etDiachi.getText().toString().trim();
            String soDt = etSodt.getText().toString().trim();
            String tenTk = etTentk.getText().toString().trim();
            String matKhau = etMatkhau.getText().toString().trim();
            mapiService = RetrofitClient.getRetrofit().create(APIService.class);
            Call<SubmitResponse> call = mapiService.addneworder(maNv, soXe, tenXe, moTa, tenKh, ngayNhan, thoiGian, dichVu, tongTien, diaChi, soDt, tenTk, matKhau);
            call.enqueue(new Callback<SubmitResponse>() {
                @Override
                public void onResponse(Call<SubmitResponse> call, Response<SubmitResponse> response) {
                    if (response.isSuccessful()) {
                        Toast.makeText(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(i);
                    }
                    else{
                        Toast.makeText(getApplicationContext(), "Vui lòng nhập đủ dữ liệu", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(getApplicationContext(), AddNewOrder.class);
                        startActivity(i);
                    }
                }

                @Override
                public void onFailure(Call<SubmitResponse> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), "Vui long nhap du", Toast.LENGTH_LONG).show();
                }
            });
    }
}