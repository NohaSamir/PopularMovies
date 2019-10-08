package com.example.android.popularmovies.utils;

import android.content.Context;

import com.example.android.popularmovies.rest.ApiClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import okhttp3.mockwebserver.Dispatcher;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;

import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;

/**
 * Created by nsamir on 10/2/2019.
 */
public class WebServerRule {

    private static final String URL = "http://localhost:8080";
    private static final int PORT = 8080;
    private MockWebServer webServer;
    private Context context;

    public WebServerRule() {
        context = getInstrumentation().getTargetContext();
        ApiClient.changeBaseURL(URL);
        webServer = new MockWebServer();
        try {
            webServer.start(PORT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void tearDown() {
        try {
            webServer.shutdown();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public MockResponse getSuccessResponse(String fileName) {
        MockResponse mockResponse = new MockResponse();

        try {
            String source = getStringFromFile(context, "api-response/" + fileName);
            mockResponse.setResponseCode(200).setBody(source);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mockResponse;
       /* try {
            InputStream inputStream = context.getResources().getAssets().open("api-response/" + fileName);
            BufferedSource source = Okio.buffer(Okio.source(inputStream));
            return mockResponse.setResponseCode(200).setBody(source.readString(Charsets.UTF_8));
        } catch (IOException e) {
            e.printStackTrace();
            return mockResponse;
        }*/
    }

    public MockResponse getErrorResponse() {
        MockResponse mockResponse = new MockResponse();
        return mockResponse.setResponseCode(400);
    }


    private Dispatcher getSuccessDispatcher(final String fileName) {
        return new Dispatcher() {
            @Override
            public MockResponse dispatch(RecordedRequest request) {
                return getSuccessResponse(fileName);
            }
        };
    }

    private Dispatcher getErrorDispatcher() {
        return new Dispatcher() {
            @Override
            public MockResponse dispatch(RecordedRequest request) {
                return getErrorResponse();
            }
        };
    }


    /**
     * To customize our dispatcher use setDispatcher method
     * @param dispatcher dispatcher
     */
    public void setDispatcher(Dispatcher dispatcher) {
        webServer.setDispatcher(dispatcher);
    }

    public void error() {
        webServer.setDispatcher(getErrorDispatcher());
    }

    //******************************************************************************

    private static String convertStreamToString(InputStream is) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line).append("\n");
        }
        reader.close();
        return sb.toString();
    }

    private static String getStringFromFile(Context context, String filePath) throws Exception {
        final InputStream stream = context.getResources().getAssets().open(filePath);

        String ret = convertStreamToString(stream);
        //Make sure you close all streams.
        stream.close();
        return ret;
    }
}

