package com.thebaileybrew.firefly.quizversionone;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickDisplayFrontScreenMenu(View view) {
        ImageView backgroundMainImage = findViewById(R.id.firefly_main_header_background);
        AlphaAnimation animateBackground = new AlphaAnimation(1.0f, 0.2f);
        animateBackground.setDuration(2000);
        animateBackground.setRepeatCount(0);
        backgroundMainImage.startAnimation(animateBackground);

        //Calls for the Quiz Activity
        Intent goToQuizStart = new Intent (this, StartQuizActivity.class);
        startActivity(goToQuizStart);
    }
}
