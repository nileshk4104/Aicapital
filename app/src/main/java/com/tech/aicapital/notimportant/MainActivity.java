
package com.tech.aicapital.notimportant;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.utils.Utils;
import com.tech.aicapital.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements OnItemClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        setTitle("MPAndroidChart Example");

        // initialize the utilities
        Utils.init(this);

        ArrayList<ContentItem> objects = new ArrayList<>();

        ////
        objects.add(0, new ContentItem("Line Charts"));

        objects.add(1, new ContentItem("Basic", "Simple line chart."));
        objects.add(2, new ContentItem("Multiple", "Show multiple data sets."));
        objects.add(3, new ContentItem("Dual Axis", "Line chart with dual y-axes."));
        objects.add(4, new ContentItem("Inverted Axis", "Inverted y-axis."));
        objects.add(5, new ContentItem("Cubic", "Line chart with a cubic line shape."));
        objects.add(6, new ContentItem("Colorful", "Colorful line chart."));
        objects.add(7, new ContentItem("Performance", "Render 30.000 data points smoothly."));
        objects.add(8, new ContentItem("Filled", "Colored area between two lines."));

        ////
        objects.add(9, new ContentItem("Bar Charts"));

        objects.add(10, new ContentItem("Basic", "Simple bar chart."));
        objects.add(11, new ContentItem("Basic 2", "Variation of the simple bar chart."));
        objects.add(12, new ContentItem("Multiple", "Show multiple data sets."));
        objects.add(13, new ContentItem("Horizontal", "Render bar chart horizontally."));
        objects.add(14, new ContentItem("Stacked", "Stacked bar chart."));
        objects.add(15, new ContentItem("Negative", "Positive and negative values with unique colors."));
        objects.add(16, new ContentItem("Negative Horizontal", "demonstrates how to create a HorizontalBarChart with positive and negative values."));
        objects.add(17, new ContentItem("Stacked 2", "Stacked bar chart with negative values."));
        objects.add(18, new ContentItem("Sine", "Sine function in bar chart format."));

        ////
        objects.add(19, new ContentItem("Pie Charts"));

        objects.add(20, new ContentItem("Basic", "Simple pie chart."));
        objects.add(21, new ContentItem("Value Lines", "Stylish lines drawn outward from slices."));
        objects.add(22, new ContentItem("Half Pie", "180° (half) pie chart."));

        ////
        objects.add(23, new ContentItem("Other Charts"));

        objects.add(24, new ContentItem("Combined Chart", "Bar and line chart together."));
        objects.add(25, new ContentItem("Scatter Plot", "Simple scatter plot."));
        objects.add(26, new ContentItem("Bubble Chart", "Simple bubble chart."));
        objects.add(27, new ContentItem("Candlestick", "Simple financial chart."));
        objects.add(28, new ContentItem("Radar Chart", "Simple web chart."));

        ////
        objects.add(29, new ContentItem("Scrolling Charts"));

        objects.add(30, new ContentItem("Multiple", "Various types of charts as fragments."));
        objects.add(31, new ContentItem("View Pager", "Swipe through different charts."));
        objects.add(32, new ContentItem("Tall Bar Chart", "Bars bigger than your screen!"));
        objects.add(33, new ContentItem("Many Bar Charts", "More bars than your screen can handle!"));

        ////
        objects.add(34, new ContentItem("Even More Line Charts"));

        objects.add(35, new ContentItem("Dynamic", "Build a line chart by adding points and sets."));
        objects.add(36, new ContentItem("Realtime", "Add data points in realtime."));
        objects.add(37, new ContentItem("Hourly", "Uses the current time to add a data point for each hour."));
        //objects.add(38, new ContentItem("Realm.io Examples", "See more examples that use Realm.io mobile database."));

        MyAdapter adapter = new MyAdapter(this, objects);

//        ListView lv = findViewById(R.id.listView1);
//        lv.setAdapter(adapter);
//
//        lv.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> av, View v, int pos, long arg3) {

        Intent i = null;



        if (i != null) startActivity(i);

        overridePendingTransition(R.anim.move_right_in_activity, R.anim.move_left_out_activity);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        Intent i;

        switch (item.getItemId()) {
//            case R.id.viewGithub:
//                i = new Intent(Intent.ACTION_VIEW);
//                i.setData(Uri.parse("https://github.com/PhilJay/MPAndroidChart"));
//                startActivity(i);
//                break;
//            case R.id.report:
//                i = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
//                        "mailto", "philjay.librarysup@gmail.com", null));
//                i.putExtra(Intent.EXTRA_SUBJECT, "MPAndroidChart Issue");
//                i.putExtra(Intent.EXTRA_TEXT, "Your error report here...");
//                startActivity(Intent.createChooser(i, "Report Problem"));
//                break;
//            case R.id.website:
//                i = new Intent(Intent.ACTION_VIEW);
//                i.setData(Uri.parse("http://at.linkedin.com/in/philippjahoda"));
//                startActivity(i);
//                break;
        }

        return true;
    }
}
