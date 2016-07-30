package com.exmple.safeguard;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.exmple.besafe.R;

public class call extends Activity {
CardView card,card1,card2;
    ImageView imageView,imageView1,imageView2;
    SharedPreferences sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call);
        ImageView imageView = (ImageView) findViewById(R.id.card_thumbnail_image);
        ImageView imageView1 = (ImageView) findViewById(R.id.card_thumbnail_image1);
        ImageView imageView2 = (ImageView) findViewById(R.id.card_thumbnail_image2);
        sp= getSharedPreferences("login", MODE_PRIVATE);
        card=(CardView) findViewById(R.id.card_view);
        card.setPreventCornerOverlap(false);
        card1=(CardView) findViewById(R.id.card_view1);
        card1.setPreventCornerOverlap(false);
        card2=(CardView) findViewById(R.id.card_view2);
        card2.setPreventCornerOverlap(false);
        card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phone1=sp.getString("number1", null);
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:"+phone1));
                startActivity(callIntent);
            }
        });
        card1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phone2=sp.getString("number2", null);
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:"+phone2));
                startActivity(callIntent);
            }
        });
        card2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phone3=sp.getString("number3", null);
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:"+phone3));
                startActivity(callIntent);
            }
        });


    }
}
