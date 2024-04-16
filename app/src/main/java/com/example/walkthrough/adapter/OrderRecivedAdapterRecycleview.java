package com.example.walkthrough.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.walkthrough.R;
import com.example.walkthrough.activites.productclothesFragment;
import com.example.walkthrough.model.ClotheModel;
import com.example.walkthrough.model.RequestOrderRecycleModel;

import java.util.ArrayList;

public class OrderRecivedAdapterRecycleview  extends RecyclerView.Adapter<OrderRecivedAdapterRecycleview.MyViewHolder>
{
    ArrayList<RequestOrderRecycleModel> requestOrderRecyclelist;


    public OrderRecivedAdapterRecycleview(ArrayList<RequestOrderRecycleModel> requestOrderRecyclelist) {
        this.requestOrderRecyclelist = requestOrderRecyclelist;
    }



    @NonNull
    @Override
    public OrderRecivedAdapterRecycleview.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View view=layoutInflater.inflate(R.layout.order_received_recycleview,parent,false);
        return new OrderRecivedAdapterRecycleview.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderRecivedAdapterRecycleview.MyViewHolder holder, int position) {
        holder.gender.setText(requestOrderRecyclelist.get(position).getGender().toString().trim());
        holder.name.setText(requestOrderRecyclelist.get(position).getName().toString().trim());
        holder.image.setImageResource(requestOrderRecyclelist.get(position).getImages());
        holder.image_check.setImageResource(requestOrderRecyclelist.get(position).getImagecheck());
        holder.image_cancel.setImageResource(requestOrderRecyclelist.get(position).getImagecancel());

      /*  holder.name_category.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppCompatActivity appCompatActivity=(AppCompatActivity)view.getContext();
                appCompatActivity.getSupportFragmentManager().beginTransaction().replace(R.id.userhome, new productclothesFragment()).commit();
            }
        });*/
    }


    @Override
    public int getItemCount() {
        if (requestOrderRecyclelist != null) {
            return requestOrderRecyclelist.size();
        } else {
            return 0; // or handle this case appropriately
        }
    }

    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        private ImageView image;
        private ImageView image_cancel;
        private ImageView image_check;
        private TextView name;
        private TextView gender;

        public MyViewHolder(@NonNull View itemView) {

            super(itemView);
            image=itemView.findViewById(R.id.img);
            name=itemView.findViewById(R.id.name);
            gender=itemView.findViewById(R.id.gender);
            image_cancel=itemView.findViewById(R.id.imageView4);
            image_check=itemView.findViewById(R.id.imageView5);


        }
    }
}
