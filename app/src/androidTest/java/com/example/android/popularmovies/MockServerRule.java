package com.example.android.popularmovies;

import android.util.Log;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import okhttp3.mockwebserver.MockWebServer;

/**
 * Created by nsamir on 8/27/2019.
 */
public class MockServerRule implements TestRule {

    private MockWebServer mServer;

    public static final int MOCK_WEBSERVER_PORT = 8080;


    public Statement apply(final Statement base, Description description) {
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                startServer();
                try {
                    base.evaluate();
                } finally {
                    stopServer();
                }
            }
        };
    }

    /**
     * Returns the started web server instance
     *
     * @return mock server
     */
    public MockWebServer server() {
        return mServer;
    }

    public void startServer() throws IOException, NoSuchAlgorithmException {
        mServer = new MockWebServer();
        try {
            mServer.start(MOCK_WEBSERVER_PORT);
        } catch (IOException e) {
            throw new IllegalStateException("Exception", e);
        }
    }

    public void stopServer() {
        try {
            mServer.shutdown();
        } catch (IOException e) {
            Log.e("Exception", "mock server shutdown error");
        }
    }
}
