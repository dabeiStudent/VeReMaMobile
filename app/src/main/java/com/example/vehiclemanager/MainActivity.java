package com.example.vehiclemanager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ImageView imgAdd;
    private RecyclerView rcVieworder;
    private APIService mapiService;
    private ArrayList<Order> orderArrayList;
    private String namestaff;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        User user = SharedPrefManager.getInstance(this).getUser();
        String role = user.getQuyen();
        if(role.equals("admin")) {
            setContentView(R.layout.home_page_layout);
            rcVieworder = (RecyclerView) findViewById(R.id.rcVieworder);
            GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
            rcVieworder.setLayoutManager(gridLayoutManager);
            showorder();
            imgAdd = (ImageView) findViewById(R.id.ivAdd);
            imgAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(MainActivity.this, AddNewOrder.class);
                    startActivity(i);
                }
            });
            //Bottom nav
            BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);
            bottomNavigationView.setSelectedItemId(R.id.action_home);
            bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    switch (item.getItemId()) {
                        case R.id.action_home:
                            return true;
                        case R.id.action_manage:
                            startActivity(new Intent(getApplicationContext(), ManageActivity.class));
                            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                            finish();
                            return true;
                        case R.id.action_profile:
                            startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
                            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                            finish();
                            return true;
                    }
                    return false;
                }
            });
        }else if(role.equals("nv")){
            namestaff=user.getTen_tk();
            setContentView(R.layout.staff_home);
            rcVieworder = (RecyclerView) findViewById(R.id.rcVieworder);
            GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
            rcVieworder.setLayoutManager(gridLayoutManager);

            showstafforder();
            //Bottom nav
            BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);
            bottomNavigationView.setSelectedItemId(R.id.action_home);
            bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    switch (item.getItemId()) {
                        case R.id.action_home:
                            return true;
                        case R.id.action_manage:
                            startActivity(new Intent(getApplicationContext(), ManageActivity.class));
                            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                            finish();
                            return true;
                        case R.id.action_profile:
                            startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
                            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                            finish();
                            return true;
                    }
                    return false;
                }
            });
        }else{
            Toast.makeText(this, "Bạn không phải admin", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(getApplicationContext(),ProfileActivity.class);
            startActivity(i);
        }
    }
    private void showstafforder(){
        mapiService=RetrofitClient.getRetrofit().create(APIService.class);
        Call<OrderStaffResponse> call = mapiService.orderstaff(namestaff);
        call.enqueue(new Callback<OrderStaffResponse>() {
            @Override
            public void onResponse(Call<OrderStaffResponse> call, Response<OrderStaffResponse> response) {
                if(response.isSuccessful()){
                    try {
                        orderArrayList = response.body().getOrders();
                        OrderAdapter orderAdapter = new OrderAdapter(orderArrayList, getApplicationContext());
                        rcVieworder.setAdapter(orderAdapter);
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                }else{
                    Toast.makeText(getApplicationContext(), response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<OrderStaffResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
    private void showorder(){
        mapiService=RetrofitClient.getRetrofit().create(APIService.class);
        Call<OrderResponse> call = mapiService.getorder();
        call.enqueue(new Callback<OrderResponse>() {
            @Override
            public void onResponse(Call<OrderResponse> call, Response<OrderResponse> response) {
                if(response.isSuccessful()){
                    try {
                        orderArrayList = response.body().getAllOrders();
                        OrderAdapter orderAdapter = new OrderAdapter(orderArrayList, getApplicationContext());
                        rcVieworder.setAdapter(orderAdapter);
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                }else{
                    Toast.makeText(getApplicationContext(), response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<OrderResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    public void onBackPressed(){
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);
    }
}