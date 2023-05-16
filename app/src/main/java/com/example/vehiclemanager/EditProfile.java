package com.example.vehiclemanager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditProfile extends AppCompatActivity {

    EditText etFullname, etPhone, etAddress;
    TextView tvUsername;
    ImageView img;
    Button btnSubmit;
    APIService mapiService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        getSupportActionBar().hide();
        Bundle bundle = getIntent().getExtras();
        etFullname=findViewById(R.id.etFullname);
        etAddress=findViewById(R.id.etAddress);
        etPhone=findViewById(R.id.etPhone);
        tvUsername=findViewById(R.id.tvUsername);
        img = findViewById(R.id.imageView);
        btnSubmit = findViewById(R.id.btnSubmit);
        //Bottom nav
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);
        bottomNavigationView.setSelectedItemId(R.id.action_profile);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()){
                    case R.id.action_home:
                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        finish();
                        return true;
                    case R.id.action_manage:
                        startActivity(new Intent(getApplicationContext(),ManageActivity.class));
                        finish();
                        return true;
                    case R.id.action_profile:
                        return true;
                }
                return false;
            }
        });
        if(bundle == null)
        {
            return;
        }
        User user = (User) bundle.get("object_user");
        if(user!= null){
            tvUsername.setText(user.getTen_tk());
            Glide.with(getApplicationContext()).load(user.getImage()).into(img);
        }
        String role = user.getQuyen();
        if(role.equals("kh")){
            btnSubmit.setOnClickListener(view -> editCustomer());
        }else{
            btnSubmit.setOnClickListener(view -> editStaff());
        }
        AccountDetail detail = (AccountDetail) bundle.get("object_detail");
        if(detail != null){
            etFullname.setText(detail.getTen());
            etAddress.setText(detail.getDia_chi());
            etPhone.setText(detail.getSdt());
        }
    }
    private void editStaff(){
        String ten = etFullname.getText().toString().trim();
        String username = tvUsername.getText().toString().trim();
        String sdt = etPhone.getText().toString().trim();
        String dia_chi = etAddress.getText().toString().trim();
        mapiService=RetrofitClient.getRetrofit().create(APIService.class);
        Call<SubmitResponse> call = mapiService.updatestaff(ten,username,sdt,dia_chi);
        call.enqueue(new Callback<SubmitResponse>() {
            @Override
            public void onResponse(Call<SubmitResponse> call, Response<SubmitResponse> response) {
                if(response.isSuccessful()){
                    try {
                        Toast.makeText(getApplicationContext(),response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(getApplicationContext(),ProfileActivity.class);
                        startActivity(i);
                    } catch(Exception e){
                        e.printStackTrace();
                    }
                }
            }
            @Override
            public void onFailure(Call<SubmitResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void editCustomer(){
        String ten = etFullname.getText().toString().trim();
        String username = tvUsername.getText().toString().trim();
        String sdt = etPhone.getText().toString().trim();
        String dia_chi = etAddress.getText().toString().trim();
        mapiService=RetrofitClient.getRetrofit().create(APIService.class);
        Call<SubmitResponse> call = mapiService.updatescustomer(ten,username,sdt,dia_chi);
        call.enqueue(new Callback<SubmitResponse>() {
            @Override
            public void onResponse(Call<SubmitResponse> call, Response<SubmitResponse> response) {
                if(response.isSuccessful()){
                    try {
                        Toast.makeText(getApplicationContext(),response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(getApplicationContext(),ProfileActivity.class);
                        startActivity(i);
                    } catch(Exception e){
                        e.printStackTrace();
                    }
                }
            }
            @Override
            public void onFailure(Call<SubmitResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}