package com.example.vova.flickrvt.model;

import com.example.vova.flickrvt.model.dto.PhotosStat;

import io.reactivex.Observable;

/**
 * Created by vova on 28.01.17.
 */

public interface Model {

    Observable<PhotosStat> getPhotosStat(int page);
}
