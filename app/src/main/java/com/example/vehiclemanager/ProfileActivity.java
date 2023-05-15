package com.example.vehiclemanager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ProfileActivity extends AppCompatActivity  implements View.OnClickListener{
    TextView tvUsername, tvFullname, tvAddress, tvPhone, tvRole1;
    ImageView ivImage, ivLogout;
    Button btnEdit;
    @Override
    protected void onCreate(Bundle savedInstancestate){
        super.onCreate(savedInstancestate);
        setContentView(R.layout.profile_layout);
        getSupportActionBar().hide();
        if(SharedPrefManager.getInstance(this).isLoggedIn()) {
            ivImage=findViewById(R.id.imageView);
            ivLogout=findViewById(R.id.btnLogout);
            ivLogout.setOnClickListener(this);
            tvUsername=findViewById(R.id.tvUsername);
            tvFullname=findViewById(R.id.tvFullname);
            tvAddress=findViewById(R.id.tvAddress);
            tvPhone=findViewById(R.id.tvPhone);
            tvRole1=findViewById(R.id.tvRole1);
            btnEdit=findViewById(R.id.btnEdit);
            User user = SharedPrefManager.getInstance(this).getUser();
            AccountDetail detail = SharedPrefManager.getInstance(this).getDetail();
            tvUsername.setText(user.getTen_tk());
            tvFullname.setText(detail.getTen());
            tvAddress.setText(detail.getDia_chi());
            tvPhone.setText(detail.getSdt());
            tvRole1.setText(user.getQuyen());
            Glide.with(getApplicationContext()).load(user.getImage()).into(ivImage);
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
        }else{
            Intent intent = new Intent(ProfileActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }
    }
    public void onClick(View view){
        if(view.equals(ivLogout)){
            SharedPrefManager.getInstance(getApplicationContext()).logout();
        }
    }
    @Override
    public void onBackPressed(){
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);
    }
}
