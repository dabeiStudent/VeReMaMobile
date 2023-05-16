package com.example.vehiclemanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class DetailActivity extends AppCompatActivity {
    private TextView tvUsername, tvPassword, tvId, tvRole,tvIdacc;
    private TextView tvma,tvmk,tvvt,tvacc;
    private ImageView imageView;
    private Button btnEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        getSupportActionBar().hide();
        tvUsername= (TextView) findViewById(R.id.tvUsername);
        tvId=(TextView) findViewById(R.id.tvId);
        tvPassword=(TextView) findViewById(R.id.tvPassword);
        tvRole=(TextView) findViewById(R.id.tvRole);
        tvIdacc=(TextView)findViewById(R.id.tvIdacc);
        imageView=(ImageView) findViewById(R.id.imageView);
        btnEdit=(Button)findViewById(R.id.btnEdit);

        tvma= findViewById(R.id.id);
        tvmk=findViewById(R.id.pass);
        tvvt=findViewById(R.id.role);
        tvacc=findViewById(R.id.idacc);
        Bundle bundle = getIntent().getExtras();
        if(bundle == null)
        {
            return;
        }
        Account account = (Account) bundle.get("object_account");
        if(account != null) {
            tvUsername.setText(account.getTen_tk());
            tvId.setText(account.getMa_tk());
            tvPassword.setText(account.getMat_khau());
            tvRole.setText(account.getQuyen());
            if (account.getImage().length() > 0) {
                Glide.with(getApplicationContext()).load(account.getImage()).into(imageView);
            }
            btnEdit.setVisibility(View.INVISIBLE);
        }

        Staff staff =(Staff) bundle.get("object_staff");
        if(staff != null) {
            tvUsername.setText(staff.getTen());
            tvId.setText(staff.getMa_nv());
            tvPassword.setText(staff.getSdt());
            tvRole.setText(staff.getDia_chi());
            tvIdacc.setText(staff.getTen_tk());
            tvma.setText("Mã nhân viên: ");
            tvmk.setText("Số điện thoại: ");
            tvvt.setText("Địa chỉ: ");
            tvacc.setText("Tên tài khoản liên kết: ");
            btnEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(DetailActivity.this,EditStaff.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("object_staff",staff);
                    i.putExtras(bundle);
                    startActivity(i);
                }
            });

        }
        Customer customer = (Customer) bundle.get("object_customer");
        if(customer != null){
            tvUsername.setText(customer.getTen());
            tvId.setText(customer.getMa_kh());
            tvPassword.setText(customer.getSdt());
            tvRole.setText(customer.getDia_chi());
            tvIdacc.setText(customer.getTen_tk());
            tvma.setText("Mã khách hàng: ");
            tvmk.setText("Số điện thoại: ");
            tvvt.setText("Địa chỉ: ");
            tvacc.setText("Tên tài khoản liên kết: ");
            btnEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(DetailActivity.this,EditCustomer.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("object_customer",customer);
                    i.putExtras(bundle);
                    startActivity(i);
                }
            });
        }
    }
}