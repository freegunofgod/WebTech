package com.example.webtech;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import java.io.Serializable;
import java.util.List;

public class SplashScreen extends AppCompatActivity implements OnRequestCompleted{

    private final static String TAG = SplashScreen.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        /*new Handler().postDelayed(new Runnable(){
            @Override
            public void run(){

            }
        },3000);*/

        if (AppUtils.isNetworkAvailable(getApplicationContext())){
            new HttpGetLyricsRequest(SplashScreen.this).execute();
        } else {
            new GetSongDataBase(getApplicationContext(), SplashScreen.this).execute();
        }
    }

    @Override
    public void onGetSongsCompleted(List<Song> s) {
        Intent intent = new Intent(SplashScreen.this, MainActivity.class);
        intent.putExtra("KEY_DATA", (Serializable) s);
        startActivity(intent);
        finish();
    }
}