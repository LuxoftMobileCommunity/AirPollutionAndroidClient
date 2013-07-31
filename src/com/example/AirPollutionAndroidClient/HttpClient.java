package com.example.AirPollutionAndroidClient;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.koushikdutta.async.http.AsyncHttpClient;
import com.koushikdutta.async.http.AsyncHttpResponse;

import java.io.IOException;

/**
 * Created by sema on 26.07.2013.
 */
public class HttpClient
{
    private AsyncHttpClient client = AsyncHttpClient.getDefaultInstance();
    private ObjectMapper mapper = new ObjectMapper();
    private String nodeServerURL;

    public HttpClient(String nodeServerURL)
    {
        this.nodeServerURL = nodeServerURL;
    }

    public void fetchDataForStation(int stationNumber, final HttpClientListener listener )
    {

        // TODO: Jackson works ok but lib is to heavy, better idea is to parse JSON manually in release version
        client.getString(nodeServerURL + "/?node=" + stationNumber, new AsyncHttpClient.StringCallback()
        {
            public void onCompleted(Exception e, AsyncHttpResponse asyncHttpResponse, String s)
            {
                if( e != null ) {

                    listener.onError( e );
                    return;
                }

                try
                {
                    listener.onComplete( mapper.readValue(s, Data.class));

                } catch (IOException e1)
                {
                    listener.onError( e );
                }
            }

        });
    };

    public static interface HttpClientListener {

        public void onComplete(Data data);
        public void onError(Exception e);



    }


}
