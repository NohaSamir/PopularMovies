package com.example.android.popularmovies;

import androidx.test.platform.app.InstrumentationRegistry;

import okhttp3.mockwebserver.Dispatcher;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.RecordedRequest;

/**
 * Created by nsamir on 8/28/2019.
 */
public class MockServerDispatcher {

    /**
     * Return ok response from mock server
     */
    class RequestDispatcher extends Dispatcher {
        @Override
        public MockResponse dispatch(RecordedRequest request) {

            try {

                String x = request.getPath();
                if (request.getPath().contains("movie/popular")) {
                    return new MockResponse().setResponseCode(200)
                            .setBody(RestServiceTestHelper.getStringFromFile(InstrumentationRegistry.getInstrumentation().getContext(), "movies_200_ok_response.json"));
                }
                return new MockResponse().setResponseCode(404);
            } catch (Exception e) {
                e.printStackTrace();
            }

            return new MockResponse().setResponseCode(404);
        }
    }

    /**
     * creates a mock web server dispatcher with prerecorded requests and responses
     **/
    public static Dispatcher getSuccessDispatcher() {
        final Dispatcher dispatcher = new Dispatcher() {
            @Override
            public MockResponse dispatch(RecordedRequest request) throws InterruptedException {
                if (request.getPath().contains("/movie/popular")) {

                    String fileName = "movies_200_ok_response.json";
                    try {
                        return new MockResponse().setResponseCode(200)
                                .setBody(RestServiceTestHelper.getStringFromFile(InstrumentationRegistry.getInstrumentation().getContext(), fileName));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                throw new IllegalStateException("no mock set up for " + request.getPath());
            }
        };
        return dispatcher;
    }

    /**
     * Return error response from mock server
     */
    class ErrorDispatcher extends Dispatcher {

        @Override
        public MockResponse dispatch(RecordedRequest request) {

            return new MockResponse().setResponseCode(400);

        }
    }

}
