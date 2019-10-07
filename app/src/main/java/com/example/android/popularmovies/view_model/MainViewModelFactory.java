package com.example.android.popularmovies.view_model;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.annotation.NonNull;

import com.example.android.popularmovies.repository.MovieRepository;


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
