package com.example.vehiclemanager;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class StaffAdapter extends RecyclerView.Adapter<StaffAdapter.StaffViewHolder>{
    private ArrayList<Staff> staffArrayList;
    Context context;

    public StaffAdapter(ArrayList<Staff> staffArrayList, Context context) {
        this.staffArrayList = staffArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public StaffViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_staff,parent,false);
        return new StaffViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StaffViewHolder holder, int position) {
        Staff staff = staffArrayList.get(position);
        if(staff == null){
            return;
        }else{
            Glide.with(this.context).load("https://media.istockphoto.com/id/486663966/vector/human-head.jpg?s=612x612&w=0&k=20&c=7hCjqNLEoDJreQoR-xZSHgYXkClkk9HHB7wEQuoFRlY=").into(holder.img);
            holder.tvtenNv.setText(String.valueOf(staff.getTen()));
            holder.tvMatk.setText(String.valueOf(staff.getMa_nv()));
            holder.tvPassword.setText(String.valueOf(staff.getSdt()));
            holder.tvRole.setText(String.valueOf(staff.getDia_chi()));
            holder.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onClickToDetail(staff);
                }
            });
        }
    }
    public void onClickToDetail(Staff staff){
        Intent intent = new Intent(context,DetailActivity.class);
        //Luu du lieu vao bundle
        Bundle bundle = new Bundle();
        bundle.putSerializable("object_staff",staff);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }
    @Override
    public int getItemCount() {
        if(staffArrayList!= null){
            return staffArrayList.size();
        }else{
            return 0;
        }
    }

    public class StaffViewHolder extends RecyclerView.ViewHolder{

        private ImageView img;
        private TextView tvtenNv,tvMatk,tvPassword,tvRole,tvIdacc;
        private CardView cardView;
        public StaffViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img);
            tvtenNv = itemView.findViewById(R.id.tvtenNv);
            tvMatk=itemView.findViewById(R.id.tvmaTk);
            tvPassword=itemView.findViewById(R.id.tvmatKhau);
            tvRole=itemView.findViewById(R.id.tvrole);
            tvIdacc=itemView.findViewById(R.id.tvIdacc);
            cardView=itemView.findViewById(R.id.cardview_account);
        }
    }
}
