package com.zhongmian.mall.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.zhongmian.mall.R;
import com.zhongmian.mall.activity.DepartmentBusinessActivity;
import com.zhongmian.mall.utils.BitmapCache;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by L on 2016/3/2 0002.
 */
public class DepartmentGridViewAdapter extends BaseAdapter {
    private Context context;
    private List<Map<String,String>> maps;
    private RequestQueue requestQueue;
    private ImageLoader imageLoader;
    private int count;

    public DepartmentGridViewAdapter(Context context){
        this.context =context;
        requestQueue = Volley.newRequestQueue(context);
        imageLoader = new ImageLoader(requestQueue, BitmapCache.getBitmapCache());
        maps = new ArrayList<Map<String, String>>();
    }
    public void  setData(List<Map<String,String>> maps){
        count = 0;
        this.maps = maps;
        for(Map<String,String>map:maps){

            if("".equals(map.get("icon"))){
                count++;
            }
        }
        System.out.println("count"+count);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return maps.size()-count;
    }

    @Override
    public Object getItem(int position) {
        return maps.get(position+count);

    }
    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder =null;
        if (convertView==null){
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.departement_item2,parent,false);
            viewHolder.imageView = (ImageView) convertView.findViewById(R.id.imageView_department_item2);
            viewHolder.textView = (TextView) convertView.findViewById(R.id.textView_department_item2);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        ImageLoader.ImageListener imageListener=ImageLoader.getImageListener(viewHolder.imageView,R.mipmap.ic_launcher,R.mipmap.ic_launcher);
        imageLoader.get(maps.get(position+count).get("icon"),imageListener);
        viewHolder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DepartmentBusinessActivity.class);
                intent.putExtra("id",maps.get(position+count).get("id"));
                context.startActivity(intent);
            }
        });
        viewHolder.textView.setText(maps.get(position+count).get("name"));
        return convertView;
    }
    private class ViewHolder{
        private ImageView imageView;
        private TextView textView;
    }
}
