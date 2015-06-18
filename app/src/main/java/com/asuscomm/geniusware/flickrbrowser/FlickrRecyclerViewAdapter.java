package com.asuscomm.geniusware.flickrbrowser;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by markfavis on 6/12/15.
 */
public class FlickrRecyclerViewAdapter extends RecyclerView.Adapter<FlickrImageViewHolder> {
    private List<Photo> mPhotosList;
    private Context mContext;
    private final String LOG_TAG = FlickrRecyclerViewAdapter.class.getSimpleName();

    public FlickrRecyclerViewAdapter(Context context, List<Photo> mPhotosList){
        mContext = context;
        this.mPhotosList = mPhotosList;
    }

    @Override
    public FlickrImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.browse, null);
        FlickrImageViewHolder flickrImageViewHolder = new FlickrImageViewHolder(view);
        return flickrImageViewHolder;
    }

    @Override
    public int getItemCount() {
        return (null != mPhotosList ? mPhotosList.size() : 0);
    }

    public void loadViewData(List<Photo> newPhotos){
        mPhotosList = newPhotos;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(FlickrImageViewHolder flickrImageViewHolder, int i) {
        Photo photoItem = mPhotosList.get(i);
        Log.d(LOG_TAG,"Processing: " + photoItem.getmTitle() + " --> " + Integer.toString(i));
        Picasso.with(mContext).load(photoItem.getmImage())
                .error(R.drawable.placeholder)
                .placeholder(R.drawable.placeholder)
                .into(flickrImageViewHolder.thumbnail);
        flickrImageViewHolder.title.setText(photoItem.getmTitle());
    }

    public Photo getPhoto(int position){
        return (null != mPhotosList ? mPhotosList.get(position) : null);
    }
}
