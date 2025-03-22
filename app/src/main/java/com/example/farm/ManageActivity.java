package com.example.farm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.HashMap;

public class ManageActivity extends AppCompatActivity {

    private TextView temText, humText, luxText;
    private Boolean isRun = true;
    private SwitchCompat fan, light, manual, water;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage);



        String randtext[] = {"아주 맘에 들어요!","잘 자라고 있어요!","작물이 아주 싱싱하군요!", "아주 멋진 농장이군요!","짝짝짝!!!!"};


        int r = (int)(Math.random()*5);
        TextView randText = (TextView)findViewById(R.id.randText);
        randText.setText(randtext[r]);

        temText = findViewById(R.id.textView1);
        humText = findViewById(R.id.textView2);
        luxText = findViewById(R.id.textView3);
        water = findViewById(R.id.water);
        fan = findViewById(R.id.fan);
        light = findViewById(R.id.light);
        manual = findViewById(R.id.manual);

        water.setTag("pump");
        fan.setTag("fan");
        light.setTag("bulb");
        manual.setTag("auto");

        final CompoundButton.OnCheckedChangeListener listener = new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (!compoundButton.isPressed()) {
                    return;
                }
                String boolString = b ? "True" : "False";
                MyApplication.Companion.getWriteQueue().add(String.format("%s,%s", compoundButton.getTag(), boolString));
                MyApplication.Companion.getData().put((String) compoundButton.getTag(), boolString);
            }
        };

        water.setOnCheckedChangeListener(listener);
        fan.setOnCheckedChangeListener(listener);
        light.setOnCheckedChangeListener(listener);
        manual.setOnCheckedChangeListener(listener);

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                while (isRun) {
                    final HashMap<String, String> hashMap = MyApplication.Companion.getData();

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            for (String key: hashMap.keySet()) {
                                switch (key) {
                                    case "temperature":
                                        temText.setText(String.format("온도\n\n%s도", hashMap.get(key)));
                                        break;
                                    case "humidity":
                                        humText.setText(String.format("습도\n\n%s%%", hashMap.get(key)));
                                        break;
                                    case "lux":
                                        luxText.setText(String.format("조도\n\n%slux", hashMap.get(key)));
                                        break;
                                    case "auto":
                                        manual.setOnCheckedChangeListener(null);
                                        manual.setChecked(hashMap.get(key).trim().equals("True"));
                                        manual.setOnCheckedChangeListener(listener);
                                        break;
                                    case "bulb":
                                        light.setOnCheckedChangeListener(null);
                                        light.setChecked(hashMap.get(key).trim().equals("True"));
                                        light.setOnCheckedChangeListener(listener);
                                        break;
                                    case "fan":
                                        fan.setOnCheckedChangeListener(null);
                                        fan.setChecked(hashMap.get(key).trim().equals("True"));
                                        fan.setOnCheckedChangeListener(listener);
                                        break;
                                    case "pump":
                                        water.setOnCheckedChangeListener(null);
                                        water.setChecked(hashMap.get(key).trim().equals("True"));
                                        water.setOnCheckedChangeListener(listener);
                                        break;
                                }
                            }
                        }
                    });

                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        t.start();
    }


    @Override
    protected void onDestroy() {
        isRun = false;
        super.onDestroy();
    }
}