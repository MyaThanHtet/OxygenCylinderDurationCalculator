package com.lym.o2cylinderdurationcalculator;

import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.collection.ArrayMap;

import com.lym.o2cylinderdurationcalculator.model.Tank;
import com.xw.repo.BubbleSeekBar;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private final String[] keys = {
            "E Tank",
            "D Tank",
            "H Tank",
            "M Tank"
    };
    private final Tank[] objects = {
            new Tank("E Tank", 0.28f),
            new Tank("D Tank", 0.16f),
            new Tank("H Tank", 3.14f),
            new Tank("M Tank", 1.56f),
    };
    private final ArrayMap<String, Float> map = new ArrayMap<>();
    Float Tank_Size_Value;
    int FinalVale;
    EditText tank_pressure;
    TextView tvResult;
    int flowRate = 0;
    private BubbleSeekBar bubbleSeekBar;
    private Spinner mySpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvResult = findViewById(R.id.display_result);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            getWindow().getAttributes().layoutInDisplayCutoutMode = WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES;
        }

        tank_pressure = findViewById(R.id.input_tank_pressure);
        mySpinner = (Spinner) findViewById(R.id.tank_size_spinner);


        tank_pressure.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                updateResult(bubbleSeekBar.getProgress());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        for (Tank object : objects) {
            map.put(object.getTank_type(), object.getTank_value());
        }


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, keys);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mySpinner.setAdapter(adapter);
        mySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String key = (String) parent.getItemAtPosition(position);
                Tank_Size_Value = map.get(key);
                updateResult(bubbleSeekBar.getProgress());

            }

            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        bubbleSeekBar = findViewById(R.id.bubbleSeekBar);
        flowRate = 0;
        bubbleSeekBar.setOnProgressChangedListener(new BubbleSeekBar.OnProgressChangedListenerAdapter() {
            @Override
            public void onProgressChanged(BubbleSeekBar bubbleSeekBar, int progress, float progressFloat, boolean fromUser) {
                flowRate = progress;
                updateResult(flowRate);
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public int updateResult(int progress) {
        if (!tank_pressure.getText().toString().equals("")) {
            FinalVale = (int) ((Integer.parseInt(tank_pressure.getText().toString()) - 200) * Tank_Size_Value) / progress;
            tvResult.setText(FinalVale + " min");
            FinalVale = 0;
        } else {

        }

        return FinalVale;
    }
}