package com.example.AirPollutionAndroidClient;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by sema on 26.07.2013.
 */
public class Data
{
    public static class DataEntry
    {

        private String medium;
        private String unit;

        public String getMedium()
        {
            return medium;
        }

        public void setMedium(String medium)
        {
            this.medium = medium;
        }

        public String getUnit()
        {
            return unit;
        }

        public void setUnit(String unit)
        {
            this.unit = unit;
        }

        private ArrayList<DataPoint> data;

        public ArrayList<DataPoint> getData()
        {
            return data;
        }

        public void setData(ArrayList<DataPoint> data)
        {
            this.data = data;
        }
    }

    public static class DataPoint
    {
        private double value;

        public Date getDate()
        {
            return date;
        }

        public void setDate(Date date)
        {
            this.date = date;
        }

        public double getValue()
        {
            return value;
        }

        public void setValue(double value)
        {
            this.value = value;
        }

        private Date date;
    }

    public ArrayList<DataEntry> getData()
    {
        return data;
    }

    public void setData(ArrayList<DataEntry> data)
    {
        this.data = data;
    }

    private ArrayList<DataEntry> data;


}
