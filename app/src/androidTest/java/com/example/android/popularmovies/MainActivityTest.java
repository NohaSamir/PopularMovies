package com.example.android.popularmovies;


import android.content.Context;
import android.content.Intent;

import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.rule.ActivityTestRule;

import com.example.android.popularmovies.activity.MainActivity;
import com.example.android.popularmovies.rest.ApiClient;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;

//@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> activityTestRule = new ActivityTestRule<>(MainActivity.class);

    private MockWebServer server;
    private Context context;

    @Before
    public void setUp() throws Exception {

        //IMPORTANT:** point your application to your mockwebserver endpoint e.g.
        ApiClient.setEndpointURL("http://localhost:8080/");

        server = new MockWebServer();
        context = getInstrumentation().getContext();
        server.start(8080);
       // server.url("movie/popular?api_key=");

    }


    @Test
    public void getPopularMovies() throws Exception {

        //server.setDispatcher(new MockServerDispatcher().new RequestDispatcher());

       /* String fileName = "movies_200_ok_response.json";
        server.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody(RestServiceTestHelper.getStringFromFile(getInstrumentation().getContext(), fileName)));
*/
        server.setDispatcher(MockServerDispatcher.getSuccessDispatcher());
        activityTestRule.launchActivity(new Intent());

        onView(ViewMatchers.withId(R.id.movies_recycler_view))
                .perform(RecyclerViewActions.scrollToPosition(1));

        onView(withId(R.id.movies_recycler_view)).check(matches(isDisplayed()));

    }


    @After
    public void tearDown() throws Exception {
        System.out.println("test end\n");
        server.shutdown();
    }


}
