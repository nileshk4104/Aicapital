package com.tech.aicapital.followups;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.tech.aicapital.R;
import com.tech.aicapital.mvps.Utility;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TradeDetailsActivity extends AppCompatActivity {
    String name,profit,open_p,close_p,start_t,end_t,type_id,lot;

    @BindView(R.id.tvEntity)
    TextView tvEntity;
    @BindView(R.id.tvLotSize)
    TextView tvLotSize;
    @BindView(R.id.tvOpenTrade)
    TextView tvOpenTrade;
    @BindView(R.id.tvCloseTrade)
    TextView tvCloseTrade;

    @BindView(R.id.tvStartTime)
    TextView tvStartTime;
    @BindView(R.id.tvEndTime)
    TextView tvEndTime;

    @BindView(R.id.tvTradeGain)
    TextView tvTradeGain;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.trade_details_activity);
        ButterKnife.bind(this);
        Utility.pageSetupBackButton(TradeDetailsActivity.this,"");

        Intent intent=getIntent();
        name=intent.getStringExtra("NAME");
        profit=intent.getStringExtra("PROFIT");
        open_p=intent.getStringExtra("OPEN_P");
        close_p=intent.getStringExtra("CLOSE_P");
        start_t=intent.getStringExtra("START_T");
        end_t=intent.getStringExtra("END_T");
        type_id=intent.getStringExtra("TYPE");
        lot=intent.getStringExtra("LOT");

        if(type_id.equalsIgnoreCase("0"))
        {
            tvLotSize.setText("sell "+lot);
            tvLotSize.setTextColor(Color.parseColor("#FF0000"));
        }
        else{
            tvLotSize.setText("buy "+lot);
            tvLotSize.setTextColor(Color.parseColor("#0088ff"));
        }

        tvEntity.setText(name);
        tvTradeGain.setText(profit);
        tvOpenTrade.setText(open_p);
        tvCloseTrade.setText(close_p);
        tvStartTime.setText(start_t);
        tvEndTime.setText(end_t);



    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }
}
