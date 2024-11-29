package com.marlenepaper.walkers;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_splash_screen);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        launchMain();

        Animation gradientColor = AnimationUtils.loadAnimation(this, R.anim.gradient_color);
        ImageView splashLogo = findViewById(R.id.splashLogo);
        TextView splashAppName = findViewById(R.id.marlenepaper);
        splashLogo.startAnimation(gradientColor);
        splashAppName.startAnimation(gradientColor);

        ImageView glideBackground = findViewById(R.id.splashGlideBackground);

        Glide. with(this)
                .load("https://images.pexels.com/photos/12762122/pexels-photo-12762122.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1")
                .transition(DrawableTransitionOptions.withCrossFade(800))
                .centerCrop()
                .into(glideBackground);
    }

    public void launchMain() {
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreen.this, Login.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        }, 4000);
    }



    }
