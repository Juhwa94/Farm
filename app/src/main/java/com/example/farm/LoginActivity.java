package com.example.farm;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends Activity {

    private EditText mNameView;
    private EditText mPwdView;
    private Button mJoinButton;
    private Button mLoginButton;
    private Button registerBtn;
    private ServiceApi service;
    private ProgressBar mProgressView;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);
        mNameView = (EditText) findViewById(R.id.idText);
        mPwdView = (EditText) findViewById(R.id.pwText);
        mLoginButton = (Button)findViewById(R.id.loginbtn);
        mProgressView = (ProgressBar) findViewById(R.id.login_progress);

        service = RetrofitClient.getClient().create(ServiceApi.class);

        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
                Intent enterIntetnt = new Intent(LoginActivity.this,CropListActivity.class);
                LoginActivity.this.startActivity(enterIntetnt);
            }
        });



        //로딩 intent
        Intent loadingIntent = new Intent(this,LoadingActivity.class);
        startActivity(loadingIntent);



     /*   //로그인 버튼 클릭시 myfarm.activity로
        loginbtn = (Button)findViewById(R.id.loginbtn);
        loginbtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent loginIntent = new Intent(LoginActivity.this,myfarmActivity.class);
                LoginActivity.this.startActivity(loginIntent);
            }
        });*/

        //회원가입 버튼
        TextView registerButton = (TextView)findViewById(R.id.registerButton);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registerIntent = new Intent(LoginActivity.this, RegisterActivity.class);
                LoginActivity.this.startActivity(registerIntent);

            }
        });


    }

    private void attemptLogin() {
        mNameView.setError(null);
        mPwdView.setError(null);

        String email = mNameView.getText().toString();
        String password = mPwdView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        /*// 패스워드의 유효성 검사
        if (password.isEmpty()) {
            mNameView.setError("비밀번호를 입력해주세요.");
            focusView = mn;
            cancel = true;
        } else if (!isPasswordValid(password)) {
            mPasswordView.setError("6자 이상의 비밀번호를 입력해주세요.");
            focusView = mPasswordView;
            cancel = true;
        }

        // 이메일의 유효성 검사
        if (email.isEmpty()) {
            mEmailView.setError("이메일을 입력해주세요.");
            focusView = mEmailView;
            cancel = true;
        } else if (!isEmailValid(email)) {
            mEmailView.setError("@를 포함한 유효한 이메일을 입력해주세요.");
            focusView = mEmailView;
            cancel = true;
        }*/

        if (cancel) {
            focusView.requestFocus();
        } else {
            startLogin(new LoginData(email, password));
            showProgress(true);
        }
    }

    private void startLogin(LoginData data) {
        service.userLogin(data).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                LoginResponse result = response.body();
                Toast.makeText(LoginActivity.this, result.getMessage(), Toast.LENGTH_SHORT).show();
                showProgress(false);
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "austin님 환영합니다.", Toast.LENGTH_SHORT).show();
                Log.e("로그인 에러 발생", t.getMessage());
                showProgress(false);
            }
        });
    }


    private void showProgress(boolean show) {
        mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
    }

}

