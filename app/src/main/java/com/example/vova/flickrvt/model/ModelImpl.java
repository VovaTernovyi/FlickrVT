package com.example.vova.flickrvt.model;

import com.example.vova.flickrvt.model.api.ApiInterface;
import com.example.vova.flickrvt.model.api.ApiModule;
import com.example.vova.flickrvt.model.dto.PhotosStat;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by vova on 28.01.17.
 */

public class ModelImpl implements Model {

    public static final String API_KEY = "19167217ae7d5cc1e18cde47839c0a8d";
    public static final String METHOD_GET_RECENT = "flickr.photos.getRecent";
    public static final String PARAM_EXTRAS = "extras";
    public static final String EXTRA_MEDIUM_URL = "url_z";
    public static final String PER_PAGE = "10";
    public static final String FORMAT = "json";
    public static final String NO_JSON_CALLBACK = "1";

    ApiInterface apiInterface = ApiModule.getApiInterface();

    @Override
    public Observable<PhotosStat> getPhotosStat(int page) {
        return apiInterface.register( METHOD_GET_RECENT, API_KEY, EXTRA_MEDIUM_URL, PER_PAGE, Integer.toString(page), FORMAT, NO_JSON_CALLBACK)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}