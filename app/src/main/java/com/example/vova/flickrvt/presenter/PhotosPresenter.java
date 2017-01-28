package com.example.vova.flickrvt.presenter;

import com.example.vova.flickrvt.model.Model;
import com.example.vova.flickrvt.model.ModelImpl;
import com.example.vova.flickrvt.model.dto.PhotosStat;
import com.example.vova.flickrvt.view.View;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by vova on 27.01.17.
 */

public class PhotosPresenter implements Presenter {

    private Model mModel = new ModelImpl();
    private View mView;

    public PhotosPresenter(View view) {
        this.mView = view;
    }

    @Override
    public void onCreateView(int page) {

        mModel.getPhotosStat(page).subscribe(new Observer<PhotosStat>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(PhotosStat value) {
                mView.showData(value);
            }

            @Override
            public void onError(Throwable e) {
                mView.showError(e.getLocalizedMessage());
            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    public void onClickItem(int position) {

    }

    @Override
    public void onStop() {

    }
}