package com.hardcopy.btchat;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.gson.JsonObject;
import com.hardcopy.btchat.model.SaveInfo;
import com.hardcopy.btchat.network.CallbackEvent;
import com.hardcopy.btchat.network.HttpReqManager;
import com.hardcopy.btchat.service.BTCTemplateService;
import com.tsengvn.typekit.TypekitContextWrapper;

public class AccountActivity extends FragmentActivity {
    public static Activity contexta;

    //layout
    private TextView tv_userPhone;
    private TextView tv_userMoney;
    private Button btn_check_account;

    //new
    private String uid;
    private int totalAmount;
    private SaveInfo saveInfo = new SaveInfo();
    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            String strAmount = intent.getStringExtra("amount");

            int amount = Integer.parseInt(strAmount);
            totalAmount += amount;

            tv_userMoney.setText(totalAmount + "￦");
            saveInfo.amount = totalAmount;
        }
    };

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(TypekitContextWrapper.wrap(newBase));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(broadcastReceiver);
    }

    @Override
    public void onBackPressed() {
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        contexta = AccountActivity.this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/Dosis-Bold.ttf");
        tv_userPhone = (TextView) findViewById(R.id.tv_userPhone);
        tv_userMoney = (TextView) findViewById(R.id.tv_userMoney);
        ImageButton btn_check_account = (ImageButton) findViewById(R.id.btn_check_account);

        tv_userMoney.setText("0 원");

        registerReceiver(broadcastReceiver, new IntentFilter(BTCTemplateService.BROADCAST_ACTION));

        String uid = "";

        Intent intent = getIntent();
        if (intent.getExtras() != null) {
            uid = intent.getExtras().getString("uid");
            tv_userPhone.setText("" + uid);
        }

        saveInfo.amount = 0;
        saveInfo.uid = uid;
        saveInfo.content = "퍼즐벤처스 카페";
        saveInfo.depositType = 0;

        //전화번호 넘겨받기
        Intent intentNumber = getIntent();
        Data data_first = (Data) intentNumber.getSerializableExtra("first_phone");

        tv_userPhone.setText("" + data_first.phone_first);
        tv_userPhone.addTextChangedListener(new PhoneNumberFormattingTextWatcher());


        //메인 화면 넘어가기


        btn_check_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (saveInfo.amount == 0) {
                    Intent intent = new Intent(AccountActivity.this, WaitActivity.class);
                    startActivity(intent);
                    finish();
                } else {

                    HttpReqManager.postSaveMoney(saveInfo, new CallbackEvent<JsonObject>() {
                        //                        @Override
                        public void onSuccess(JsonObject jsonObject) {

                            if (jsonObject != null) ;

                            else
                                ;
                            Intent intent = new Intent(AccountActivity.this, WaitActivity.class);
                            startActivity(intent);
                            finish();
                        }

                        @Override
                        public void onError() {

                        }
                    });
                }
            }
        });
    }
}

