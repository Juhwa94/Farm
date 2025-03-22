package com.example.farm;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.Chart;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import java.util.ArrayList;
import java.util.List;

public class myfarmActivity extends AppCompatActivity {

    private Button m_Button;
    private View.OnClickListener m_listener;
    private TextView temText, humText, luxText;
    private View.OnLongClickListener long_listener;
    private Button managebtn;
    public TextView adtv;
    private Button chartbtn;

    private int counter;



    @SuppressLint("HandlerLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myfarm);



        // 예뻐해주기 버튼 클릭시 카운트 올라감
        m_Button = (Button)findViewById(R.id.adorebtn);
        adtv = (TextView)findViewById(R.id.adoretv);
        m_listener = new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                adtv.setText(String.valueOf(++counter));
            }
        };
        m_Button.setOnClickListener(m_listener);
        // 예뻐해주기 버튼 오래 누르면 gif 뜨도록 하기
        long_listener = new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                return false;
            }
        };

        // History 버튼 누르면 activity_chart 로 이동
        chartbtn = (Button)findViewById(R.id.chart_btn);
        chartbtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent chIntent = new Intent(myfarmActivity.this, ChartActivity.class);
                myfarmActivity.this.startActivity(chIntent);
            }
        });




        // 수동 조절하기 버튼 클릭시 actvity_manage 로 이동
        managebtn = (Button)findViewById(R.id.managebtn);
        managebtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent mngIntent = new Intent(myfarmActivity.this,ManageActivity.class);
                myfarmActivity.this.startActivity(mngIntent);
            }
        });

        findViewById(R.id.history_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(myfarmActivity.this, HistoryActivity.class));
            }
        });

        temText = findViewById(R.id.tempText);
        humText = findViewById(R.id.humiditeText);
        luxText = findViewById(R.id.luxText);

        MyApplication.Companion.setSocketListener(new MyApplication.SocketListener() {
            @Override
            public void onConnect() {
                MyApplication.Companion.getWriteThread().start();
            }

            @Override
            public void onSocketError() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(myfarmActivity.this, "라즈베리파이에 연결할 수 없습니다. 확인하고 재실행해주세요.", Toast.LENGTH_LONG).show();
                        finishAndRemoveTask();
                    }
                });
            }

            @Override
            public void onReceiveData() {
                String rowData = MyApplication.Companion.getReadQueue().poll();
                if (rowData == null) return;
                final String[] data = rowData.split(":");
                switch (data[0]) {
                    case "temperature":
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                temText.setText(String.format("온도 : %s도", data[1]));
                            }
                        });
                        MyApplication.Companion.getData().put(data[0], data[1]);
                        break;
                    case "humidity":
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                humText.setText(String.format("습도 : %s%%", data[1]));
                            }
                        });
                        MyApplication.Companion.getData().put(data[0], data[1]);
                        break;
                    case "lux":
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                luxText.setText(String.format("조도 : %s lux", data[1]));
                            }
                        });
                        MyApplication.Companion.getData().put(data[0], data[1]);
                        break;
                    case "history":
                        ArrayList<String> temp = MyApplication.Companion.getHistoryData();
                        for (String item: data[1].split(",")) {
                            if (!temp.contains(item)) temp.add(item);
                        }
                    default:
                        if (data.length == 2) {
                            MyApplication.Companion.getData().put(data[0], data[1]);
                        }
                        break;
                }
            }
        });

        PermissionListener permissionlistener = new PermissionListener() {
            @Override
            public void onPermissionGranted() {
                WifiManager wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
                WifiInfo info = wifiManager.getConnectionInfo ();
                if (info.getSSID().replace("\"", "").equals("rpi3-ap")) {
                    MyApplication.Companion.getSocketThread().start();
                } else {
                    Toast.makeText(myfarmActivity.this, "라즈베리파이에 와이파이로 연결되어 있지 않습니다.\n지정하고 재실행해주세요.", Toast.LENGTH_LONG).show();
                    finish();
                }
            }

            @Override
            public void onPermissionDenied(List<String> deniedPermissions) {
                Toast.makeText(myfarmActivity.this, "권한 거부됨\n" + deniedPermissions.toString(), Toast.LENGTH_LONG).show();
                finish();
            }
        };

        TedPermission.with(this)
                .setPermissionListener(permissionlistener)
                .setRationaleMessage("와이파이 확인을 위한 필수 권한입니다. 수락해주세요.")
                .setPermissions(Manifest.permission.ACCESS_FINE_LOCATION)
                .check();
    }

    @Override
    protected void onDestroy() {
        MyApplication.Companion.getWriteQueue().add("exit");
        super.onDestroy();
    }
}