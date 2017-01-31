package com.example.vova.flickrvt.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.vova.flickrvt.R;
import com.example.vova.flickrvt.common.widgets.FreedomImageView;
import com.example.vova.flickrvt.model.dto.Photo;
import com.squareup.picasso.Picasso;

public class PhotoActivity extends AppCompatActivity {

    public static final String KEY_PHOTO_ACTIVITY_PHOTO = "photo_activity_photo";

    Photo mPhoto;
    TextView mTitle;
    TextView mSizes;
    ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);

        Bundle bundle = getIntent().getExtras();
        mPhoto = (Photo) bundle.get(KEY_PHOTO_ACTIVITY_PHOTO);

        mTitle = (TextView) findViewById(R.id.activity_photo_title);
        mSizes = (TextView) findViewById(R.id.activity_photo_size);
        mImageView = (FreedomImageView) findViewById(R.id.activity_photo_image);

        mTitle.setText(mPhoto.getTitle());
        mSizes.setText(mPhoto.getWidthS() + " x " + mPhoto.getHeightS());
        Picasso.with(getApplicationContext()).load(mPhoto.getUrlS()).into(mImageView);
    }
}
