package com.example.android.popularmovies.activity;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import androidx.work.Constraints;
import androidx.work.NetworkType;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;

import com.example.android.popularmovies.Injection;
import com.example.android.popularmovies.R;
import com.example.android.popularmovies.adapter.MoviesAdapter;
import com.example.android.popularmovies.databinding.ActivityMainBinding;
import com.example.android.popularmovies.view_model.MainViewModel;
import com.example.android.popularmovies.view_model.MainViewModelFactory;
import com.example.android.popularmovies.workers.MoviesWorker;

import java.util.concurrent.TimeUnit;

//ToDo Introduction: What is WorkManager?
// -
// WorkManager is basically a task scheduler, It makes it easy to specify the asynchronous task and when they should run
// For example, you might point your app to download new resources from the network from time to time
// and now the downloading is a task and you can set up this task to run at an appropriate time depending on the availability of the WIFI network
// -
// we will use the work manager to download 3 pages of movies and update them every day
// This is just an idea to use the work manager on a simple application,
// but we use it based on business requirement if it is required to Periodically syncing application data with a server
// *******
// How it works?
// 1- Worker : It specifies what task to perform
// 2- WorkRequest represents an individual task that is to be performed
//    WorkRequest can be of to type
// -
//         OneTimeWorkRequest– That means you requesting for non-repetitive work.
//         PeriodicWorkRequest– This class is used for creating a request for repetitive work
// 3- WorkManager class in enqueues and manages all the work request. We pass work request object to this WorkManager to enqueue the task.
//
public class MainActivity extends AppCompatActivity {

    private Context mContext;
    private MoviesAdapter moviesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mContext = this;
        moviesAdapter = new MoviesAdapter();

        final ActivityMainBinding activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        activityMainBinding.setAdapter(moviesAdapter);

        final MainViewModel mainViewModel = ViewModelProviders.of(this, new MainViewModelFactory(Injection.provideMovieRepository(this)))
                .get(MainViewModel.class);

        mainViewModel.getMovies().observe(this, movies -> {
            if (movies != null) {
                moviesAdapter.submitList(movies);
            } else {
                Toast.makeText(mContext, R.string.error, Toast.LENGTH_LONG).show();
            }
        });


        //ToDo 3. Define our constrains
        Constraints constraints = new Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED)
                //.setRequiresCharging(true)
                // .setRequiresBatteryNotLow(true)
                //.setRequiresStorageNotLow(true)
                .build();

        //If you need to send data to worker
        /*Data source = new Data.Builder()
                .putString("workType", "PeriodicTime")
                .build();*/

        //ToDo 4. Create WorkRequest to be repeat every 15 minute to test it
        // the minimum time interval where a task can be repeated is set to 15 minutes
        PeriodicWorkRequest periodicWorkRequest = new PeriodicWorkRequest.Builder(MoviesWorker.class, 15, TimeUnit.MINUTES)
                .setConstraints(constraints)
                //.setInputData(source)
                .build();

        /*PeriodicWorkRequest periodicWorkRequest = new PeriodicWorkRequest.Builder(MoviesWorker.class, 24, TimeUnit.HOURS)
                .setConstraints(constraints).build();*/

        //ToDo 5: Enqueue the request with WorkManager
        WorkManager.getInstance().enqueue(periodicWorkRequest);

    }

}