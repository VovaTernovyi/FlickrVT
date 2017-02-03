package com.example.vova.flickrvt.view.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.vova.flickrvt.R;
import com.example.vova.flickrvt.common.widgets.FreedomImageView;
import com.example.vova.flickrvt.model.dto.Photo;
import com.example.vova.flickrvt.view.OnItemClickListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by vova on 26.01.17.
 */

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {
    private ArrayList<Photo> mPhotoList;
    private Context mContext;
    private OnItemClickListener mListener;

    public DataAdapter(OnItemClickListener listener) {
        mListener = listener;
        mPhotoList = new ArrayList<>();
    }

    public DataAdapter(ArrayList<Photo> items, OnItemClickListener listener) {
        for (Photo photo : items) {
            this.mPhotoList.add(photo);
        }
        mListener = listener;
    }

    public void addData(ArrayList<Photo> items) {
        for (Photo photo : items) {
            this.mPhotoList.add(photo);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_row, parent, false);
        mContext = parent.getContext();
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(mPhotoList.get(position), mListener);
    }

    @Override
    public int getItemCount() {
        return mPhotoList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView mTitle;
        private FreedomImageView mImageView;

        public ViewHolder(View view) {
            super(view);
            mTitle = (TextView)view.findViewById(R.id.item_photo_title);
            this.mImageView = (FreedomImageView) itemView.findViewById(R.id.item_photo_image);
        }

        public void bind(final Photo item, final OnItemClickListener listener) {
            mTitle.setText(item.getTitle());

            Picasso.with(mContext)
                    .load(item.getUrlS())
                    .into(mImageView);
            itemView.setOnClickListener( (View v) -> listener.onItemClick(item));
        }
    }
}