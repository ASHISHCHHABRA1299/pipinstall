package com.example.doctordetails;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
   DatabaseReference mDatabaseReference;
   EditText name,phn,age,email,address,expertise;
   CheckBox mbbs,ms,md;
   Button sign;
   private ArrayList<String> degree;
   @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDatabaseReference=FirebaseDatabase.getInstance().getReference();
        name=findViewById(R.id.name);
        phn=findViewById(R.id.phonenumber);
        age=findViewById(R.id.age);
        email=findViewById(R.id.email);
        address=findViewById(R.id.address);
        expertise=findViewById(R.id.expertise);
        mbbs=findViewById(R.id.mbbs);
        ms=findViewById(R.id.ms);
        md=findViewById(R.id.md);
        sign=findViewById(R.id.sign);
        degree=new ArrayList<>();

        mbbs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               if(mbbs.isChecked())
                    degree.add("MBBS");
               else
                    degree.remove("MBBS");
            }
        });
        ms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ms.isChecked())
                    degree.add("MS");
                else
                    degree.remove("MS");
            }
        });
        md.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(md.isChecked())
                    degree.add("MD");
                else
                    degree.remove("MD");
            }
        });

        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name1=name.getText().toString();
                String phn1=phn.getText().toString();
                String age1=age.getText().toString();
                String email1=email.getText().toString();
                String address1=address.getText().toString();
                String expertise1=expertise.getText().toString();
                if(name1.length()==0||phn1.length()==0||age1.length()==0||email1.length()==0||address1.length()==0||expertise1.length()==0)
                {
                    Toast.makeText(MainActivity.this,"Empty Fields",Toast.LENGTH_SHORT).show();
                }else
                {
                   mDatabaseReference.child("NAME").setValue(name1);
                    mDatabaseReference.child("NUMBER").setValue(phn1);
                    mDatabaseReference.child("AGE").setValue(age1);
                    mDatabaseReference.child("EMAIL").setValue(email1);
                    mDatabaseReference.child("ADDRESS").setValue(address1);
                    mDatabaseReference.child("SPECIALIZATION").setValue(expertise1);
                    mDatabaseReference.child("DEGREE").setValue(degree);
                    Toast.makeText(MainActivity.this,"VALUES UPDATED",Toast.LENGTH_SHORT).show();
                }
                //INtent to signature pad
            }
        });
    }
}
