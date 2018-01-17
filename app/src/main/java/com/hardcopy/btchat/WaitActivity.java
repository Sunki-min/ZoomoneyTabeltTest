package com.hardcopy.btchat;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.hardcopy.btchat.service.BTCTemplateService;

public class WaitActivity extends FragmentActivity {
    public static Activity contexta;

    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            finish();
        }
    };

    @Override
    protected void onDestroy(){
        super.onDestroy();
        unregisterReceiver(broadcastReceiver);
    }

    @Override
    public void onBackPressed() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        contexta = WaitActivity.this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wait);

        Intent intent = new Intent(WaitActivity.this, StartActivity.class);
        startActivity(intent);
        finish();

        registerReceiver(broadcastReceiver, new IntentFilter(BTCTemplateService.BROADCAST_ACTION));
    }
}
