package com.example.vova.flickrvt.presenter;

import android.util.Log;

import com.example.vova.flickrvt.model.Model;
import com.example.vova.flickrvt.model.ModelImpl;
import com.example.vova.flickrvt.model.dto.PhotosStat;
import com.example.vova.flickrvt.view.MyView;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by vova on 27.01.17.
 */

public class PhotosPresenter implements Presenter {

    private Model mModel = new ModelImpl();
    private MyView mMyView;

    public PhotosPresenter(MyView view) {
        this.mMyView = view;
    }

    @Override
    public void onLoadData(int page) {

        mModel.getPhotosStat(page).subscribe(new Observer<PhotosStat>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(PhotosStat value) {
                mMyView.showData(value);
                Log.i("PhotosPresenter", "Load page: " + page);
            }

            @Override
            public void onError(Throwable e) {
                mMyView.showError(e.getLocalizedMessage());
            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    public void onStop() {

    }
}