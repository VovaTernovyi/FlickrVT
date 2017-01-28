package com.example.vova.flickrvt.view;

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

public class MainActivity extends AppCompatActivity implements View {

    private RecyclerView mRecyclerView;
    private DataAdapter mAdapter;

    private Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPresenter = new PhotosPresenter(this);
        initRecyclerView();
        mPresenter.onCreateView(1);
    }

    private void initRecyclerView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void showData(PhotosStat photosStat) {
        ArrayList<Photo> mPhotoArrayList = new ArrayList<>(photosStat.getPhotos().getPhoto());
        mAdapter = new DataAdapter(mPhotoArrayList);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void showError(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }
}