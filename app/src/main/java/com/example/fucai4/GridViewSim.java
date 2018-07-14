package com.example.fucai4;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 *
 * Created by 42224 on 2018/4/20.
 */
public class GridViewSim extends BaseAdapter {

    private ArrayList<String> data = new ArrayList<>();

    private Context context;
    int i;

    //构造方 法
    public GridViewSim(ArrayList<String> data, Context context, int i) {
        this.data = data;
        this.context = context;
        this.i = i;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        TextView iv = new TextView(context);
        iv.setTextColor(ContextCompat.getColor(context, R.color.white));
        iv.setTextSize(16);
        iv.setGravity(Gravity.CENTER);
        if (i == 1) {
            iv.setText("XXX");
        } else {
            String t= data.get(position);
            t=t.replace(" ","");
            t=t.replace("（来自组选王","");
            t=t.replace("微信:aawwss1788)","");
            iv.setText(t);
            Log.e("(data.get(position)",(data.get(position)));
        }

        return iv;
    }
}