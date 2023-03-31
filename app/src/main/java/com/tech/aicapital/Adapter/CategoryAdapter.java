package com.tech.aicapital.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

//import com.tech.aicapital.utils.GlideApp;
import com.squareup.picasso.Picasso;
import com.tech.aicapital.R;
import com.tech.aicapital.datalist.CategoryDataList;


import java.util.ArrayList;
import java.util.List;

public class CategoryAdapter extends ArrayAdapter<CategoryDataList> {
    private Context mContext;
    private int layoutResourceId;
    private List<CategoryDataList> mGridData;
    private CategoryAdapter.OnItemClickListener listener;
    String baseurl;

    public CategoryAdapter(Context mContext, int layoutResourceId,String baseurl,
                           List<CategoryDataList> mGridData, CategoryAdapter.OnItemClickListener listener)
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
    public void setGridData(ArrayList<CategoryDataList> mGridData) {
        this.mGridData = mGridData;
        notifyDataSetChanged();
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View row = convertView;
        CategoryAdapter.ViewHolder holder;
        LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
        row = inflater.inflate(layoutResourceId, parent, false);
        holder = new CategoryAdapter.ViewHolder();
        CategoryDataList item = mGridData.get(position);

//        holder.levelButton = (ImageView) row.findViewById(R.id.imgView);
        holder.tvCategoryName = (TextView) row.findViewById(R.id.tvCategoryName);


        holder.tvCategoryName.setText(item.getCategoryName());


//        Picasso.with(mContext).load(baseurl+item.getCategoryImage()).placeholder(R.drawable.defa).
//                into(holder.levelButton);

//        GlideApp.with(mContext)
//                .load(baseurl+item.getCategoryImage())
//                .into(holder.levelButton);




//        holder.levelButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                listener.OnItemClickListener(position);
//            }
//        });
        row.setTag(holder);
        return row;
    }
    public interface OnItemClickListener {
        void OnItemClickListener(int position);
    }
    static class ViewHolder {
//        ImageView levelButton;
        TextView tvCategoryName;
//        RatingBar ratingBar;
//        TextView tvDescription;

    }

}
