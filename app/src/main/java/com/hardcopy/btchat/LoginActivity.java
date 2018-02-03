package com.hardcopy.btchat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

public class LoginActivity extends Activity {

    private EditText ed_number_login;
    private String string;

    //    private EditText ed_number_login_two;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText ed_number_login = (EditText) findViewById(R.id.ed_number_login);

        //숫자 입력 제한
        InputFilter[] inputFilters = new InputFilter[]{
                new InputFilter.LengthFilter(9)

        };

//        // 자동 하이픈 출력
//        ed_number_login.addTextChangedListener(new PhoneNumberFormattingTextWatcher());

        ImageButton btn_CHECK = (ImageButton) findViewById(R.id.btn_check_login);
        btn_CHECK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String phone_first = ed_number_login.getText().toString();
                Data data_first = new Data(phone_first);

//                Integer phone_two = Integer.parseInt(ed_number_login_two.getText().toString());
//                Data data_two = new Data(phone_two);

                Intent intent = new Intent(LoginActivity.this, AccountActivity.class);
                intent.putExtra("first_phone", data_first);
//                intent.putExtra("second_phone", data_two);
                startActivity(intent);

            }
        });

        // 지우기 버튼

        ImageButton btn_DEL = (ImageButton) findViewById(R.id.btn_DEL_login);
        if (btn_DEL != null) {
            btn_DEL.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String textString = ed_number_login.getText().toString();
                    if (textString.length() > 0 ){
                        ed_number_login.setText(textString.substring(0, textString.length()-1));
                        ed_number_login.setSelection(ed_number_login.getText().length());

                    }
                }
            });
        }


        ed_number_login.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged (CharSequence s,int start, int count, int after){

            }

            @Override
            public void onTextChanged (CharSequence s,int start, int before, int count){
                if (TextUtils.isEmpty(s.toString())) {
                    return;
                }

                String temp = s.toString().replace("-", "");
                //하이픈을 제거한 문자가 4개 이하일 경우
                if (temp.length() <= 4) {
                    return;
                }

                StringBuffer stringBuffer = new StringBuffer();
                while (temp.length() > 4) {
                    String piece = temp.substring(0, 4);                                 // 4글자 추출
                    stringBuffer.append(String.format("%s-", piece));                   // 1234- 포멧팅
                    temp = temp.replace(piece, "");                         // 추출한 4글자 제거
                }

                stringBuffer.append(temp);

                if (!s.toString().equals(stringBuffer.toString())) {
                    //변경 하려던 형식과 기존의 형식이 같이 않을 경우
                    ed_number_login.setText(stringBuffer.toString());
                    // 커서 마지막 이동
                    ed_number_login.setSelection(stringBuffer.toString().length());
                }
            }

            @Override
            public void afterTextChanged (Editable s){
            }
        });


    }

    public void num(View view) {
        EditText ed_number_login = (EditText) findViewById(R.id.ed_number_login);
        switch (view.getId()) {
            case R.id.btn_num0_login:
                ed_number_login.append("0");
                break;

            case R.id.btn_num1_login:
                ed_number_login.append("1");
                break;

            case R.id.btn_num2_login:
                ed_number_login.append("2");
                break;

            case R.id.btn_num3_login:
                ed_number_login.append("3");
                break;

            case R.id.btn_num4_login:
                ed_number_login.append("4");
                break;

            case R.id.btn_num5_login:
                ed_number_login.append("5");
                break;

            case R.id.btn_num6_login:
                ed_number_login.append("6");
                break;

            case R.id.btn_num7_login:
                ed_number_login.append("7");
                break;

            case R.id.btn_num8_login:
                ed_number_login.append("8");
                break;

            case R.id.btn_num9_login:
                ed_number_login.append("9");
                break;

        }

    }
}