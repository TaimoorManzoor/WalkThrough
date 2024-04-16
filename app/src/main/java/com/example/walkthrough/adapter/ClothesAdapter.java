package com.example.walkthrough.adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.walkthrough.R;

public class ClothesAdapter extends RecyclerView.Adapter<ClothesAdapter.MyViewHolder>{
    String [] name_clothes;
    String [] price_clothes;
    int [] image_clothes;
    public ClothesAdapter(String [] name_clothes,int [] image_clothes,String [] price_clothes)
    {
        this.image_clothes=image_clothes;
        this.name_clothes=name_clothes;
        this.price_clothes=price_clothes;
    }

    @NonNull
    @Override
    public ClothesAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View view=layoutInflater.inflate(R.layout.tailor_list_layout,parent,false);
        return new ClothesAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ClothesAdapter.MyViewHolder holder, int position) {
        holder.image_clothes.setImageResource(image_clothes[position]);
        holder.name_clothes.setText(name_clothes[position]);
        holder.price_clothes.setText(price_clothes[position]);
    }


    @Override
    public int getItemCount() {
        return image_clothes.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        private ImageView image_clothes;
        private TextView name_clothes;

        private TextView price_clothes;

        public MyViewHolder(@NonNull View itemView) {

            super(itemView);
            image_clothes=itemView.findViewById(R.id.img);
            name_clothes=itemView.findViewById(R.id.name);
            price_clothes=itemView.findViewById(R.id.price);
        }
    }
}




