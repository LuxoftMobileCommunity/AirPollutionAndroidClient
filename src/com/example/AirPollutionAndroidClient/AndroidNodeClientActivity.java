package com.example.AirPollutionAndroidClient;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.widget.FrameLayout;
import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.model.TimeSeries;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import java.util.Iterator;

public class AndroidNodeClientActivity extends Activity
{

    private static final String dataServerURL =  "http://192.168.1.121:1337";

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

               HttpClient httpClient = new HttpClient( dataServerURL );

               httpClient.fetchDataForStation(5, new HttpClient.HttpClientListener()
               {
                   public void onComplete(Data data)
                   {
                       System.out.println(data);
                       generateChart(data);
                   }

                   public void onError(Exception e)
                   {
                       System.out.println(e);
                   }
               });
    }

    public void generateChart(Data data)
        {

            XYMultipleSeriesRenderer renderer = new XYMultipleSeriesRenderer();
            renderer.setApplyBackgroundColor(true);
            renderer.setBackgroundColor(Color.BLACK);
            renderer.setXLabelsColor(Color.YELLOW);
            renderer.setShowGrid(true);
            renderer.setYLabelsAlign(Paint.Align.LEFT);
            renderer.setChartTitle("STATION DATA");
            renderer.setXTitle("Time");
            renderer.setYTitle("Pollution");
            renderer.setShowLegend(false);
            renderer.setZoomButtonsVisible(false);


            int colors[] = { Color.WHITE, Color.YELLOW, Color.BLUE, Color.GREEN, Color.RED, Color.MAGENTA, Color.LTGRAY };



            XYMultipleSeriesDataset mDataSet = new XYMultipleSeriesDataset();

            Iterator<Data.DataEntry> dataEntryIterator = data.getData().iterator();
            Data.DataEntry dataEntry;
            TimeSeries series;
            XYSeriesRenderer seriesRenderer;
            while (dataEntryIterator.hasNext())
            {

                dataEntry = dataEntryIterator.next();
                series = new TimeSeries(dataEntry.getMedium());
                seriesRenderer = new XYSeriesRenderer();
                int index = data.getData().indexOf( dataEntry );
                index = ( index > ( data.getData().size() - 1 ) ) ? 0 : index;
                seriesRenderer.setColor( colors[index] );

                Iterator<Data.DataPoint> dataPointIterator = dataEntry.getData().iterator();
                Data.DataPoint dataPoint;
                while (dataPointIterator.hasNext())
                {

                    dataPoint = dataPointIterator.next();
                    series.add(dataPoint.getDate(), dataPoint.getValue());

                }

                mDataSet.addSeries(series);
                renderer.addSeriesRenderer( seriesRenderer );

            }

            GraphicalView mChart = ChartFactory.getTimeChartView(this, mDataSet, renderer, "HH");
            FrameLayout chart = new FrameLayout(this);
            chart.addView(mChart);
            addContentView(chart, new FrameLayout.LayoutParams(FrameLayout.LayoutParams.FILL_PARENT, FrameLayout.LayoutParams.FILL_PARENT));


        }
}
