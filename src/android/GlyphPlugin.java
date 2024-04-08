package com.kelter.glyphplugin;

import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaWebView;

import android.content.ComponentName;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.nothing.ketchum.Common;
import com.nothing.ketchum.GlyphException;
import com.nothing.ketchum.GlyphFrame;
import com.nothing.ketchum.GlyphManager;

import org.json.JSONArray;
import org.json.JSONException;

public class GlyphPlugin extends CordovaPlugin {

    private GlyphManager mGM = null;
    private GlyphManager.Callback mCallback = null;
    private Context context;

    @Override
    public void initialize(CordovaInterface cordova, CordovaWebView webView) {
        super.initialize(cordova, webView);
        this.context = cordova.getActivity().getApplicationContext();
    }

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if (action.equals("init")) {
            try {
                // Initialize GlyphManager
                mGM = GlyphManager.getInstance(context);

                // Initialize Callback
                mCallback = new GlyphManager.Callback() {
                    @Override
                    public void onServiceConnected(ComponentName componentName) {
                        if (Common.is20111()) mGM.register(Common.DEVICE_20111);
                        if (Common.is22111()) mGM.register(Common.DEVICE_22111);
                        if (Common.is23111()) mGM.register(Common.DEVICE_23111);
                        try {
                            mGM.openSession();
                        } catch(GlyphException e) {
                            Log.e("GlyphPlugin", e.getMessage());
                        }
                    }

                    @Override
                    public void onServiceDisconnected(ComponentName componentName) {
                        try {
                            mGM.closeSession();
                        } catch (GlyphException e) {
                            Log.e("GlyphPlugin", e.getMessage());
                        }
                    }
                };

                // Initialize GlyphManager with the Callback
                mGM.init(mCallback);

                // Indicate success to JavaScript
                callbackContext.success();
                return true;
            } catch (Exception e) {
                // Catch any exceptions and pass them back to JavaScript
                callbackContext.error("Exception: " + e.getMessage());
                return false;
            }
        } else if (action.equals("createFrame")) {
            try {

                /*
                GlyphFrame.Builder builder = mGM.getGlyphFrameBuilder();
                GlyphFrame frame = builder.buildChannelA().build();
                mGM.toggle(frame);
    
                */
                JSONArray channelsArray = args.getJSONArray(0);
                int period = args.getInt(1);
                int cycles = args.getInt(2);
                int interval = args.getInt(3);
    
                GlyphFrame.Builder builder = mGM.getGlyphFrameBuilder();
                GlyphFrame.Builder frameBuilder = builder;
                for (int i = 0; i < channelsArray.length(); i++) {
                    int channel = channelsArray.getInt(i);
                    frameBuilder = frameBuilder.buildChannel(channel);
                }
                GlyphFrame frame = frameBuilder.buildPeriod(period)
                                                .buildCycles(cycles)
                                                .buildInterval(interval)
                                                .build();

                mGM.toggle(frame);




                // Return the frame JSON representation to JavaScript
                callbackContext.success();
                return true;
            } catch (Exception e) {
                // Catch any exceptions and pass them back to JavaScript
                callbackContext.error("Exception: " + e.getMessage());
                return false;
            }
        } else if (action.equals("toggleFrame")) {
            try {
                // Parse input parameters
               // String frameJSON = args.getString(0);

                // Create GlyphFrame from JSON
                //GlyphFrame frame = GlyphFrame.fromJSON(frameJSON);

                // Toggle the GlyphFrame
                //mGM.toggle(frame);

                // Indicate success to JavaScript
                callbackContext.success();
                return true;
            } catch (Exception e) {
                // Catch any exceptions and pass them back to JavaScript
                callbackContext.error("Exception: " + e.getMessage());
                return false;
            }
        } else if (action.equals("turnOff")) {
            try {
  
                // Toggle the GlyphFrame
                mGM.turnOff();

                // Indicate success to JavaScript
                callbackContext.success();
                return true;
            } catch (Exception e) {
                // Catch any exceptions and pass them back to JavaScript
                callbackContext.error("Exception: " + e.getMessage());
                return false;
            }
        }

        return false;
    }
}
