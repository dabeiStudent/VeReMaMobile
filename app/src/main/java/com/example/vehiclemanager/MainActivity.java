package com.example.vehiclemanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
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
        setContentView(R.layout.main_page_layout);

        rcView = (RecyclerView)findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rcView.setLayoutManager(linearLayoutManager);
        DividerItemDecoration itemDecoration = new DividerItemDecoration(this,DividerItemDecoration.VERTICAL);
        rcView.addItemDecoration(itemDecoration);
        accountArrayList = new ArrayList<>();

        btnacc = (Button) findViewById(R.id.btnacc);
        btnstaff = (Button) findViewById(R.id.btnstaff);
        btncus = (Button) findViewById(R.id.btncus);
        btnacc.setOnClickListener(view -> showacc());
        btnstaff.setOnClickListener(view -> showstaff());
        btncus.setOnClickListener(view -> showcustomer());
        imgprofile = (ImageView) findViewById(R.id.imgprofile);
        imgprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
                startActivity(intent);
            }
        });
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
                        Toast.makeText(MainActivity.this, "tài khoản", Toast.LENGTH_LONG).show();
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }else{
                    Toast.makeText(getApplicationContext(), response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<AccountResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage().toString(), Toast.LENGTH_SHORT).show();
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
                            Customstaffadapter custom = new Customstaffadapter(staffArrayList,MainActivity.this,R.layout.single_view_staff);
                            listView.setAdapter(custom);
                        }
                        Toast.makeText(MainActivity.this, "nhân viên", Toast.LENGTH_LONG).show();
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                }else{
                    Toast.makeText(getApplicationContext(), response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<StaffResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage().toString(), Toast.LENGTH_SHORT).show();
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
                            Customcustomeradapter custom = new Customcustomeradapter(customerArrayList,MainActivity.this,R.layout.single_view_customer);
                            listView.setAdapter(custom);
                        }
                        Toast.makeText(MainActivity.this, "khách hàng", Toast.LENGTH_LONG).show();
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                }else{
                    Toast.makeText(getApplicationContext(), response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<CustomerResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage().toString(), Toast.LENGTH_SHORT).show();
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