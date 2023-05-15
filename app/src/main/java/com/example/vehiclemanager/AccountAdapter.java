package com.example.vehiclemanager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextClock;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
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
        final Account account = accountArrayList.get(position);
        if(account==null){
            return;
        }else{
            Glide.with(this.context).load(account.getImage()).into(holder.img);
            holder.tvTentk.setText(String.valueOf(account.getTen_tk()));
            holder.tvMatk.setText(String.valueOf(account.getMa_tk()));
            holder.tvPassword.setText(String.valueOf(account.getMat_khau()));
            holder.tvRole.setText(String.valueOf(account.getQuyen()));
            //Bat su kien click show detail
            holder.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onClickToDetail(account);
                }
            });
        }
    }
    private void onClickToDetail(Account account){
        Intent intent = new Intent(context,DetailActivity.class);
        //Luu du lieu vao bundle
        Bundle bundle = new Bundle();
        bundle.putSerializable("object_account",account);
        intent.putExtras(bundle);
        context.startActivity(intent);
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
        private final TextView tvTentk,tvMatk,tvPassword,tvRole;
        private CardView cardView;
        public AccountViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img);
            tvTentk=itemView.findViewById(R.id.tvtenTk);
            tvMatk=itemView.findViewById(R.id.tvmaTk);
            tvPassword=itemView.findViewById(R.id.tvmatKhau);
            tvRole=itemView.findViewById(R.id.tvrole);
            cardView=itemView.findViewById(R.id.cardview_account);
        }
    }
}
