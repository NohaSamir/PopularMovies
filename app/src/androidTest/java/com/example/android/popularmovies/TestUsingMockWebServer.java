package com.example.android.popularmovies;


import android.content.Intent;

import androidx.test.espresso.ViewInteraction;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import com.example.android.popularmovies.activity.MainActivity;
import com.example.android.popularmovies.utils.RecyclerViewItemCountAssertion;
import com.example.android.popularmovies.utils.RecyclerViewMatcher;
import com.example.android.popularmovies.utils.WebServerRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import okhttp3.mockwebserver.Dispatcher;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.RecordedRequest;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

@RunWith(AndroidJUnit4.class)
public class TestUsingMockWebServer {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule =
            new ActivityTestRule<>(MainActivity.class, false, false);


    private WebServerRule webServer;

    @Before
    public void init() {
        webServer = new WebServerRule();
    }

    @Test
    public void loadSuccess() {
        Dispatcher dispatcher = new Dispatcher() {
            @Override
            public MockResponse dispatch(RecordedRequest request) throws InterruptedException {

                try {
                    if (request.getPath().contains("/movie/popular")) {

                        return webServer.getSuccessResponse("movies_response");
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }
        };

        webServer.setDispatcher(dispatcher);

        mActivityTestRule.launchActivity(new Intent());

        onView(withId(R.id.movies_recycler_view)).check(matches(isDisplayed()));

        onView(withId(R.id.movies_recycler_view)).check(new RecyclerViewItemCountAssertion(2));

        ViewInteraction viewInteraction = onView(new RecyclerViewMatcher(R.id.movies_recycler_view).atPositionOnView(0, R.id.title));

        viewInteraction.check(matches(withText("Joker")));

    }

    @Test
    public void loadError() {

        webServer.error();

        mActivityTestRule.launchActivity(new Intent());

        onView(withId(R.id.movies_recycler_view)).check(matches(isDisplayed()));

        onView(withId(R.id.movies_recycler_view)).check(new RecyclerViewItemCountAssertion(0));

        //test toast message appear
        onView(withText(R.string.error)).inRoot(withDecorView(not(is(mActivityTestRule.getActivity().getWindow().getDecorView())))).check(matches(isDisplayed()));

    }

    @After
    public void serverShutDown() {
        webServer.tearDown();
    }
}
