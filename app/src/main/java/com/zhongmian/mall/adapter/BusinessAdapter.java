package com.zhongmian.mall.adapter;

import android.content.Context;
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
import com.zhongmian.mall.utils.BitmapCache;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by L on 2016/3/3 0003.
 */
public class BusinessAdapter extends BaseAdapter {
    private Context context;
    private List<Map<String, String>> maps;
    private RequestQueue requestQueue;
    private ImageLoader imageLoader;

    public BusinessAdapter(Context context) {
        this.context = context;
        maps = new ArrayList<Map<String, String>>();
        requestQueue = Volley.newRequestQueue(context);
        imageLoader = new ImageLoader(requestQueue, BitmapCache.getBitmapCache());
    }

    public void setData(List<Map<String, String>> maps) {
        this.maps = maps;
        notifyDataSetChanged();
    }
    public void addData(List<Map<String, String>> maps){
        this.maps.addAll(maps);
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
            convertView = LayoutInflater.from(context).inflate(R.layout.home_business_item, parent, false);
            viewHolder.imageView = (ImageView) convertView.findViewById(R.id.imageView_home_business);
            viewHolder.textViewName = (TextView) convertView.findViewById(R.id.textView_home_business_name);
            viewHolder.textViewPrice = (TextView) convertView.findViewById(R.id.textView_home_business_price);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        ImageLoader.ImageListener imageListener = ImageLoader.getImageListener(viewHolder.imageView, R.mipmap.ic_launcher, R.mipmap.ic_launcher);
        imageLoader.get(maps.get(position).get("pic"), imageListener, 540, 540);
        viewHolder.textViewName.setText(maps.get(position).get("prodname"));
        viewHolder.textViewPrice.setText("￥" + maps.get(position).get("price"));
        return convertView;
    }

    public class ViewHolder {
        public ImageView imageView;
        public TextView textViewName;
        public TextView textViewPrice;
    }

}
