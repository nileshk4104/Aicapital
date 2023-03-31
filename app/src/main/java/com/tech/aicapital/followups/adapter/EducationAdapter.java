package com.tech.aicapital.followups.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.tech.aicapital.Adapter.VillageAdapter;
import com.tech.aicapital.R;
import com.tech.aicapital.followups.datalist.EducationDataList;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EducationAdapter extends RecyclerView.Adapter<EducationAdapter.MyViewHolder> {
    Context context;
    private List<EducationDataList> mDataList;
    private EducationAdapter.OnItemClickListener listener;
    private EducationAdapter.OnDoubleClickListener listener2;
   
    public EducationAdapter(Context context, List<EducationDataList> resourceList,
                            EducationAdapter.OnItemClickListener listener,
                            EducationAdapter.OnDoubleClickListener listener2) {
        this.context = context;
        this.mDataList = resourceList;
        this.listener = listener;
        this.listener2 = listener2;
    }
    @Override
    public EducationAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_city, parent, false);
        return new EducationAdapter.MyViewHolder(itemView);
    }
    @Override
    public void onBindViewHolder(final EducationAdapter.MyViewHolder holder, final int position) {
        final EducationDataList bean = mDataList.get(position);

        holder.tvCityname.setText(bean.getEducation());


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
        @BindView(R.id.tvCityname)
        TextView tvCityname;



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
