package com.hardcopy.btchat.bluetooth;

import android.content.Context;
import android.content.SharedPreferences;

import com.hardcopy.btchat.utils.Constants;

/**
 * Created by sunki on 2018-01-23.
 */

public class ConnectionInfo {
    private static ConnectionInfo mInstance = null;

    private Context mContext;

    // Target device's MAC address
    private String mDeviceAddress = null;
    // Name of the connected device
    private String mDeviceName = null;


    private ConnectionInfo(Context c) {
        mContext = c;

        SharedPreferences prefs = mContext.getSharedPreferences(Constants.PREFERENCE_NAME, Context.MODE_PRIVATE);
        mDeviceAddress = prefs.getString(Constants.PREFERENCE_CONN_INFO_ADDRESS, null);
        mDeviceName = prefs.getString(Constants.PREFERENCE_CONN_INFO_NAME, null);
    }

    public synchronized static ConnectionInfo getInstance(Context c) {
        if(mInstance == null) {
            if(c != null)
                mInstance = new ConnectionInfo(c);
            else
                return null;
        }
        return mInstance;
    }


    public String getDeviceName() {
        return mDeviceName;
    }


    public void setDeviceName(String name) {
        mDeviceName = name;
        SharedPreferences prefs = mContext.getSharedPreferences(Constants.PREFERENCE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(Constants.PREFERENCE_CONN_INFO_ADDRESS, mDeviceAddress);
        editor.putString(Constants.PREFERENCE_CONN_INFO_NAME, mDeviceName);
        editor.commit();
    }

    public String getDeviceAddress() {
        return mDeviceAddress;
    }

    public void setDeviceAddress(String address) {
        mDeviceAddress = address;
    }
}
