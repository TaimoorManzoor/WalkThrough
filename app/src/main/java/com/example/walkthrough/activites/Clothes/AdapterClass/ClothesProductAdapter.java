package com.example.walkthrough.activites.Clothes.AdapterClass;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.walkthrough.R;
import com.example.walkthrough.activites.Clothes.ModelClass.AddCategoryProductModelClass;
import com.example.walkthrough.activites.Clothes.ModelClass.ClothesProductModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class ClothesProductAdapter extends RecyclerView.Adapter<ClothesProductAdapter.ClothesProductViewHolder> {
    private ArrayList<AddCategoryProductModelClass> data;
    private Context context;

    public ClothesProductAdapter(ArrayList<AddCategoryProductModelClass> data, Context context) {
        this.data = data;
        this.context = context;
    }
    public void setFilteredlist(List<AddCategoryProductModelClass> filteredlist){
        this.data= (ArrayList<AddCategoryProductModelClass>) filteredlist;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ClothesProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.clothes_products, parent, false);
        return new ClothesProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ClothesProductViewHolder holder, int position) {
        final AddCategoryProductModelClass productsData = data.get(position);
        Glide.with(context).load(productsData.getProductimage()).into(holder.productImg);

        holder.productName.setText(productsData.getSellerproductusername());
        holder.productCategory.setText(productsData.getCategory());
        holder.productPrice.setText(productsData.getColor());
        holder.productCount.setText(productsData.getCount());

        holder.imagedel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              DeleteRequested(productsData.getSelleruserID(),productsData.getProductID());
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    static class ClothesProductViewHolder extends RecyclerView.ViewHolder {
        ImageView productImg,imagedel;
        TextView productName, productCategory, productPrice, productCount;

        public ClothesProductViewHolder(@NonNull View itemView) {
            super(itemView);

            productImg = itemView.findViewById(R.id.productClothImage);
            imagedel = itemView.findViewById(R.id.imagedeleteID);
            productName = itemView.findViewById(R.id.productClothName);
            productCategory = itemView.findViewById(R.id.productClothCategory);
            productPrice = itemView.findViewById(R.id.productClothPrice);
            productCount = itemView.findViewById(R.id.productClothCount);
        }
    }
    private void DeleteRequested(String sellerID, String productID){
        DatabaseReference userRequestsRef = FirebaseDatabase.getInstance()
                .getReference(
                        "Clothes Product Details To User");
        DatabaseReference clothesRequestsRef = FirebaseDatabase.getInstance()
                .getReference("Clothes Product Details")
                .child(sellerID);
        clothesRequestsRef.child(productID).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
//                    tailorList.remove(position);
                    notifyDataSetChanged();
                    userRequestsRef.child(productID).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            notifyDataSetChanged();

//                            Toast.makeText(context, "Request Delete", Toast.LENGTH_SHORT).show();

                        }
                    });
                }
            }
        });

    }

}
