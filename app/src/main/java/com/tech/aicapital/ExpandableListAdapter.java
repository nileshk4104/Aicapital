package com.tech.aicapital;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.tech.aicapital.datalist.GeneoogyUserlist;

import java.util.HashMap;
import java.util.List;

public class ExpandableListAdapter extends BaseExpandableListAdapter {

    private Context _context;
    private List<String> _listDataHeader; // header titles
    // child data in format of header title, child title
    private HashMap<String, List<GeneoogyUserlist>> _listDataChild;
    private OnItemClickListener listener;
    int planamt, half;


    public ExpandableListAdapter(Context context, List<String> listDataHeader,
                                 HashMap<String, List<GeneoogyUserlist>>
            listChildData, int planamt, OnItemClickListener listener) {
        this._context = context;
        this._listDataHeader = listDataHeader;
        this._listDataChild = listChildData;
        this.listener = listener;
        this.planamt = planamt;
    }

    @Override
    public Object getChild(int groupPosition, int childPosititon) {
        return this._listDataChild.get(this._listDataHeader.get(groupPosition)).get(childPosititon).getUserName();
    }



    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(final int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {

        final String childText = (String) getChild(groupPosition, childPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.list_item, null);
        }
        String headerTitle = (String) getGroup(groupPosition);
        TextView txtListChild = (TextView) convertView.findViewById(R.id.lblListItem);


        TextView lblListItemIncome = (TextView) convertView.findViewById(R.id.lblListItemIncome);
        LinearLayout linear = (LinearLayout) convertView.findViewById(R.id.linear);
        TextView tvCreditedDate = (TextView) convertView.findViewById(R.id.tvCreditedDate);
        TextView tvInstallation = (TextView) convertView.findViewById(R.id.tvInstallation);
        TextView tvActivation = (TextView) convertView.findViewById(R.id.tvActivation);
        tvCreditedDate.setText(_listDataChild.get(this._listDataHeader.get(groupPosition)).get(childPosition).getCreated_date());

        if(groupPosition==0)
        {
            planamt=600;
            lblListItemIncome.setVisibility(View.VISIBLE);
            tvActivation.setText("25 % ");
            tvInstallation.setText("Rs 10");

        }
        else if(groupPosition==1)
        {

            planamt=200;
            tvActivation.setText("5 % ");
            tvInstallation.setText("Rs 02");
        }
        else if(groupPosition==2)
        {

            planamt=400 ;
            tvActivation.setText("5 % ");
            tvInstallation.setText("Rs 02");
        }else if(groupPosition==3)
        {

            planamt=500;
            tvActivation.setText("5 % ");
            tvInstallation.setText("Rs 02");
        }else if(groupPosition==4)
        {

            planamt=500;
            tvActivation.setText("5 % ");
            tvInstallation.setText("Rs 01");
        }
        else if(groupPosition==5){

            planamt=500;
            tvActivation.setText("5 % ");
            tvInstallation.setText("Rs 01");
        }else if(groupPosition==6){

            planamt=500;
            tvActivation.setText("5 % ");
            tvInstallation.setText("Rs 01");
        }else if(groupPosition==7){

            planamt=500;
            tvActivation.setText("5 % ");
            tvInstallation.setText("Rs 01");
        }else if(groupPosition==8){

            planamt=500;
            tvActivation.setText("5 % ");
            tvInstallation.setText("Rs 01");
        }else if(groupPosition==9){

            planamt=500;
            tvActivation.setText("5 % ");
            tvInstallation.setText("Rs 01");
        }

        if(_listDataChild.get(this._listDataHeader.get(groupPosition)).get(childPosition).getPStatus().equalsIgnoreCase("2"))
        {
            tvActivation.setTextColor(Color.parseColor("#00AA00"));
            txtListChild.setTextColor(Color.parseColor("#00AA00"));
            tvInstallation.setTextColor(Color.parseColor("#00AA00"));
            txtListChild.setText(_listDataChild.get(this._listDataHeader.get(groupPosition)).get(childPosition).getUserId()+" : "+childText+"");
        }
        else
        {
            txtListChild.setTextColor(Color.parseColor("#FF0000"));
            tvActivation.setTextColor(Color.parseColor("#FF0000"));
            tvInstallation.setTextColor(Color.parseColor("#FF0000"));
            txtListChild.setText(_listDataChild.get(this._listDataHeader.get(groupPosition)).get(childPosition).getUserId()+" : "+childText+"");
        }
        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this._listDataChild.get(this._listDataHeader.get(groupPosition))
                .size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this._listDataHeader.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return this._listDataHeader.size();
    }

    public interface OnItemClickListener {
        void onListItemClick(int groupposition, int cpos);
    }


    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent)
    {

        String headerTitle = (String) getGroup(groupPosition);
        if (convertView == null)
        {
            LayoutInflater infalInflater = (LayoutInflater) this._context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.list_group, null);
        }
        TextView lblListHeader = (TextView) convertView.findViewById(R.id.lblListHeader);
        TextView tvSmartEcommerce = (TextView) convertView.findViewById(R.id.tvSmartEcommerce);
        TextView tvTokens = (TextView) convertView.findViewById(R.id.tvTokens);
        TextView tvActivation = (TextView) convertView.findViewById(R.id.tvActivation);

        lblListHeader.setTypeface(null, Typeface.BOLD);
        lblListHeader.setText(headerTitle);
       if(groupPosition==0){
           tvTokens.setText("Rs 10");
           tvActivation.setText("25 % ");
           tvSmartEcommerce.setText("2 % PER MONTH");
       }
       else if(groupPosition==1){
           tvTokens.setText("Rs 02");
           tvActivation.setText("5 % ");
           tvSmartEcommerce.setText("1 % PER MONTH");
       } else if(groupPosition==2){
           tvTokens.setText("Rs 02");
           tvActivation.setText("5 % ");
           tvSmartEcommerce.setText("0.5 % PER MONTH");
       } else if(groupPosition==3){
           tvTokens.setText("Rs 02");
           tvActivation.setText("5 % ");
           tvSmartEcommerce.setText("0.5 % PER MONTH");
       } else if(groupPosition==4){
           tvTokens.setText("Rs 01");
           tvActivation.setText("5 % ");
           tvSmartEcommerce.setText("0.5 % PER MONTH");
       } else if(groupPosition==5){
           tvTokens.setText("Rs 01");
           tvActivation.setText("5 % ");
           tvSmartEcommerce.setText("0.5 % PER MONTH");
       } else if(groupPosition==6){
           tvTokens.setText("Rs 01");
           tvActivation.setText("5 % ");
           tvSmartEcommerce.setText("0.5 % PER MONTH");
       } else{
           tvTokens.setText("Rs 01");
           tvActivation.setText("5 % ");
           tvSmartEcommerce.setText("0.5 % PER MONTH");
       }


        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}