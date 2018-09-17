package com.example.jor.metealldetektor;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.TextureView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity implements SensorEventListener{

    double betrag;
    private SensorManager mSensorManager;


    public MainActivity() {

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Magnetdetektor");

        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);


    }


    @Override
    protected void onPause(){
        super.onPause();
        mSensorManager.unregisterListener(this);

    }

    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(this, mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD),0);

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onSensorChanged(SensorEvent event) {
        float[] mag = event.values;
        betrag = Math.sqrt(mag[0] * mag[0] + mag[1] * mag[1] + mag[2] * mag[2]);


        ProgressBar progressBar = (ProgressBar)findViewById(R.id.progressBar);
        progressBar.setMin(16);
        progressBar.setMax(5000);

        progressBar.setProgress((int) betrag);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        //not in use
    }
}
