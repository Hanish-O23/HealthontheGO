package com.example.hanish.health_on_the_go;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.ImageView;
import android.widget.VideoView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

public class Splash_activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_activity);
        VideoView vvsplash = (VideoView) findViewById(R.id.vvsplash);
        ImageView ivLoading = (ImageView) findViewById(R.id.ivLoading);
        Uri uri = Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.doctor);
        vvsplash.setVideoURI(uri);
        vvsplash.start();
        Glide.with(getApplicationContext()).load(R.drawable.sliding_square_loader_view).asGif().diskCacheStrategy(DiskCacheStrategy.SOURCE).crossFade().into(ivLoading);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                    Intent i = new Intent(Splash_activity.this,LoginActivity.class);
                    startActivity(i);
                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
