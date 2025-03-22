/*
package com.example.farm;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class activity_list extends AppCompatActivity {
    private ListView listView;
    private ListAdapter adapter;
    private List<Plant> plantList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        TextView userListTextView = (TextView)findViewById(R.id.listView);

        //ManagementActivity는 MainActivity에서 호출되므로 호출시 넘겨준 데이터를 뿌려주는 역할을 한다
        Intent intent = getIntent();
        //intent.putExtra("userList", result); 에서 userList에 저장했으므로 아래와 같이 쓰게됨
        userListTextView.setText(intent.getStringExtra("userList"));


        Intent intent = getIntent();

        listView = (ListView) findViewById(R.id.listView);
        plantList = new ArrayList<Plant>();

        // 어댑터 초기화 부분 userList와 어댑터를 연결해준다.
        adapter = new ListAdapter(getApplicationContext(), plantList);
        listView.setAdapter(adapter);


        try {
            //intent로 값을 가져옵니다. 이때 JSONObject 타입으로 가져온다.
            JSONObject jsonObject = new JSONObject(intent.getStringExtra("plantList"));

            //List. php 웹페이지에서 response라는 변수명으로 JSON배열을 만들었음..
            JSONArray jsonArray = jsonObject.getJSONArray("response");
            int count = 0;

            String plantName, plantDate;

            while (count < jsonArray.length()) {
                //count는 배열의 인덱스를 의미
                JSONObject object = jsonArray.getJSONObject(count);
            }
            plantName = object.getString("plantName");
            plantDate = object.getstring("plantDate");

            Plant plant = new Plant(plantName, plantDate);
            plantList.add(plant);
            count++;


        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}


*/
