package com.tech.aicapital.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.tech.aicapital.R;
import com.tech.aicapital.datalist.TreeDataList;

import java.util.ArrayList;
import java.util.List;

public class TreeDataAdapter extends ArrayAdapter<TreeDataList> {
    private Context mContext;
    private int layoutResourceId;
    private List<TreeDataList> mGridData;
    private TreeDataAdapter.OnItemClickListener listener;
    String baseurl;

    public TreeDataAdapter(Context mContext, int layoutResourceId, String baseurl,
                           List<TreeDataList> mGridData, TreeDataAdapter.OnItemClickListener listener)
    {
        super(mContext, layoutResourceId, mGridData);
        this.layoutResourceId = layoutResourceId;
        this.mContext = mContext;
        this.mGridData = mGridData;
        this.listener=listener;
        this.baseurl=baseurl;
    }
    /**
     * Updates grid data and refresh grid items.
     *
     * @param mGridData
     */
    public void setGridData(ArrayList<TreeDataList> mGridData) {
        this.mGridData = mGridData;
        notifyDataSetChanged();
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View row = convertView;
        TreeDataAdapter.ViewHolder holder;
        LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
        row = inflater.inflate(layoutResourceId, parent, false);
        holder = new TreeDataAdapter.ViewHolder();
        TreeDataList item = mGridData.get(position);

        holder.levelButton = (ImageView) row.findViewById(R.id.imgView);
        holder.tvCategoryName = (TextView) row.findViewById(R.id.tvCategoryName);


        holder.tvCategoryName.setText(item.getName());


//        Picasso.with(mContext).load(baseurl+item.getImage()).placeholder(R.mipmap.ic_launcher).into(holder.levelButton);

//        holder.levelButton.setImageResource(item.getProductImage());


        holder.levelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.OnItemClickListener(position);
            }
        });
        row.setTag(holder);
        return row;
    }
    public interface OnItemClickListener {
        void OnItemClickListener(int position);
    }
    static class ViewHolder {
        ImageView levelButton;
        TextView tvCategoryName;
//        RatingBar ratingBar;
//        TextView tvDescription;

    }

}
