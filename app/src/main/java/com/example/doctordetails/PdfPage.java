package com.example.doctordetails;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PdfPage extends AppCompatActivity {
Button b1,b2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf_page);
        b1=findViewById(R.id.view);
        b2=findViewById(R.id.redo);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openpdfpageactivity();
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            openspeechactivity();
            }
        });
    }

    private void openpdfpageactivity() {
        Intent i=new Intent(this,pdfview.class);
        startActivity(i);
    }

    private void openspeechactivity() {
        Intent i=new Intent(this,SpeechText.class);
        startActivity(i);
    }

}
