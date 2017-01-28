package com.example.vova.flickrvt.view.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.vova.flickrvt.R;
import com.example.vova.flickrvt.model.dto.Photo;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by vova on 26.01.17.
 */

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {
    private ArrayList<Photo> mPhotoList;
    private Context mContext;

    public DataAdapter(ArrayList<Photo> photoList) {
        mPhotoList = photoList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_row, parent, false);
        mContext = parent.getContext();
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mTitle.setText(mPhotoList.get(position).getTitle());

        Picasso.with(mContext)
                .load(mPhotoList.get(position).getUrlZ())
                .into(holder.mImageView);
    }

    @Override
    public int getItemCount() {
        return mPhotoList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView mTitle;
        private ImageView mImageView;

        public ViewHolder(View view) {
            super(view);
            mTitle = (TextView)view.findViewById(R.id.item_photo_title);
            this.mImageView = (ImageView) itemView.findViewById(R.id.item_photo_image);
        }
    }
}