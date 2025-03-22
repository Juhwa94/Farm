/*
package com.example.farm;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.security.PrivateKey;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class gettestActivity extends AppCompatActivity {

    private ServiceApi service;
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gettest);

        service = RetrofitClient.getClient().create(ServiceApi.class);
        tv = (TextView)findViewById(R.id.testview);

        Call<UserList> call = service.getUserInfo("qqqq");

        call.enqueue(new Callback<UserList>(){
            @Override
            public void onResponse(Call<UserList> call, Response<UserList> response) {
              UserList userList = response.body();
              String id = userList.id;
              String pw = userList.password;
              String nickname = userList.nickname;

              tv.setText(id+pw+nickname);

            }
            @Override
            public void onFailure(Call<UserList> call, Throwable t){
                call.cancel();
            }
        });

    }


}
*/
