package com.example.accelerometersensor;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

public class Accelerometer extends AppCompatActivity implements SensorEventListener{

    SensorManager sensorManager;
    Sensor mAccelerometer;
    LineChart mChart;
    Thread thread = null;
    boolean plotData = true;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
        mAccelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        setContentView(R.layout.activity_main);
        if (mAccelerometer != null){
            sensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        }

        mChart = findViewById(R.id.lineChart);
        mChart.getDescription().setEnabled(true);
        mChart.getDescription().setText("Accel Data");

        mChart.setTouchEnabled(true);
        mChart.setDragEnabled(false);
        mChart.setScaleEnabled(true);
        mChart.setPinchZoom(false);

        LineData data = new LineData();
        data.setValueTextColor(Color.BLACK);
        mChart.setData(data);

        Legend l = mChart.getLegend();
        l.setTextColor(Color.WHITE);

        XAxis xl = mChart.getXAxis();
        xl.setTextColor(Color.WHITE);
        xl.setDrawGridLines(true);
        xl.setAvoidFirstLastClipping(true);
        xl.setEnabled(true);

        YAxis leftAxis = mChart.getAxisLeft();
        leftAxis.setTextColor(Color.WHITE);
        leftAxis.setDrawGridLines(false);
        leftAxis.setAxisMaximum(30f);
        leftAxis.setAxisMinimum(0f);
        leftAxis.setDrawGridLines(true);

        YAxis rightAxis = mChart.getAxisRight();
        rightAxis.setEnabled(false);
        mChart.getXAxis().setDrawGridLines(false);
        mChart.setDrawBorders(false);

        startPlot();
    }

    private void startPlot() {
        if(thread != null){
            thread.interrupt();
        }

        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    plotData = true;
                    try{
                        Thread.sleep(10);
                    }catch (InterruptedException e){

                    }
                }
            }
        });

        thread.start();
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if(plotData){
            addEntry(sensorEvent);
            plotData = false;
        }
    }

    private void addEntry(SensorEvent event) {
        LineData data = mChart.getData();

        if(data !=null){
            ILineDataSet set = data.getDataSetByIndex(0);
            ILineDataSet set1 = data.getDataSetByIndex(1);
            ILineDataSet set2 = data.getDataSetByIndex(2);

            if(set == null){
                set = createSet();
                set1 = createSet1();
                set2 = createSet2();
                data.addDataSet(set);
                data.addDataSet(set1);
                data.addDataSet(set2);
            }

            data.addEntry(new Entry(set.getEntryCount(),event.values[0] + 5),0);
            data.addEntry(new Entry(set1.getEntryCount(),event.values[1] + 5),1);
            data.addEntry(new Entry(set2.getEntryCount(),event.values[2] + 5),2);
            data.notifyDataChanged();
            mChart.notifyDataSetChanged();
            mChart.setMaxVisibleValueCount(150);
            mChart.moveViewToX(data.getEntryCount());
            mChart.setVisibleXRange(0f,20f);
        }
    }

    private LineDataSet createSet(){
        LineDataSet set = new LineDataSet(null,"X Axis");
        set.setAxisDependency(YAxis.AxisDependency.LEFT);
        set.setLineWidth(3f);
        set.setColor(Color.MAGENTA);
        set.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        set.setCubicIntensity(0.2f);
        return  set;
    }

    private LineDataSet createSet1(){
        LineDataSet set = new LineDataSet(null,"Y Axis");
        set.setAxisDependency(YAxis.AxisDependency.LEFT);
        set.setLineWidth(3f);
        set.setColor(Color.WHITE);
        set.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        set.setCubicIntensity(0.2f);
        return  set;
    }

    private LineDataSet createSet2(){
        LineDataSet set = new LineDataSet(null,"Z Axis");
        set.setAxisDependency(YAxis.AxisDependency.LEFT);
        set.setLineWidth(3f);
        set.setColor(Color.RED);
        set.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        set.setCubicIntensity(0.2f);
        return  set;
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    @Override
    public void onPause() {
        super.onPause();

        if (thread != null){
            thread.interrupt();
        }
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        sensorManager.registerListener(this,mAccelerometer,SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onDestroy() {
        sensorManager.unregisterListener(this);
        thread.interrupt();
        super.onDestroy();
    }
}