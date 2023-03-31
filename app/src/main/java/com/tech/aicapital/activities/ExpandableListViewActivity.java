package com.tech.aicapital.activities;

import static com.tech.aicapital.mvps.Constant.USER_NAME;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.tech.aicapital.Pref;
import com.tech.aicapital.R;
import com.tech.aicapital.datalist.GeneologydataList;
import com.tech.aicapital.datalist.GeneologydataResponse;
import com.tech.aicapital.datalist.GeneoogyUserlist;
import com.tech.aicapital.mvps.ApiClient;
import com.tech.aicapital.mvps.NetworkCaller;
import com.tech.aicapital.mvps.Utility;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ExpandableListViewActivity extends AppCompatActivity {

    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String,List<GeneoogyUserlist>> listHashMap;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    TextView tvViewHistory;

    boolean isAdmin;
    String user_id;int planamt,cashbackamt;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.expandable_listview);
        ButterKnife.bind(this);
        Intent intent=getIntent();
        planamt=intent.getIntExtra("planamt",0);
        cashbackamt=intent.getIntExtra("planamt",0);
        isAdmin=intent.getBooleanExtra("isAdmin",false);

        if(isAdmin){
            user_id=intent.getStringExtra("user_id");
            planamt=10;
        }
        else{
            sharedPreferences = getSharedPreferences("Reg", 0);
            user_id = sharedPreferences.getString("user_id","");
        }

        tvViewHistory = (TextView) findViewById(R.id.tvViewHistory);
        Utility.pageSetupPrimarySub(ExpandableListViewActivity.this,"MY RELATIVE NETWORK", Pref.getValue(ExpandableListViewActivity.this,USER_NAME,""));
        expListView = (ExpandableListView) findViewById(R.id.lvExp);

        expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener()
        {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id)
            {
                Toast.makeText(getApplicationContext(),listHashMap.get(listDataHeader.get(groupPosition)).get(childPosition).getUserName(),Toast.LENGTH_SHORT).show();
                return false;
            }
        });
        tvViewHistory.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {

            }
        });

        prepareListData();
    }

    @Override
    protected void onResume()
    {
        super.onResume();
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }

    public void prepareListData()
    {
        Utility.showProgressDialog(this);
        final NetworkCaller apiService = ApiClient.getClient8().create(NetworkCaller.class);
        Call<GeneologydataResponse> call = apiService.getMygeneology("10000");
        call.enqueue(new Callback<GeneologydataResponse>() {
            @Override
            public void onResponse(Call<GeneologydataResponse> call, Response<GeneologydataResponse> response)
            {
                GeneologydataResponse hospitalListResponse = response.body();
//                Log.e("Response: ",hospitalListResponse.getData().toString()+" user_id "+user_id);
                Utility.hideProgressBar();
                if (hospitalListResponse != null) {
                    if(hospitalListResponse.isStatus()){
                        prepareListData(hospitalListResponse.getData());
                    }
                    else{
                        Toast.makeText(getApplicationContext(),hospitalListResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                } else
                    Toast.makeText(getApplicationContext(),"Response error ", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<GeneologydataResponse> call, Throwable t) {
                Utility.hideProgressBar();
                if (t instanceof IOException) {
                    //Add your code for displaying no network connection error
                    Toast.makeText(getApplicationContext(),"Network Issue ", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(),"Server Error", Toast.LENGTH_SHORT).show();
                }


            }
        });

    }
    private void prepareListData(List<GeneologydataList> geneologydataLists)
    {
        listHashMap = new HashMap<String, List<GeneoogyUserlist>>();
        listDataHeader = new ArrayList<String>();
                for(int i=0;i<geneologydataLists.size();i++)
                {
                   if(i==0){
                        cashbackamt=1000;
                        listDataHeader.add("Distributor ");
                    }else if(i==1){
                        cashbackamt=750;
                        listDataHeader.add("Executive : "+(i));
                    }else if(i==2){
                        cashbackamt=500;
                        listDataHeader.add("Executive : "+(i));
                    }else if(i==3){
                        cashbackamt=500;
                        listDataHeader.add("Executive : "+(i));
                    }else if(i==4){
                        cashbackamt=500;
                        listDataHeader.add("Executive : "+(i));
                    }else if(i==5){
                        cashbackamt=500;
                        listDataHeader.add("Executive : "+(i));
                    }else if(i==6){
                        cashbackamt=500;
                        listDataHeader.add("Executive : "+(i));
                    }else{
                        cashbackamt=500;
                        listDataHeader.add("Executive : "+(i));
                    }
                        listHashMap.put(listDataHeader.get(i), geneologydataLists.get(i).getUserlist());


                }
//                listAdapter = new ExpandableListAdapter(this, listDataHeader,listHashMap,cashbackamt,
//                        new ExpandableListAdapter.OnItemClickListener()
//                        {
//                            @Override
//                            public void onListItemClick(int groupposition, int cpos) {
//                                Toast.makeText(getApplicationContext(),
//                                        listHashMap.get(groupposition).get(cpos).getUserName(),
//                                        Toast.LENGTH_SHORT).show();
//                            }
//                    });

                expListView.setAdapter(listAdapter);




    }


}