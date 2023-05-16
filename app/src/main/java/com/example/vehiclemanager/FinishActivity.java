package com.example.vehiclemanager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FinishActivity extends AppCompatActivity {

    private TextView tvMaphieu, tvTenxe, tvBienso, tvNgayxong;
    private CalendarView cNgayxong;
    private Button btnSubmit;
    private String selected, idorder,date;
    APIService mapiService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish);
        getSupportActionBar().hide();
        Bundle bundle = getIntent().getExtras();
        if(bundle == null)
        {
            return;
        }
        Order order = (Order) bundle.get("object_order");
        tvMaphieu=findViewById(R.id.tvMaphieu);
        tvTenxe =findViewById(R.id.tvTenxe);
        tvBienso=findViewById(R.id.tvBienso);
        cNgayxong=findViewById(R.id.cFinish);
        tvNgayxong=findViewById(R.id.tvNgayxong);
        btnSubmit=findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finishOrder();
            }
        });
        cNgayxong.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int date) {
                selected=year+"-"+(month+1)+"-"+date;
                tvNgayxong.setText(selected);
            }
        });
        idorder=order.getMa_psc();
        tvMaphieu.setText("Mã phiếu sửa chữa: "+order.getMa_psc());
        tvTenxe.setText("Tên xe: "+order.getTen_xe());
        tvBienso.setText("Biển số: "+order.getBien_so());
    }
    private void finishOrder(){
        mapiService = RetrofitClient.getRetrofit().create(APIService.class);
        Call<SubmitResponse> call = mapiService.finishorder(idorder,selected);
        call.enqueue(new Callback<SubmitResponse>() {
            @Override
            public void onResponse(Call<SubmitResponse> call, Response<SubmitResponse> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(FinishActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(i);
                }
                else{
                    Toast.makeText(getApplicationContext(), response.message(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<SubmitResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}