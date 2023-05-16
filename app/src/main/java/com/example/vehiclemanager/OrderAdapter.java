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

import javax.microedition.khronos.opengles.GL;

import kotlin.jvm.internal.Lambda;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderViewHolder>{

    ArrayList<Order> orderArrayList;
    Context context;

    public OrderAdapter(ArrayList<Order> orderArrayList, Context context) {
        this.orderArrayList = orderArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_order,parent,false);
        return new OrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
        final Order order = orderArrayList.get(position);
        if(order == null)
        {
            return;
        }else{
            if(order.getImg() != null) {
                Glide.with(this.context).load(order.getImg()).into(holder.imgOrder);
            }
            holder.tvOrderid.setText(String.valueOf(order.getMa_psc()));
            holder.tvNameVehicle.setText(String.valueOf(order.getTen_xe()));
            holder.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onClickToDetail(order);
                }
            });
        }
    }
    private void onClickToDetail(Order order){
        Intent i = new Intent(context,DetailOrder.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("object_order",order);
        i.putExtras(bundle);
        context.startActivity(i);
    }

    @Override
    public int getItemCount() {
        if(orderArrayList!= null){
            return orderArrayList.size();
        }else{
            return 0;
        }
    }

    public class OrderViewHolder extends RecyclerView.ViewHolder{
        private final ImageView imgOrder;

        private final TextView tvOrderid, tvNameVehicle;

        private CardView cardView;

        public OrderViewHolder(@NonNull View itemView) {
            super(itemView);
            imgOrder=itemView.findViewById(R.id.imgOrder);
            tvOrderid=itemView.findViewById(R.id.tvOrderid);
            tvNameVehicle =itemView.findViewById(R.id.tvVehiname);
            cardView=itemView.findViewById(R.id.cardview_order);
        }
    }
}
