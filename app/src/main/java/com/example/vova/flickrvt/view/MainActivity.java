package com.example.vova.flickrvt.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.vova.flickrvt.R;
import com.example.vova.flickrvt.model.api.RequestInterface;
import com.example.vova.flickrvt.model.dto.Photo;
import com.example.vova.flickrvt.model.dto.PhotosStat;
import com.example.vova.flickrvt.view.adapters.DataAdapter;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.ArrayList;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    public static final String ENDPOINT = "https://api.flickr.com/services/";
    public static final String API_KEY = "19167217ae7d5cc1e18cde47839c0a8d";
    public static final String METHOD_GET_RECENT = "flickr.photos.getRecent";
    public static final String PARAM_EXTRAS = "extras";
    public static final String EXTRA_MEDIUM_URL = "url_z";
    public static final String PER_PAGE = "10";
    public static final String FORMAT = "json";
    public static final String NO_JSON_CALLBACK = "1";
    public String page = "1";

    private RecyclerView mRecyclerView;
    private CompositeDisposable mCompositeDisposable;
    private DataAdapter mAdapter;
    private ArrayList<Photo> mPhotoArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mCompositeDisposable = new CompositeDisposable();
        initRecyclerView();
        loadJSON();
    }

    private void initRecyclerView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(layoutManager);
    }

    private void loadJSON() {
        RequestInterface requestInterface = new Retrofit.Builder()
                .baseUrl(ENDPOINT)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(RequestInterface.class);

        mCompositeDisposable.add(requestInterface.register(
                METHOD_GET_RECENT,API_KEY, EXTRA_MEDIUM_URL, PER_PAGE,page,FORMAT,NO_JSON_CALLBACK)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponse,this::handleError));
    }

    private void handleResponse(PhotosStat photosStat) {
        mPhotoArrayList = new ArrayList<>(photosStat.getPhotos().getPhoto());
        mAdapter = new DataAdapter(getApplicationContext(), mPhotoArrayList);
        mRecyclerView.setAdapter(mAdapter);
    }

    private void handleError(Throwable error) {
        Toast.makeText(this, "Error "+error.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mCompositeDisposable.clear();
    }
}
