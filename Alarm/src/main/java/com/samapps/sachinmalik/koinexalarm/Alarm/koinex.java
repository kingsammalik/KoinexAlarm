package com.samapps.sachinmalik.koinexalarm.Alarm;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.PowerManager;
import android.os.Vibrator;
import android.util.Log;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class koinex extends BroadcastReceiver {
    String baseurl="https://koinex.in/api/";
    Context context;
    @Override
    public void onReceive(final Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
       // throw new UnsupportedOperationException("Not yet implemented");
        this.context=context;
        try {
            //ret();

            koinexAPI.ret(new Callback<TickerResponse>() {
                @Override
                public void onResponse(Call<TickerResponse> call, Response<TickerResponse> response) {
                    if (response.isSuccessful()) {

                        TickerResponse user = response.body();
                        String text="Ripple is "+user.getPrices().getXRP()+" and Eth is "+user.getPrices().getETH();

                        try {
                            sendNotification(text,context);
                            if(Float.parseFloat(user.getPrices().getXRP())>149){
                                showWarning("Price Hike Alert!!  "+"Ripple is "+user.getPrices().getXRP(),2);
                            }
                            if(Float.parseFloat(user.getPrices().getXRP())<100){
                                showWarning("Price Drop Alert!!  "+"Ripple is "+user.getPrices().getXRP(),2);
                            }
                            if(Float.parseFloat(user.getPrices().getETH())>80000){
                                showWarning("Price Hike Alert!!  "+"Eth is "+user.getPrices().getETH(),3);
                            }
                            if(Float.parseFloat(user.getPrices().getETH())<60000){
                                showWarning("Price Drop Alert!!  "+"Eth is "+user.getPrices().getETH(),3);
                            }
                            Log.e("koinex","ripple "+user.getPrices().getXRP());
                        } catch (Exception e) {
                            e.printStackTrace();

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
                    Log.e("message", "" +t.getMessage());

                    System.out.print(t.getMessage());
                    t.printStackTrace();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*void ret() throws IOException {


        final OkHttpClient okHttpClient;
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(30, TimeUnit.SECONDS);
        builder.readTimeout(30, TimeUnit.SECONDS);
        builder.writeTimeout(30, TimeUnit.SECONDS);
        okHttpClient = builder.build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseurl)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        gitAPI stackOverflowAPI = retrofit.create(gitAPI.class);


        Call<TickerResponse> call = stackOverflowAPI.sendnotes();

        call.enqueue(new Callback<TickerResponse>() {
            @Override
            public void onResponse(Call<TickerResponse> call, Response<TickerResponse> response) {
                if (response.isSuccessful()) {

                    TickerResponse user = response.body();
                    String text="Ripple is "+user.getPrices().getXRP()+" and Eth is "+user.getPrices().getETH();

                    try {
                        sendNotification(text,context);
                        if(Float.parseFloat(user.getPrices().getXRP())>149){
                            showWarning("Price Hike Alert!!  "+"Ripple is "+user.getPrices().getXRP(),2);
                        }
                        if(Float.parseFloat(user.getPrices().getXRP())<100){
                            showWarning("Price Drop Alert!!  "+"Ripple is "+user.getPrices().getXRP(),2);
                        }
                        if(Float.parseFloat(user.getPrices().getETH())>80000){
                            showWarning("Price Hike Alert!!  "+"Eth is "+user.getPrices().getETH(),3);
                        }
                        if(Float.parseFloat(user.getPrices().getETH())<60000){
                            showWarning("Price Drop Alert!!  "+"Eth is "+user.getPrices().getETH(),3);
                        }
                        Log.e("koinex","ripple "+user.getPrices().getXRP());
                    } catch (Exception e) {
                        e.printStackTrace();

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

                Log.e("message", "" +t.getMessage());

                System.out.print(t.getMessage());
                t.printStackTrace();

            }


        });

    }*/

    private void sendNotification(String msg,Context context) {//sachin
        PendingIntent contentIntent;
        Notification notification;
        NotificationManager mNotificationManager;
        mNotificationManager = (NotificationManager) context
                .getSystemService(Context.NOTIFICATION_SERVICE);
        contentIntent = PendingIntent.getActivity(context, 0,
                new Intent(context, MainActivity.class), PendingIntent.FLAG_UPDATE_CURRENT);
        Notification.Builder builder = new Notification.Builder(context);

        builder.setAutoCancel(true);
        builder.setDefaults(Notification.DEFAULT_SOUND|Notification.DEFAULT_LIGHTS|Notification.DEFAULT_VIBRATE);
        builder.setContentTitle("Koinex");
        builder.setContentText(msg);
        builder.setSmallIcon(R.drawable.koinex_enables_withdrawals);
        builder.setContentIntent(contentIntent);
        builder.setOngoing(true);
        builder.build();

        notification = builder.getNotification();

        mNotificationManager.notify(1, notification);
    }

    void showWarning(String msg,int id){
        PowerManager pm = (PowerManager)context.getSystemService(Context.POWER_SERVICE); //sachin

        boolean isScreenOn = pm.isScreenOn();

        Log.e("screen on..........", ""+isScreenOn);

        if(!isScreenOn)
        {
            PowerManager.WakeLock wl = pm.newWakeLock(PowerManager.FULL_WAKE_LOCK |PowerManager.ACQUIRE_CAUSES_WAKEUP |PowerManager.ON_AFTER_RELEASE,"MyLock");
            wl.acquire(10000);
            PowerManager.WakeLock wl_cpu = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK,"MyCpuLock");
            wl_cpu.acquire(10000);
        }

        Vibrator v = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        // Vibrate for 500 milliseconds
        v.vibrate(3000);

        PendingIntent contentIntent;
        Notification notification;
        NotificationManager mNotificationManager;
        mNotificationManager = (NotificationManager) context
                .getSystemService(Context.NOTIFICATION_SERVICE);
        contentIntent = PendingIntent.getActivity(context, 0,
                new Intent(context, MainActivity.class), PendingIntent.FLAG_UPDATE_CURRENT);
        Notification.Builder builder = new Notification.Builder(context);

        builder.setAutoCancel(true);
        builder.setDefaults(Notification.DEFAULT_SOUND|Notification.DEFAULT_LIGHTS|Notification.DEFAULT_VIBRATE);
        builder.setContentTitle("Koinex");
        builder.setContentText(msg);
        builder.setSmallIcon(R.drawable.koinex_enables_withdrawals);
        builder.setContentIntent(contentIntent);
        builder.setOngoing(false);
        builder.build();

        notification = builder.getNotification();

        mNotificationManager.notify(id, notification);


    }
}
