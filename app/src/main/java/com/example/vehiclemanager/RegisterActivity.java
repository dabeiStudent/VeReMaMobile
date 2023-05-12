package com.example.vehiclemanager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {
    EditText etName,etPass,etRepass;
    Button btnSignup;
    ImageView imgBack;
    TextView tvNoti;

    APIService mApi;
    @Override
    protected void onCreate(Bundle savedInstancestate){
        super.onCreate(savedInstancestate);
        setContentView(R.layout.signup_layout);
        getSupportActionBar().hide();
        etName=findViewById(R.id.etName);
        etPass=findViewById(R.id.etPass);
        etRepass=findViewById(R.id.etRepass);
        tvNoti=findViewById(R.id.tvNoti);
        btnSignup=findViewById(R.id.btnSignup);
        btnSignup.setOnClickListener(view -> signup());
        imgBack=findViewById(R.id.imgBack);
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(i);
            }
        });
    }

    private boolean validate(){
        String password= etPass.getText().toString().trim();
        String repassword= etRepass.getText().toString().trim();
        if (!password.equals(repassword)) {
            tvNoti.setText("Password Would Not be matched");
            return false;
        }else {
            tvNoti.setText("Password Matched");
            return true;
        }
    }
    private void signup(){
        String password= etPass.getText().toString().trim();
        String repassword= etRepass.getText().toString().trim();
        String username = etName.getText().toString().trim();
        mApi = RetrofitClient.getRetrofit().create(APIService.class);
        Call<User> call = mApi.signup(username, password,repassword);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(RegisterActivity.this, response.message(), Toast.LENGTH_SHORT).show();
                    tvNoti.setText("");
                } else {
                    Toast.makeText(RegisterActivity.this, response.message(), Toast.LENGTH_LONG).show();
                    tvNoti.setText("");
                }
            }
            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(RegisterActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
