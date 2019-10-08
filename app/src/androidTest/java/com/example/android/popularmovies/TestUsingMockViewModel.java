package com.example.android.popularmovies;


import android.content.Context;
import android.content.Intent;

import androidx.lifecycle.MutableLiveData;
import androidx.test.InstrumentationRegistry;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.intercepting.SingleActivityFactory;

import com.example.android.popularmovies.activity.MainActivity;
import com.example.android.popularmovies.model.Movie;
import com.example.android.popularmovies.utils.RecyclerViewItemCountAssertion;
import com.example.android.popularmovies.utils.RecyclerViewMatcher;
import com.example.android.popularmovies.utils.TestUtil;
import com.example.android.popularmovies.view_model.MainViewModel;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.List;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.mockito.Mockito.mock;

@RunWith(AndroidJUnit4.class)
public class TestUsingMockViewModel {

    @Rule
    public ActivityTestRule<MainActivity> activityTestRule = new ActivityTestRule<>(MainActivity.class, false, true);

    @Mock
    public MainViewModel mainViewModel;

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    private MutableLiveData<List<Movie>> data = new MutableLiveData<>();

    @Before
    public void init() {

        activityTestRule.getActivity().mainViewModel = mainViewModel;

        Mockito.when(mainViewModel.getMovies()).thenReturn(data);

        //activityTestRule.launchActivity(new Intent());

    }

    @Test
    public void success() {

        List<Movie> movies = TestUtil.getMovies(5);

        data.postValue(movies);

        onView(withId(R.id.movies_recycler_view)).check(matches(isDisplayed()));

        onView(withId(R.id.movies_recycler_view)).check(new RecyclerViewItemCountAssertion(5));

        ViewInteraction viewInteraction = onView(new RecyclerViewMatcher(R.id.movies_recycler_view).atPositionOnView(0, R.id.title));

        viewInteraction.check(matches(withText(movies.get(0).getTitle())));

    }




}
