package com.zhongmian.mall.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.zhongmian.mall.R;
import com.zhongmian.mall.utils.BitmapCache;
import com.zhongmian.mall.utils.LogUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by L on 2016/2/28 0028.
 */
public class HomeAdapter extends BaseAdapter {
    private RequestQueue requestQueue;
    private ImageLoader imageLoader;
    private Context context;
    private List<Map<String, String>> content_maps;


    public HomeAdapter(Context context) {

        this.context = context;
        content_maps = new ArrayList<Map<String, String>>();
        requestQueue = Volley.newRequestQueue(context);
        imageLoader = new ImageLoader(requestQueue, BitmapCache.getBitmapCache());

    }

    public void setList(List<Map<String, String>> content_maps) {

        this.content_maps = content_maps;
        notifyDataSetChanged();
    }


    @Override
    public int getViewTypeCount() {
        return 3;
    }

    @Override
    public int getItemViewType(int position) {

        String type = content_maps.get(position).get("type");
        int t = 0;


        switch (type) {
            case 1 + "":
                t = 0;
                break;
            case 2 + "":
                t = 1;
                break;
            case 6 + "":
                t = 2;
                break;
            default:
                break;
        }
        return t;
    }

    @Override
    public int getCount() {
        int size = content_maps.size();
        return size;
    }

    @Override
    public Object getItem(int position) {

        return content_maps.get(position);


    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        String type = content_maps.get(position).get("type");
        ViewHolder1 viewHolder1 = null;
        ViewHolder2 viewHolder2 = null;
        ViewHolder3 viewHolder3 = null;
        if (convertView == null) {
            switch (type) {
                case 1 + "":
                    convertView = LayoutInflater.from(context).inflate(R.layout.home_item_layout1, parent, false);
                    viewHolder1 = new ViewHolder1();
                    viewHolder1.pic = (ImageView) convertView.findViewById(R.id.Image_home_item1_01);
                    convertView.setTag(viewHolder1);
                    break;
                case 2 + "":
                    convertView = LayoutInflater.from(context).inflate(R.layout.home_item_layout2, parent, false);
                    viewHolder2 = new ViewHolder2();
                    viewHolder2.pic01 = (ImageView) convertView.findViewById(R.id.Image_home_item2_01);

                    viewHolder2.pic02 = (ImageView) convertView.findViewById(R.id.Image_home_item2_02);
                    convertView.setTag(viewHolder2);
                    break;
                case 6 + "":
                    convertView = LayoutInflater.from(context).inflate(R.layout.home_item_layout3, parent, false);
                    viewHolder3 = new ViewHolder3();
                    viewHolder3.pic01 = (ImageView) convertView.findViewById(R.id.Image_home_item3_01);
                    viewHolder3.pic02 = (ImageView) convertView.findViewById(R.id.Image_home_item3_02);
                    viewHolder3.price01 = (TextView) convertView.findViewById(R.id.textView_home_item_3_price01);
                    viewHolder3.price02 = (TextView) convertView.findViewById(R.id.textView_home_item_3_price02);
                    viewHolder3.prodname01 = (TextView) convertView.findViewById(R.id.textView_home_item_3_prodname01);
                    viewHolder3.prodname02 = (TextView) convertView.findViewById(R.id.textView_home_item_3_prodname02);
                    convertView.setTag(viewHolder3);
                    break;
                default:
                    break;
            }
        } else {
            switch (type) {
                case 1 + "":
                    viewHolder1 = (ViewHolder1) convertView.getTag();
                    break;
                case 2 + "":
                    viewHolder2 = (ViewHolder2) convertView.getTag();
                    break;
                case 6 + "":
                    viewHolder3 = (ViewHolder3) convertView.getTag();
                    break;
                default:
                    break;
            }
        }
        //设置数据
        switch (type) {
            case 1 + "":
                ImageLoader.ImageListener imageListener1 = ImageLoader.getImageListener(viewHolder1.pic, R.mipmap.ic_launcher, R.mipmap.ic_launcher);
                imageLoader.get(content_maps.get(position).get("pic"), imageListener1);
                viewHolder1.pic.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(context, "点击图片", Toast.LENGTH_SHORT).show();
                    }
                });

                break;
            case 2 + "":
                ImageLoader.ImageListener imageListener2 = ImageLoader.getImageListener(viewHolder2.pic01, R.mipmap.ic_launcher, R.mipmap.ic_launcher);
                ImageLoader.ImageListener imageListener3 = ImageLoader.getImageListener(viewHolder2.pic02, R.mipmap.ic_launcher, R.mipmap.ic_launcher);
                imageLoader.get(content_maps.get(position).get("pic"), imageListener2);
                imageLoader.get(content_maps.get(position).get("pic1"), imageListener3);
                viewHolder2.pic01.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(context, "点击Left图片", Toast.LENGTH_SHORT).show();
                    }
                });
                viewHolder2.pic02.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(context, "点击Right图片", Toast.LENGTH_SHORT).show();
                    }
                });
                break;
            case 6 + "":
                ImageLoader.ImageListener imageListener4 = ImageLoader.getImageListener(viewHolder3.pic01, R.mipmap.loading, R.mipmap.ic_launcher);
                ImageLoader.ImageListener imageListener5 = ImageLoader.getImageListener(viewHolder3.pic02, R.mipmap.loading, R.mipmap.ic_launcher);
                imageLoader.get(content_maps.get(position).get("pic"), imageListener4, 540, 540);
                imageLoader.get(content_maps.get(position).get("pic1"), imageListener5,540,540);
                viewHolder3.prodname01.setText(content_maps.get(position).get("prodname"));
                viewHolder3.prodname02.setText(content_maps.get(position).get("prodname1"));
                viewHolder3.price01.setText(content_maps.get(position).get("price"));
                viewHolder3.price02.setText(content_maps.get(position).get("price1"));
                viewHolder3.pic01.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(context, "点击Left图片", Toast.LENGTH_SHORT).show();
                    }
                });
                viewHolder3.pic02.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(context, "点击Right图片", Toast.LENGTH_SHORT).show();
                    }
                });
                break;
            default:
                break;
        }

        return convertView;
    }

    class ViewHolder1 {
        private ImageView pic;
    }

    class ViewHolder2 {
        private ImageView pic01;
        private ImageView pic02;
    }

    class ViewHolder3 {
        private ImageView pic01;
        private TextView prodname01;
        private TextView price01;
        private ImageView pic02;
        private TextView prodname02;
        private TextView price02;
    }
}
