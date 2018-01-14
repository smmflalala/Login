package com.example.smmf.login;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

/**
 * Created by smmf on 2017/11/19.
 */

public class EditTextClearButton {

    public static void clearButtonListener(final EditText et, final View X) {
        // 取得et中的文字
        String etInputString = et.getText().toString();
        // 根据et中是否有文字进行X可见或不可见的判断
        if (TextUtils.isEmpty(etInputString)) {
            X.setVisibility(View.INVISIBLE);
        } else {
            X.setVisibility(View.VISIBLE);
        }
        //点击X时使et中的内容为空
        X.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et.setText("");
                et.requestFocusFromTouch();
            }
        });
        //对et的输入状态进行监听
        et.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() == 0) {
                    X.setVisibility(View.INVISIBLE);
                } else {
                    X.setVisibility(View.VISIBLE);
                }
            }
        });
    }
}
