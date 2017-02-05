package com.example.vova.flickrvt.view;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.vova.flickrvt.R;
import com.example.vova.flickrvt.model.dto.Photo;

public class MainActivity extends AppCompatActivity {

    FragmentManager mFragmentManager;
    FragmentTransaction mFragmentTransaction;

    Fragment mPhotosListFragment;
    Fragment mPhotoDetailFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPhotosListFragment = new PhotosListFragment();

        mFragmentManager = getSupportFragmentManager();
        mFragmentTransaction = mFragmentManager.beginTransaction();
        mFragmentTransaction.add(R.id.fragment_container, mPhotosListFragment, "FragmentPhotosList");
        mFragmentTransaction.commit();
    }

    void openPhotoDetailFragment(Photo photo) {
        mPhotoDetailFragment = new PhotoDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(PhotoDetailFragment.KEY_PHOTO_FRAGMENT_PHOTO, photo);
        mPhotoDetailFragment.setArguments(bundle);

        mFragmentTransaction = mFragmentManager.beginTransaction();
        mFragmentTransaction.replace(R.id.fragment_container, mPhotoDetailFragment, "FragmentPhotoDetail")
                .addToBackStack(null);
        mFragmentTransaction.commit();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}