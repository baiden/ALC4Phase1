package com.example.alc4phase1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.airbnb.lottie.LottieAnimationView;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

public class MainActivity extends AppCompatActivity {
    LottieAnimationView animation_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        animation_view = (LottieAnimationView)findViewById(R.id.animation_view);
        animation_view.setVisibility(View.VISIBLE);
    }

    public void aboutALC_btn(View view) {
        Intent intent = new Intent(MainActivity.this, AboutALCActivity.class);
        YoYo.with(Techniques.Tada)
                .duration(700)
                .playOn(findViewById(R.id.aboutALC_button));
        startActivity(intent);
    }

    public void myProfile_btn(View view) {
        Intent intent = new Intent(MainActivity.this, MyProfileActivity.class);
        YoYo.with(Techniques.Tada)
                .duration(700)
                .playOn(findViewById(R.id.myProfile_button));
        startActivity(intent);
    }

    public void welcomeMsg_click(View view) {
        YoYo.with(Techniques.StandUp)
                .duration(700)
                .playOn(findViewById(R.id.welcomeMsg_textView));
    }
}
