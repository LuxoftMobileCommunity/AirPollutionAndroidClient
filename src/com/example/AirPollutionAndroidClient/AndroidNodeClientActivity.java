package com.example.AirPollutionAndroidClient;

import android.app.Activity;
import android.os.Bundle;

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
                   }

                   public void onError(Exception e)
                   {
                       System.out.println(e);
                   }
               });
    }
}
