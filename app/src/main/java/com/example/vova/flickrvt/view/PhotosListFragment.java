package com.example.vova.flickrvt.view;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.vova.flickrvt.R;
import com.example.vova.flickrvt.common.views.BasicFragment;
import com.example.vova.flickrvt.model.dto.Photo;
import com.example.vova.flickrvt.model.dto.PhotosStat;
import com.example.vova.flickrvt.presenter.PhotosPresenter;
import com.example.vova.flickrvt.presenter.Presenter;
import com.example.vova.flickrvt.view.adapters.DataAdapter;

import java.util.ArrayList;

public class PhotosListFragment extends BasicFragment implements MyView{

    public static final int PAGE_SIZE = 15;

    private Presenter mPresenter;
    private boolean isLastPage = false;
    private int mCurrentPage = 1;
    private boolean isLoading = false;

    private RecyclerView mRecyclerView;
    LinearLayoutManager mLayoutManager;

    DataAdapter mDataAdapter;
    Context mContext;

    RecyclerView.OnScrollListener mRecyclerViewOnScrollListener;;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext.getApplicationContext();
        mPresenter = new PhotosPresenter(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_photos_list, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(mContext);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mRecyclerView.setAdapter(mDataAdapter = new DataAdapter(new OnItemClickListener() {
            @Override
            public void onItemClick(Photo photo) {

                getMainActivity().openPhotoDetailFragment(photo);
                Toast.makeText(mContext, R.string.item_clicked, Toast.LENGTH_LONG).show();
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

        mPresenter.onLoadData(mCurrentPage);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void showData(PhotosStat photosStat) {
        ArrayList<Photo> mPhotoArrayList = new ArrayList<>(photosStat.getPhotos().getPhoto());

        mDataAdapter.addData(mPhotoArrayList);
        mDataAdapter.notifyDataSetChanged();
    }

    @Override
    public void showError(String error) {
        Toast.makeText(mContext, error, Toast.LENGTH_SHORT).show();
    }
}
