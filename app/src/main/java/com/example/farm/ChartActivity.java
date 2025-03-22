package com.example.farm;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;
import java.util.List;


public class ChartActivity extends AppCompatActivity {

    private LineChart lineChart;
    private LineChart lineChart2;
    private LineChart lineChart3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);


        lineChart = (LineChart)findViewById(R.id.lineChart);

        List<Entry> entries = new ArrayList<>();
        entries.add(new Entry(10, 20));
        entries.add(new Entry(15, 22));
        entries.add(new Entry(20, 21));
        entries.add(new Entry(25, 23));
        entries.add(new Entry(30, 24));
        entries.add(new Entry(35, 26));
        entries.add(new Entry(40, 28));
        entries.add(new Entry(45, 31));
        entries.add(new Entry(50, 32));
        entries.add(new Entry(55, 29));
        entries.add(new Entry(60, 27));
        entries.add(new Entry(65, 26));
        entries.add(new Entry(70, 27));
        entries.add(new Entry(75, 25));
        entries.add(new Entry(80, 21));
        entries.add(new Entry(85, 19));
        entries.add(new Entry(90, 17));
        entries.add(new Entry(95, 19));
        entries.add(new Entry(100, 20));

        LineDataSet lineDataSet = new LineDataSet(entries, "온도");
        lineDataSet.setLineWidth(2);
        lineDataSet.setCircleRadius(6);
        lineDataSet.setCircleColor(Color.parseColor("#FFA1B4DC"));
        lineDataSet.setCircleColor(Color.BLUE);
        lineDataSet.setColor(Color.parseColor("#FFA1B4DC"));
        lineDataSet.setDrawCircleHole(true);
        lineDataSet.setDrawCircles(true);
        lineDataSet.setDrawHorizontalHighlightIndicator(false);
        lineDataSet.setDrawHighlightIndicators(false);
        lineDataSet.setDrawValues(false);

        LineData lineData = new LineData(lineDataSet);
        lineChart.setData(lineData);

        XAxis xAxis = lineChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setTextColor(Color.BLACK);
        xAxis.enableGridDashedLine(8, 24, 0);

        YAxis yLAxis = lineChart.getAxisLeft();
        yLAxis.setTextColor(Color.BLACK);

        YAxis yRAxis = lineChart.getAxisRight();
        yRAxis.setDrawLabels(false);
        yRAxis.setDrawAxisLine(false);
        yRAxis.setDrawGridLines(false);

        Description description = new Description();
        description.setText("");

        lineChart.setDoubleTapToZoomEnabled(false);
        lineChart.setDrawGridBackground(false);
        lineChart.setDescription(description);
        lineChart.invalidate();


        lineChart2 = (LineChart)findViewById(R.id.lineChart2);

        List<Entry> entries2 = new ArrayList<>();
        entries2.add(new Entry(10, 70));
        entries2.add(new Entry(15, 72));
        entries2.add(new Entry(20, 71));
        entries2.add(new Entry(25, 83));
        entries2.add(new Entry(30, 64));
        entries2.add(new Entry(35, 66));
        entries2.add(new Entry(40, 68));
        entries2.add(new Entry(45, 71));
        entries2.add(new Entry(50, 72));
        entries2.add(new Entry(55, 79));
        entries2.add(new Entry(60, 77));
        entries2.add(new Entry(65, 66));
        entries2.add(new Entry(70, 67));
        entries2.add(new Entry(75, 65));
        entries2.add(new Entry(80, 71));
        entries2.add(new Entry(85, 79));
        entries2.add(new Entry(90, 77));
        entries2.add(new Entry(95, 79));
        entries2.add(new Entry(100, 70));

        LineDataSet lineDataSet2 = new LineDataSet(entries2, "습도");
        lineDataSet2.setLineWidth(2);
        lineDataSet2.setCircleRadius(6);
        lineDataSet2.setCircleColor(Color.parseColor("#FF7373"));
        lineDataSet2.setCircleColor(Color.RED);
        lineDataSet2.setColor(Color.parseColor("#FF7373"));
        lineDataSet2.setDrawCircleHole(true);
        lineDataSet2.setDrawCircles(true);
        lineDataSet2.setDrawHorizontalHighlightIndicator(false);
        lineDataSet2.setDrawHighlightIndicators(false);
        lineDataSet2.setDrawValues(false);

        LineData lineData2 = new LineData(lineDataSet2);
        lineChart2.setData(lineData2);

        XAxis xAxis2 = lineChart2.getXAxis();
        xAxis2.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis2.setTextColor(Color.BLACK);
        xAxis2.enableGridDashedLine(8, 24, 0);

        YAxis yLAxis2 = lineChart2.getAxisLeft();
        yLAxis2.setTextColor(Color.BLACK);

        YAxis yRAxis2 = lineChart2.getAxisRight();
        yRAxis2.setDrawLabels(false);
        yRAxis2.setDrawAxisLine(false);
        yRAxis2.setDrawGridLines(false);

        Description description2 = new Description();
        description2.setText("");

        lineChart2.setDoubleTapToZoomEnabled(false);
        lineChart2.setDrawGridBackground(false);
        lineChart2.setDescription(description2);
        lineChart2.invalidate();


        lineChart3 = (LineChart)findViewById(R.id.lineChart3);

        List<Entry> entries3 = new ArrayList<>();
        entries3.add(new Entry(10, 453));
        entries3.add(new Entry(15, 460));
        entries3.add(new Entry(20, 473));
        entries3.add(new Entry(25, 469));
        entries3.add(new Entry(30, 483));
        entries3.add(new Entry(35, 491));
        entries3.add(new Entry(40, 499));
        entries3.add(new Entry(45, 512));
        entries3.add(new Entry(50, 524));
        entries3.add(new Entry(55, 534));
        entries3.add(new Entry(60, 522));
        entries3.add(new Entry(65, 517));
        entries3.add(new Entry(70, 508));
        entries3.add(new Entry(75, 497));
        entries3.add(new Entry(80, 493));
        entries3.add(new Entry(85, 472));
        entries3.add(new Entry(90, 477));
        entries3.add(new Entry(95, 453));
        entries3.add(new Entry(100, 446));

        LineDataSet lineDataSet3 = new LineDataSet(entries3, "조도");
        lineDataSet3.setLineWidth(2);
        lineDataSet3.setCircleRadius(6);
        lineDataSet3.setCircleColor(Color.parseColor("#FFD700"));
        lineDataSet3.setCircleColor(Color.YELLOW);
        lineDataSet3.setColor(Color.parseColor("#FFD700"));
        lineDataSet3.setDrawCircleHole(true);
        lineDataSet3.setDrawCircles(true);
        lineDataSet3.setDrawHorizontalHighlightIndicator(false);
        lineDataSet3.setDrawHighlightIndicators(false);
        lineDataSet3.setDrawValues(false);

        LineData lineData3 = new LineData(lineDataSet3);
        lineChart3.setData(lineData3);

        XAxis xAxis3 = lineChart3.getXAxis();
        xAxis3.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis3.setTextColor(Color.BLACK);
        xAxis3.enableGridDashedLine(8, 24, 0);

        YAxis yLAxis3 = lineChart3.getAxisLeft();
        yLAxis3.setTextColor(Color.BLACK);

        YAxis yRAxis3 = lineChart3.getAxisRight();
        yRAxis3.setDrawLabels(false);
        yRAxis3.setDrawAxisLine(false);
        yRAxis3.setDrawGridLines(false);

        Description description3 = new Description();
        description3.setText("");

        lineChart3.setDoubleTapToZoomEnabled(false);
        lineChart3.setDrawGridBackground(false);
        lineChart3.setDescription(description3);
        lineChart3.invalidate();









    }
}