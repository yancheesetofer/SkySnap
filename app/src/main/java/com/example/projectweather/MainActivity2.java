package com.example.projectweather;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import java.util.Calendar;


public class MainActivity2 extends AppCompatActivity {

    TextView c1;
    TextView c2;
    TextView c3;
    TextView c4;
    TextView c5;
    TextView c6;
    TextView c7;
    ImageView icon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weather);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Resources res = getResources();
        View view = this.getWindow().getDecorView();
        Calendar c = Calendar.getInstance();
        Drawable drawableday = ResourcesCompat.getDrawable(res, R.drawable.day, null);
        Drawable drawablesunset = ResourcesCompat.getDrawable(res, R.drawable.sunsett, null);
        Drawable drawablenight = ResourcesCompat.getDrawable(res, R.drawable.night, null);
        int timeOfDay = c.get(Calendar.HOUR_OF_DAY);
        if(timeOfDay >= 5 && timeOfDay < 12){
            view.setBackground(drawableday);
        }
        else if(timeOfDay >= 12 && timeOfDay < 18){
            view.setBackground(drawablesunset);
        }
        else {
            view.setBackground(drawablenight);
        }

        c1 = findViewById(R.id.c1);
        c2 = findViewById(R.id.c2);
        c3 = findViewById(R.id.c3);
        c4 = findViewById(R.id.c4);
        c5 = findViewById(R.id.c5);
        c6 = findViewById(R.id.c6);
        c7 = findViewById(R.id.c7);
        icon = findViewById(R.id.icon);

        Intent in1 = getIntent();
        String country = ((Intent) in1).getStringExtra("city");
        String temp = ((Intent) in1).getStringExtra("temp");
        String humidity = ((Intent) in1).getStringExtra("humidity");
        String pressure = ((Intent) in1).getStringExtra("pressure");
        String clouds = ((Intent) in1).getStringExtra("clouds");
        String wind = ((Intent) in1).getStringExtra("wind");
        String i = ((Intent) in1).getStringExtra("icon");
        String main = ((Intent) in1).getStringExtra("main");

        if(timeOfDay >= 5 && timeOfDay < 18){
            switch (i){
                case "01d" : icon.setImageResource(R.drawable.i12);break;
                case "02d" : icon.setImageResource(R.drawable.i11);break;
                case "03d" : icon.setImageResource(R.drawable.i10);break;
                case "04d" : icon.setImageResource(R.drawable.i9);break;
                case "09d" : icon.setImageResource(R.drawable.i8);break;
                case "10d" : icon.setImageResource(R.drawable.i7);break;
                case "11d" : icon.setImageResource(R.drawable.i6);break;
                case "13d" : icon.setImageResource(R.drawable.i5);break;
                case "50d" : icon.setImageResource(R.drawable.i4);break;
            }
        }
        else{
            switch (i){
                case "01n" : icon.setImageResource(R.drawable.i1);break;
                case "02n" : icon.setImageResource(R.drawable.i2);break;
                case "03n" : icon.setImageResource(R.drawable.i10);break;
                case "04n" : icon.setImageResource(R.drawable.i9);break;
                case "09n" : icon.setImageResource(R.drawable.i8);break;
                case "10n" : icon.setImageResource(R.drawable.i3);break;
                case "11n" : icon.setImageResource(R.drawable.i6);break;
                case "13n" : icon.setImageResource(R.drawable.i5);break;
                case "50n" : icon.setImageResource(R.drawable.i4);break;
            }
        }

        c1.setText(country);
        c2.setText(temp);
        c3.setText(humidity);
        c4.setText(pressure);
        c5.setText(clouds);
        c6.setText(wind);
        c7.setText(main);

    }
}
