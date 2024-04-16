package com.example.walkthrough.activites;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.walkthrough.R;
import com.example.walkthrough.model.CurrentStatusDetails;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UserActivity extends AppCompatActivity {

    EditText edusername,edemail,edpassword,edconpass,edphonenumber;
    Button btnsignup;
    FirebaseAuth mAuth;
    ProgressDialog dialog;
    TextView signInLink;
    String name,phonenumber,useremail,userpassword,userconpass,userid,getcurrentstatus;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        ids();
        clicklistener();
        signInLink=findViewById(R.id.signInLink);
        signInLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(UserActivity.this,SignInUserActivity.class);
                startActivity(intent);
            }
        });
        getcurrentstatus=getIntent().getStringExtra("currentuserdata");
    }
    private void ids(){
        edusername=findViewById(R.id.editText);
        edemail=findViewById(R.id.editText11);
        edpassword=findViewById(R.id.passwordLogin);
        edconpass=findViewById(R.id.editText7);
        edphonenumber=findViewById(R.id.editText12);
        btnsignup=findViewById(R.id.signup_btn);

    }
    private void clicklistener(){
        btnsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             name=edusername.getText().toString().trim();
             phonenumber=edphonenumber.getText().toString().trim();
             useremail=edemail.getText().toString().trim();
             userpassword=edpassword.getText().toString().trim();
             userconpass=edconpass.getText().toString().trim();
                if(edusername.getText().toString().isEmpty()||
                        edpassword.getText().toString().isEmpty()||
                        edemail.getText().toString().isEmpty() ||
                        edconpass.getText().toString().isEmpty()||
                        edphonenumber.getText().toString().isEmpty()
                ){
                    Toast.makeText(UserActivity.this, "Enter Detail please", Toast.LENGTH_SHORT).show();

                }else if(edpassword.getText().toString().length()<6){
                    Toast.makeText(UserActivity.this, "Enter valid password", Toast.LENGTH_SHORT).show();
                }else if(!edemail.getText().toString().contains("@gmail.com")){
                    Toast.makeText(UserActivity.this, "Enter valid email", Toast.LENGTH_SHORT).show();
                } else if (phonenumber.length() != 11) {
                    Toast.makeText(UserActivity.this, "Enter correct phone number", Toast.LENGTH_SHORT).show();
                } else if (!userconpass.equals(userpassword)) {
                    Toast.makeText(UserActivity.this, "Passwords are not equals", Toast.LENGTH_SHORT).show();
                } else{
                    dialog = new ProgressDialog(UserActivity.this);
                    dialog.setMessage("please wait...");
                    dialog.setTitle("Ez sial");
                    dialog.show();
                    signUp(edemail.getText().toString(),edpassword.getText().toString());


                }
            }
        });
    }
    private void signUp(String email,String pass){

        mAuth=FirebaseAuth.getInstance();

        mAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                dialog.hide();
                if (task.isSuccessful()){
                    FirebaseUser user = mAuth.getCurrentUser();
                    if (user!=null){
                        userid = user.getUid();
                                    Intent obj =new Intent(getApplicationContext(),SignInUserActivity.class);
                                    startActivity(obj);
                                    Toast.makeText(UserActivity.this, "Account is Created", Toast.LENGTH_SHORT).show();
                                    AddUSerDataToRealtime(userid,name,email,pass,userconpass,phonenumber);
                                }
                            }
                    else{
                        dialog.hide();
                    }

                }

        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });

    }
    private void AddUSerDataToRealtime(String userid,String name,String email,String pass,String conpass,String number){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("SailAppForUser").child("Account Details");
        CurrentStatusDetails obj=new CurrentStatusDetails(
                userid,
                name,
                pass,
                email,
                conpass,
                number,
                "User"
                );
        myRef.child(userid).setValue(obj).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    Toast.makeText(UserActivity.this, "Sucesfully Updated", Toast.LENGTH_SHORT).show();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                dialog.dismiss();
                Toast.makeText(UserActivity.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}