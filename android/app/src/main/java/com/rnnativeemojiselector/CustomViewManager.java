package com.rnnativeemojiselector;

import android.graphics.Color;
import android.graphics.Typeface;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;

public class CustomViewManager extends SimpleViewManager<LinearLayout> {

    public static final String REACT_CLASS = "CustomView";
    ReactApplicationContext mCallerContext;

    public CustomViewManager(ReactApplicationContext reactContext) {
        mCallerContext = reactContext;
    }

    @NonNull
    @Override
    public String getName() {
        return REACT_CLASS;
    }

    @NonNull
    @Override
    protected LinearLayout createViewInstance(@NonNull ThemedReactContext themedReactContext) {
        LinearLayout newView = new LinearLayout(themedReactContext);
        LinearLayout.LayoutParams newViewLP = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        newView.setBackgroundColor(Color.MAGENTA);
        newView.setOrientation(LinearLayout.VERTICAL);
        newView.setLayoutParams(newViewLP);
        newView.setPadding(20, 20, 20, 20);

        TextView newTextView = new TextView(themedReactContext);
        newTextView.setText("Hello There Man");
        newTextView.setTextColor(Color.WHITE);
        newTextView.setTypeface(newTextView.getTypeface(), Typeface.BOLD_ITALIC);

        newView.addView(newTextView, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        Button newButton = new Button(themedReactContext);
        newButton.setOnClickListener(view -> {
            Toast.makeText(themedReactContext, "Hello Thats a toast from native", Toast.LENGTH_SHORT).show();
        });
        newButton.setText("Submit");

        // newButton.setOnTouchListener((view, motionEvent) -> {
        //     if (motionEvent.getAction() == android.view.MotionEvent.ACTION_DOWN) {
        //         view.setAlpha(0.5f);
        //         view.setScaleX(0.8f);
        //     } else if (motionEvent.getAction() == MotionEvent.ACTION_UP || motionEvent.getAction() == MotionEvent.ACTION_CANCEL) {
        //         view.setAlpha(1f);
        //         view.setScaleX(1f);
        //     }
        //     return false;
        // });

        // scale the button down when pressed with animation
        newButton.setOnTouchListener((view, motionEvent) -> {
            if (motionEvent.getAction() == android.view.MotionEvent.ACTION_DOWN) {
                view.animate().scaleX(0.9f).scaleY(0.9f).setDuration(100).start();
            } else if (motionEvent.getAction() == MotionEvent.ACTION_UP || motionEvent.getAction() == MotionEvent.ACTION_CANCEL) {
                view.animate().scaleX(1f).scaleY(1f).setDuration(100).start();
            }
            return false;
        });



        newView.addView(newButton);

        return newView;
    }
}

// Things that seem that it doesn't work with react native is passing a react native component to a native
// component so that the native component can display it farther down the tree.