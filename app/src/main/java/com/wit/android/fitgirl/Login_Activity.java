package com.wit.android.fitgirl;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class Login_Activity extends Activity {

    LinearLayout log_in, sign_in_btn, sign_up_btn, login;
    RelativeLayout coordinator;
    EditText user_name, user_email, user_password;
    TextView skip, line_name, txt_login, terms, in, up;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_);

        log_in = findViewById(R.id.login);


        sign_in_btn = findViewById(R.id.sign_in_btn);
        skip = findViewById(R.id.skip_textview);
        user_name=findViewById(R.id.user_name);
        user_email=findViewById(R.id.user_email);
        line_name=findViewById(R.id.line_name);
        user_password=findViewById(R.id.user_password);
        txt_login=findViewById(R.id.txt_login);

        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Login_Activity.this, HomeActivity.class);
                startActivity(i);
            }
        });
        sign_in_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sign_in_btn.setBackgroundResource(R.drawable.left_selected);
                sign_up_btn.setBackgroundResource(R.drawable.right);

                in.setTextColor(getResources().getColor(R.color.colorPrimary));
                up.setTextColor(getResources().getColor(R.color.colorWhite));
                user_name.setVisibility(View.GONE);
                line_name.setVisibility(View.GONE);
                txt_login.setText("SIGN IN");
            }
        });

        sign_up_btn = findViewById(R.id.sign_up_btn);
        sign_up_btn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                sign_in_btn.setBackgroundResource(R.drawable.left);
                sign_up_btn.setBackgroundResource(R.drawable.right_selected);

                in = findViewById(R.id.in);
                up = findViewById(R.id.up);
                in.setTextColor(getResources().getColor(R.color.colorWhite));
                up.setTextColor(getResources().getColor(R.color.colorPrimary));
                user_name.setVisibility(View.VISIBLE);
                line_name.setVisibility(View.VISIBLE);
                txt_login.setText("SIGN UP");
            }
        });
        log_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login_Activity.this, HomeActivity.class);
                startActivity(intent);
            }
        });



    }
}


