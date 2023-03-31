package com.tech.aicapital.followups.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.tech.aicapital.R;
import com.tech.aicapital.datalist.CategoryDataList;

import java.util.List;

public class CategoryNewAdapter extends RecyclerView.Adapter<CategoryNewAdapter.MyViewHolder> {
    private List<CategoryDataList> mGridData;
    private OnItemClickListener listener;
    String baseurl;
    private Context mContext;

    public interface OnItemClickListener {
        void onCategoryClickListener(int position);
    }

    public CategoryNewAdapter(Context mContext, String baseurl, List<CategoryDataList> mGridData, OnItemClickListener listener) {
        this.mContext = mContext;
        this.baseurl = baseurl;
        this.mGridData = mGridData;
        this.listener = listener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_category_new, parent, false);
        return new MyViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        CategoryDataList item = mGridData.get(position);
        holder.tvCategoryName.setText(item.getCategoryName());

        Picasso.with(mContext).load(baseurl + item.getCategoryImage()).placeholder(R.drawable.defa).
                into(holder.levelButton);


        holder.levelButton.setOnClickListener(view -> listener.onCategoryClickListener(position));
    }

    @Override
    public int getItemCount() {
        return mGridData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView levelButton;
        public TextView tvCategoryName;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            levelButton = (ImageView) itemView.findViewById(R.id.imgView);
            tvCategoryName = (TextView) itemView.findViewById(R.id.tvCategoryName);
        }
    }
}
