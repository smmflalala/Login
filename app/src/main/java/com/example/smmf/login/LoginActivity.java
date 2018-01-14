package com.example.smmf.login;

import android.app.Activity;
import android.os.StrictMode;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONException;

import java.io.IOException;

public class LoginActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_login);

        final EditText username_edittext = (EditText) findViewById(R.id.login_username_edittext);
        final EditText password_edittext = (EditText) findViewById(R.id.login_password_edittext);

        View username_clear_imageview = findViewById(R.id.username_clear_imageview);
        View password_clear_imageview = findViewById(R.id.password_clear_imageview);

        EditTextClearButton.clearButtonListener(username_edittext, username_clear_imageview);
        EditTextClearButton.clearButtonListener(password_edittext, password_clear_imageview);

        final TextInputLayout usernameWrapper = (TextInputLayout) findViewById(R.id.usernameWrapper);
        final TextInputLayout passwordWrapper = (TextInputLayout) findViewById(R.id.passwordWrapper);
        usernameWrapper.setHint("用户名");
        passwordWrapper.setHint("密码");

        Button login = (Button) findViewById(R.id.login_button);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = username_edittext.getText().toString();
                String password = password_edittext.getText().toString();
                try {
                    LoginPost.setJson(username, password);
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

    }

}
