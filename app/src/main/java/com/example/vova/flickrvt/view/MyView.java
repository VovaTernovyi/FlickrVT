package com.example.vova.flickrvt.view;

import com.example.vova.flickrvt.model.dto.PhotosStat;

/**
 * Created by vova on 28.01.17.
 */

public interface MyView {

    void showData(PhotosStat photosStat);

    void showError(String error);
}
