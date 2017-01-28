package com.example.vova.flickrvt.presenter;

/**
 * Created by vova on 27.01.17.
 */

public interface Presenter {

    void onCreateView(int page);

    void onClickItem(int position);

    void onStop();
}