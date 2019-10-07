package com.example.android.popularmovies;


import androidx.lifecycle.MutableLiveData;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import com.example.android.popularmovies.activity.MainActivity;
import com.example.android.popularmovies.model.Movie;
import com.example.android.popularmovies.utils.TestUtil;
import com.example.android.popularmovies.utils.ViewModelUtil;
import com.example.android.popularmovies.view_model.MainViewModel;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.List;

import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.mockito.Mockito.mock;

@RunWith(AndroidJUnit4.class)
public class TestUsingMockViewModel {

    @Rule
    public ActivityTestRule<MainActivity> activityTestRule = new ActivityTestRule<>(MainActivity.class, true, true);

    @Mock
    public MainViewModel mainViewModel;

    private MutableLiveData<List<Movie>> data = new MutableLiveData<>();

    @Before
    public void init() {

        mainViewModel = mock(MainViewModel.class);
        Mockito.when(mainViewModel.getMovies()).thenReturn(data);

        activityTestRule.getActivity().viewModelFactory = ViewModelUtil.createFor(mainViewModel);

        //activityTestRule.getActivity().mainViewModel = mainViewModel;

        //activityTestRule.launchActivity(new Intent());
    }

    @Test
    public void success() {

        List<Movie> movies = TestUtil.getMovies(5);
        data.postValue(movies);

        Espresso.onView(withId(R.id.movies_recycler_view)).check(ViewAssertions.matches(isDisplayed()));

    }


}
