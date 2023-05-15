package com.example.vehiclemanager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ManageActivity extends AppCompatActivity {
    private ListView listView;
    private RecyclerView rcView;
    private Button btnacc,btnstaff, btncus;
    private ImageView imgprofile;
    APIService mapiService;
    private ArrayList<Account> accountArrayList;
    private ArrayList<Staff> staffArrayList;
    private ArrayList<Customer> customerArrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.manage_layout);
        //Set adapter cho recyclerview
        rcView = (RecyclerView)findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        rcView.setLayoutManager(linearLayoutManager);

        //Bottom nav
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);
        bottomNavigationView.setSelectedItemId(R.id.action_manage);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()){
                    case R.id.action_home:
                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
                        finish();
                        return true;
                    case R.id.action_manage:
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
        showacc();
    }
    private void showacc(){
        mapiService=RetrofitClient.getRetrofit().create(APIService.class);
        Call<AccountResponse> call = mapiService.getacc();
        call.enqueue(new Callback<AccountResponse>() {
            @Override
            public void onResponse(Call<AccountResponse> call, Response<AccountResponse> response) {
                if(response.isSuccessful())
                {
                    try{
                        accountArrayList = response.body().getAllAccounts();
                        AccountAdapter accountAdapter = new AccountAdapter(accountArrayList, getApplicationContext());
                        rcView.setAdapter(accountAdapter);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }else{
                    Toast.makeText(getApplicationContext(), response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<AccountResponse> call, Throwable t) {
                Toast.makeText(ManageActivity.this, t.getMessage().toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void showstaff(){
        mapiService= RetrofitClient.getRetrofit().create(APIService.class);
        Call<StaffResponse> call = mapiService.getstaff();
        call.enqueue(new Callback<StaffResponse>() {
            @Override
            public void onResponse(Call<StaffResponse> call, Response<StaffResponse> response) {
                if(response.isSuccessful()){
                    try{
                        staffArrayList = response.body().getAllStaffs();
                        for(int i=0;i<staffArrayList.size();i++){
                            Customstaffadapter custom = new Customstaffadapter(staffArrayList,ManageActivity.this,R.layout.single_view_staff);
                            listView.setAdapter(custom);
                        }
                        Toast.makeText(ManageActivity.this, "nhân viên", Toast.LENGTH_LONG).show();
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                }else{
                    Toast.makeText(getApplicationContext(), response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<StaffResponse> call, Throwable t) {
                Toast.makeText(ManageActivity.this, t.getMessage().toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void showcustomer(){
        mapiService= RetrofitClient.getRetrofit().create(APIService.class);
        Call<CustomerResponse> call = mapiService.getcustomer();
        call.enqueue(new Callback<CustomerResponse>() {
            @Override
            public void onResponse(Call<CustomerResponse> call, Response<CustomerResponse> response) {
                if(response.isSuccessful()){
                    try{
                        customerArrayList= response.body().getAllCustomers();
                        for(int i=0;i<customerArrayList.size();i++){
                            Customcustomeradapter custom = new Customcustomeradapter(customerArrayList,ManageActivity.this,R.layout.single_view_customer);
                            listView.setAdapter(custom);
                        }
                        Toast.makeText(ManageActivity.this, "khách hàng", Toast.LENGTH_LONG).show();
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                }else{
                    Toast.makeText(getApplicationContext(), response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<CustomerResponse> call, Throwable t) {
                Toast.makeText(ManageActivity.this, t.getMessage().toString(), Toast.LENGTH_SHORT).show();
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
