package com.tech.aicapital.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.media.ThumbnailUtils;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.recyclerview.widget.RecyclerView;


import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubeThumbnailLoader;
import com.google.android.youtube.player.YouTubeThumbnailView;
import com.squareup.picasso.Picasso;
import com.tech.aicapital.R;
import com.tech.aicapital.datalist.VideoDataList;
import com.tech.aicapital.mvps.Constant;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class VideoListAdapter extends
        RecyclerView.Adapter<VideoListAdapter.MyViewHolder> {
    Context context;
    private List<VideoDataList> allVideos;
    private HashMap<String, Bitmap> bitmapCache;
    private VideoListAdapter.OnItemClickListener listener;
    private VideoListAdapter.OnDoubleClickListener listener2;
    int lastPosition = -1;
    boolean alert;
    int mainDuration,mainAmounnt=1;
    double intrestrate;
    String baseurl;
    public VideoListAdapter(Context context, String baseurl, List<VideoDataList> resourceList,
                            VideoListAdapter.OnItemClickListener listener,
                            VideoListAdapter.OnDoubleClickListener listener2) {
        this.context = context;
        this.baseurl = baseurl;
        this.allVideos = resourceList;
        this.listener = listener;
        this.listener2 = listener2;
//        setUpBitmaps();

    }
    @Override
    public VideoListAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_application, parent, false);
        return new VideoListAdapter.MyViewHolder(itemView);
    }
    @Override
    public void onBindViewHolder(final VideoListAdapter.MyViewHolder holder, final int position) {
        final VideoDataList bean = allVideos.get(position);

        holder.tvAppName.setText(bean.getVideoName());
        holder.tvDescription.setText(bean.getVideoDesc());
        if(bean.getAmount().equalsIgnoreCase("0")){
            holder.tvAmount.setText("Install and earn ");
        }
        else{
            holder.tvAmount.setText("Install and earn "+context.getResources().getString(R.string.Rs)+bean.getAmount());
        }



        Picasso.with(context).load(baseurl+bean.getVideoImage()).placeholder(R.drawable.defa).into(holder.ivAppLogo);

    }

    private void setUpBitmaps() {
        try{
            bitmapCache = new HashMap<String, Bitmap>(allVideos.size());
            for(VideoDataList video : allVideos){
                bitmapCache.put(video.getVideoImage(),
                        ThumbnailUtils.createVideoThumbnail(video.getVideoImage(),
                                MediaStore.Video.Thumbnails.MICRO_KIND));
            }
        }
        catch (Exception e){

        }

    }

    @Override
    public int getItemCount() {
        return allVideos.size();
    }
    public interface OnItemClickListener {
        void onListItemClick(int position);
        void onUpdateQty(int position, boolean isSubscribe);
    }
    public interface OnDoubleClickListener {
        void onDetailsView(int position);
    }
    public static String stripNonDigits(
            final CharSequence input /* inspired by seh's comment */){
        final StringBuilder sb = new StringBuilder(input.length() /* also inspired by seh's comment */);
        for(int i = 0; i < input.length(); i++){
            final char c = input.charAt(i);
            if(c > 47 && c < 58){
                sb.append(c);
            }
        }
        return sb.toString();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder implements
            View.OnClickListener, View.OnLongClickListener {

        @BindView(R.id.ivAppLogo)
        ImageView ivAppLogo;
        @BindView(R.id.tvAppName)
        TextView tvAppName;
        @BindView(R.id.tvDescription)
        TextView tvDescription;
        @BindView(R.id.tvAmount)
        TextView tvAmount;
        @BindView(R.id.tvDownload)
        TextView tvDownload;






        public MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            view.setOnClickListener(this);
        }
        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            listener.onListItemClick(position);
        }
        @Override
        public boolean onLongClick(View v) {
            int position = getAdapterPosition();
            listener2.onDetailsView(position);
            return false;
        }
    }


}
