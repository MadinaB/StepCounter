package com.madinabektayeva.stepcounter;

import android.content.Context;
import android.hardware.*;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import static android.hardware.SensorManager.SENSOR_DELAY_UI;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    TextView tv_steps;
    SensorManager sensorManager;
    boolean running = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_steps = (TextView) findViewById(R.id.tv_steps);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
    }

    protected void onResume(){
        super.onResume();
        running = true;
        Sensor countSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        if(countSensor!= null){
            sensorManager.registerListener((SensorEventListener) this, countSensor, SensorManager.SENSOR_DELAY_UI);

        }else{
            Toast.makeText(this, "Sensor not found", Toast.LENGTH_SHORT).show();
        }

    }

    protected void onPause(){
        super.onPause();
        sensorManager.unregisterListener((SensorListener) this);
        running = false;
    }

    public void onSensorChanged(SensorEvent event){
        if(running){
            tv_steps.setText(""+String.valueOf(event.values[0]));
        }
    }


    public void onAccuracyChanged(Sensor sensor, int accuracy){
        if(running){

        }
    }
}
