package com.example.doctordetails;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.github.barteksc.pdfviewer.PDFView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;

public class pdfview extends AppCompatActivity {
    private PDFView pdfview;
    private ImageButton back,share;
    private FirebaseDatabase mDatabase=FirebaseDatabase.getInstance();
    DatabaseReference mref=mDatabase.getReference("url");
    String url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdfview);
        pdfview = findViewById(R.id.pdfview);
        back = findViewById(R.id.back);
        share = findViewById(R.id.share);
        mref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Toast.makeText(pdfview.this, "Updated", Toast.LENGTH_SHORT).show();
                url = dataSnapshot.getValue(String.class).toString();
                new RetrievepdfStream().execute(url);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                returnactivity();
            }
        });
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sharepdf();
            }
        });
    }
        class RetrievepdfStream extends AsyncTask<String,Void, InputStream>
        {
            @Override
            protected InputStream doInBackground(String... strings) {

                InputStream inputStream=null;
                try{
                    URL url=new URL(strings[0]);
                    HttpURLConnection urlConnection=(HttpURLConnection)url.openConnection();
                    if(urlConnection.getResponseCode()==200)
                    {
                        inputStream=new BufferedInputStream(urlConnection.getInputStream());
                    }

                }catch(IOException e)
                {
                    return null;
                }
                return inputStream;
            }

            @Override
            protected void onPostExecute(InputStream inputStream) {
                pdfview.fromStream(inputStream).load();
            }
        }


    private void returnactivity() {
        Intent i=new Intent(this,PdfPage.class);
        startActivity(i);
    }
    private void sharepdf()
    {
        Intent i=new Intent(Intent.ACTION_SEND);
        i.setType("application/pdf");
        String[] array={"gulati.anmol10@gmail.com"};
        i.putExtra(Intent.EXTRA_EMAIL,array);
        i.putExtra(Intent.EXTRA_SUBJECT,"RECORDED PRESCRIPTION");
        i.putExtra(Intent.EXTRA_TEXT,"THE LINK TO YOUR PRESCRIPTION IS MENTIONED BELOW:-"+"\n\n"+url);
        if(i.resolveActivity(getPackageManager())!=null)
        {
            startActivity(i);
        }
    }

}
