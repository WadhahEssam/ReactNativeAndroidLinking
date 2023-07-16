package com.rnnativeemojiselector;

import static java.security.AccessController.getContext;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.view.View;
import android.widget.ProgressBar;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.uimanager.events.RCTEventEmitter;

import javax.annotation.Nullable;

public class ProgressBarViewManager extends SimpleViewManager<ProgressBar> {

    public static final String REACT_CLASS = "CustomProgressBar";

    @Override
    public String getName() {
        return REACT_CLASS;
    }

    @Override
    protected ProgressBar createViewInstance(ThemedReactContext reactContext) {
        ProgressBar view = new ProgressBar(reactContext, null, android.R.attr.progressBarStyleInverse);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("test");
                WritableMap params = Arguments.createMap();
                params.putString("eventProperty", "someValue");
                sendEvent(reactContext, "ChangeColorEvent", params);
            }
        });
        return view;
    }

    @ReactProp(name = "progress", defaultInt = 0)
    public void setProgress(ProgressBar view, int progress) {
        view.setProgress(progress);
    }

    @ReactProp(name = "indeterminate", defaultBoolean = false)
    public void setIndeterminate(ProgressBar view, boolean indeterminate) {
        view.setIndeterminate(indeterminate);
    }

    @ReactProp(name = "color", customType = "Color")
    public void setColor(ProgressBar view, @Nullable Integer color) {
        if (color != null) {
            view.getIndeterminateDrawable().setColorFilter(color, PorterDuff.Mode.SRC_IN);
        }
    }

    private void sendEvent(ReactContext reactContext,
                           String eventName,
                           @Nullable WritableMap params) {
        reactContext
                .getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
                .emit(eventName, params);
    }

    private int listenerCount = 0;

    @ReactMethod
    public void addListener(String eventName) {
        if (listenerCount == 0) {
            // Set up any upstream listeners or background tasks as necessary
        }

        listenerCount += 1;
    }

    @ReactMethod
    public void removeListeners(Integer count) {
        listenerCount -= count;
        if (listenerCount == 0) {
            // Remove upstream listeners, stop unnecessary background tasks
        }
    }


}