package com.tech.aicapital.Adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;


import com.tech.aicapital.R;
import com.tech.aicapital.datalist.SubmenuDetailList;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DynamicMenuAdapter extends
        RecyclerView.Adapter<DynamicMenuAdapter.MyViewHolder> {
    Context context;
    private List<SubmenuDetailList> mDataList;
    private OnItemClickListener listener;
    private OnDoubleClickListener listener2;
    int lastPosition = -1;
    String baseurl;
    int mainDuration,mainAmounnt=1;
    boolean isImage;
    public DynamicMenuAdapter(Context context, String baseurl, List<SubmenuDetailList> resourceList, boolean isImage,
                              OnItemClickListener listener,
                              OnDoubleClickListener listener2)
    {
        this.context = context;
        this.mDataList = resourceList;
        this.listener = listener;
        this.baseurl = baseurl;this.isImage = isImage;
        this.listener2 = listener2;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_submenu, parent, false);
        return new MyViewHolder(itemView);
    }
    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position)
    {
        final SubmenuDetailList menuItemListData = mDataList.get(position);



        String submenu=menuItemListData.getSubMenuId();
        Drawable im=null;
        if ((submenu.equalsIgnoreCase("1"))){
            im=context.getResources().getDrawable(R.drawable.ic_account_circle_black_24dp);
        }else if ((submenu.equalsIgnoreCase("2"))){
            im=context.getResources().getDrawable(R.drawable.ic_baseline_perm_identity_24);
        }else if ((submenu.equalsIgnoreCase("3"))){
            im=context.getResources().getDrawable(R.drawable.ic_baseline_account_balance_24);
        }else if ((submenu.equalsIgnoreCase("5"))){
            im=context.getResources().getDrawable(R.drawable.ic_baseline_manage_accounts_24);
        }else if ((submenu.equalsIgnoreCase("6"))){
            im=context.getResources().getDrawable(R.drawable.ic_baseline_switch_account_24);
        }
        else if ((submenu.equalsIgnoreCase("7"))){
            im=context.getResources().getDrawable(R.drawable.ic_baseline_monetization_on_24);
        }else if ((submenu.equalsIgnoreCase("8"))){
            im=context.getResources().getDrawable(R.drawable.ic_baseline_transform_24);
        }else if ((submenu.equalsIgnoreCase("14"))){
            im=context.getResources().getDrawable(R.drawable.ic_baseline_receipt_long_24);
        }else if ((submenu.equalsIgnoreCase("26"))){
            im=context.getResources().getDrawable(R.drawable.ic_baseline_playlist_add_check_circle_24);
        }

        else if ((submenu.equalsIgnoreCase("13"))){
            im=context.getResources().getDrawable(R.drawable.ic_baseline_logout_24);
        }
        holder.tvLevelName.setCompoundDrawablesWithIntrinsicBounds( im, null, null, null);
        holder.tvLevelName.setText(menuItemListData.getSubMenuName());


    }
    @Override
    public int getItemCount() {
        return mDataList.size();
    }
    public interface OnItemClickListener {
        void onListItemClick(int position);
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
        @BindView(R.id.submenu)
        TextView tvLevelName;




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
