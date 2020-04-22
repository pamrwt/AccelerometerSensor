package com.example.accelerometersensor;

import android.graphics.Color;
import android.hardware.SensorEvent;
import android.util.Log;

import com.github.mikephil.charting.charts.BarLineChartBase;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;
import java.util.List;

import static com.example.accelerometersensor.MainActivity.lineChart;


public  class CreateChartClass {
    List<String> var = new ArrayList<String>();
    ArrayList<String> xAXES = new ArrayList<>();
    ArrayList<Entry> yAXESsin = new ArrayList<>();
    ArrayList<Entry> yAXEScos = new ArrayList<>();
    ArrayList<Entry> var1Array = new ArrayList<>();
    ArrayList<Entry> var2Array = new ArrayList<>();
    ArrayList<Entry> var3Array = new ArrayList<>();

    public  void create(SensorEvent event){
      arrayXY(event);
        double x = 0 ;
        int numDataPoints = 5;
//        for(int i=0;i<1;i++){
////            float sinFunction = Float.parseFloat(String.valueOf(Math.sin(x)));
////            float cosFunction = Float.parseFloat(String.valueOf(Math.cos(x)));
//            x = x + 0.1;
////            yAXESsin.add(new Entry(sinFunction,i));
////            yAXEScos.add(new Entry(cosFunction,i));
//            xAXES.add(i, String.valueOf(x));
//        }
//        String[] xaxes = new String[xAXES.size()];
//        for(int i=0; i<xAXES.size();i++){
//            xaxes[i] = xAXES.get(i).toString();
//        }

        ArrayList<ILineDataSet> lineDataSets = new ArrayList<>();
        LineDataSet lineDataSet1 = new LineDataSet(yAXEScos,"actual production");
        lineDataSet1.setDrawCircles(false);
        lineDataSet1.setColor(Color.BLUE);
        lineDataSet1.setLineWidth(4.5f);
        lineDataSet1.setValueTextSize(12f);
        lineDataSet1.setValueTextColor(Color.BLUE);

        LineDataSet lineDataSet2 = new LineDataSet(yAXESsin,"cumulative production");
        lineDataSet2.setDrawCircles(false);
        lineDataSet2.setColor(Color.RED);
        lineDataSet2.setLineWidth(3.5f);
        lineDataSet2.setValueTextSize(12f);
        lineDataSet2.setValueTextColor(Color.RED);

      LineDataSet lineDataSet3 = new LineDataSet(var1Array,"var1");
        lineDataSet3.setDrawCircles(false);
        lineDataSet3.setColor(Color.CYAN);
        lineDataSet3.setLineWidth(2.5f);
        lineDataSet3.setValueTextSize(12f);
        lineDataSet3.setValueTextColor(Color.CYAN);

       LineDataSet lineDataSet4 = new LineDataSet(var2Array,"var2");
        lineDataSet4.setDrawCircles(false);
        lineDataSet4.setColor(Color.BLACK);
        lineDataSet4.setLineWidth(2f);
        lineDataSet4.setValueTextSize(12f);
        lineDataSet4.setValueTextColor(Color.BLACK);

        LineDataSet lineDataSet5 = new LineDataSet(var3Array,"var3");
        lineDataSet5.setDrawCircles(false);
        lineDataSet5.setColor(Color.GREEN);
        lineDataSet5.setValueTextColor(Color.GREEN);
        lineDataSet5.setValueTextSize(12f);

        lineDataSets.add(lineDataSet1);
        lineDataSets.add(lineDataSet2);
        lineDataSets.add(lineDataSet3);
        lineDataSets.add(lineDataSet4);
        lineDataSets.add(lineDataSet5);

        BarLineChartBase mChart;
        lineChart.setData(new LineData(lineDataSets));

        lineChart.notifyDataSetChanged();
        lineChart.invalidate();
        lineChart.setVisibleXRangeMaximum(65f);
    }


    void arrayXY(SensorEvent event) {

//        for (int i = 0; i < max + 2; i++) {
            yAXESsin.add(new Entry(40, 0));
            yAXEScos.add(new Entry(40, 0));
            var1Array.add(new Entry(0, event.values[0]+2));
            var2Array.add(new Entry(0, event.values[1]+2));
            var3Array.add(new Entry(0, event.values[2]+2));
//        }


//        int count = 0;
////            int api_month = Integer.parseInt(materialList.get(k).getManufacturingDate());
////            if ((api_month+"").equals(month)){
////                j++;
////            }else {
//        for (int i = materialList.size() - 1; i >= 0; i--) {
//            int api_month = Integer.parseInt(materialList.get(i).getManufacturingDate().substring(3, 5));
//
//            if (k == api_month) {
//                count = count + Integer.valueOf(materialList.get(i).getActualProduction());
//
//                yAXEScos.set(Integer.parseInt(materialList.get(i).getManufacturingDate().substring(0, 2)),
//                        new Entry(Integer.parseInt(materialList.get(i).getManufacturingDate().substring(0, 2)), count));
//
//                Log.e("api month ", api_month + ",  " + materialList.get(i).getManufacturingDate().substring(3, 5) + " count " + count);
////                    month = materialList.get(j).getManufacturingDate().substring(3, 5);
//            } else {
//                // break;
//            }
//        }
//        cumulative_max_value.add(count + "");
////            }
////        }
//
//        Log.e("max", max + "");
//
//
//        for (int i = 0; i < PlantList_DateWiseStatusFragment.materialList.size(); i++) {
//            date = Integer.parseInt(PlantList_DateWiseStatusFragment.materialList.get(i).getManufacturingDate().substring(0, 2));
//            month = Integer.parseInt(PlantList_DateWiseStatusFragment.materialList.get(i).getManufacturingDate().substring(0, 2));
//            production = Integer.parseInt(PlantList_DateWiseStatusFragment.materialList.get(i).getActualProduction());
//            yAXESsin.add(date, new Entry(month, production));
//
////            yAXEScos.add(new Entry(i + 1, i));
//
//        }
//
//        for (int i = 0; i < PlantList_DateWiseStatusFragment.materialList.size(); i++) {
//            try {
//                var1 = Integer.parseInt(PlantList_DateWiseStatusFragment.materialList.get(i).getmVar1());
//            } catch (Exception e) {
//            }
//            date = Integer.parseInt(PlantList_DateWiseStatusFragment.materialList.get(i).getManufacturingDate().substring(0, 2));
//            month = Integer.parseInt(PlantList_DateWiseStatusFragment.materialList.get(i).getManufacturingDate().substring(0, 2));
//            var1Array.add(date, new Entry(month, var1));
//
////            yAXEScos.add(new Entry(i + 1, i));
//
//        }
//



}

}
