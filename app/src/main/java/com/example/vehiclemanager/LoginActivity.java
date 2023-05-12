package com.example.vehiclemanager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    private EditText etName,etPassword;
    private Button btnLogin,btnProfile;
    private TextView txtV,txtReg;
    APIService mapiService;
    @Override
    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);
        getSupportActionBar().hide();
        etName=findViewById(R.id.etName);
        etPassword=findViewById(R.id.etPass);
        btnLogin=(Button) findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(view -> login());
        txtReg=findViewById(R.id.tvRegister);
        txtReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
    private void login(){
        String userName = etName.getText().toString().trim();
        String passWord = etPassword.getText().toString().trim();
        mapiService=RetrofitClient.getRetrofit().create(APIService.class);
        Call<LoginResponse> call = mapiService.login(userName,passWord);
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if(response.isSuccessful()) {
                    try {
                        Toast.makeText(getApplicationContext(), response.body().getUser().getUser().getTen_tk(), Toast.LENGTH_LONG).show();
                        User user = new User(
                                response.body().getUser().getUser().getMa_tk(),
                                response.body().getUser().getUser().getTen_tk(),
                                response.body().getUser().getUser().getMat_khau(),
                                response.body().getUser().getUser().getQuyen()
                        );
                        SharedPrefManager.getInstance(getApplicationContext()).userLogin(user);
                        Intent intent = new Intent(LoginActivity.this, ProfileActivity.class);
                        startActivity(intent);
                    } catch(Exception e){
                        e.printStackTrace();
                    }
                }
                else{
                    Toast.makeText(LoginActivity.this, response.message(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(LoginActivity.this,t.getMessage().toString(),Toast.LENGTH_LONG).show();
            }
        });
    }
}


