package com.example.vehiclemanager;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextClock;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class AccountAdapter extends RecyclerView.Adapter<AccountAdapter.AccountViewHolder>{
    Context context;
    ArrayList<Account> accountArrayList;

    public AccountAdapter(ArrayList<Account> accountArrayList, Context context) {
        this.accountArrayList = accountArrayList;
        this.context= context;
    }

    @NonNull
    @Override
    public AccountViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_account,parent,false);
        return new AccountViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AccountViewHolder holder, int position) {
        Account account = accountArrayList.get(position);
        if(account==null){
            return;
        }else{
            Glide.with(this.context).load(account.getImage()).into(holder.img);
            holder.tvmaTk.setText(String.valueOf(account.getMa_tk()));
            holder.tvTentk.setText(String.valueOf(account.getTen_tk()));
            holder.tvMatkhau.setText(String.valueOf(account.getMat_khau()));
            holder.tvQuyen.setText(String.valueOf(account.getQuyen()));
        }
    }

    @Override
    public int getItemCount() {
        if(accountArrayList!= null){
            return accountArrayList.size();
        }else{
            return 0;
        }
    }

    public class AccountViewHolder extends RecyclerView.ViewHolder {

        private final ImageView img;
        private final TextView tvmaTk,tvTentk,tvMatkhau,tvQuyen;
        public AccountViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img);
            tvmaTk= itemView.findViewById(R.id.tvmaTk);
            tvTentk=itemView.findViewById(R.id.tvtenTk);
            tvMatkhau=itemView.findViewById(R.id.tvMatkhau);
            tvQuyen = itemView.findViewById(R.id.tvQuyen);
        }
    }
}
