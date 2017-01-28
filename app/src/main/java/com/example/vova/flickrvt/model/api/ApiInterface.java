package com.example.vova.flickrvt.model.api;

import com.example.vova.flickrvt.model.dto.PhotosStat;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by vova on 26.01.17.
 */

public interface ApiInterface {

    @GET("rest/")
    Observable<PhotosStat> register(@Query("method") String method,
                                    @Query("api_key") String api_key,
                                    @Query("extras") String extras,
                                    @Query("per_page") String per_page,
                                    @Query("page") String page,
                                    @Query("format") String format,
                                    @Query("nojsoncallback") String nojsoncallback
                                    );
}