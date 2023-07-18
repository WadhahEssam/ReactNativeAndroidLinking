package com.rnnativeemojiselector;

import android.content.Context;
import android.content.Intent;

import androidx.annotation.NonNull;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

public class CustomModule extends ReactContextBaseJavaModule {

    private Context context;

    @NonNull
    @Override
    public String getName() {
        return "CustomModule";
    }

    CustomModule(ReactApplicationContext context) {
        super(context);
        this.context = context;
    }

    @ReactMethod
    public void launchAnotherActivity(String text) {
        System.out.println(text);
        Intent intent = new Intent(this.context, AnotherActivity.class);
        intent.putExtra("text", text);
        getCurrentActivity().startActivity(intent);
        getCurrentActivity().overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
    }
}
