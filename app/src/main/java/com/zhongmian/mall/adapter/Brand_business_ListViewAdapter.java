package com.zhongmian.mall.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.zhongmian.mall.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by L on 2016/3/4 0004.
 */
public class Brand_business_ListViewAdapter extends BaseAdapter {
    private Context context;
    private List<Map<String, Object>> maps;
    public Brand_business_ListViewAdapter(Context context){
        this.context = context;
        maps = new ArrayList<Map<String, Object>>();
    }
    public void setData(List<Map<String, Object>> maps) {
        this.maps = maps;
        notifyDataSetChanged();
    }
    @Override
    public int getCount() {
        return maps.size();
    }

    @Override
    public Object getItem(int position) {
        return maps.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.businesslistviewitem, parent, false);
            viewHolder.textViewName = (TextView) convertView.findViewById(R.id.textView_business_item1);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.textViewName.setText((String)maps.get(position).get("cateName"));
        return convertView;
    }

    public class ViewHolder {
        public TextView textViewName;
    }
}
