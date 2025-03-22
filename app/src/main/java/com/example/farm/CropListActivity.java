package com.example.farm;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class CropListActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter adapter;
    private Button enterbtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crop_list);

        recyclerView=findViewById(R.id.recycle); // 화면에 보일 xml 리사이클러뷰 아이디. 난 recyclerView를 아이디로 줌 < 이거 어디있어?ㅋㅋ
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        String[] name = {"참외나무", "가지하나", "James"};
        String[] date = {"2020-02-02","2020-06-29","2020-10-26"};

        adapter = new UserAdapter(name, date);

        recyclerView.setAdapter(adapter);

        //입장하기 누르면 마이팜 입성
        enterbtn = (Button)findViewById(R.id.enterFarm);
        enterbtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent farmIntent = new Intent(CropListActivity.this, myfarmActivity.class);
                CropListActivity.this.startActivity(farmIntent);
            }
        });


    }



}