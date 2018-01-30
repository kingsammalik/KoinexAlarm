package com.samapps.sachinmalik.koinexalarm.Alarm;

import android.Manifest;
import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import java.io.IOException;
import java.text.DecimalFormat;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {

    private String TAG = MainActivity.class.getSimpleName();
    TextView earning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            koinexAPI.ret(new Callback<TickerResponse>() {
                @Override
                public void onResponse(Call<TickerResponse> call, Response<TickerResponse> response) {
                    if (response.isSuccessful()) {

                        TickerResponse user = response.body();
                        String text="Ripple is "+user.getPrices().getXRP()+" and Eth is "+user.getPrices().getETH();
                        Log.e("price", "" + text);
                        ((TextView)findViewById(R.id.textView)).setText(user.getPrices().getETH());
                        ((TextView)findViewById(R.id.textView4)).setText(user.getPrices().getXRP());
                        DecimalFormat df = new DecimalFormat("#.##");
                        earning=(TextView)findViewById(R.id.textView6);
                        Double earningd=(Float.parseFloat(user.getPrices().getXRP())*37.80);
                        earning.setText(String.valueOf(df.format(earningd)));
                        if(earningd<5000){
                            earning.setTextColor(Color.RED);
                        }
                        else {
                            earning.setTextColor(Color.GREEN);
                        }

                    } else {
                        int statusCode = response.code();
                        Log.e("status", "" + statusCode);
                        // ResponseBody errorBody = response.message();
                        Log.e("message", "" + response.message());
                    }
                }

                @Override
                public void onFailure(Call<TickerResponse> call, Throwable t) {

                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.WAKE_LOCK);

        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WAKE_LOCK}, 1);
        } else {
            //TODO
        }

        ((Switch)findViewById(R.id.switch1)).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    cancelAlarm();
                    setAlarm();
                }
                else {
                    cancelAlarm();
                }
            }
        });


    }

    void setAlarm(){
        AlarmManager alarmManager=(AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, koinex.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0);
        try {
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,System.currentTimeMillis(),900000,
                    pendingIntent);
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }

    void cancelAlarm(){
        NotificationManager mNotificationManager;
        mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        AlarmManager alarmManager=(AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, koinex.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0);
        try {
            alarmManager.cancel(pendingIntent);
            mNotificationManager.cancelAll();
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1:
                if ((grantResults.length > 0) && (grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    //TODO
                }
                break;

            default:
                break;
        }
    }

}
