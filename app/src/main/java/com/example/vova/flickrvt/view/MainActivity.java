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

    public static final int PAGE_SIZE = 15;

    private Presenter mPresenter;
    private boolean isLastPage = false;
    private int mCurrentPage = 1;
    private boolean isLoading = false;

    private RecyclerView mRecyclerView;
    LinearLayoutManager mLayoutManager;

    DataAdapter mDataAdapter;

    RecyclerView.OnScrollListener mRecyclerViewOnScrollListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPresenter = new PhotosPresenter(this);
        initRecyclerView();
        mPresenter.onLoadData(mCurrentPage);
    }

    private void initRecyclerView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(mLayoutManager);

        mRecyclerView.setAdapter(mDataAdapter = new DataAdapter(new OnItemClickListener() {
            @Override
            public void onItemClick(Photo photo) {
                Toast.makeText(getApplicationContext(), R.string.item_clicked, Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getApplicationContext(), PhotoActivity.class);
                intent.putExtra(PhotoActivity.KEY_PHOTO_ACTIVITY_PHOTO, photo);
                startActivity(intent);
            }
        }));

        mRecyclerViewOnScrollListener = new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int visibleItemCount = mLayoutManager.getChildCount();
                int totalItemCount = mLayoutManager.getItemCount();
                int firstVisibleItemPosition = mLayoutManager.findFirstVisibleItemPosition();

                if (!isLoading && !isLastPage) {
                    if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount
                            && firstVisibleItemPosition >= 0
                            && totalItemCount >= PAGE_SIZE) {
                        ++mCurrentPage;
                        mPresenter.onLoadData(mCurrentPage);
                    }
                }
            }
        };

        mRecyclerView.addOnScrollListener(mRecyclerViewOnScrollListener);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void showData(PhotosStat photosStat) {
        ArrayList<Photo> mPhotoArrayList = new ArrayList<>(photosStat.getPhotos().getPhoto());

        mDataAdapter.addData(mPhotoArrayList);
        mDataAdapter.notifyDataSetChanged();
    }

    @Override
    public void showError(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }

}