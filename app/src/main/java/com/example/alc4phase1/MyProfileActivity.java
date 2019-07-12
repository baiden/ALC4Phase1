package com.example.alc4phase1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

public class MyProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);
    }

    public void backToMainBtn(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void messageButton(View view) {
        YoYo.with(Techniques.Tada)
                .duration(700)
                .playOn(findViewById(R.id.messageButton));
    }

    public void editButton(View view) {
        YoYo.with(Techniques.Tada)
                .duration(700)
                .playOn(findViewById(R.id.editProfilebutton));
    }

    public void settingsButton(View view) {
        YoYo.with(Techniques.Wobble)
                .duration(700)
                .playOn(findViewById(R.id.settingsButton));
    }
}
