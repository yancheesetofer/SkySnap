package com.example.projectweather;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.res.ResourcesCompat;
import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    String BaseUrl = "http://api.openweathermap.org/";
    String AppId = "80795121acadcb2bc77cf7c3701940c4";
    Button confirm;
    Button location;
    EditText city;
    TextView tit;

    private static final int REQUEST_LOCATION = 1;
    LocationManager locationManager;
    String latitude, longitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        confirm = findViewById(R.id.confirm);
        location = findViewById(R.id.location);
        city = findViewById(R.id.city);
        tit = findViewById(R.id.tit);

        Resources res = getResources();
        View view = this.getWindow().getDecorView();
        Calendar c = Calendar.getInstance();
        Drawable drawableday = ResourcesCompat.getDrawable(res, R.drawable.day, null);
        Drawable drawablesunset = ResourcesCompat.getDrawable(res, R.drawable.sunsett, null);
        Drawable drawablenight = ResourcesCompat.getDrawable(res, R.drawable.night, null);
        int timeOfDay = c.get(Calendar.HOUR_OF_DAY);
        if(timeOfDay >= 5 && timeOfDay < 12){
            view.setBackground(drawableday);
            tit.setText("Good Morning");
        }
        else if(timeOfDay >= 12 && timeOfDay < 18){
            view.setBackground(drawablesunset);
            tit.setText("Good Afternoon");
        }
        else {
                view.setBackground(drawablenight);
            tit.setText("Good Evening");
        }


        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txt = city.getText().toString();
                getCurrentData(txt,latitude,longitude);
            }
        });

        ActivityCompat.requestPermissions( this,new String[] {Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);
        location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
                if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                    OnGPS();
                } else {
                    getLocation();
                }
            }
        });
    }

    void getCurrentData(String txt,String latitude,String longitude) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        WeatherApi service = retrofit.create(WeatherApi.class);
        if(txt != "") {
            Call<ResponseWeather> call = service.getCurrentWeatherDataByCity(txt, AppId);
            call.enqueue(new Callback<ResponseWeather>() {
                @Override
                public void onResponse(@NonNull Call<ResponseWeather> call, @NonNull Response<ResponseWeather> response) {
                    if (response.code() == 200) {
                        ResponseWeather weatherResponse = response.body();
                        assert weatherResponse != null;
                        String t0 = weatherResponse.name;
                        float t1 = weatherResponse.main.temp;
                        int round_int = (int)Math.round(t1);
                        String t11 = String.valueOf(round_int)+" °C";
                        float t2 = weatherResponse.main.humidity;
                        String t12 = "Humidity           " + String.valueOf(t2)+"%";
                        float t3 = weatherResponse.main.pressure;
                        String t13 = "Pressure            " + String.valueOf(t3);
                        float t4 = weatherResponse.clouds.all;
                        String t14 = "Clouds              " + String.valueOf(t4);
                        float t5 = weatherResponse.wind.speed;
                        String t15 = "Wind                " + String.valueOf(t5)+"km/h";
                        String t16 = weatherResponse.getWeatherList().get(0).icon;
                        String t17 = weatherResponse.getWeatherList().get(0).main;
                        goTonextLayout(t0,t11,t12,t13,t14,t15,t16,t17);
                    }
                }

                @Override
                public void onFailure(@NonNull Call<ResponseWeather> call, @NonNull Throwable t) {
                    Toast.makeText(getApplicationContext(), "An error has occured", Toast.LENGTH_LONG).show();
                }
            });
        }
        else{
            Call<ResponseWeather> call = service.getCurrentWeatherDataByLocation(latitude,longitude, AppId);
            call.enqueue(new Callback<ResponseWeather>() {
                @Override
                public void onResponse(@NonNull Call<ResponseWeather> call, @NonNull Response<ResponseWeather> response) {
                    if (response.code() == 200) {
                        ResponseWeather weatherResponse = response.body();
                        assert weatherResponse != null;
                        String t0 = weatherResponse.name;
                        float t1 = weatherResponse.main.temp;
                        int round_int = (int)Math.round(t1);
                        String t11 = String.valueOf(t1)+'°';
                        float t2 = weatherResponse.main.humidity;
                        String t12 = "Humidity               " + String.valueOf(t2);
                        float t3 = weatherResponse.main.pressure;
                        String t13 = "Pressure              " + String.valueOf(t3);
                        float t4 = weatherResponse.clouds.all;
                        String t14 = "Clouds                " + String.valueOf(t4);
                        float t5 = weatherResponse.wind.speed;
                        String t15 = "Wind                   " + String.valueOf(t5);
                        String t16 = weatherResponse.getWeatherList().get(0).icon;
                        String t17 = weatherResponse.getWeatherList().get(0).main;
                        goTonextLayout(t0,t11,t12,t13,t14,t15,t16,t17);
                    }
                }

                @Override
                public void onFailure(@NonNull Call<ResponseWeather> call, @NonNull Throwable t) {
                    Toast.makeText(getApplicationContext(), "An error has occured", Toast.LENGTH_LONG).show();
                }
            });
        }
    }

    void goTonextLayout(String t0,String t11,String t12,String t13,String t14,String t15,String t16,String t17){
        Intent in = new Intent(MainActivity.this,MainActivity2.class);
        in.putExtra("city",t0);
        in.putExtra("temp",t11);
        in.putExtra("humidity",t12);
        in.putExtra("pressure",t13);
        in.putExtra("clouds",t14);
        in.putExtra("wind",t15);
        in.putExtra("icon",t16);
        in.putExtra("main",t17);
        startActivity(in);
    }

    private void OnGPS() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Enable GPS").setCancelable(false).setPositiveButton("Yes", new  DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
            }
        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        final AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void getLocation() {
        if (ActivityCompat.checkSelfPermission(
                MainActivity.this,Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);
        } else {
            Location locationGPS = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            if (locationGPS != null) {
                double lat = locationGPS.getLatitude();
                double longi = locationGPS.getLongitude();
                latitude = String.valueOf(lat);
                longitude = String.valueOf(longi);
                String txt = "";
                getCurrentData(txt,latitude,longitude);

            } else {
                Toast.makeText(this, "Unable to find location.", Toast.LENGTH_SHORT).show();
            }
        }
    }

}













