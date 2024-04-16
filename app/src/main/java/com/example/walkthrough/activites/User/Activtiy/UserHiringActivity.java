package com.example.walkthrough.activites.User.Activtiy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.walkthrough.R;

public class UserHiringActivity extends AppCompatActivity {
    TextView tvhire,tvprice;
    String tailoruserID,Price,tailoradd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_hiring);
        tailoruserID=getIntent().getStringExtra("TailorID");
        Price=getIntent().getStringExtra("price");
        tailoradd=getIntent().getStringExtra("add");
        Toast.makeText(this, "Location"+tailoradd.toString(), Toast.LENGTH_SHORT).show();

        clicklistener();
    }
    private void clicklistener(){
        tvhire=findViewById(R.id.hireID);
        tvprice=findViewById(R.id.pricetvid);
        tvprice.setText(Price);
        tvhire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent obj =new Intent(UserHiringActivity.this,UserMeasurementActivity.class);
               obj.putExtra("TailorId",tailoruserID);
               startActivity(obj);
                
            }
        });
    }

}