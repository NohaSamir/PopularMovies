package com.example.android.popularmovies.utils;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import androidx.annotation.NonNull;

public final class ViewModelUtil {
    public static final ViewModelUtil INSTANCE;

    @NonNull
    public final static ViewModelProvider.Factory createFor(@NonNull final ViewModel model) {
        return new ViewModelProvider.Factory() {
            @NonNull
            public ViewModel create(@NonNull Class modelClass) {
                if (modelClass.isAssignableFrom(model.getClass())) {
                    return model;
                } else {
                    try {
                        throw (Throwable) (new IllegalArgumentException("unexpected model class " + modelClass));
                    } catch (Throwable throwable) {
                        throwable.printStackTrace();
                    }
                }

                return model;
            }
        };
    }

    private ViewModelUtil() {
    }

    static {
        ViewModelUtil var0 = new ViewModelUtil();
        INSTANCE = var0;
    }
}

