package com.rnnativeemojiselector;


import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class AnotherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);

        LinearLayout main = new LinearLayout(this);
        LinearLayout.LayoutParams mainLayoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        main.setLayoutParams(mainLayoutParams);
        main.setOrientation(LinearLayout.VERTICAL);

        LinearLayout wrapperLayout = new LinearLayout(this);
        wrapperLayout.setOrientation(LinearLayout.VERTICAL);
        wrapperLayout.setGravity(Gravity.CENTER);
        wrapperLayout.setLayoutParams(mainLayoutParams);
        main.addView(wrapperLayout);

        TextView viewText = new TextView(this);
        Intent callingIntent = getIntent();
        LinearLayout.LayoutParams textLayoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        viewText.setGravity(Gravity.CENTER);
        viewText.setLayoutParams(textLayoutParams);
        String message = callingIntent.getStringExtra("text");
        System.out.println(message);
        viewText.setText(message);
        wrapperLayout.addView(viewText);


        Button goToMainActivityButton = new Button(this);
        goToMainActivityButton.setText("Go To Main Activity");
        goToMainActivityButton.setOnClickListener(view -> {
            Intent intentToGoToMainActivity = new Intent(this, MainActivity.class);
            startActivity(intentToGoToMainActivity);
            this.overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
        });
        wrapperLayout.addView(goToMainActivityButton, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        setContentView(main);
    }
}
