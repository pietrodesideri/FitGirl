package com.wit.android.fitgirl;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login_Activity extends Activity {

    LinearLayout log_in, sign_in_btn, sign_up_btn, login;
    RelativeLayout coordinator;
    EditText user_name, user_email, user_password;
    TextView skip, line_name, txt_login, terms, in, up;
    private ProgressBar progressBar;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_);

        log_in = findViewById(R.id.login);
        progressBar = findViewById(R.id.progressBar);

        sign_in_btn = findViewById(R.id.sign_in_btn);
        skip = findViewById(R.id.skip_textview);
        user_name=findViewById(R.id.user_name);
        user_email=findViewById(R.id.user_email);
        line_name=findViewById(R.id.line_name);
        user_password=findViewById(R.id.user_password);
        txt_login=findViewById(R.id.txt_login);
        auth = FirebaseAuth.getInstance();

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
        log_in.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(Login_Activity.this, HomeActivity.class);
//                startActivity(intent);

                if (txt_login.getText().toString() == "SIGN UP"){

                String email = user_email.getText().toString().trim();
                String password = user_password.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (password.length() < 6) {
                    Toast.makeText(getApplicationContext(), "Password too short, enter minimum 6 characters!", Toast.LENGTH_SHORT).show();
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);
                //create user
                auth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(Login_Activity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                Toast.makeText(Login_Activity.this, "createUserWithEmail:onComplete:" + task.isSuccessful(), Toast.LENGTH_SHORT).show();
                                progressBar.setVisibility(View.GONE);
                                // If sign in fails, display a message to the user. If sign in succeeds
                                // the auth state listener will be notified and logic to handle the
                                // signed in user can be handled in the listener.
                                if (!task.isSuccessful()) {
                                    Toast.makeText(Login_Activity.this, "Authentication failed." + task.getException(),
                                            Toast.LENGTH_SHORT).show();
                                } else {
                                    startActivity(new Intent(Login_Activity.this, HomeActivity.class));
                                    finish();
                                }
                            }
                        });

            }
            else
                {
                    String email = user_email.getText().toString();
                    final String password = user_password.getText().toString();

                    if (TextUtils.isEmpty(email)) {
                        Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    if (TextUtils.isEmpty(password)) {
                        Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    progressBar.setVisibility(View.VISIBLE);

                    //authenticate user
                    auth.signInWithEmailAndPassword(email, password)
                            .addOnCompleteListener(Login_Activity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    // If sign in fails, display a message to the user. If sign in succeeds
                                    // the auth state listener will be notified and logic to handle the
                                    // signed in user can be handled in the listener.
                                    progressBar.setVisibility(View.GONE);
                                    if (!task.isSuccessful()) {
                                        // there was an error
                                        if (password.length() < 6) {
                                            user_email.setError(getString(R.string.minimum_password));
                                        } else {
                                            Toast.makeText(Login_Activity.this, getString(R.string.auth_failed), Toast.LENGTH_LONG).show();
                                        }
                                    } else {
                                        Intent intent = new Intent(Login_Activity.this, HomeActivity.class);
                                        startActivity(intent);
                                        finish();
                                    }
                                }
                            });
                }
                }




            });





    }

    @Override
    protected void onResume() {
        super.onResume();
        progressBar.setVisibility(View.GONE);
    }
}


