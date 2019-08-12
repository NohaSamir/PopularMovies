package com.example.android.popularmovies.view_model;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.example.android.popularmovies.repository.MovieRepository;

//ToDo 5: Edit the ViewModelFactory like MainViewModel constructor to take the repository as a parameter
public class MainViewModelFactory implements ViewModelProvider.Factory {

    private MovieRepository repository;

    public MainViewModelFactory(MovieRepository repository) {
        this.repository = repository;
    }


    @NonNull
    @Override
    @SuppressWarnings("unchecked")
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new MainViewModel(repository);
    }
}
