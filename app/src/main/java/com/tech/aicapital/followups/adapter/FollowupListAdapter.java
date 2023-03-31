package com.tech.aicapital.followups.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.tech.aicapital.R;
import com.tech.aicapital.followups.datalist.FollowupDataList;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FollowupListAdapter extends
        RecyclerView.Adapter<FollowupListAdapter.MyViewHolder> {
    Context context;
    private List<FollowupDataList> mDataList;
    private FollowupListAdapter.OnItemClickListener listener;
    private FollowupListAdapter.OnDoubleClickListener listener2;
    int lastPosition = -1;
    boolean isAdmin;
    int mainDuration,mainAmounnt=1;
    double intrestrate;
    public FollowupListAdapter(Context context, boolean isAdmin, List<FollowupDataList> resourceList,
                          FollowupListAdapter.OnItemClickListener listener,
                          FollowupListAdapter.OnDoubleClickListener listener2) {
        this.context = context;
        this.mDataList = resourceList;
        this.listener = listener;
        this.isAdmin = isAdmin;
        this.listener2 = listener2;
    }
    @Override
    public FollowupListAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_followup_list, parent, false);
        return new FollowupListAdapter.MyViewHolder(itemView);
    }
    @Override
    public void onBindViewHolder(final FollowupListAdapter.MyViewHolder holder, final int position)
    {

        final FollowupDataList bean = mDataList.get(position);
        holder.tvFollowup.setText(bean.getFollowUp());
        holder.tvRemark.setText(bean.getRemark());
        holder.tvFollowupDate.setText(bean.getFollowDate());
        holder.tvReminder.setText(bean.getRemindDate());

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
        @BindView(R.id.tvFollowup)
        TextView tvFollowup;
        @BindView(R.id.tvRemark)
        TextView tvRemark;
        @BindView(R.id.tvFollowupDate)
        TextView tvFollowupDate;
        @BindView(R.id.tvReminder)
        TextView tvReminder;



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
