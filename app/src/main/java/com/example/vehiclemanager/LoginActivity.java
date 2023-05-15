package com.example.vehiclemanager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
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
    private Button btnLogin;
    private CheckBox rmb;
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
        rmb= findViewById(R.id.cbremember);
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
                        User user = new User(
                                response.body().getUser().get(0).getMa_tk(),
                                response.body().getUser().get(0).getTen_tk(),
                                response.body().getUser().get(0).getMat_khau(),
                                response.body().getUser().get(0).getImage(),
                                response.body().getUser().get(0).getQuyen()
                        );
                        AccountDetail detail = new AccountDetail(
                                response.body().getDetail().get(0).getTen(),
                                response.body().getDetail().get(0).getDia_chi(),
                                response.body().getDetail().get(0).getSdt()
                        );
                        SharedPrefManager.getInstance(getApplicationContext()).userLogin(user,detail);
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
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
                Toast.makeText(LoginActivity.this,"Server đã đóng",Toast.LENGTH_LONG).show();
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


