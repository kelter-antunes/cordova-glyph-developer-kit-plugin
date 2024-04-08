package com.kelter.glyphplugin;

import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;

import android.content.Context;

import com.nothing.ketchum.Common;
import com.nothing.ketchum.GlyphException;
import com.nothing.ketchum.GlyphManager;


import org.json.JSONArray;
import org.json.JSONException;

public class GlyphPlugin extends CordovaPlugin {

    private Context context; // Declare a Context variable

    @Override
    public void initialize(CordovaInterface cordova, CordovaWebView webView) {
        super.initialize(cordova, webView);
        // Save the context for later use
        this.context = cordova.getActivity().getApplicationContext();
    }

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if (action.equals("init")) {
            try {
                // Call the necessary Glyph SDK methods here
                // You can use the args parameter to pass any additional data from JavaScript
                
                // Example:
                boolean success = GlyphManager.getInstance(context).init(); // Pass the context
                
                // Handle the result and callback to JavaScript
                // Example:
                if (success) {
                    callbackContext.success();
                } else {
                    callbackContext.error("Initialization failed");
                }
            } catch (Exception e) {
                // Catch any exceptions and pass them back to JavaScript
                callbackContext.error("Exception: " + e.getMessage());
            }

            return true;
        }
        
        // Add more actions and corresponding implementations as needed
        
        return false;
    }
}
