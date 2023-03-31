package com.tech.aicapital.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.tech.aicapital.R;
import com.tech.aicapital.datalist.ArticleDataList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ArticleAdapter extends
        RecyclerView.Adapter<ArticleAdapter.MyViewHolder> {
    Context context;
    private List<ArticleDataList> mDataList;
    private ArticleAdapter.OnItemClickListener listener;
    private ArticleAdapter.OnDoubleClickListener listener2;
    int lastPosition = -1;
    String baseurl;
    int mainDuration,mainAmounnt=1;
    boolean isImage;
    public ArticleAdapter(Context context, String baseurl, List<ArticleDataList> resourceList, boolean isImage,
                          ArticleAdapter.OnItemClickListener listener,
                          ArticleAdapter.OnDoubleClickListener listener2)
    {
        this.context = context;
        this.mDataList = resourceList;
        this.listener = listener;
        this.baseurl = baseurl;this.isImage = isImage;
        this.listener2 = listener2;
    }
    @Override
    public ArticleAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_hori_article, parent, false);
        return new ArticleAdapter.MyViewHolder(itemView);
    }
    @Override
    public void onBindViewHolder(final ArticleAdapter.MyViewHolder holder, final int position)
    {
        final ArticleDataList menuItemListData = mDataList.get(position);

        if(isImage)
        {
            Picasso.with(context).load(baseurl+menuItemListData.getImage()).placeholder(R.drawable.defa).into(holder.ivImageAddres);
        }
        else{
            Picasso.with(context).load(baseurl+menuItemListData.getImage()).placeholder(R.drawable.defa).into(holder.ivImageAddres);
        }
        holder.tvTitle.setText(menuItemListData.getTitle());
        holder.tvFullAddress.setText(menuItemListData.getDescription());
    }
    @Override
    public int getItemCount() {
        return mDataList.size();
    }
    public interface OnItemClickListener {
        void onListItemClick(int position);
        void onUpdateQty(int position, String qty, double intrestrate, int selvalue, int mainDuration, boolean alert);
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
    public class MyViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener, View.OnLongClickListener {
        @BindView(R.id.tvTitle)
        TextView tvTitle;
        @BindView(R.id.tvFullAddress)
        TextView tvFullAddress;
        
        @BindView(R.id.ivImageAddress)
        ImageView ivImageAddres;




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
