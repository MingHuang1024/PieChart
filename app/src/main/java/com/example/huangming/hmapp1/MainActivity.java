package com.example.huangming.hmapp1;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.os.StatFs;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    int i=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        LinearLayout pieLayout= (LinearLayout) findViewById(R.id.pieLayout);
        List<PieChart.PieElement> list= new ArrayList<>();
        PieChart.PieElement e1=new PieChart.PieElement();
        e1.isHighlight=true;
        e1.scale=0.2f;
        e1.color= Color.parseColor("#0000ff");

        PieChart.PieElement e2=new PieChart.PieElement();
        e2.isHighlight=false;
        e2.scale=0.3f;
        e2.color= Color.parseColor("#8f0080");

        PieChart.PieElement e3=new PieChart.PieElement();
        e3.isHighlight=false;
        e3.scale=0.4f;
        e3.color= Color.parseColor("#23aa90");

        PieChart.PieElement e4=new PieChart.PieElement();
        e4.isHighlight=false;
        e4.scale=0.1f;
        e4.color= Color.parseColor("#aa0000");
        list.add(e1);
        list.add(e2);
        list.add(e3);
        list.add(e4);
        final PieChart pc=new PieChart(this,list);
//        final PieChart pc=new PieChart(this,list,150);
        pieLayout.addView(pc);

        findViewById(R.id.btnRefresh).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (i % 2 == 0) {
                    pc.getItem(0).color = Color.parseColor("#00ff00");
                } else {
                    pc.getItem(0).color = Color.parseColor("#0000ff");
                }
                i++;
                pc.refresh();
            }
        });

        long kySize=getAvailableInternalMemorySize();
        System.out.println("可用空间=" + kySize);

        long zSize=getTotalInternalMemorySize();
        System.out.println("总空间="+zSize);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * 获取手机内部剩余存储空间
     * @return
     */
    public static long getAvailableInternalMemorySize() {
        File path = Environment.getDataDirectory();
        StatFs stat = new StatFs(path.getPath());
        long blockSize = stat.getBlockSize();
        long availableBlocks = stat.getAvailableBlocks();
        return availableBlocks * blockSize;
    }

    /**
     * 获取手机内部总的存储空间
     * @return
     */
    public static long getTotalInternalMemorySize() {
        File path = Environment.getDataDirectory();
        StatFs stat = new StatFs(path.getPath());
        long blockSize = stat.getBlockSize();
        long totalBlocks = stat.getBlockCount();
        return totalBlocks * blockSize;
    }

}
