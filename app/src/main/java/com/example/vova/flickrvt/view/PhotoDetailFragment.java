package com.example.vova.flickrvt.view;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.vova.flickrvt.R;
import com.example.vova.flickrvt.common.widgets.FreedomImageView;
import com.example.vova.flickrvt.model.dto.Photo;
import com.squareup.picasso.Picasso;

/**
 * A simple {@link Fragment} subclass.
 */
public class PhotoDetailFragment extends Fragment {

    public static final String KEY_PHOTO_FRAGMENT_PHOTO = "photo_fragment_photo";

    Context mContext;

    Photo mPhoto;
    TextView mTitle;
    TextView mSizes;
    ImageView mImageView;

    public PhotoDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_photo_detail, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        Bundle bundlePhoto = getArguments();

        mPhoto = (Photo) bundlePhoto.get(KEY_PHOTO_FRAGMENT_PHOTO);

        mTitle = (TextView) view.findViewById(R.id.activity_photo_title);
        mSizes = (TextView) view.findViewById(R.id.activity_photo_size);
        mImageView = (FreedomImageView) view.findViewById(R.id.activity_photo_image);

        mTitle.setText(mPhoto.getTitle());
        mSizes.setText(mPhoto.getWidthS() + " x " + mPhoto.getHeightS());
        Picasso.with(mContext).load(mPhoto.getUrlS()).into(mImageView);
    }
}
