package com.tech.aicapital.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.tech.aicapital.R;
import com.tech.aicapital.datalist.EarningDataList;
import com.tech.aicapital.datalist.EarningDataList;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EarningAdapter extends
        RecyclerView.Adapter<EarningAdapter.MyViewHolder> {
    Context context;
    private List<EarningDataList> mDataList;
    private EarningAdapter.OnItemClickListener listener;
    private EarningAdapter.OnDoubleClickListener listener2;
    int lastPosition = -1;
    String baseurl;
    int mainDuration,mainAmounnt=1;
    boolean isImage;
    public EarningAdapter(Context context, String baseurl, List<EarningDataList> resourceList,boolean isImage,
                       EarningAdapter.OnItemClickListener listener,
                       EarningAdapter.OnDoubleClickListener listener2)
    {
        this.context = context;
        this.mDataList = resourceList;
        this.listener = listener;
        this.baseurl = baseurl;this.isImage = isImage;
        this.listener2 = listener2;
    }
    @Override
    public EarningAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_earning, parent, false);
        return new EarningAdapter.MyViewHolder(itemView);
    }
    @Override
    public void onBindViewHolder(final EarningAdapter.MyViewHolder holder, final int position)
    {
        final EarningDataList menuItemListData = mDataList.get(position);


          

            holder.tvPreBalance.setText(menuItemListData.getPre());
            holder.tvAvail.setText(menuItemListData.getAvail());
            holder.tvCredit.setText(" + "+menuItemListData.getCredit());
            holder.tvDateTime.setText(menuItemListData.getDateTime());

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
        @BindView(R.id.tvPreBalance)
        TextView tvPreBalance;
        @BindView(R.id.tvCredit)
        TextView tvCredit;
        @BindView(R.id.tvAvail)
        TextView tvAvail;
        @BindView(R.id.tvDateTime)
        TextView tvDateTime;

        




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
