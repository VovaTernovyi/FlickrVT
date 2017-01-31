package com.example.vova.flickrvt.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.vova.flickrvt.R;
import com.example.vova.flickrvt.model.dto.Photo;
import com.example.vova.flickrvt.model.dto.PhotosStat;
import com.example.vova.flickrvt.presenter.PhotosPresenter;
import com.example.vova.flickrvt.presenter.Presenter;
import com.example.vova.flickrvt.view.adapters.DataAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements MyView {

    private Presenter mPresenter;

    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPresenter = new PhotosPresenter(this);
        initRecyclerView();
        mPresenter.onLoadData(1);
    }

    private void initRecyclerView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(layoutManager);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void showData(PhotosStat photosStat) {
        ArrayList<Photo> mPhotoArrayList = new ArrayList<>(photosStat.getPhotos().getPhoto());
        mRecyclerView.setAdapter(new DataAdapter(mPhotoArrayList, new OnItemClickListener() {
            @Override
            public void onItemClick(Photo photo) {
                Toast.makeText(getApplicationContext(), R.string.item_clicked, Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getApplicationContext(), PhotoActivity.class);
                intent.putExtra(PhotoActivity.KEY_PHOTO_ACTIVITY_PHOTO, photo);
                startActivity(intent);
            }
        }));

    }

    @Override
    public void showError(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }
}