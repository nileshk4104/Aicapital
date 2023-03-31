package com.tech.aicapital;

import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.tech.aicapital.datalist.SubmenuDetailList;

import java.util.HashMap;
import java.util.List;

public class ExpandableListAdapterMenu extends BaseExpandableListAdapter {

    private Context _context;
    private List<String> _listDataHeader; // header titles
    // child data in format of
    // header title, child title
    private HashMap<String, List<SubmenuDetailList>> _listDataChild;
    private ExpandableListAdapter.OnItemClickListener listener;
    int planamt, half;


    public ExpandableListAdapterMenu(Context context, List<String> listDataHeader,
                                     HashMap<String, List<SubmenuDetailList>>
                                             listChildData, int planamt, int[] myImageList, ExpandableListAdapter.OnItemClickListener listener) {
        this._context = context;
        this._listDataHeader = listDataHeader;
        this._listDataChild = listChildData;
        this.listener = listener;
        this.planamt = planamt;
    }


    @Override
    public Object getChild(int groupPosition, int childPosititon) {
        return this._listDataChild.get(this._listDataHeader.get(groupPosition)).
                get(childPosititon).getSubMenuName();
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
            convertView = infalInflater.inflate(R.layout.list_submenu, null);
        }
        String headerTitle = (String) getGroup(groupPosition);

        TextView txtListChild = (TextView) convertView.findViewById(R.id.submenu);

        String submenu=_listDataChild.get(_listDataHeader.get(groupPosition)).get(childPosition).getSubMenuId();
        Drawable im=null;
        if ((submenu.equalsIgnoreCase("1"))){
             im=_context.getResources().getDrawable(R.drawable.ic_account_circle_black_24dp);
        }else if ((submenu.equalsIgnoreCase("2"))){
             im=_context.getResources().getDrawable(R.drawable.ic_baseline_perm_identity_24);
        }else if ((submenu.equalsIgnoreCase("3"))){
             im=_context.getResources().getDrawable(R.drawable.ic_baseline_account_balance_24);
        }else if ((submenu.equalsIgnoreCase("5"))){
             im=_context.getResources().getDrawable(R.drawable.ic_baseline_manage_accounts_24);
        }else if ((submenu.equalsIgnoreCase("6"))){
             im=_context.getResources().getDrawable(R.drawable.ic_baseline_switch_account_24);
        }
        else if ((submenu.equalsIgnoreCase("7"))){
             im=_context.getResources().getDrawable(R.drawable.ic_baseline_monetization_on_24);
        }else if ((submenu.equalsIgnoreCase("8"))){
             im=_context.getResources().getDrawable(R.drawable.ic_baseline_transform_24);
        }else if ((submenu.equalsIgnoreCase("14"))){
             im=_context.getResources().getDrawable(R.drawable.ic_baseline_receipt_long_24);
        }else if ((submenu.equalsIgnoreCase("26"))){
             im=_context.getResources().getDrawable(R.drawable.ic_baseline_playlist_add_check_circle_24);
        }

        else if ((submenu.equalsIgnoreCase("13"))){
             im=_context.getResources().getDrawable(R.drawable.ic_baseline_logout_24);
        }

        txtListChild.setCompoundDrawablesWithIntrinsicBounds( im, null, null, null);
        txtListChild.setText(childText);


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
            LayoutInflater infalInflater = (LayoutInflater) this._context.getSystemService(
                    Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.list_group2, null);
        }
        TextView lblListHeader = (TextView) convertView.findViewById(R.id.lblListHeader);
        lblListHeader.setTypeface(null, Typeface.BOLD);
        lblListHeader.setLayoutParams(new LinearLayout.LayoutParams
                (LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 0));
        lblListHeader.setText(headerTitle);
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