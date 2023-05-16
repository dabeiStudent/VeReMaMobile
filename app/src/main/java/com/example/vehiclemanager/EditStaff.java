package com.example.vehiclemanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditStaff extends AppCompatActivity {
    private TextView textView;
    private EditText etName,etPhone,etAddress;
    private Button btnSubmit;
    private String name;
    APIService mapiService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_staff);
        getSupportActionBar().hide();
        textView=findViewById(R.id.textView5);
        etName=findViewById(R.id.etName);
        etPhone=findViewById(R.id.etPhone);
        etAddress=findViewById(R.id.etAddress);
        btnSubmit=findViewById(R.id.btnSubmit);
        Bundle bundle = getIntent().getExtras();
        if(bundle == null)
        {
            return;
        }
        Staff staff =(Staff) bundle.get("object_staff");
        name=staff.getTen_tk();
        textView.setText("Edit: "+staff.getTen_tk());
        etName.setText(staff.getTen());
        etPhone.setText(staff.getSdt());
        etAddress.setText(staff.getDia_chi());
        btnSubmit.setOnClickListener(view -> submitUpdate());
    }
    private void submitUpdate(){
        String username= name;
        String ten = etName.getText().toString().trim();
        String sdt = etPhone.getText().toString().trim();
        String dia_chi = etAddress.getText().toString().trim();
        mapiService=RetrofitClient.getRetrofit().create(APIService.class);
        Call<SubmitResponse> call = mapiService.updatestaff(ten,username,sdt,dia_chi);
        call.enqueue(new Callback<SubmitResponse>() {
            @Override
            public void onResponse(Call<SubmitResponse> call, Response<SubmitResponse> response) {
                if(response.isSuccessful())
                {
                    Toast.makeText(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(getApplicationContext(),ManageActivity.class);
                    startActivity(i);
                }
            }

            @Override
            public void onFailure(Call<SubmitResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}