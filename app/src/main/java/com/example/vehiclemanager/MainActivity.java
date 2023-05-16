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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.home_page_layout);
        rcVieworder = (RecyclerView) findViewById(R.id.rcVieworder);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,3);
        rcVieworder.setLayoutManager(gridLayoutManager);
        showorder();
        imgAdd = (ImageView) findViewById(R.id.ivAdd);
        imgAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,AddNewOrder.class);
                startActivity(i);
            }
        });
        //Bottom nav
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);
        bottomNavigationView.setSelectedItemId(R.id.action_home);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()){
                    case R.id.action_home:
                        return true;
                    case R.id.action_manage:
                        startActivity(new Intent(getApplicationContext(),ManageActivity.class));
                        overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
                        finish();
                        return true;
                    case R.id.action_profile:
                        startActivity(new Intent(getApplicationContext(),ProfileActivity.class));
                        overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
                        finish();
                        return true;
                }
                return false;
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