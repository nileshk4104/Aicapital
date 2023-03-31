package com.tech.aicapital.ytb;

import android.app.Activity;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.pierfrancescosoffritti.youtubeplayer.player.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.youtubeplayer.player.YouTubePlayerView;
import com.tech.aicapital.R;
import com.tech.aicapital.datalist.VideoDataList;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class YoutubeRecyclerAdapter extends RecyclerView.Adapter<BaseViewHolder> {
    public static final int VIEW_TYPE_NORMAL = 1;
    private List<VideoDataList> mVideoDataLists;
    DisplayMetrics displayMetrics = new DisplayMetrics();
    public YoutubeRecyclerAdapter(List<VideoDataList> VideoDataLists) {
        mVideoDataLists = VideoDataLists;
    }
    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_y_tube, parent, false));
    }
    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.onBind(position);
    }
    @Override
    public int getItemViewType(int position) {
        return VIEW_TYPE_NORMAL;
    }
    @Override
    public int getItemCount() {
        if (mVideoDataLists != null && mVideoDataLists.size() > 0) {
            return mVideoDataLists.size();
        } else {
            return 1;
        }
    }
    public void setItems(List<VideoDataList> VideoDataLists) {
        mVideoDataLists = VideoDataLists;
        notifyDataSetChanged();
    }
    public class ViewHolder extends BaseViewHolder {
        @BindView(R.id.textViewTitle)
        TextView textWaveTitle;
        @BindView(R.id.btnPlay)
        ImageView playButton;
        @BindView(R.id.imageViewItem)
        ImageView imageViewItems;
        @BindView(R.id.youtube_view)
        YouTubePlayerView youTubePlayerView;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
        protected void clear() {
        }
        public void onBind(int position) {
            super.onBind(position);
            final VideoDataList mVideoDataList = mVideoDataLists.get(position);
            ((Activity) itemView.getContext()).getWindowManager()
                    .getDefaultDisplay()
                    .getMetrics(displayMetrics);
            int width = displayMetrics.widthPixels;
            if (mVideoDataList.getVideoName() != null) {
                textWaveTitle.setText(mVideoDataList.getVideoName());
            }
            if ("https://img.youtube.com/vi/"+mVideoDataList.getYoutubeId()+"/maxresdefault.jpg" != null) {
                Glide.with(itemView.getContext())
                        .load("https://img.youtube.com/vi/"+mVideoDataList.getYoutubeId()+"/maxresdefault.jpg")
                        .apply(new RequestOptions().override(width - 36, 200))
                        .into(imageViewItems);
            }



            imageViewItems.setVisibility(View.VISIBLE);
            playButton.setVisibility(View.VISIBLE);
            youTubePlayerView.setVisibility(View.GONE);
            playButton.setOnClickListener(view -> {
                imageViewItems.setVisibility(View.GONE);
                youTubePlayerView.setVisibility(View.VISIBLE);
                playButton.setVisibility(View.GONE);
                youTubePlayerView.initialize(
                        initializedYouTubePlayer -> initializedYouTubePlayer.addListener(
                                new AbstractYouTubePlayerListener() {
                                    @Override
                                    public void onReady() {
                                        initializedYouTubePlayer.loadVideo(mVideoDataList.getYoutubeId(), 0);
                                    }
                                }), true);
            });
        }
    }
}