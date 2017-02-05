package com.example.vova.flickrvt.common.views;

import android.support.v4.app.Fragment;

import com.example.vova.flickrvt.view.MainActivity;

/**
 * Created by vova on 05.02.17.
 */

public abstract class BasicFragment extends Fragment {

    public MainActivity getMainActivity() {
        return (MainActivity)getActivity();
    }
}
