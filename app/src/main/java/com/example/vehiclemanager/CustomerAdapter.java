package com.example.vehiclemanager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class CustomerAdapter extends  RecyclerView.Adapter<CustomerAdapter.CustomerViewholder>{
    private ArrayList<Customer> customerArrayList;
    private Context context;

    public CustomerAdapter(ArrayList<Customer> customerArrayList, Context context) {
        this.customerArrayList = customerArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public CustomerViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_customer,parent,false);
        return new CustomerViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomerViewholder holder, int position) {
        Customer customer = customerArrayList.get(position);
        if(customer==null){
            return;
        }else{
            Glide.with(this.context).load("https://media.istockphoto.com/id/486663966/vector/human-head.jpg?s=612x612&w=0&k=20&c=7hCjqNLEoDJreQoR-xZSHgYXkClkk9HHB7wEQuoFRlY=").into(holder.img);
            holder.tvtenKh.setText(String.valueOf(customer.getTen()));
            holder.tvMatk.setText(String.valueOf(customer.getMa_kh()));
            holder.tvPassword.setText(String.valueOf(customer.getSdt()));
            holder.tvRole.setText(String.valueOf(customer.getDia_chi()));
            holder.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onClickToDetail(customer);
                }
            });
        }

    }
    private void onClickToDetail(Customer customer){
        Intent intent = new Intent(context,DetailActivity.class);
        //Luu du lieu vao bundle
        Bundle bundle = new Bundle();
        bundle.putSerializable("object_customer",customer);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    @Override
    public int getItemCount() {
        if(customerArrayList != null){
            return customerArrayList.size();
        }else{
            return 0;
        }
    }

    public class CustomerViewholder extends RecyclerView.ViewHolder{

        private ImageView img;
        private TextView tvtenKh,tvMatk,tvPassword,tvRole, tvIdacc;
        private CardView cardView;
        public CustomerViewholder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img);
            tvtenKh= itemView.findViewById(R.id.tvtenKh);
            tvMatk=itemView.findViewById(R.id.tvmaTk);
            tvPassword=itemView.findViewById(R.id.tvmatKhau);
            tvRole=itemView.findViewById(R.id.tvrole);
            tvIdacc=itemView.findViewById(R.id.tvIdacc);
            cardView=itemView.findViewById(R.id.cardview_account);
        }
    }
}
