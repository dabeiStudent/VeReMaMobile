package com.example.vehiclemanager;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private ListView listView;
    private Button btn;
    APIService mapiService;
    private ArrayList<Account> accountArrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);

        listView=(ListView) findViewById(R.id.lv);
        btn = (Button) findViewById(R.id.btnacc);
        btn.setOnClickListener(view -> showacc());

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
                            Custom custom = new Custom(accountArrayList,MainActivity.this,R.layout.single_view);
                            listView.setAdapter(custom);
                        }
                        Toast.makeText(getApplicationContext(), response.body().getSuccess(), Toast.LENGTH_LONG).show();
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
}