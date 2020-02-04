package com.example.doctordetails;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    Button signatureButton;
    ImageView signImage;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        signatureButton = (Button) findViewById(R.id.getSign);
        signImage = (ImageView) findViewById(R.id.imageView1);
        signatureButton.setOnClickListener(onButtonClick);
    }

    Button.OnClickListener onButtonClick = new Button.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent i = new Intent(MainActivity.this, SignatureActivity.class);
            startActivityForResult(i,0);
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //to get imagepath from SignatureActivity and set it on ImageView
        String image_path = data.getStringExtra("imagePath");
        if(!image_path.equals("")) {
            Bitmap bitmap = BitmapFactory.decodeFile(image_path);
            signImage.setImageBitmap(bitmap);
        }
    }
}
