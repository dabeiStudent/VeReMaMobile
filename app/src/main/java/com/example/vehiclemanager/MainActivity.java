package com.example.vehiclemanager;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private ListView listView;
    private Button btnacc,btnstaff;
    APIService mapiService;
    private ArrayList<Account> accountArrayList;
    private ArrayList<Staff> staffArrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);

        listView=(ListView) findViewById(R.id.lv);
        btnacc = (Button) findViewById(R.id.btnacc);
        btnacc.setOnClickListener(view -> showacc());
        btnstaff = (Button) findViewById(R.id.btnstaff);
        btnstaff.setOnClickListener(view -> showstaff());

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
                        for(int i=0;i<accountArrayList.size();i++){
                            Custom custom = new Custom(accountArrayList,MainActivity.this,R.layout.single_view_account);
                            listView.setAdapter(custom);
                        }
                        Toast.makeText(getApplicationContext(), "tài khoản", Toast.LENGTH_LONG).show();
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
                        for(int i=0;i<accountArrayList.size();i++){
                            Customstaffadapter custom = new Customstaffadapter(staffArrayList,MainActivity.this,R.layout.single_view_staff);
                            listView.setAdapter(custom);
                        }
                        Toast.makeText(getApplicationContext(), "nhân viên", Toast.LENGTH_LONG).show();
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<StaffResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage().toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}