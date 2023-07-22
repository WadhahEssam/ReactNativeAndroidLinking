package com.rnnativeemojiselector;

import android.graphics.Color;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

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
        newView.setBackgroundColor(Color.RED);
        newView.setOrientation(LinearLayout.VERTICAL);
        newView.setLayoutParams(newViewLP);
        newView.setPadding(20, 20, 20, 20);

        TextView newTextView = new TextView(themedReactContext);
        newTextView.setText("Hello There Man");
        newTextView.setTextColor(Color.WHITE);
        newView.addView(newTextView, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        Button newButton = new Button(themedReactContext);
        newButton.setText("Submit");
        newView.addView(newButton);

        return newView;
    }
}
